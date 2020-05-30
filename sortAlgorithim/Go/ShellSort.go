package main

import "fmt"

func show(nums []int){
	for _,num:=range nums{
		fmt.Printf("%d ",num)
	}
	fmt.Println()
}

func shellSort(nums[]int)[]int{
	delta := len(nums)/2
	for delta>=1{
		for i:=delta;i<len(nums);i++{
			tmp:=nums[i]
			j:=i-delta
			for j>=0&&nums[j]>tmp{
					nums[j+delta]= nums[j]
					j =j-delta
				}
				nums[j+delta] = tmp
			}
			delta=delta/2
		}

	return nums
	}
func main(){
	nums :=[]int{84,83,88,87,61,50,70,60,80,99}
	fmt.Println("排序前：")
	show(nums)
	numsSorted := shellSort(nums)
	fmt.Println("排序后：")
	show(numsSorted)
}
