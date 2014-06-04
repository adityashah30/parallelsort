package benchmarks;

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import sort.*;

public class SizeComparison {

    public static void main(String[] args) throws IOException {
        int size = 1;
        BufferedWriter out = new BufferedWriter(new FileWriter(new File("sizebenchmark")));
        ParallelSort<Integer> psort;
        MergeSort msort;
        QuickSort qsort;
        int power10 = 7;
        int iter = 10;
        out.write("-------------------------------------------------------------------------------------------------------\n");
        for (int count = 1; count <= power10; count++) {
            size *= 10;
            Integer[] a = new Integer[size];
            long ptime = 0, mtime = 0, qtime = 0, atime = 0;
            for (int cnt = 0; cnt < iter; cnt++) {
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < size; i++) {
                    s.append((int) (Math.random() * size));
                    s.append(' ');
                }
                BufferedWriter fout = new BufferedWriter(new FileWriter(new File("data")));
                fout.write(s.toString());
                fout.close();
                fillArray(a);
                psort = new ParallelSort<>(a);
                msort = new MergeSort();
                qsort = new QuickSort();
                long s1 = System.currentTimeMillis();
                msort.mergeSort(a, 0, a.length - 1);
                long e1 = System.currentTimeMillis();
                mtime += (e1 - s1);
                fillArray(a);
                long s2 = System.currentTimeMillis();
                qsort.quickSort(a, 0, a.length - 1);
                long e2 = System.currentTimeMillis();
                qtime += (e2 - s2);
                fillArray(a);
                long s3 = System.currentTimeMillis();
                psort.sort();
                long e3 = System.currentTimeMillis();
                ptime += (e3 - s3);
                fillArray(a);
                long s4 = System.currentTimeMillis();
                Arrays.sort(a);
                long e4 = System.currentTimeMillis();
                atime += (e4 - s4);
                out.write("Size: " + size + ", Iteration: " + cnt + ", Parallel Sort: " + (e3 - s3) + ", Merge Sort: " + (e1 - s1) + ", QuickSort: " + (e2 - s2) + ", Arrays: " + (e4 - s4) + "\n");
                System.out.println("Size: " + size + ", Iteration: " + cnt + ", Parallel Sort: " + (e3 - s3) + ", Merge Sort: " + (e1 - s1) + ", QuickSort: " + (e2 - s2) + ", Arrays: " + (e4 - s4));
            }
            ptime /= iter;
            mtime /= iter;
            qtime /= iter;
            atime /= iter;
            System.out.println("Size: " + size + ", Parallel Sort: " + ptime + ", MergeSort: " + mtime + ", QuickSort: " + qtime + ", Arrays: " + atime);
            out.write("-------------------------------------------------------------------------------------------------------\n");
            out.write("Size: " + size + ", Parallel Sort: " + ptime + ", MergeSort: " + mtime + ", QuickSort: " + qtime + ", Arrays: " + atime + "\n");
            out.write("-------------------------------------------------------------------------------------------------------\n");
        }
        out.close();
    }

    public static void fillArray(Integer[] a) throws IOException {
        Scanner fin = new Scanner(new BufferedReader(new FileReader(new File("data"))));
        int i = 0;
        while (fin.hasNextInt()) {
            a[i++] = fin.nextInt();
        }
        fin.close();
    }

}
