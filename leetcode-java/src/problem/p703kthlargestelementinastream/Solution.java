package problem.p703kthlargestelementinastream;

import java.util.PriorityQueue;

/**
 * 703. Kth Largest Element in a Stream
 *
 * Design a class to find the kth largest element in a stream.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Implement KthLargest class:
 *
 * KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
 * int add(int val) Returns the element representing the kth largest element in the stream.
 */

public class Solution {

    static class KthLargest {

        private int k;
        private PriorityQueue<Integer> pq;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.pq = new PriorityQueue<>();
            for (var n : nums) {
                add(n);
            }
        }

        public int add(int val) {
            pq.add(val);
            if (pq.size() > k) {
                pq.poll();
            }
            return pq.peek();
        }

    }

    public static void main(String[] args) {
        KthLargest kth = new KthLargest(3, new int[]{4, 5, 8, 2});
        assert kth.add(3) == 4;
        assert kth.add(5) == 5;
        assert kth.add(10) == 5;
        assert kth.add(9) == 8;
        assert kth.add(4) == 8;
    }

}
