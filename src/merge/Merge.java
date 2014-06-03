package merge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Merge<T extends Comparable> extends Thread {

    T[] array;
    ArrayList<Integer> start1;
    ArrayList<Integer> end1;
    ArrayList<Integer> start2;
    ArrayList<Integer> end2;


    public Merge(T[] array, ArrayList<Integer> s1, ArrayList<Integer> e1, ArrayList<Integer> s2, ArrayList<Integer> e2) {
        this.array = array;
        start1 = s1;
        end1 = e1;
        start2 = s2;
        end2 = e2;
        Collections.sort(start1);
        Collections.sort(end1);
        Collections.sort(start2);
        Collections.sort(end2);
    }

    public void run() {
        merge();
    }

    public void merge() {
        int len1 = start1.size(), len2 = start2.size();
        ArrayList<T> newarr = new ArrayList<T>();
        int ctr1 = 0, ctr2 = 0;
        int ptr1 = 0, ptr2 = 0;
        boolean flag1 = true, flag2 = true;
        while (ctr1 < len1 && ctr2 < len2) {
            int s1 = start1.get(ctr1);
            int e1 = end1.get(ctr1);
            int s2 = start2.get(ctr2);
            int e2 = end2.get(ctr2);
            if (flag1) {
                ptr1 = s1;
            }
            if (flag2) {
                ptr2 = s2;
            }
            flag1 = false;
            flag2 = false;
            while (ptr1 <= e1 && ptr2 <= e2) {
                if (array[ptr1].compareTo(array[ptr2]) < 0) {
                    newarr.add(array[ptr1]);
                    ptr1++;
                } else {
                    newarr.add(array[ptr2]);
                    ptr2++;
                }
            }
            if (ptr1 > e1) {
                ctr1++;
                flag1 = true;
            } else if (ptr2 > e2) {
                ctr2++;
                flag2 = true;
            }
        }
        flag1 = false;
        flag2 = false;
        while (ctr1 < len1) {
            int s1 = start1.get(ctr1);
            int e1 = end1.get(ctr1);
            if(flag1){
                ptr1 = s1;
            }
            flag1=true;
            while (ptr1 <= e1) {
                newarr.add(array[ptr1]);
                ptr1++;
            }
            ctr1++;
        }
        while (ctr2 < len2) {
            int s2 = start2.get(ctr2);
            int e2 = end2.get(ctr2);
            if(flag2){
                ptr2 = s2;
            }
            flag2=true;
            while (ptr2 <= e2) {
                newarr.add(array[ptr2]);
                ptr2++;
            }
            ctr2++;
        }
        start1.addAll(start2);
        Collections.sort(start1);
        end1.addAll(end2);
        Collections.sort(end1);
        Iterator<T> newit = newarr.iterator();
        Iterator<Integer> startit = start1.iterator();
        Iterator<Integer> endit = end1.iterator();
        while (startit.hasNext() && endit.hasNext()) {
            int s1 = startit.next();
            int e1 = endit.next();
            for (int j = s1; j <= e1; j++) {
                array[j] = newit.next();
            }
        }
        sanitize();
        MergeEngine.getInstance().insertThread(start1, end1);
    }
    
    public void sanitize(){
        ArrayList<Integer> start = new ArrayList<Integer>();
        ArrayList<Integer> end = new ArrayList<Integer>();
        for(int i=0; i<start1.size(); i++){
            start.add(start1.get(i));
            while(i<start1.size()-1 && start1.get(i+1)-end1.get(i) == 1){
                i++;
            }
            end.add(end1.get(i));
        }
        start1 = start;
        end1 = end;
    }
}
