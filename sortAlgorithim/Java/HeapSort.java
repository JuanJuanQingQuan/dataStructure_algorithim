package sortAlgorithm;

public class HeapSort {
    // 堆排序
    public static int[] heapSort(int[] nums) {
    	int n = nums.length;
    	int i,tmp;
    	//构建大顶堆
    	for(i=(n-2)/2;i>=0;i--) {//从只有一层子节点的父节点开始往树的根节点进行下沉操作
    		downAdjust(nums,i,n-1);
    	}
    	//进行堆排序
    	for(i=n-1;i>=1;i--){
    		tmp = nums[i];
    		nums[i] = nums[0];
    		nums[0] = tmp;
    		downAdjust(nums,0,i-1);
    	}
    	return nums;
    }

        //小元素下沉操作
    public static void downAdjust(int[] nums, int parentIndex, int n) {
        //临时保存要下沉的元素
        int temp = nums[parentIndex];
        //左子节点的位置
        int childIndex = 2 * parentIndex + 1;
        while (childIndex <= n) {
            // 如果右子节点比左子节点大，则与右子节点交换
            if(childIndex + 1 <= n && nums[childIndex] < nums[childIndex + 1])
            	childIndex++;
            if (nums[childIndex] <= temp ) break;//该子节点符合大顶堆特点
            //注意由于我们是从高度为1的节点进行堆排序的，所以不用担心节点子节点的子节点不符合堆特点
            // 父节点进行下沉
            nums[parentIndex] = nums[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * parentIndex + 1;
        }
        nums[parentIndex] = temp;
    }
	public static void main(String[] args) {
		int[] a = {91,60,96,13,35,65,81,46,13,10,30,20,31,77,81,22};
		System.out.print("排序前数组a：\n");
		for(int i:a) {
			System.out.print(i);
			System.out.print(" ");	
		}
		a=heapSort(a);
		System.out.print("\n排序后数组a：\n");
		for(int i:a) {
			System.out.print(i);
			System.out.print(" ");
		}
	}
}
