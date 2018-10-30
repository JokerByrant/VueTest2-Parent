package cn.sxh;

import org.junit.Test;

public class test {
    static int arr[] = {5,9,27,12,36,4,20};

    static void swap(int arr[], int i, int j){
        arr[i] = arr[j] - arr[i];
        arr[j] = arr[j] - arr[i];
        arr[i] = arr[i] + arr[j];
    }

    @Test
    public void directsort(){
        for(int i = 1;i < arr.length; i++){
            for(int j = i; j > 0;j--){
                if(arr[j-1]>arr[j]){
                    swap(arr,j-1,j);
                }
            }
        }
        for(int a:arr){
            System.out.print(a + " ");
        }
    }

    @Test
    public void shellsort(){
        int gap,j;
        int n = arr.length;
        for(gap = n/2; gap > 0; gap /= 2){
            for(j = gap; j < n; j++){   //这里我们是从第gap个元素开始，依次寻找之前与其在同一组的元素，也可以变为从第一个元素开始遍历
                if(arr[j]<arr[j-gap]){
                    swap(arr,j,j-gap);
                }
            }
        }
        for(int a:arr){
            System.out.print(a + " ");
        }
    }

    /**
     *
     * 希尔排序的实现原理如下
     * 首先给出一个数组arr[]:{5,9,27,12,36,4,20}
     * 接着计算arr的首步步长  arr.length/2 = 3
     * 接着我们根据步长来对数组元素进行分组，即由首个元素开始，每隔一个步长的元素在一个组内
     * 5       12       20
     *   9        36
     *      27        4
     * 分组完毕，可以发现有三组{5,12,20},{9,36},{27,4}
     * 接着我们只需对这三组元素分别进行直接插入排序即可，排序完毕后元素位置发生变化，但各个组的位置不变
     * 5 9 4 12 36 27 20
     * 进行下一次
     * 步长：3/2 = 1,因为步长为1，因此整个数组就是单独的一组
     * 直接对其进行直接插入排序（从第二个元素开始，每次与之前的元素进行比较）
     * 最后结果：4 5 9 12 20 27 36
     *
     */
    @Test
    public void shellsort1(){
        //首先定义步长gap和要操作的元素y
        int gap,j;
        int n=arr.length;

        // 接着计算每次遍历的步长
        for(gap = n/2; gap > 0; gap /= 2){
            for(j = gap; j < n; j++){
                if(arr[j] < arr[j-gap]){
                    swap(arr,j,j-gap);
                }
            }
        }
        for(int a:arr){
            System.out.print(a + " ");
        }

    }

    //fibonacci数列
    public int fibonacci(int n){
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }

    //fibonacci优化1
    public int fibonacci1(int n){
        int f[] = new int[n];
        f[0] = 0;
        f[1] = 1;
        for(int i = 2; i < n; i++){
            f[i] = f[i-1] + f[i-2];
        }
        return f[n-1];

    }

    // 乘法运算
    public int multiply(int x,int y){
        int sum = 0;
        for (int i = x;i > 0;i /= 2){
            if(i % 2!=0){
                sum += y;
            }
            y = y*2;
        }
        return sum;
    }

    //冒泡排序
    @Test
    public void bubblesort(){
        for(int i = 0;i < arr.length;i++){
            for(int j = 1;j < arr.length - i; j++){
                if(arr[j-1]>arr[j]){
                    swap(arr,j-1,j);
                }
            }
        }
        for(int a:arr){
            System.out.print(a + " ");
        }
    }

    @Test
    public void test(){
//        System.out.println(fibonacci1(50));
        System.out.println(multiply(12,12));
    }
}
