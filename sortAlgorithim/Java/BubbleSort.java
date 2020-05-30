package sortAlgorithm;

public class BubbleSort {
	public static void bubbleSort(int[] nums){
		int i,j,tmp;
		boolean exchange = false;
		for(i=0;i<nums.length-1;i++){
			exchange = false;
			for(j=1;j<nums.length-i;j++) {
				if(nums[j-1]>nums[j]){
					tmp = nums[j-1];
					nums[j-1] = nums[j];
					nums[j] = tmp;
					exchange =true;
				}
			}
			if(!exchange) {
				return;
			}
		}
	}
	public static void show(int[] nums) {
		for(int i =0;i<nums.length;i++) {
			System.out.printf("%d ", nums[i]);
		}
		System.out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {9,5,6,7,2,8,3,4,1};
		System.out.println("排序前：");
		show(nums);
		bubbleSort(nums);
		System.out.println("排序后：");
		show(nums);
	}
}
