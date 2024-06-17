package weekly.w402.D;

import java.util.ArrayList;
import java.util.List;

/**
 * 3187. Peaks in Array
 *
 * https://leetcode.cn/contest/weekly-contest-402/problems/peaks-in-array/
 *
 * A peak in an array arr is an element that is greater than its previous and next element in arr.
 *
 * You are given an integer array nums and a 2D integer array queries.
 *
 * You have to process queries of two types:
 *
 * queries[i] = [1, li, ri], determine the count of peak elements in the subarray nums[li..ri].
 * queries[i] = [2, indexi, vali], change nums[indexi] to vali.
 *
 * Return an array answer containing the results of the queries of the first type in order.
 *
 * Notes:
 * The first and the last element of an array or a subarray cannot be a peak.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public List<Integer> countOfPeaks(int[] nums, int[][] queries) {
        for (int i = 1; i < nums.length - 1; i++) {
            if (isPeak(nums, i)) update(i, 1);
        }

        List<Integer> ans = new ArrayList<>();
        for (var q : queries) {
            int a = q[1], b = q[2];
            switch (q[0]) {
                case 1 -> {
                    ans.add(query(a + 1, b - 1));
                }
                case 2 -> {
                    nums[a] = b;
                    if (contains(a - 1) && !isPeak(nums, a - 1)) update(a - 1, -1);
                    if (!contains(a - 1) && isPeak(nums, a - 1)) update(a - 1, 1);
                    if (contains(a + 1) && !isPeak(nums, a + 1)) update(a + 1, -1);
                    if (!contains(a + 1) && isPeak(nums, a + 1)) update(a + 1, 1);
                    if (!contains(a) && isPeak(nums, a)) update(a, 1);
                    if (contains(a) && !isPeak(nums, a)) update(a, -1);
                }
            }
        }

        return ans;
    }

    private final int[] tree = new int[100_100];
    private void update(int x, int v) { for (; x < tree.length; x += lowbit(x)) tree[x] += v; }
    private int query(int a, int b) { return b >= a ? query(b) - query(a - 1) : 0; }
    private boolean contains(int x) { return query(x, x) == 1; }

    private int query(int x) {
        int ans = 0;
        while (x > 0) {
            ans += tree[x];
            x -= lowbit(x);
        }
        return ans;
    }

    private static int lowbit(int x) { return x & -x; }

    private boolean isPeak(int[] array, int i) {
        if (i <= 0 || i >= array.length - 1) return false;
        return array[i] > array[i - 1] && array[i] > array[i + 1];
    }

    public static void main(String[] args) {
        assert new Solution().countOfPeaks(new int[]{9,10,10,9,10}, new int[][]{{2,0,1}, {2,2,7}, {1,0,2}}).equals(List.of(1));
        assert new Solution().countOfPeaks(new int[]{5,4,8,6}, new int[][]{{1,2,2}, {1,1,2}, {2,1,6}}).equals(List.of(0,0));
        assert new Solution().countOfPeaks(new int[]{7,10,7}, new int[][]{{1,2,2}, {2,0,6}, {1,0,2}}).equals(List.of(0,1));
    }

}
