package weekly.w483.D;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Q4. Minimum Cost to Merge Sorted Lists
 *
 * https://leetcode.cn/contest/weekly-contest-483/problems/minimum-cost-to-merge-sorted-lists/
 *
 * You are given a 2D integer array lists, where each lists[i] is a non-empty
 * array of integers sorted in non-decreasing order.
 *
 * You may repeatedly choose two lists a = lists[i] and b = lists[j], where i != j,
 * and merge them. The cost to merge a and b is:
 *
 * len(a) + len(b) + abs(median(a) - median(b)), where len and median
 * denote the list length and median, respectively.
 *
 * After merging a and b, remove both a and b from lists and insert the new merged
 * sorted list in any position. Repeat merges until only one list remains.
 *
 * Return an integer denoting the minimum total cost required to merge all lists into one single sorted list.
 *
 * The median of an array is the middle element after sorting it in non-decreasing order.
 * If the array has an even number of elements, the median is the left middle element.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private static final int MAX_N = 13;
    private final int[] lens = new int[1 << MAX_N];

    public long minMergeCost(int[][] lists) {
        // 合并两个有序数组的代价是 len(a) + len(b) + abs(median(a) - median(b))
        //  - 在数组合并之后, 如何快速取到中位数

        // 预计算所有可能的组合的长度
        int n = lists.length;
        for (int i = 0; i < n; i++) {
            int curr = lists[i].length, mask = 1 << i;
            for (int j = 0; j < mask; j++) {
                lens[(1 << i) + j] = curr + lens[j];
            }
        }

        return mergeCost(lists, IntStream.range(0, n).map(v -> 1 << v).boxed().sorted().toList());
    }

    private final Map<String, Long> memo = new HashMap<>();

    private long mergeCost(int[][] lists, List<Integer> merged) {
        if (merged.size() == 1) return 0;
        String key = memoKey(merged);
        if (memo.containsKey(key)) return memo.get(key);

        long ans = Long.MAX_VALUE;
        for (int i = 0; i < merged.size(); i++) {
            int aMask = merged.get(i);
            for (int j = i + 1; j < merged.size(); j++) {
                int bMask = merged.get(j);

                int l1 = lens[aMask], l2 = lens[bMask];
                int m1 = median(lists, aMask, (l1 - 1) >> 1), m2 = median(lists, bMask, (l2 - 1) >> 1);
                long cost = (long) l1 + l2 + Math.abs(m1 - m2);
                ans = Math.min(ans, cost + mergeCost(lists, merge(merged, i, j)));
            }
        }

        memo.put(key, ans);
        return ans;
    }

    private String memoKey(List<Integer> merged) {
        return String.join(",", merged.stream().sorted().map(String::valueOf).toList());
    }

    private List<Integer> merge(List<Integer> merged, int a, int b) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < merged.size(); i++) {
            if (i != a && i != b) ans.add(merged.get(i));
        }
        ans.add(merged.get(a) | merged.get(b));
        ans.sort(Integer::compare);
        return ans;
    }

    private final int[] medianMemo = new int[1 << MAX_N];
    { Arrays.fill(medianMemo, -1); }

    private int median(int[][] lists, int mask, int k) {
        if ((mask & (mask - 1)) == 0) {
            return lists[31 - Integer.numberOfLeadingZeros(mask)][k];
        }
        if (medianMemo[mask] != -1) return medianMemo[mask];

        List<int[]> curr = new ArrayList<>();
        for (int i = 0; i < lists.length; i++) {
            if ((mask & (1 << i)) != 0) curr.add(lists[i]);
        }

        // 在值域上做二分查找
        return medianMemo[mask] = binarySearch(curr, k);
    }

    private static int binarySearch(List<int[]> curr, int k) {
        int l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;
        for (var v : curr) {
            l = Math.min(l, v[0]);
            r = Math.max(r, v[v.length - 1]);
        }

        while (l < r) {
            int mid = l + (r - l) / 2, count = 0;
            // 在每个数组中找到 <= mid 的数量有多少个
            for (var v : curr) {
                int f = Arrays.binarySearch(v, mid);
                if (f < 0) f = ~f;
                count += f;
            }

            if (count >= k) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    @SuppressWarnings("DuplicatedCode")
    private static class Iteration {
        public long minMergeCost(int[][] lists) {
            int n = lists.length, MAX_N = 1 << n;
            // 合并两个有序数组的代价是 len(a) + len(b) + abs(median(a) - median(b))
            //  - 在数组合并之后, 如何快速取到中位数

            // 预计算所有可能的组合的长度以及中位数
            int[] lens = new int[MAX_N];
            int[][] sorted = new int[MAX_N][];
            for (int i = 0; i < n; i++) {
                int curr = lists[i].length, mask = 1 << i;
                for (int j = 0; j < mask; j++) {
                    int fromLen = lens[j], mergedLen = lens[mask | j] = curr + fromLen;
                    if (sorted[j] != null) {
                        // 合并新的数组
                        int[] nums = new int[mergedLen];
                        for (int a = 0, b = 0, k = 0; a < curr || b < fromLen; ) {
                            if (a == curr || (b < fromLen && sorted[j][b] < lists[i][a])) nums[k++] = sorted[j][b++];
                            else nums[k++] = lists[i][a++];
                        }
                        sorted[mask | j] = nums;
                    } else sorted[mask | j] = lists[i].clone();
                }
            }

            // 转迭代, 逐步合并新的数组
            long[] dp = new long[MAX_N];
            for (int merges = 2; merges <= n; merges++) {
                for (int i = 0; i < MAX_N; i++) {
                    if (Integer.bitCount(i) != merges) continue;

                    dp[i] = Long.MAX_VALUE;
                    // 现在需要从 i 中选一个 a 合并到 i ^ a
                    for (var a = (i - 1) & i; a > 0; a = (a - 1) & i) {
                        int la = lens[a], lb = lens[a ^ i];
                        dp[i] = Math.min(dp[i], dp[a] + dp[a ^ i] + la + lb + Math.abs(sorted[a][(la - 1) >> 1] - sorted[a ^ i][(lb - 1) >> 1]));
                    }
                }
            }

            return dp[(1 << n) - 1];
        }
    }

    public static void main(String[] args) {
        assert new Iteration().minMergeCost(new int[][]{{7,7},{1,4},{9}}) == 16;
        assert new Iteration().minMergeCost(new int[][]{{7,7,9},{1,5},{3,7,9}}) == 19;
        assert new Iteration().minMergeCost(new int[][]{{1,3,5},{2,4},{6,7,8}}) == 18;

        assert new Solution().minMergeCost(new int[][]{{1,3,5},{2,4},{6,7,8}}) == 18;
    }

}
