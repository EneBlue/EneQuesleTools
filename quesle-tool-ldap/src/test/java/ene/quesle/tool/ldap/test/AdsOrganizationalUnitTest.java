package ene.quesle.tool.ldap.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import ene.quesle.tool.ldap.AdsLdapService;
import ene.quesle.tool.ldap.OrganizationalUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-ldap.xml" })
public class AdsOrganizationalUnitTest {
	
	@Resource
	private AdsLdapService adsLdapService;

	@Test
	public void test(){
		//List<OrganizationalUnit> units = adsLdapService.getAdsOrganizationalUnits();
		List<OrganizationalUnit> units = adsLdapService.getAdsTopElements("ou=北京");
		System.out.println(JSON.toJSONString(units));
	}
	
}
