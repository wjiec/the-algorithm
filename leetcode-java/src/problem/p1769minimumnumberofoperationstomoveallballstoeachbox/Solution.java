package problem.p1769minimumnumberofoperationstomoveallballstoeachbox;

import common.Checker;
import common.Tag;

/**
 * 1769. Minimum Number of Operations to Move All Balls to Each Box
 *
 * https://leetcode.cn/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/
 *
 * You have n boxes. You are given a binary string boxes of length n, where
 * boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.
 *
 * In one operation, you can move one ball from a box to an adjacent box.
 * Box i is adjacent to box j if abs(i - j) == 1. Note that after doing so, there
 * may be more than one ball in some boxes.
 *
 * Return an array answer of size n, where answer[i] is the minimum number of operations
 * needed to move all the balls to the ith box.
 *
 * Each answer[i] is calculated considering the initial state of the boxes.
 */

public class Solution {

    @Tag({"移动次数"})
    public int[] minOperations(String boxes) {
        char[] chars = boxes.toCharArray();
        int cnt = 0, total = 0, n = boxes.length();
        for (int i = 0; i < n; i++) {
            if (chars[i] == '1') { cnt++; total += i; }
        }

        int pCnt = 0, pSum = 0;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            // 后面的所有 1 移动到当前位置需要操作 total 次
            // 前面有 pCurr 个 1 移动需要移动到当前位置
            ans[i] = total + pSum;

            // 前面的所有 1 往右移动一位
            if (chars[i] == '1') pCnt++;
            pSum += pCnt;

            // 后面的所有 1 少移动一位
            if (chars[i] == '1') cnt--;
            total -= cnt;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().minOperations("110"), new int[]{1, 1, 3});
        assert Checker.check(new Solution().minOperations("001011"), new int[]{11,8,5,4,3,4});
    }

}
