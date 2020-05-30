package main

import "fmt"

func show(nums []int){
	for _,num:=range nums{
		fmt.Printf("%d ",num)
	}
	fmt.Println()
}

func countSort(nums []int)[]int{
	if(len(nums)<2) {
		return nums
	}
	i,j :=0,0
	//先找出最大值，计算其最大位数
	max :=nums[0]
	min :=nums[0]
	for _,num :=range nums{
		if max<num{
			max = num
		}
		if min>num{
			min = num
		}
	}
	countBox := make([]int,max+1)

	//计数
	for _,num :=range nums{
		countBox[num]++
	}
	for i=min;i<max+1;{
		for countBox[i]!=0{
			nums[j]=i
			j++
			countBox[i]--
		}
		i++
	}
	return nums
}

func main(){
	nums :=[]int{3,44,38,5,47,15,36,26,27,2,46,4,19,50,48}
	fmt.Println("排序前：")
	show(nums)
	nums = countSort(nums)
	fmt.Println("排序后：")
	show(nums)
}


