package werson.sort;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.seckill.entity.Seckill;
import sun.misc.BASE64Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @version 1.0.0
 * @since 2018/5/29
 */
public class MainTest {

	@Test
	public void ByteTest(){
		BigDecimal a = BigDecimal.valueOf(1L);
		System.out.println(a.subtract(null));
	}

	@Test
	public void testSorted(){
		List<Seckill> seckillList = new ArrayList<>();
		Seckill seckill = new Seckill();
		seckill.setName("gagaga");
		seckill.setSeckillId(1);
		seckillList.add(seckill);
		Seckill seckill2 = new Seckill();
		seckill2.setName("wawa");
		seckill2.setSeckillId(2);
		seckillList.add(seckill2);
		List<Seckill> seckillList2 = seckillList.stream().sorted(Comparator.comparing(Seckill::getSeckillId).reversed()).collect(Collectors.toList());
		for (Seckill s : seckillList2) {
			System.out.println(s.getName());
		}
	}

	@Test
	public void testRSA() throws Exception {
		//String publicKeyStr = loadPublicKeyByFile("D:\\project\\study\\seckill\\src\\main\\resources");
		String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7TGtyGd+CJUXIl/hDp6U\n" +
				"XOPwlg8BM0dnvYZ0Mo4JOgYpbvgtwbZR+3Nvps2c2YHGyMxgNWF9zJZyaQ57GyTL\n" +
				"QuY5UxWXWfz8xJR4Q7ioklQZ9/vCDEaDyXEpQ5cspAq75Z2rbtN0ZpK8sWG7cRsb\n" +
				"89OrDx+97oIzN7o/yBwEeAMwYSDpWhOrKQCJG8gCdiYQ8goPvBQRXWeDKBerL86T\n" +
				"mH8GIJVej0Oux612oZk/XYGvMJKj2ESRoaOJhnyUpUPvVeN/neDyouTLZCU7PvjM\n" +
				"nmkMXkHx80+2/IIM65ZDnq+IEWYA2CQVitLUHYd56ZY2Of4on9WLuWuqC8rw1CQ9\n" +
				"pQIDAQAB";
		RSAPublicKey publicKey = loadPublicKeyByStr(publicKeyStr);
		byte[] data = encrypt(publicKey,("欢迎来到").getBytes());
		System.out.println(Base64.encodeBase64String(data));
	}

	/**
	 * 公钥加密过程
	 * @param publicKey 公钥
	 * @param plainTextData 明文数据
	 * @throws Exception 加密过程中的异常信息
	 */
	public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData)
			throws Exception {
		if (publicKey == null) {
			throw new Exception("加密公钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			// 使用默认RSA
			cipher = Cipher.getInstance("RSA");
			//cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return cipher.doFinal(plainTextData);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("加密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("明文数据已损坏");
		}
	}

	/**
	 * 从文件中输入流中加载公钥
	 */
	public static String loadPublicKeyByFile(String path) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path
					+ "/public.pem"));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			throw new Exception("公钥数据流读取错误");
		} catch (NullPointerException e) {
			throw new Exception("公钥输入流为空");
		}
	}

	/**
	 * 从字符串中加载公钥
	 */
	public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr)
			throws Exception {
		try {
			//byte[] buffer = publicKeyStr.getBytes();
			byte[] buffer = (new BASE64Decoder()).decodeBuffer(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法,"+e.getMessage());
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}



}
