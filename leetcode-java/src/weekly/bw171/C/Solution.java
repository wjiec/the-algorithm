package weekly.bw171.C;

import java.util.ArrayList;
import java.util.List;

/**
 * Q3. Maximize Points After Choosing K Tasks
 *
 * https://leetcode.cn/contest/biweekly-contest-171/problems/maximize-points-after-choosing-k-tasks/
 *
 * You are given two integer arrays, technique1 and technique2, each of length n,
 * where n represents the number of tasks to complete.
 *
 * If the ith task is completed using technique 1, you earn technique1[i] points.
 * If it is completed using technique 2, you earn technique2[i] points.
 * You are also given an integer k, representing the minimum number of tasks that must be completed using technique 1.
 *
 * You must complete at least k tasks using technique 1 (they do not need to be the first k tasks).
 *
 * The remaining tasks may be completed using either technique.
 *
 * Return an integer denoting the maximum total points you can earn.
 */

public class Solution {

    public long maxPoints(int[] technique1, int[] technique2, int k) {
        // 至少使用 technique1 中的 k 个数, 问能得到的最大总分是多少
        //  - 如果 technique1 >= technique2, 那么就可以完成一个要求
        //
        // 否则我们需要在剩余的 technique2 > technique1 的情况下中选出 k' 个使得对答案的影响最小
        //  - 也就是找到 k' 个最小的 technique2 - technique1 (损失)
        long ans = 0; int n = technique1.length;
        List<Integer> loss = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (technique1[i] >= technique2[i]) {
                k--; ans += technique1[i];
            } else {
                ans += technique2[i];
                loss.add(technique2[i] - technique1[i]);
            }
        }

        // 剩下的都是 technique2 >= technique1 的情况
        loss.sort(Integer::compare);
        for (int i = 0; i < k; i++) ans -= loss.get(i);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxPoints(new int[]{5, 2, 10}, new int[]{10, 3, 8}, 2) == 22;
        assert new Solution().maxPoints(new int[]{10, 20, 30}, new int[]{5, 15, 25}, 2) == 60;
        assert new Solution().maxPoints(new int[]{1, 2, 3}, new int[]{4, 5, 6}, 0) == 15;
    }

}
