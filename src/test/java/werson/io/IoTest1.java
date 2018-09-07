package werson.io;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

/**
 * @author Administrator
 * @version 1.0.0
 * @since 2018/9/5
 */
public class IoTest1 {

	@Test
	public void ioReadFile(){
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(new File("C:\\Users\\Administrator\\Desktop\\支付模块问题记录.txt"));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

			while (bufferedReader.ready()){
				System.out.println(bufferedReader.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Test
	public void ioWriteFile(){
		try {
			File file = new File("C:\\Users\\Administrator\\Desktop\\hha2.txt");
			Reader reader = new FileReader(file);
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.append("Hello world!");
			fileWriter.append("\n this is a writer test.你好啊");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			Reader reader = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(reader);
			System.out.println("a=" + bufferedReader.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void input(){
		Scanner sc=new Scanner(System.in);
		String exit="exit";
		System.out.println("请输入数据：（exit表示退出指令）");
		while(sc.hasNext()){
			if(sc.hasNextByte()){
				System.out.println("输入了一个字节： "+sc.nextByte());
			}else if(sc.hasNextInt()){
				System.out.println("输入了一个整数： "+sc.nextInt());
			}else if(sc.hasNextDouble()){
				System.out.println("输入了一个浮点数： "+sc.nextDouble());
			}else if(sc.hasNextBoolean()){
				System.out.println("输入了一个布尔值： "+sc.nextBoolean());
			}else if(sc.next().contentEquals(exit)){
				System.out.println("退出！");
				break;
			}
		}
	}

	@Test
	public void ioStreamDemo(){
		try {
			//1. Buffered input file
			BufferedReader in = new BufferedReader(new StringReader("hahah"));
			String s, s2 = "";
			while ((s = in.readLine()) != null)
				s2 += s + "\n";
			in.close();

			//2 input from memory
			StringReader reader = new StringReader(s2);
			int c;
			while ((c = reader.read()) != -1){
				System.out.println((byte) c);
			}

			//3  FileOutputStream
			FileOutputStream fileOutputStream = new FileOutputStream("");
			fileOutputStream.write(null,0, 1024);

			byte[] bytes = new byte[512];
			FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\支付模块问题记录.txt");
			fileInputStream.read(bytes);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
