package ene.quesle.tool.freemarker;

/**
 * @author Quesle
 * @Email  zrwuxian@126.com
 * @date   2015年8月19日 下午5:52:20 
 * @version 1.0 
 */
public class Constants {

	private static String PATH_WEB_INF_NO = Constants.class.getClassLoader().getResource("").toString()
			.replace("file:", "").replace("classes", "");
	
	public static final String PATH_WEB_INF = PATH_WEB_INF_NO.substring(1, PATH_WEB_INF_NO.length() - 1);
	
	public static final String PATH_FM = PATH_WEB_INF + "fm";
}
