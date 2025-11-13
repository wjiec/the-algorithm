package weekly.w468.C;

import ability.Benchmark;

import java.util.*;

/**
 * Q3. Split and Merge Array Transformation
 *
 * https://leetcode.cn/contest/weekly-contest-468/problems/split-and-merge-array-transformation/
 *
 * You are given two integer arrays nums1 and nums2, each of length n.
 *
 * You may perform the following split-and-merge operation on nums1 any number of times:
 *
 * Choose a subarray nums1[L..R].
 *
 * Remove that subarray, leaving the prefix nums1[0..L-1] (empty if L = 0)
 * and the suffix nums1[R+1..n-1] (empty if R = n - 1).
 *
 * Re-insert the removed subarray (in its original order) at any position
 * in the remaining array (i.e., between any two elements, at the very start, or at the very end).
 *
 * Return the minimum number of split-and-merge operations needed to transform nums1 into nums2.
 */

public class Solution {

    public int minSplitMerge(int[] nums1, int[] nums2) {
        if (equals(nums1, nums2)) return 0;

        Set<Integer> uniq = new HashSet<>();
        for (var v : nums1) uniq.add(v);

        List<Integer> sorted = new ArrayList<>(uniq);
        sorted.sort(Integer::compare);

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = Collections.binarySearch(sorted, nums1[i]);
            nums2[i] = Collections.binarySearch(sorted, nums2[i]);
        }

        // 长度最多只有 6 个, 直接暴力枚举
        dfs(nums1, nums2, 0);
        return ans;
    }

    private int ans = INF;
    private static final int INF = Integer.MAX_VALUE / 2;
    private final int[] seen = new int[1 << 18];
    { Arrays.fill(seen, INF); }

    private void dfs(int[] s1, int[] s2, int curr) {
        if (curr >= s1.length) return;
        if (equals(s1, s2)) { ans = Math.min(ans, curr); return; }

        int key = 0;
        for (var v : s1) key = (key << 3) | v;
        if (curr >= seen[key]) return; else seen[key] = curr;

        // 暴力枚举将每一个子数组进行交换
        for (int l = 0; l < s1.length; l++) {
            for (int r = l + 1; r <= s1.length; r++) {
                for (var next : move(s1, l, r)) {
                    dfs(next, s2, curr + 1);
                }
            }
        }
    }

    private boolean equals(int[] s1, int[] s2) {
        for (int i = 0; i < s1.length; i++) {
            if (s1[i] != s2[i]) return false;
        }
        return true;
    }

    // 将 [l, r) 移动到之后的所有可能的地方
    private List<int[]> move(int[] nums, int l, int r) {
        List<int[]> ans = new ArrayList<>();
        for (int insert = r; insert < nums.length; insert++) {
            ans.add(move(nums, l, r, insert));
        }
        return ans;
    }

    // 将 [l, r) 移动到原数组的位置 k 之后
    private int[] move(int[] nums, int l, int r, int k) {
        // 我们需要保持 [r, k) 之间的所有元素, 然后填充 [l, r), 最后则是 [k + r - l, n)
        int[] ans = nums.clone(); int i = l, n = nums.length;
        for (int j = r; j <= k; j++) ans[i++] = nums[j];
        for (int j = l; j < r; j++) ans[i++] = nums[j];
        for (int j = k + 1; j < n; j++) ans[i++] = nums[j];
        return ans;
    }

    public static void main(String[] args) {
        Benchmark.benchmark("", () -> {
            assert new Solution().minSplitMerge(new int[]{1,1,2,3,4,5}, new int[]{5,4,3,2,1,1}) == 3;
        });
    }

}

