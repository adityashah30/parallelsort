package benchmarks;

public class MergeSort {

    public void mergeSort(Integer a[], int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(a, start, mid);
            mergeSort(a, mid + 1, end);
            mergeList(a, start, mid, mid + 1, end);
        }
    }

    public void mergeList(Integer a[], int start1, int end1, int start2, int end2) {
        int temp[] = new int[end1 - start1 + end2 - start2 + 2];
        int ptr1 = start1, ptr2 = start2, ptrmain = 0;
        while (ptr1 <= end1 && ptr2 <= end2) {
            if (a[ptr1] < a[ptr2]) {
                temp[ptrmain++] = a[ptr1++];
            } else {
                temp[ptrmain++] = a[ptr2++];
            }
        }
        while (ptr1 <= end1) {
            temp[ptrmain++] = a[ptr1++];
        }
        while (ptr2 <= end2) {
            temp[ptrmain++] = a[ptr2++];
        }
        ptrmain = 0;
        for (int i = start1; i <= end1; i++) {
            a[i] = temp[ptrmain++];
        }
        for (int i = start2; i <= end2; i++) {
            a[i] = temp[ptrmain++];
        }
    }

}
