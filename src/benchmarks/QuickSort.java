package benchmarks;

public class QuickSort {

    public void quickSort(Integer[] a, int start, int end) {
        if (start < end) {
            int key = partitionArray(a, start, end);
            quickSort(a, start, key - 1);
            quickSort(a, key + 1, end);
        }
    }

    public int partitionArray(Integer[] arr, int start, int end) {
        int pivot = start + (int) (Math.random() * (end - start + 1));
        swapElement(arr, start, pivot);
        int key = arr[start];
        int i = start;
        for (int j = i + 1; j <= end; j++) {
            if (arr[j] < key) {
                i++;
                swapElement(arr, i, j);
            }

        }
        swapElement(arr, start, i);
        return i;
    }

    public void swapElement(Integer[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
