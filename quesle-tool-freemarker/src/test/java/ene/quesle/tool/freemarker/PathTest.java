
package ene.quesle.tool.freemarker;

import org.junit.Test;

/**
 * @author Quesle
 * @Email  zrwuxian@126.com
 * @date   2015年8月19日 下午5:50:36 
 * @version 1.0 
 */
public class PathTest {

	@Test
	public void test(){
		System.out.println(Constants.class.getClassLoader().getResource("").toString());
		System.out.println(Constants.PATH_WEB_INF);
	}
}
