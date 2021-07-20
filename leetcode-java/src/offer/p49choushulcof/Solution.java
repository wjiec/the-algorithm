package offer.p49choushulcof;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 剑指 Offer 49. 丑数
 *
 * https://leetcode-cn.com/problems/chou-shu-lcof/
 *
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 */

public class Solution {

    public int nthUglyNumber(int n) {
        Set<Long> numbers = new HashSet<>();
        PriorityQueue<Long> heap = new PriorityQueue<>();

        heap.add(1L);
        numbers.add(1L);

        long current = 0;
        long[] factors = new long[]{2, 3, 5};
        for (int i = 0; i < n; i++) {
            current = heap.remove();
            for (var factor : factors) {
                long next = current * factor;
                if (numbers.add(next)) heap.add(next);
            }
        }
        return (int) current;
    }

    public static void main(String[] args) {
        assert new Solution().nthUglyNumber(10) == 12;
        assert new Solution().nthUglyNumber(1) == 1;
    }

}
