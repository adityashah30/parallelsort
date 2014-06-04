package sort;

import java.util.ArrayList;
import merge.MergeEngine;

public class Sort<T extends Comparable> extends Thread {

    T[] array;
    int start;
    int end;

    public Sort(T[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    public void run() {
        quickSort(start, end);
        try {
            MergeEngine.getInstance().insertThread(new ArrayList<Integer>() {
                {
                    add(start);
                }
            }, new ArrayList<Integer>() {
                {
                    add(end);
                }
            });
        } catch (NullPointerException e) {
            System.err.println("Merge Engine not initialized...");
        }
    }

    public void quickSort(int start, int end) {
        if (start < end) {
            int key = partitionArray(start, end);
            quickSort(start, key - 1);
            quickSort(key + 1, end);
        }
    }

    public int partitionArray(int start, int end) {
        int pivot = start + (int) (Math.random() * (end - start + 1));
        swapElement(start, pivot);
        T key = array[start];
        int i = start;
        for (int j = i + 1; j <= end; j++) {
            if (array[j].compareTo(key) == -1) {
                i++;
                swapElement(i, j);
            }
        }
        swapElement(start, i);
        return i;
    }

    public void swapElement(int a, int b) {
        T temp = array[ a];
        array[a] = array[b];
        array[b] = temp;
    }

}
