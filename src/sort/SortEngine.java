package sort;

import java.util.ArrayList;

public class SortEngine<T extends Comparable> extends Thread {

    T[] array;
    int start;
    int end;
    int numProcessors;


    public SortEngine(T[] array, int start, int end, int numProcessors) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.numProcessors = numProcessors;
    }

    public void run() {
        int len = (end - start + 1);
        for (int i = 0; i < numProcessors; i++) {
            new Sort<T>(array, (i * len) / numProcessors, ((i + 1) * len) / numProcessors - 1).start();
        }
    }
}
