package weekly.bw173.C;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Q3. Find Maximum Value in a Constrained Sequence
 *
 * https://leetcode.cn/contest/biweekly-contest-173/problems/find-maximum-value-in-a-constrained-sequence/
 *
 * You are given an integer n, a 2D integer array restrictions, and an integer array diff of length n - 1.
 * Your task is to construct a sequence of length n, denoted by a[0], a[1], ..., a[n - 1], such that
 * it satisfies the following conditions:
 *
 * a[0] is 0.
 *
 * All elements in the sequence are non-negative.
 *
 * For every index i (0 <= i <= n - 2), abs(a[i] - a[i + 1]) <= diff[i].
 *
 * For each restrictions[i] = [idx, maxVal], the value at position idx in the sequence
 * must not exceed maxVal (i.e., a[idx] <= maxVal).
 *
 * Your goal is to construct a valid sequence that maximizes the largest value within
 * the sequence while satisfying all the above conditions.
 *
 * Return an integer denoting the largest value present in such an optimal sequence.
 */

public class Solution {

    public int findMaxVal(int n, int[][] restrictions, int[] diff) {
        Arrays.sort(restrictions, Comparator.comparing(r -> r[0]));
        // 对于每一个位置 i, 需要满足 abs(a[i] - a[i + 1]) <= diff[i] && a[i] <= r
        //  - a[0] 必须为 0
        //
        // 直接记录每个位置的 [0, upper] 可取值的区间
        int[] uppers = new int[n]; Arrays.fill(uppers, Integer.MAX_VALUE);
        for (var restriction : restrictions) uppers[restriction[0]] = restriction[1];

        // 从左往右扫描计算最后一个值的取值区间
        int[] array = new int[n];
        for (int i = 0; i < diff.length; i++) {
            array[i + 1] = Math.min(array[i] + diff[i], uppers[i + 1]);
        }

        // 再从右往左枚举所有的约束
        for (int i = diff.length - 1; i > 0; i--) {
            array[i] = Math.min(array[i], array[i + 1] + diff[i]);
        }

        int ans = 0;
        for (var v : array) ans = Math.max(ans, v);
        return ans;
    }

    public static void main(String[] args) {
    }

}
