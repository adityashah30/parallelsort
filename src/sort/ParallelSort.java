package sort;

import java.util.Arrays;
import merge.*;

public class ParallelSort<T extends Comparable> extends Thread{

    private T[] array;
    private int start;
    private int end;
    private int numProc;
    private SortEngine sortEngine;
    private boolean flag = true;
    
    public static void main(String [] args){
        Integer [] a = new Integer[1000000];
        Integer [] b = new Integer[1000000];
        for(int i=0; i<a.length; i++){
            a[i] = (int)(Math.random()*(100000));
            b[i] = a[i];
        }
        ParallelSort obj1 = new ParallelSort(a, 0, a.length-1, 4);
//        obj1.displayArray();
//        System.out.println("----------------");
        long s1 = System.currentTimeMillis();
        obj1.sort();
        long e1 = System.currentTimeMillis();
        long s2 = System.currentTimeMillis();
        Arrays.sort(b);
        long e2 = System.currentTimeMillis();
        System.out.println((e1-s1)+" "+(e2-s2));
//        obj1.displayArray();
        System.out.println(obj1.isSorted());
    }
    
    public ParallelSort(T [] array){
        this.array = array;
        this.start = 0;
        this.end = array.length-1;
        this.numProc = Runtime.getRuntime().availableProcessors()-1;
    }
    
    public ParallelSort(T [] array, int start, int end){
        this(array);
        this.start = start;
        this.end = end;
    }
    
    public ParallelSort(T [] array, int start, int end, int numProc){
        this(array, start, end);
        this.numProc = numProc;
    }
    
    public void sort(){
        MergeEngine.setInstance(array, this);
        sortEngine = new SortEngine(array, start, end, numProc);
        sortEngine.start();
        waitForCompletion();
    }
    
    public void displayArray(){
        for(T i:array){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    
    public boolean isSorted(){
        for(int i=0; i<array.length-1; i++){
            if(array[i].compareTo(array[i+1])>0){
                return false;
            }
        }
        return true;
    }
    
    public void waitForCompletion(){
        synchronized(this){
            try{
                this.wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    
    public void notifyCompletion(){
        synchronized(this){
            this.notify();
        }
    }
}
