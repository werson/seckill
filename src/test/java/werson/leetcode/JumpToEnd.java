package werson.leetcode;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * 用最少步数跳到最后一步
 * @author Administrator
 * @version 1.0.0
 * @since 2018/6/12
 */
public class JumpToEnd {

	@Test
	public void jumpTest(){
		int[] nums = new int[9002];
		for(int i = 0; i < 9000; i++){
			nums[i] = 1;
		}
		nums[9000] = 1;
		nums[9001] = 0;
		System.out.println(Arrays.toString(nums));
		long startTime = System.currentTimeMillis();
		System.out.println(jump2(nums,0));
		System.out.println("运行时间：" + (System.currentTimeMillis() - startTime));
	}

	@Test
	public void test(){
		int[] nums = {3,1,6,1,5,0,4,5,6,7};
		long startTime = System.currentTimeMillis();
		System.out.println(jump2(nums,0));
		System.out.println("运行时间：" + (System.currentTimeMillis() - startTime));
	}


	public int jump(int[] nums, int stepNum){
		if(nums.length == 1){
			return stepNum;
		}
		int thisStep = nums[0];
		while (thisStep > nums.length){
			thisStep--;
		}
		while (nums[thisStep] == 0){
			thisStep--;
		}
		nums = Arrays.copyOfRange(nums, thisStep, nums.length);
		return jump(nums, ++stepNum);
	}

	public int jump2(int[] nums, int stepNum){
		int max = 1;
		int endIndex = nums.length - 1;
		while (endIndex > 0){
			stepNum++;
			for(int i = 0; i < endIndex; i++){
				int jumpLength = endIndex - i;
				if(nums[i] >= jumpLength){
					max = jumpLength;
					break;
				}
			}
			endIndex = endIndex - max;
		}
		return stepNum;
	}


	@Test
	public void test2(){
		String imgId1 = "你as";

		System.out.println(imgId1.length());
	}
}
