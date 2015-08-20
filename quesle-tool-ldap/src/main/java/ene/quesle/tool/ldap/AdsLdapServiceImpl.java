package ene.quesle.tool.ldap;

import java.util.List;

import javax.annotation.Resource;
import javax.naming.Name;

import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.support.LdapNameBuilder;

public class AdsLdapServiceImpl extends LdapSyntaxFilter implements AdsLdapService{
	
	public static final String[] PERSON_OBJECT_CLASS = 
			new String[]{"organizationalPerson", "user" , "top", "person"};

	
	private String baseDN;
	
	@Resource(name="adsLdapTemplate")
	private LdapTemplate adsLdapTemplate;
	
	@Override
	public void createAdsAccount(String name, String... ou){
		Name dn = this.buildDomainName(name, ou);
		DirContextAdapter context = new DirContextAdapter(dn);
		context.setAttributeValues("objectClass", PERSON_OBJECT_CLASS);

		adsLdapTemplate.bind(context);
	}
	
	

	@Override
	public void updateAdsAccount(String name, String... ou) {
		Name dn = this.buildDomainName(name, ou);
		DirContextOperations context = adsLdapTemplate.lookupContext(dn);
		
		context.setAttributeValues("objectclass", PERSON_OBJECT_CLASS);
		
		
		adsLdapTemplate.modifyAttributes(context);
	}

	@Override
	public void deleteAdsAccount(String name, String... ou) {
		Name dn = this.buildDomainName(name, ou);
		adsLdapTemplate.unbind(dn);
	}

	@Override
	public List<OrganizationalUnit> getAdsOrganizationalUnits() {
		
		List<OrganizationalUnit> units = adsLdapTemplate.search(
				this.getBaseDN(), this.organizationalUnitFilter(), UnitContextMapper);
		
		return units;
	}
	
	@Override
	public List<Person> getAdsPersonsByUnits(String... ou){
		
		List<Person> persons = adsLdapTemplate.search(
				this.base(this.baseDN, ou),
				this.personByOrganizationalUnit(), 
				PersonContextMapper);
		
		return persons;
	}
	
	@Override
	public List<OrganizationalUnit> getAdsTopElements(String... ou) {
		String filter = this.topFilter(this.baseDN);

		List<OrganizationalUnit> units = adsLdapTemplate.search(
				this.base(this.baseDN, ou), filter, UnitContextMapper);
		
		return units;
	}
	
	
	private final static ContextMapper<OrganizationalUnit> UnitContextMapper = new AbstractContextMapper<OrganizationalUnit>() {

		@Override
		public OrganizationalUnit doMapFromContext(DirContextOperations context) {
			
			return new OrganizationalUnit(context);
		}
	};
	
	/**
	 * Maps from DirContextAdapter to Person objects. A DN for a person will be
	 * of the form <code>cn=[fullname],ou=[company],c=[country]</code>, so
	 * the values of these attributes must be extracted from the DN. For this,
	 * we use the LdapName along with utility methods in LdapUtils.
	 */
	private final static ContextMapper<Person> PersonContextMapper = 
			new AbstractContextMapper<Person>() {
        @Override
		public Person doMapFromContext(DirContextOperations context) {
        	System.out.println(context);
			return new Person(context);
		}
	};
	
	
	/**
	 * 通过Person的OU和名称，获取Name对象，此对象可用来创建，修改用户信息的主键
	 * @param name Person的名称
	 * @param ou   Person的目录名
	 * @return
	 */
	private Name buildDomainName(String name, String... ou){
		
		if(ou == null || ou.length == 0){
			return null;
		}
		
		LdapNameBuilder builder = LdapNameBuilder.newInstance(this.getBaseDN());
		
		for (int i = 0; i < ou.length; i++) {
			builder.add(ou[i]);
		}
		
		builder.add("cn", name);

		return builder.build();
	}



	public String getBaseDN() {
		return baseDN;
	}
	public void setBaseDN(String baseDN) {
		this.baseDN = baseDN;
	}

}
