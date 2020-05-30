package sortAlgorithm;

public class InsertSort {
	public static void insertSort(int[] nums){
		int i,j,tmp;
		for(i=1;i<nums.length;i++){
			for(j=i;j>0;j--) {
				if(nums[j-1]>nums[j]){
					tmp = nums[j-1];
					nums[j-1] = nums[j];
					nums[j] = tmp;
				}else {
					break;
				}
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
		int[] nums = {9,7,8,2,5,1,3,6,4};
		System.out.println("排序前：");
		show(nums);
		insertSort(nums);
		System.out.println("排序后：");
		show(nums);
	}
}
