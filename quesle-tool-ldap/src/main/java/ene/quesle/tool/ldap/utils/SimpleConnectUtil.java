package ene.quesle.tool.ldap.utils;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;

/**
 * 简单的连接LDAP服务的方法
 * @author Quesle
 */
public class SimpleConnectUtil {

	/**
	 * 一个简单的测试是否连接LDAP的方法，可以用来测试这个用户名和密码是否正确
	 * @param ldapUrl   ldap://127.0.0.1:389/
	 * @param account   用户名
	 * @param password  密码
	 * @throws NamingException  当连接不成功的是否弹出的异常
	 */
	public void connect(String ldapUrl, String account, String password) throws NamingException{
        Hashtable<String, Object> env = new Hashtable<String, Object>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapUrl);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, account);
        env.put(Context.SECURITY_CREDENTIALS, password);
        // 链接ldap
        new InitialDirContext(env);
	}
}
