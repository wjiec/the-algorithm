package weekly.w489.D;

import ability.Benchmark;
import common.Preposing;
import common.Tag;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Q4. Maximum Subarray XOR with Bounded Range
 *
 * https://leetcode.cn/contest/weekly-contest-489/problems/maximum-subarray-xor-with-bounded-range/
 *
 * You are given a non-negative integer array nums and an integer k.
 *
 * You must select a subarray of nums such that the difference between its maximum and minimum elements is at most k.
 * The value of this subarray is the bitwise XOR of all elements in the subarray.
 *
 * Return an integer denoting the maximum possible value of the selected subarray.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int maxXor(int[] nums, int k) {
        int n = nums.length;
        // 子数组的异或值可以用过前缀和实现, 因为异或是可逆操作的
        int[] xor = new int[n + 1], or = new int[n + 1];
        for (int i = 0; i < n; i++) {
            xor[i + 1] = xor[i] ^ nums[i];
            or[i + 1] = or[i] | nums[i];
        }
        // 由于没有更新操作, 子数组的最大最小值可以通过 ST 表实现

        int ans = 0;
        // 枚举子数组会达到 1e9, 使用滑动窗口和最大/最小单调队列找到符合条件的子数组左右区间
        Deque<Integer> mxq = new ArrayDeque<>(n), mnq = new ArrayDeque<>(n);
        for (int l = 0, r = 0; r < n; r++) {
            while (!mxq.isEmpty() && nums[mxq.getLast()] < nums[r]) mxq.removeLast();
            while (!mnq.isEmpty() && nums[mnq.getLast()] > nums[r]) mnq.removeLast();
            mxq.addLast(r); mnq.addLast(r);

            // 检查区间内是否满足
            while (nums[mxq.getFirst()] - nums[mnq.getFirst()] > k) {
                l++;
                while (!mxq.isEmpty() && mxq.getFirst() < l) mxq.removeFirst();
                while (!mnq.isEmpty() && mnq.getFirst() < l) mnq.removeFirst();
            }


            // 此时 [l, r] 区间内都是满足条件的子数组, 我们需要找到最大值
            //  - 在枚举区间中的所有子数组的时候, r = r - 1 的位置之前已经枚举过了
            //
            // 同时如果当前值与前一个位置的值一样, 那么在枚举的时候是没有必要的
            //
            // 如果 or(nums[0..r]) 的值已经 <= ans 了, 那么就没啥必要继续枚举了
            //  - 因为 xor 的值不可能比 or 更大
            if (r > 0 && nums[r] == nums[r - 1] || (or[r + 1] <= ans)) continue;
            for (int i = l; i <= r; i++) ans = Math.max(ans, xor[r + 1] ^ xor[i]);
        }
        return ans;
    }

    private static class Optimization {
        private static final int MAX_BIT_WIDTH = 15;

        private static class Trie {
            private int leaf = 0; // 叶子节点的数量
            private final Trie[] children = new Trie[2];
            public void insert(int v) {
                Trie curr = this;
                for (int i = MAX_BIT_WIDTH - 1; i >= 0; i--) {
                    int b = (v >> i) & 1;
                    if (curr.children[b] == null) curr.children[b] = new Trie();
                    (curr = curr.children[b]).leaf++;
                }
            }
            public void remove(int v) {
                Trie curr = this;
                for (int i = MAX_BIT_WIDTH - 1; i >= 0; i--) {
                    (curr = curr.children[(v >> i) & 1]).leaf--;
                }
            }

            // 找到和 v 的最大异或值
            public int max(int v) {
                int ans = 0; Trie curr = this;
                for (int i = MAX_BIT_WIDTH - 1; i >= 0; i--) {
                    int b = (v >> i) & 1;
                    // 与当前位不同才能是最佳的匹配
                    if (curr.children[b ^ 1] != null && curr.children[b ^ 1].leaf > 0) {
                        ans |= 1 << i; b ^= 1; // 需要切换路径
                    }
                    curr = curr.children[b]; // npe?
                }
                return ans;
            }
        }

        @Tag("子数组最大异或和")
        @Preposing(offer2.p67.Solution.class)
        public int maxXor(int[] nums, int k) {
            int n = nums.length;
            // 子数组的异或值可以用过前缀和实现, 因为异或是可逆操作的
            int[] s = new int[n + 1];
            for (int i = 0; i < n; i++) s[i + 1] = s[i] ^ nums[i];
            // 令 s 为数组的前缀异或和
            //  - 那么滑动窗口在枚举 r 的时候, 我们需要枚举 i 在 [l, r] 中找到最大 xor(nums[i..r])
            //
            // 这个过程实际上就是找到 s[r + 1] 和 s[l..r] 区间之间的最大异或值
            //  - 可以参考 p421 的做法: 使用二进制字典树来实现
            //
            // 从最高位往下, 如果 s[r + 1] 的当前位是 1, 那么我们需要一个 0 与之匹配, 剩下的位递归处理即可

            int ans = 0; Trie root = new Trie();
            Deque<Integer> mxq = new ArrayDeque<>(n), mnq = new ArrayDeque<>(n);
            for (int l = 0, r = 0; r < n; r++) {
                while (!mxq.isEmpty() && nums[mxq.getLast()] < nums[r]) mxq.removeLast();
                while (!mnq.isEmpty() && nums[mnq.getLast()] > nums[r]) mnq.removeLast();
                mxq.addLast(r); mnq.addLast(r); root.insert(s[r]);

                // 检查区间内是否满足
                while (nums[mxq.getFirst()] - nums[mnq.getFirst()] > k) {
                    root.remove(s[l++]);
                    while (!mxq.isEmpty() && mxq.getFirst() < l) mxq.removeFirst();
                    while (!mnq.isEmpty() && mnq.getFirst() < l) mnq.removeFirst();
                }

                ans = Math.max(ans, root.max(s[r + 1]));
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("Optimization", () -> {
            assert new Optimization().maxXor(new int[]{5,4,5,6}, 1) == 6;
        });

        assert new Solution().maxXor(new int[]{5,4,5,6}, 1) == 6;
    }

}
