package sortAlgorithm;

public class CountSort {
	public static int[] countSort(int[] nums){
		if(nums.length<2) return nums;
		
		//计算最大最小值，确定数据范围
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int len = nums.length;
		int i,j=0;
		for(i=0;i<nums.length;i++){
			max = max>nums[i]?max:nums[i];
			min = min<nums[i]?min:nums[i];
		}
		int[] count_box = new int[max+1];
		for(int num:nums) {
			count_box[num]++;
		}
		
		for(i=min;i<max+1;) {
			while(count_box[i]!=0) {
				nums[j++] = i;
				count_box[i]--;
			}
			i++;
		}
		return nums;
	}
	
	public static void show(int[] nums) {
		for(int i =0;i<nums.length;i++) {
			System.out.printf("%d ", nums[i]);
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {2,3,8,7,1,2,2,2,7,3,9,8,2,1,4,2,6,9,2};
		System.out.println("排序前：");
		show(nums);
		int[] numsSorted = countSort(nums);
		System.out.println("排序后：");
		show(numsSorted);
	}
}
