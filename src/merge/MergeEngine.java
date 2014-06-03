package merge;

import java.util.ArrayList;
import java.util.Arrays;
import sort.ParallelSort;

public class MergeEngine<T extends Comparable> {
    
    private static MergeEngine mergeObj = null;
    private int dataSize = 0;
    private T [] array;
    private int count = 0;
    private ArrayList<Integer> s1;
    private ArrayList<Integer> e1;
    private ArrayList<Integer> s2;
    private ArrayList<Integer> e2;
    private ParallelSort obj;
    
    public MergeEngine(T [] array, ParallelSort obj){
        this.array = array;
        this.obj = obj;
        dataSize = array.length;
    }
    
    public static <T extends Comparable> void setInstance(T [] array, ParallelSort obj){
        mergeObj = new MergeEngine(array, obj);
    }
    
    public static MergeEngine getInstance(){
        return mergeObj;
    }
    
    public synchronized void insertThread(ArrayList<Integer> start, ArrayList<Integer> end){        
        if(start.size() == 1 && end.size()==1 && end.get(0)-start.get(0)+1 == dataSize){
            mergeObj.finish();
            return;
        }
        count++;
        if(count == 1){
            s1 = start;
            e1 = end;
        }
        if(count == 2){
            s2 = start;
            e2 = end;
            new Merge(array, s1, e1, s2, e2).start();
            count = 0;
        }
    }
    
    public void finish(){
        obj.notifyCompletion();
    }
}
