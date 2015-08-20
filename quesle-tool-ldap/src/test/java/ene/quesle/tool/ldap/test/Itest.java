package ene.quesle.tool.ldap.test;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.naming.ldap.LdapName;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ene.quesle.tool.ldap.Person;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-ldap.xml" })
public class Itest {

	@Resource
	private LdapTemplate ldapTemplate;
	
	/**
	 * Maps from DirContextAdapter to Person objects. A DN for a person will be
	 * of the form <code>cn=[fullname],ou=[company],c=[country]</code>, so
	 * the values of these attributes must be extracted from the DN. For this,
	 * we use the LdapName along with utility methods in LdapUtils.
	 */
	@SuppressWarnings("unused")
	private final static ContextMapper<Person> PERSON_CONTEXT_MAPPER = new AbstractContextMapper<Person>() {
        @Override
		public Person doMapFromContext(DirContextOperations context) {
			Person person = new Person();

            LdapName dn = LdapUtils.newLdapName(context.getDn());
			person.setCountry(LdapUtils.getStringValue(dn, 0));
			person.setCompany(LdapUtils.getStringValue(dn, 1));
			person.setFullName(context.getStringAttribute("cn"));
			person.setLastName(context.getStringAttribute("sn"));
			person.setDescription(context.getStringAttribute("description"));
			person.setPhone(context.getStringAttribute("telephoneNumber"));

			return person;
		}
	};
	
	
	@Test
	@SuppressWarnings("unused")
	public void test(){
		System.out.println(ldapTemplate);
		/*
try {
			List<String> list = ldapTemplate.list("DC=shujutang,DC=com");
			System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		String filter = "(&(objectclass=person))";
		
		System.out.println(ldapTemplate.getContextSource());
		
		/*ContextSource source = ldapTemplate.getContextSource();
		
		DirContext context = source.getReadOnlyContext();
		
		Hashtable<?, ?> map;
		try {
			map = context.getEnvironment();
			Iterator<?> tor = map.keySet().iterator();
			while (tor.hasNext()) {
				System.out.println(map.get(tor.next()));
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}*/
		
		List<String> ous = ldapTemplate.list("OU=root");
		
		System.out.println(Arrays.toString(ous.toArray()));
		
		/*List<Person> persons = ldapTemplate.search("ou=北京",filter,
                PERSON_CONTEXT_MAPPER);
		System.out.println(persons.size());
		for (int i = 0; i < persons.size(); i++) {
			System.out.println(persons.toString());
		}
		*/
		/*List<Person> persons = ldapTemplate.search(query()
                .where("objectclass").is("user"),
                PERSON_CONTEXT_MAPPER);
		for (int i = 0; i < persons.size(); i++) {
			System.out.println(persons.toString());
		}*/
	
	}
}
