package werson.leetcode;

/**
 * 找数组两个数字的和
 * @author gejian
 * @version 1.0.0
 * @since 2018/8/3
 */
public class AryNumberSum {

	public int[] twoSum(int[] nums, int target) {
		for(int i = 0; i<nums.length-1; i++){
			for(int j = i+1; j<nums.length; j++){
				if((nums[i] + nums[j]) == target){
					return new int[]{i,j};
				}
			}
		}
		return null;
	}

	public int maxArea(int[] height) {
		int size = height.length;
		int maxArea = 0;
		for(int i = 0; i < size - 1; i++){
			for(int j = i + 1; j < size; j++){
				int area = (j - i)*(height[i]>height[j]?height[j]:height[i]);
				maxArea = area>maxArea?area:maxArea;
			}
		}
		return maxArea;
	}

	public static void main(String[] args) {
		int[] nums = {1,2,3};
		int a = 100;
		for(int i = 0; i<nums.length; i++){
			for(int j = i+1; j<nums.length; j++){
				if((nums[i] + nums[j]) == a){
					return;
				}
			}
		}
	}

}
