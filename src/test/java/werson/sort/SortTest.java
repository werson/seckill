package werson.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * 排序算法
 * @author Administrator
 * @version 1.0.0
 * @since 2018/5/25
 */
public class SortTest {

	final static int[] arr = {3,34,1,13,2,45,65,33,12};

	@Before
	public void showBefore(){
		System.out.println("排序前：" + Arrays.toString(arr));
	}

	/**
	 * 选择排序：每一趟从待排序的记录中选出最小的元素，顺序放在已排好序的序列最后，直到全部记录排序完毕
	 */
	@Test
	public void selectionSort(){
		for(int i = 0; i < arr.length - 1; i++) {// 做第i趟排序
			int k = i;
			for(int j = k + 1; j < arr.length; j++){// 选最小的记录
				if(arr[j] < arr[k]){
					k = j; //记下目前找到的最小值所在的位置
				}
			}
			//在内层循环结束，也就是找到本轮循环的最小的数以后，再进行交换
			if(i != k){//交换a[i]和a[k]
				int temp = arr[i];
				arr[i] = arr[k];
				arr[k] = temp;
			}
		}
	}

	/**
	 * 冒泡排序
	 */
	@Test
	public void maoPaoPaiXu(){
		for(int i = 0; i < arr.length - 1; i++) {
			for(int j = i + 1; j < arr.length; j++){
				if(arr[i] > arr[j]){
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

	/**
	 * 插入排序
	 */
	@Test
	public void insertSort(){
		for(int i = 1; i < arr.length; i++){
			int tmp = arr[i];
			int j;
			for(j = i; j > 0 && tmp < arr[j-1]; j--){
				arr[j] = arr[j-1];
			}
			arr[j] = tmp;
		}
	}

	/**
	 * 快速排序，分治法
	 */
	@Test
	public void quickSort(){
		quickSort(0, arr.length-1);
	}

	private void quickSort(int left, int right){
		if(left >= right) return;
		int tmp = arr[left];
		int i = left;
		int j = right;
		while (i != j){
			while (arr[j] >= tmp && i < j){
				j--;
			}
			while (arr[i] <= tmp && i < j){
				i++;
			}
			if(i < j){
				int t = arr[i];
				arr[i] = arr[j];
				arr[j] = t;
			}
		}
		arr[left] = arr[i];
		arr[i] = tmp;
		quickSort(left, i-1);
		quickSort(i+1, right);
	}

	/**
	 * 归并排序
	 */
	@Test
	public void mergeSort(){
		int i = 0;
		int j = arr.length/2;
		int[] tmp = new int[arr.length];
		for(int n = 0; n < arr.length; n++){
			if(arr[i] < arr[j]){
				tmp[n] = arr[i];
				i++;
			}else if(arr[i] > arr[j]){
				tmp[n] = arr[j];
				j++;
			}
		}

	}

	@After
	public void showAfter(){
		System.out.println("排序后：" + Arrays.toString(arr));
	}
}
