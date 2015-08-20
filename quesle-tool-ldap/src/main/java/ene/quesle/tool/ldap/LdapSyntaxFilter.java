package ene.quesle.tool.ldap;

public class LdapSyntaxFilter {

	public String organizationalUnitFilter(){
		return "(&(objectclass=organizationalUnit)(ou=*))";
	}
	
	public String base(String base, String ... ou){
		
		String filter = base;
		
		for (int i = 0; i < ou.length; i++) {
			filter = ou[i] +"," + filter;
		}
		return filter;
	}
	
	
	public String topFilter(String base){
		return "(&(objectclass=top))";
	}
	
	public String personByOrganizationalUnit(String ... ou){
		
		return "(&(objectclass=person))";
	}
}
