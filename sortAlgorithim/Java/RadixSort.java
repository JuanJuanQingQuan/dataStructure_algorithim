package sortAlgorithm;
import java.util.*;
public class RadixSort {
	public static int[] radixSort(int[] nums,int radix){
		if(nums.length<2) return nums;
		
		//先算出radix进制下，本数组最多有多少位数
		int max = 0;
		int len = nums.length;
		int[] tmpNums = new int[len];
		int bucket = radix;
		int i,j,k,digits=0,tmpRadix = 1;
		for(i=0;i<nums.length;i++){
			max = max>nums[i]?max:nums[i];
		}
		while(max!=0){
			max/=radix;
			digits++;
		} 

		for(i=0;i<digits;i++) {
			int[] count_box = new int[bucket];//桶初始化
			for(j=0;j<len;j++) {//按第i位进行放入桶中
				k = (nums[j]/tmpRadix)%bucket;
				count_box[k]++;
			}
			
			for(j=1;j<bucket;j++)//计数
				count_box[j] = count_box[j-1]+count_box[j];
			
			for(j=len-1;j>=0;j--){//排序入位
				k = (nums[j]/tmpRadix)%bucket;
				tmpNums[count_box[k]-1] = nums[j];
				count_box[k]--;
			}
			
			for(j=0;j<len;j++){
				nums[j] = tmpNums[j];
			}
			tmpRadix = radix*tmpRadix;
		}
		return tmpNums;
	}
	
	public static void show(int[] nums) {
		for(int i =0;i<nums.length;i++) {
			System.out.printf("%d ", nums[i]);
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
		System.out.println("排序前：");
		show(nums);
		int[] numsSorted = radixSort(nums,16);
		System.out.println("排序后：");
		show(numsSorted);
	}
}
