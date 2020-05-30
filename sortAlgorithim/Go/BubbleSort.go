package main

import "fmt"

func show(nums []int){
	for _,num:=range nums{
		fmt.Printf("%d ",num)
	}
	fmt.Println()
}
func bubbleSort(nums[]int)[]int{
	for i:=0;i<len(nums)-1;i++{
		exchange:=false
		for j:=1;j<len(nums)-i;j++ {
			if nums[j-1] > nums[j]{
				nums[j],nums[j-1]=nums[j-1],nums[j]
				exchange=true
			}
		}
		if !exchange{
			return nums
		}
	}
	return nums
}
func main(){
	nums :=[]int{9,5,6,7,2,8,3,4,1}
	nums  = nums[0:2]
	fmt.Println("排序前：")
	show(nums)
	numsSorted := bubbleSort(nums)
	fmt.Println("排序后：")
	show(numsSorted)
}

