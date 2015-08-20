package ene.quesle.tool.ldap;

import java.util.List;

/**
 * 
 * @author Quesle
 */
public interface AdsLdapService {
	
	/**
	 * 通过名称和ou创建一个用户
	 * @param name
	 * @param ou
	 */
	public void createAdsAccount(String name, String... ou);
	
	/**
	 * 通过名称和ou修改一个用户
	 * @param name
	 * @param ou
	 */
	public void updateAdsAccount(String name, String... ou);
	
	/**
	 * 通过名称和ou删除一个用户
	 * @param name
	 * @param ou
	 */
	public void deleteAdsAccount(String name, String... ou);
	
	/**
	 * 获取BASE DN下的所有组织
	 * @param ou
	 */
	public List<OrganizationalUnit> getAdsOrganizationalUnits();
	
	/**
	 * 通过ou找到其下面的所有用户信息
	 * @param ou
	 * @return
	 */
	public List<Person> getAdsPersonsByUnits(String... ou);
	
	public List<OrganizationalUnit> getAdsTopElements(String... ou);
}
