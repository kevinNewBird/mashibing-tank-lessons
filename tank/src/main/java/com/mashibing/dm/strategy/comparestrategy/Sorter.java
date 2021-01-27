package com.mashibing.dm.strategy.comparestrategy;

/***********************
 * Description: TODO 类描述<BR>
 * author: zhao.song
 * date: 2021/1/25 16:38
 * version: 1.0
 ***********************/
public class Sorter<T> {

    /**
     * Description: 1.0非策略模式 <BR>
     *
     * @param arr:
     * @return
     * @author zhao.song    2021/1/25 22:32
     */
    public static void sort(MineComparable[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < arr.length; j++) {
                minPos = arr[minPos].compareTo(arr[j]) == -1 ? minPos : j;
            }
            swap(arr, i, minPos);
        }
    }

    /**
     * Description: 2.0比较策略 <BR>
     *
     * @param array:
     * @param comparator:
     * @return
     * @author zhao.song    2021/1/25 22:35
     */
    public void sort(T[] array, MineComparator<T> comparator) {
        if (array == null || array.length <= 1) {
            return;
        }
        for (int i = 0; i < array.length - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < array.length; j++) {
                minPos = comparator.compare(array[minPos], array[j]) == -1 ? minPos : j;
            }
            swap(array, i, minPos);
        }
    }

    public static void swap(MineComparable[] arr, int curPos, int minPos) {
        MineComparable temp = arr[curPos];
        arr[curPos] = arr[minPos];
        arr[minPos] = temp;
//        if (curPos == minPos) {
//            return;
//        }
//        arr[curPos] = arr[curPos] ^ arr[minPos];
//        arr[minPos] = arr[curPos] ^ arr[minPos];
//        arr[curPos] = arr[curPos] ^ arr[minPos];
    }

    public void swap(T[] arr, int curPos, int minPos) {
        T temp = arr[curPos];
        arr[curPos] = arr[minPos];
        arr[minPos] = temp;
//        if (curPos == minPos) {
//            return;
//        }
//        arr[curPos] = arr[curPos] ^ arr[minPos];
//        arr[minPos] = arr[curPos] ^ arr[minPos];
//        arr[curPos] = arr[curPos] ^ arr[minPos];
    }

}
