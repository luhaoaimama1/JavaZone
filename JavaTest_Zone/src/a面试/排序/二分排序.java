package a面试.排序;

/**
 * 折半插入排序
 */
public class 二分排序 {
    public static void main(String[] args) {  
        int []a={4,2,1,6,3,6,0,-5,1,1};  
        int i,j;  
        int low,high,mid;  
        int temp;  
        for(i=1;i<a.length;i++){
            temp=a[i];  
            low=0;  
            high=i-1;
            //定位 前面那些位置 那个位置应该是temp应该在的位置。
            while(low<=high){
                //临界条件是   low<=temp<=high low和high相连的时候。
                //既 low>high的时候 over了
                //low high 相邻的时候  low=mid+1; low==high;->a[mid=high]>temp->high=high-1. 所以high再+1.找到temp放入的位置。
                // low>high的时候 那么high+1 的位置就是 temp应该呆的位置。

                //一半的一半的 和 temp对比。
                mid=(low+high)/2;
                if(a[mid]>temp)  
                    high=mid-1;  
                else  
                    low=mid+1;  
                  
            }
            //定位的位置都向后挪动一个位置
            for(j=i-1;j>high;j--)  
                a[j+1]=a[j];
            //temp弄进来即可。
            a[high+1]=temp;
        }  
        for(i=0;i<10;i++){  
            System.out.printf("%d",a[i]);  
        }  
    }  
  
}