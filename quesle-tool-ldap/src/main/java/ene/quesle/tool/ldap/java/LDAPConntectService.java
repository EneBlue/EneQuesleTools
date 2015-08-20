package ene.quesle.tool.ldap.java;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.junit.Test;

public class LDAPConntectService {
	
	
	@Test
	public void test(){
		try {
			LDAPConntectService.connect3("fernando", "ZRposs1990");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static void connect3(String account, String password) throws NamingException{
		
        Hashtable<String, Object> env = new Hashtable<String, Object>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://192.168.1.100:389/");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, account );
        env.put(Context.SECURITY_CREDENTIALS, password);
        // 链接ldap
        new InitialDirContext(env);
	}
	
	@SuppressWarnings("unused")
	public static void connect(String account, String password){
	        Hashtable<String, Object> env = new Hashtable<String, Object>();
	        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	        env.put(Context.PROVIDER_URL, "ldap://192.168.1.100:389/");
	        env.put(Context.SECURITY_AUTHENTICATION, "simple");
	        env.put(Context.SECURITY_PRINCIPAL, account );
	        env.put(Context.SECURITY_CREDENTIALS, password);
	        try {
	            // 链接ldap
				DirContext ctx = new InitialDirContext(env);
	            System.out.println("认证成功");
	        } catch (javax.naming.AuthenticationException e) {
	            System.out.println("认证失败");
	        } catch (Exception e) {
	            System.out.println("认证出错：");
	            e.printStackTrace();
	        }
	}
	
	public static void connect2(){
		Properties env = new Properties();
	    String adminName = "fernando@shujutang.com";//username@domain.com
	    String adminPassword = "ZRpo1990";//password
	    //String ldapURL = "LDAP://10.10.2.153:389";//ip:port
	    String ldapURL = "LDAP://192.168.1.100:389/";//ip:port
	    env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
	    env.put(Context.SECURITY_AUTHENTICATION, "simple");//"none","simple","strong"
	    env.put(Context.SECURITY_PRINCIPAL, adminName);
	    env.put(Context.SECURITY_CREDENTIALS, adminPassword);
	    env.put(Context.PROVIDER_URL, ldapURL);
	    try {
	      LdapContext ctx = new InitialLdapContext(env, null);
	      
	      SearchControls searchCtls = new SearchControls();
	      searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
	      String searchFilter = "(&(objectCategory=person)(objectClass=user)(name=*))";
	      String searchBase = "DC=2013,DC=com";
	      String returnedAtts[] = {"memberOf"};
	      searchCtls.setReturningAttributes(returnedAtts);
	      NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter,searchCtls);
	      while (answer.hasMoreElements()) {
	        SearchResult sr = (SearchResult) answer.next();
	        System.out.println("<<<::[" + sr.getName()+"]::>>>>");
	      }
	      ctx.close();
	    }catch (NamingException e) {
	      e.printStackTrace();
	      System.err.println("Problem searching directory: " + e);
	    }
	}
}
