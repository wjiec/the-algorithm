package weekly.w491.D;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Q4. Count Subarrays With K Distinct Integers
 *
 * https://leetcode.cn/contest/weekly-contest-491/problems/count-subarrays-with-k-distinct-integers/
 *
 * You are given an integer array nums and two integers k and m.
 *
 * Return an integer denoting the count of subarrays of nums such that:
 *
 * The subarray contains exactly k distinct integers.
 * Within the subarray, each distinct integer appears at least m times.
 */

public class Solution {

    private static class FreqFreq {
        private int size = 0;
        private final int[] tree; // 记录每个频率的出现次数
        private final Map<Integer, Integer> freq = new HashMap<>(); // 记录每个数出现的频率
        public FreqFreq(int n) { tree = new int[n + 1]; }
        private final TreeMap<Integer, Integer> f1 = new TreeMap<>();
        public void add(int v) {
            int f = freq.merge(v, 1, Integer::sum);
            if (f == 1) size++;
            update(f - 1, -1); update(f, 1);
        }
        public void remove(int v) {
            int f = freq.merge(v, -1, Integer::sum);
            if (f == 0) size--;
            update(f + 1, -1); update(f, 1);
        }
        public int query(int v) {
            int ans = 0;
            for (; v > 0; v -= v & -v) ans += tree[v];
            return ans;
        }
        private void update(int f, int v) { for (; f > 0 && f < tree.length; f += f & -f) tree[f] += v; }
    }

    public long countSubarrays(int[] nums, int k, int m) {
        long ans = 0; int n = nums.length;
        FreqFreq freqL = new FreqFreq(n);
        FreqFreq freqR = new FreqFreq(n);
        for (int l1 = 0, l2 = 0, r = 0; r < n; r++) {
            // 右边元素进入窗口
            freqL.add(nums[r]); freqR.add(nums[r]);
            // 如果窗口内的整数数量超过了 k 种, 则需要移除元素使得满足要求
            while (freqL.size > k) {
                freqL.remove(nums[l1++]);
                if (l2 < l1) freqR.remove(nums[l2++]);
            }
            // 跳出循环之后, 窗口内恰好存在 k 种整数, 开始处理第二个滑动窗口使得恰好存在小于等于 m 次的整数
            //  - 也就是次数 freq >= m, 我们查找是否有 < m 的频率出现
            while (l2 < n && freqR.size == k && freqR.query(m - 1) == 0) {
                // 但是我们要保证 freqR 的整数数量等于 k
                freqR.remove(nums[l2++]);
            }

            // 此时 [l1, l2) 之间的就是满足条件的子数组
            if (freqL.size == k) ans += l2 - l1;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countSubarrays(new int[]{1,2,1,2,2}, 2, 2) == 2;
        assert new Solution().countSubarrays(new int[]{3,1,2,4}, 2, 1) == 3;
    }

}
