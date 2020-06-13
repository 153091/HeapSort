/** %java HeapSort.java input.txt
 Note: HeapSort.java uses unchecked or unsafe operations.
 Note: Recompile with -Xlint:unchecked for details.
 A E E L M O P R S T X
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class HeapSort {

    private static Comparator comparator;

    public static void sort(Object[] pq) {
        int n = pq.length;

        // structure
        for (int k = n/2; k>=1; k--) sink(pq, k, n);

        //sortdown
        while (n >1 ) {
            exch(pq, 1, n--);
            sink(pq, 1, n);
        }
    }


/**
 * Heap helper functions
 * */

    private static void sink(Object[] pq, int k, int n) {
        while (2*k<n) {
            int j = k*2;
            if (j<n && less(pq, j, j+1)) j++;
            if (!less(pq , k, j)) break;
            exch(pq, j, k);
            k = j;
        }
    }


/**
 * Array helper functions
 * */

    //индексы уменьшены на 1, так как отсчет идет не с 1, а с 0, в отличии от PQ
    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    //индексы уменьшены на 1, так как отсчет идет не с 1, а с 0, в отличии от PQ
    private static boolean less(Object[] pq, int i, int j) {
        if (comparator == null) return ((Comparable<Object>) pq[i-1]).compareTo(pq[j-1])<0;
        else return comparator.compare(pq[i-1], pq[j-1])<0;
    }


/**
 * Version for Comparator
 * */
    public static void sort(Object[] pq, Comparator comparator) {
        HeapSort.comparator = comparator;

        int n = pq.length;

        // structure
        for (int k = n/2 ; k>=1; k++) sink(pq, k, n);

        //sortdown
        while (n>1) {
            exch(pq, 1, n--);
            sink(pq, 1, n);
        }

    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] pq = in.readAllStrings();
        sort(pq);

        for (String s : pq)
            StdOut.print(" " +s);
    }
}
