package sortAlgorithm;

public class SelectSort {
	public static void selectSort(int[] nums){
		int i,j,minIndex,tmp;
		for(i=0;i<nums.length-1;i++){
			minIndex = i;
			for(j=i+1;j<nums.length;j++) {
				if(nums[minIndex]>nums[j]){
					minIndex = j;
				}
			}
			if(minIndex!=i) {
				tmp = nums[minIndex];
				nums[minIndex] = nums[i];
				nums[i] = tmp;
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
		int[] nums = {4,12,13,4,3,42,33,1,43,44,2};
		System.out.println("排序前：");
		show(nums);
		selectSort(nums);
		System.out.println("排序后：");
		show(nums);
	}
}
