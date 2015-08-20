package ene.quesle.tool.ldap;

import java.util.ArrayList;
import java.util.List;

import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.support.LdapUtils;

public class OrganizationalUnit {

	private List<String> units;
	
	public OrganizationalUnit(DirContextOperations context) {
		LdapName dn = LdapUtils.newLdapName(context.getDn());
		List<Rdn> rdns = dn.getRdns();
		
		for (int i = 0; i < rdns.size(); i++) {
			this.add((String) rdns.get(i).getValue());
		}
	}
	
	public void add(String ou){
		if(units == null){
			units = new ArrayList<String>();
		}
		units.add(ou);
	}

	public List<String> getUnits() {
		return units;
	}

	public void setUnits(List<String> units) {
		this.units = units;
	}
}
