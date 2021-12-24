package offer2.p42H8086Q;

import java.util.PriorityQueue;

/**
 * 剑指 Offer II 042. 最近请求次数
 *
 * https://leetcode-cn.com/problems/H8086Q/
 *
 * 写一个 RecentCounter 类来计算特定时间范围内最近的请求。
 *
 * 请实现 RecentCounter 类：
 *
 * RecentCounter() 初始化计数器，请求数为 0 。
 * int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，
 *  并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，
 *  返回在 [t-3000, t] 内发生的请求数。
 *
 * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
 */

public class Solution {

    private static class RecentCounter {
        private final PriorityQueue<Integer> queue = new PriorityQueue<>();

        public RecentCounter() {}

        public int ping(int t) {
            queue.add(t);
            while (!queue.isEmpty() && queue.peek() < t - 3000) queue.remove();
            return queue.size();
        }
    }

    public static void main(String[] args) {
        RecentCounter counter = new RecentCounter();
        assert counter.ping(1) == 1;
        assert counter.ping(100) == 2;
        assert counter.ping(3001) == 3;
        assert counter.ping(3002) == 3;
    }

}
