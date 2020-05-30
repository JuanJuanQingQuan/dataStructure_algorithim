package sortAlgorithm;

	import java.util.Arrays;
	
	public class MergeSort {
		public  static int[] merge(int[]left,int[]right){
			int index=0,l=0,r=0,min=0;
			int length=left.length+right.length;
			int[] tmp = new int[length];
			for(;index<length;){
				if(l<left.length&&r<right.length){
					if(left[l]<=right[r]){
						min = left[l];
						l++;
					}else{
						min = right[r];
						r++;
					}
					tmp[index] = min;
					index++;
				}
				
				if(l==left.length&&r<right.length){
					for(;r<right.length;r++){
						tmp[index] = right[r];
						index++;
					}
				}
				
				if(r==right.length&&l<left.length){
					for(;l<left.length;l++){
					tmp[index] = left[l];
					index++;
					}
				}
			}
			return tmp;
		}
		public static int[] mergeSort(int[] arr){
			if(arr.length<=1)return arr;
			int mid = arr.length/2;
			int[] left = Arrays.copyOfRange(arr, 0,mid);
			int[] right = Arrays.copyOfRange(arr, mid,arr.length);
			return merge(mergeSort(left),mergeSort(right));
		}
		public static void show(int[] nums) {
			for(int i =0;i<nums.length;i++) {
				System.out.printf("%d ", nums[i]);
			}
			System.out.println();
		}
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			int[] nums = {5,6,3,1,8,7,2,4};
			System.out.println("排序前：");
			show(nums);
			int[] numsSorted = mergeSort(nums);
			System.out.println("排序后：");
			show(numsSorted);
		}
	}
