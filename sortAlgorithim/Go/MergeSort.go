package main

import "fmt"

func show(nums []int){
	for _,num:=range nums{
		fmt.Printf("%d ",num)
	}
	fmt.Println()
}
func merge(left []int,right []int)[]int{
	index,l,r,min,length:=0,0,0,0,len(left)+len(right)
	tmp := make([]int,length)
	for ;index<length;{
		if l<len(left)&&r<len(right) {
			if left[l]<=right[r]{
				min = left[l]
				l++
			}else{
				min = right[r]
				r++
			}
			tmp[index] = min
			index++
		}
		if l==len(left)&&r<len(right) {
			for;r<len(right);r++{
				tmp[index] = right[r]
				index++
			}
		}

		if r==len(right)&&l<len(left){
			for;l<len(left);l++{
			tmp[index] = left[l]
			index++
			}
		}
	}
	return tmp
}
func mergeSort(nums []int)[]int{
	if len(nums)<=1{
		return nums
	}
	mid:=(int)(len(nums)/2)
	left := nums[0:mid]
	right:= nums[mid:len(nums)]

	return merge(mergeSort(left),mergeSort(right))
}
func main(){
	nums :=[]int{5,6,3,1,8,7,2,4}
	fmt.Println("排序前：")
	show(nums)
	numsSorted := mergeSort(nums)
	fmt.Println("排序后：")
	show(numsSorted)
}
