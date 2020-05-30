package main

import "fmt"

func show(nums []int){
	for _,num:=range nums{
		fmt.Printf("%d ",num)
	}
	fmt.Println()
}

func radixSort(nums []int,radix int)[]int{
	if(len(nums)<2) {
		return nums
	}

	i,j,k,digits:=0,0,0,0
	bucket := radix
	n := len(nums)
	tmpRadix :=1
	tmpNums := make([]int,n)
	//先找出最大值，计算其最大位数
	max :=nums[0]

	for _,num :=range nums{
		if max<num{
			max = num
		}
	}
	for max!=0{
		max /= radix
		digits++
	}
	//按位进行排序
	for i=0;i<digits;i++{
		count_box :=make([]int,bucket)
		//计数
		for j=0;j<n;j++{
			k = (nums[j]/tmpRadix)%bucket
			count_box[k]++
		}
		for j=1;j<bucket;j++{
			count_box[j] +=count_box[j-1];
		}
		for j = n - 1; j >= 0; j-- {//排序
			k = (nums[j]/tmpRadix)%bucket
			tmpNums[count_box[k]-1] = nums[j]
			count_box[k]--
		}
		for j = 0; j < n; j++ {
			nums[j] = tmpNums[j]
		}
		tmpRadix *= radix//低位转高位
	}
	return nums
}
func main(){
	nums :=[]int{3,44,38,5,47,15,36,26,27,2,46,4,19,50,48}
	fmt.Println("排序前：")
	show(nums)
	nums = radixSort(nums,16)
	fmt.Println("排序后：")
	show(nums)
}
