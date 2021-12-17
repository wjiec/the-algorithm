package offer.p57heweisdelianxuzhengshuxulielcof;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 57 - II. 和为s的连续正数序列
 *
 * https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/
 *
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 */

public class Solution {

    public int[][] findContinuousSequence(int target) {
        List<int[]> vec = new ArrayList<>();
        for (int l = 1, r = 2; l < r;) {
            int sum = (l + r) * (r - l + 1) / 2;
            if (sum == target) {
                int[] res = new int[r - l + 1];
                for (int i = l; i <= r; ++i) {
                    res[i - l] = i;
                }
                vec.add(res);
                l++;
            } else if (sum < target) {
                r++;
            } else {
                l++;
            }
        }
        return vec.toArray(new int[vec.size()][]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().findContinuousSequence(9)));
        System.out.println(Arrays.deepToString(new Solution().findContinuousSequence(15)));
    }

}
