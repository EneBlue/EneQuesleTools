package ene.quesle.tool.ldap.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import ene.quesle.tool.ldap.AdsLdapService;
import ene.quesle.tool.ldap.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-ldap.xml" })
public class AdsPersonsByUnitsTest {

	@Resource
	private AdsLdapService adsLdapService;
	
	@Test
	public void test(){
		
		List<Person> persons = adsLdapService.getAdsPersonsByUnits("OU=北京", "OU=工程部");
		
		System.out.println(persons.size());
		
		System.out.println(JSON.toJSONString(persons));
	}
}
