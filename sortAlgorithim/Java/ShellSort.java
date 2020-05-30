package sortAlgorithm;


public class ShellSort {
	public static int[] shellSort(int[] nums){
		if(nums.length<2) return nums;
		int delta = nums.length/2;
		int i=0,j=0,tmp=0;
		while(delta>=1) {
			for(i = delta;i<nums.length;i++){
				tmp = nums[i];
				j = i- delta;
				while(j>=0 && nums[j]>tmp){
						nums[j+delta] = nums[j];
						j-=delta;
				}
				nums[j+delta] = tmp;
			}
			delta /=2;
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
		int[] nums = {84,83,88,87,61,50,70,60,80,99};
		System.out.println("排序前：");
		show(nums);
		int[] numsSorted = shellSort(nums);
		System.out.println("排序后：");
		show(numsSorted);
	}
}
