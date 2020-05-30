package main

import "fmt"

func show(nums []int){
	for _,num:=range nums{
		fmt.Printf("%d ",num)
	}
	fmt.Println()
}

func insertSort(nums[]int)[]int{
	for i:=1;i<len(nums);i++{
		for j:=i;j>0;j--{
			if nums[j-1]>nums[j]{
				nums[j-1],nums[j] = nums[j],nums[j-1]
			}else{
				break
			}
		}
	}
	return nums
}
func main(){
	nums :=[]int{9,7,8,2,5,1,3,6,4}
	fmt.Println("排序前：")
	show(nums)
	numsSorted := insertSort(nums)
	fmt.Println("排序后：")
	show(numsSorted)
}
