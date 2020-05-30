package main

import "fmt"

func show(nums []int){
	for _,num:=range nums{
		fmt.Printf("%d ",num)
	}
	fmt.Println()
}

func getIndex(nums []int,start int,end int)(int,[]int){
	tmp:= nums[start]
	for ;start<end;{
		for;start<end&&tmp<=nums[end];{
			end--
		}
		nums[start] = nums[end]
		for;start<end&&tmp>=nums[start];{
			start++
		}
		nums[end] = nums[start]
	}
	nums[start] = tmp
	return start,nums
}

func quickSort(nums []int,start int,end int)[]int{
	if(start<end){
		index,nums:= getIndex(nums,start,end)
		nums = quickSort(nums,0,index-1)
		nums = quickSort(nums,index+1,end)
	}
	return nums


}

func bucketSort(nums []int,bucketNum int)([]int){

	var bucket [][]int//二维数组

	for i := 0; i < bucketNum; i++ {
		tmp := make([]int, 1)
		bucket = append(bucket, tmp)//申请空间
	}
	//将数组分配装进桶里
	for i := 0; i < len(nums); i++ {
		bucket[nums[i]/bucketNum] = append(bucket[nums[i]/bucketNum], nums[i])
	}

	index:=0
	for i := 0; i < bucketNum; i++{
		if len(bucket[i]) > 1 {
			//对每个桶内部进行排序
			bucket[i] = quickSort(bucket[i],0,len(bucket[i])-1)
			for j := 1;j < len(bucket[i]) ;j++{//去掉一开始的tmp
				nums[index] = bucket[i][j]
				index++
			}
		}
	}
	return nums
}

func main(){
	nums :=[]int{50,6,3,1,8,7,2,4}
	fmt.Println("排序前：")
	show(nums)
	nums = bucketSort(nums,20)
	fmt.Println("排序后：")
	show(nums)
}