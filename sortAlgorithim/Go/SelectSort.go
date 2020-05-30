package main
import "fmt"

func show(nums []int){
	for _,num:=range nums{
		fmt.Printf("%d ",num)
	}
	fmt.Println()
}
func selectSort(nums[]int)[]int{

	for i:=0;i<len(nums)-1;i++{
		minIndex := i
		for j:=i+1;j<len(nums);j++{
			if nums[minIndex] > nums[j]{
				minIndex = j
			}
		}
		if minIndex!=i{
			tmp:=nums[i]
			nums[i] = nums[minIndex]
			nums[minIndex] = tmp
		}

	}
	return nums
}


func main(){
	nums :=[]int{4,12,13,4,3,42,33,1,43,44}
	fmt.Println("排序前：")
	show(nums)
	numsSorted := selectSort(nums)
	fmt.Println("排序后：")
	show(numsSorted)
}

