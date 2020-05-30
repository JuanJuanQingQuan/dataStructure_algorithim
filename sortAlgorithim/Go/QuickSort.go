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

func main(){
	nums :=[]int{5,6,3,1,8,7,2,4}
	fmt.Println("排序前：")
	show(nums)
	quickSort(nums,0,len(nums)-1)
	fmt.Println("排序后：")
	show(nums)
}
