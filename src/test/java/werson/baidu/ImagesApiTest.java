package werson.baidu;

import com.baidu.aip.imageclassify.AipImageClassify;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 百度图像识别技术test
 * @author Administrator
 * @version 1.0.0
 * @since 2018/6/7
 */
public class ImagesApiTest {

	//设置APPID/AK/SK
	private static final String APP_ID = "11366054";
	private static final String API_KEY = "tMelgPEfnAmkMcVtDHbeqPMR";
	private static final String SECRET_KEY = "87eGI46Dn00fGPMG7I30xpyMNOEhPGm9";

	@Test
	public void imageTest(){
		// 初始化一个AipImageClassifyClient
		AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		//支持的图片格式PNG、JPG、JPEG、BMP
		// 调用接口
		String path = "C:\\Users\\Administrator\\Desktop\\flower\\5.jpg";
		//JSONObject res = client.objectDetect(path, new HashMap<String, String>());
		JSONObject res = client.plantDetect(path, new HashMap<>());
		System.out.println("res="+res.toString(2));
	}

	@Test
	public void carTest(){
		AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
		String path = "C:\\Users\\Administrator\\Desktop\\flower\\dazhong.png";
		HashMap<String, String> options = new HashMap<>();
		options.put("top_num", "3");
		JSONObject res = client.carDetect(path, options);
		System.out.println("res="+res.toString(2));
	}

	@Test
	public void testQuyu(){
		System.out.println(9 % 4);
	}

}
