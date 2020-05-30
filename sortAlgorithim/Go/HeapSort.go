package main

import "fmt"

func show(nums []int){
	for _,num:=range nums{
		fmt.Printf("%d ",num)
	}
	fmt.Println()
}

func downAdjust(nums []int,parentIndex int,end int)[]int{
	//临时保存调整前的根节点
	tmp := nums[parentIndex]
	//左边子节点在数组中位置
	childIndex := 2*parentIndex + 1
	for childIndex<=end {
		//如果右边子节点比左边子节点大，则右边子节点
		if(childIndex+1<=end && nums[childIndex]<nums[childIndex+1]){
			childIndex++//右边子节点在数组中位置childIndex+1
		}
		if(nums[childIndex]<=tmp){
			break
		}//如果子节点比父节点小或者相等则构建无误
		//父节点被子节点代替，子节点上浮
		nums[parentIndex],parentIndex = nums[childIndex],childIndex
		childIndex = 2*parentIndex + 1//寻找孩子节点孩子
	}
	nums[parentIndex] = tmp//父节点下沉

	return nums
}

func heapSort(nums []int)[]int{
	length :=len(nums)
	for i:=(length-2)/2;i>=0;i--{//构建最大堆
		nums = downAdjust(nums,i,length-1)
	}
	//每取一次根节点进行一次重新构建大顶堆
	for i:=length-1;i>0;i--{
		nums[i],nums[0] = nums[0],nums[i]
		downAdjust(nums,0,i-1)
	}
	return nums
}

func main(){
	nums :=[]int{91,60,96,13,35,65,81,46,13,10,30,20,31,77,81,22}
	fmt.Println("排序前：")
	show(nums)
	heapSort(nums)
	fmt.Println("排序后：")
	show(nums)
}
