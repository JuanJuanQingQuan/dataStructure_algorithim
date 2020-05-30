package sortAlgorithm;

import java.util.*;
	public class BucketSort {
	
	public static int[] bucketSort(int[] nums,int bucketNum){
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
        ArrayList<LinkedList<Integer>> bucketList = new ArrayList<>(bucketNum);
        //初始化桶
        for (i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<Integer>());
        }
        //遍历原数组，将每个元素放入桶中
        for (i = 0; i < len; i++) {
            bucketList.get((nums[i])/bucketNum).add(nums[i]);
        }
        //对桶内的元素进行排序，采用库里自带排序
        for (i = 0; i < bucketNum; i++) {
            Collections.sort(bucketList.get(i));
        }
        //把每个桶排序好的数据进行合并汇总放回原数组
        for (i = 0; i < bucketNum; i++) {
            for (int t : bucketList.get(i)) {
            	nums[j++] = t;
            }
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
		int[] numsSorted = bucketSort(nums,10);
		System.out.println("排序后：");
		show(numsSorted);
	}
}
