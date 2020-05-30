package sortAlgorithm;

public class QuickSort {
	public static void quickSort(int[]arr,int start,int end) {
		if(start<end) {
			int index = getIndex(arr,start,end);
			quickSort(arr,start,index-1);
			quickSort(arr,index+1,end);
		}
	}
	public static int getIndex(int[]arr,int start,int end) {
		int tmp = arr[start];
		while(start<end){
			while(start<end && tmp<=arr[end]) end--;
			arr[start] = arr[end];
			while(start<end && tmp>=arr[start]) start++;
			arr[end] = arr[start];
		}
		arr[start] = tmp;
		return start;
	}
	public static void main(String[] args) {
		int[] a = {5,4,15,2,1,6,20,3,7,5,14};
		System.out.print("排序前数组a：\n");
		for(int i:a) {
			System.out.print(i);
			System.out.print(" ");	
		}
		quickSort(a,0,a.length-1);
		System.out.print("\n排序后数组a：\n");
		for(int i:a) {
			System.out.print(i);
			System.out.print(" ");
		}
	}
}
