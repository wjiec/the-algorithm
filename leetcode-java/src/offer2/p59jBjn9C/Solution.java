package offer2.p59jBjn9C;

import java.util.PriorityQueue;

/**
 * 剑指 Offer II 059. 数据流的第 K 大数值
 *
 * https://leetcode-cn.com/problems/jBjn9C/
 *
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 *
 * 请实现 KthLargest 类：
 *
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 */

public class Solution {

    private static class KthLargest {
        private final int k;
        private final PriorityQueue<Integer> queue;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            queue = new PriorityQueue<>();
            for (var n : nums) {
                queue.add(n);
            }
        }

        public int add(int val) {
            queue.add(val);
            while (queue.size() > k) queue.remove();
            return queue.peek();
        }
    }

    public static void main(String[] args) {
        KthLargest largest = new KthLargest(3, new int[]{4, 5, 8, 2});
        assert largest.add(3) == 4;
        assert largest.add(5) == 5;
        assert largest.add(10) == 5;
        assert largest.add(9) == 8;
        assert largest.add(4) == 8;
    }

}
