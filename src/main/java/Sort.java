import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by ctis-szx on 2017/8/1.
 */
public class Sort {

    /**
     * 堆排序（筛选法）:给定一个数组，将其初始化为一个大顶堆
     * 思想：从下放上初始化
     */
    public <T extends Comparable<T>> void initHeap(T[] array){
        if(array == null || array.length == 0){
            return;
        }
        int lastIndex = array.length - 1;
        int parentIndex = getParentIndex(lastIndex);

        for(int i = parentIndex; i >= 0; i--){
            int k = i;
            int j = getLeftChildIndex(k);
            while(j <= lastIndex){
                if(j < lastIndex){
                    if(Objects.compare(array[j], array[j + 1], (o1, o2) -> (o1.compareTo(o2))) < 0){
                        j++;
                    }
                }
                if(Objects.compare(array[k], array[j], (o1, o2) -> (o1.compareTo(o2))) > 0){
                    break;
                }else {
                    swap(array, k, j);
                    k = j;
                    j = getLeftChildIndex(k);
                }

            }
        }

    }

    /**
     * 调整堆
     * 思想：从根节点开始，从上往下调整
     * @param array
     * @param <T>
     */

    public <T extends Comparable<T>> void adjustHeap(T[] array, int lastIndex){
        int k = 0;
        int lastParentIndex = getParentIndex(lastIndex);
        while(k <= lastParentIndex){
            int j = getLeftChildIndex(k);
            if(j < lastIndex){
                if(Objects.compare(array[j], array[j+1], (o1, o2) -> (o1.compareTo(o2))) < 0){
                    j ++;
                }
            }
            if(Objects.compare(array[k], array[j], (o1, o2) -> (o1.compareTo(o2))) < 0){
                swap(array, k, j);
                k = j;
            }else {
                break;
            }
        }
    }

    /**
     * 将数组下标为k 和 j的两个位置的元素互换
     * @param k
     * @param j
     */
    private <T extends Comparable<T>> void swap(T[] array, int k, int j){
        if(array == null || k < 0 || k >= array.length || j < 0 || j >= array.length){
            return;
        }
        T temp = array[k];
        array[k] = array[j];
        array[j] = temp;
        return;
    }

    /**
     * 根据下标，计算其父节点的下标
     */
    private int getParentIndex(int index){
        return (index - 1)/2;
    }

    /**
     * 根据下标计算左子节点的下标
     */
    private int getLeftChildIndex(int index){
        return index * 2 + 1;
    }

    public <T extends Comparable<T>> void heapSort(T array[]){
        initHeap(array);
        int lastIndex = array.length - 1;
        while(lastIndex > 0){
            swap(array, 0, lastIndex);
            lastIndex --;
            if(lastIndex > 0){
                adjustHeap(array, lastIndex);
            }
        }
    }

    /**
     * 基于堆排序的求top(k)算法
     * @param array 输入的数组
     * @param k 前多少大个数
     * @param <T>
     */
    public <T extends Comparable<T>> void getTopK(T array[], int k){
        if(array == null || k <= 0 || k > array.length){
            return;
        }
        initHeap(array);
        int lastIndex = array.length - 1;
        while(lastIndex >= 0 && k > 0){
            System.out.println(array[0]);
            swap(array, 0, lastIndex);
            lastIndex --;
            if(lastIndex > 0){
                adjustHeap(array, lastIndex);
            }
            k --;
        }
    }

    /**
     * 堆排序测试：时间复杂度：O(nlogn)
     * 10,000,000 : 7651ms
     * 1,000,000:   1256ms
     * 100,000:     97ms
     * 10000:       93ms
     * @param args
     * @param <T>
     */

    public static <T extends Comparable<T>> void main(String[] args) {

        int num = 100000;
        Random random = new Random();
        IntStream is = random.ints(num, 0, 999);
        int[] tem = is.toArray();
        Integer[] array = new Integer[num];

        for(int k = 0; k < num; k ++){
            array[k] = tem[k];
        }

        Sort sort = new Sort();
        Long s = System.currentTimeMillis();
        sort.heapSort(array);
        //sort.getTopK(array, 5);
        Long e = System.currentTimeMillis();
        System.out.println("time:"+(e - s));
        //Arrays.asList(array).stream().forEach(o -> System.out.println(o));
    }


}
