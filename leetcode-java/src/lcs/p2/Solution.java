package lcs.p2;

import java.util.Arrays;

/**
 * LCS 02. 完成一半题目
 *
 * https://leetcode-cn.com/problems/WqXACV/
 *
 * 有 N 位扣友参加了微软与力扣举办了「以扣会友」线下活动。主办方提供了 2*N 道题目，
 * 整型数组 questions 中每个数字对应了每道题目所涉及的知识点类型。
 *
 * 若每位扣友选择不同的一题，请返回被选的 N 道题目至少包含多少种知识点类型。
 */

public class Solution {

    public int halfQuestions(int[] questions) {
        int[] map = new int[1001];
        for (var question : questions) map[question]++;

        Arrays.sort(map);
        int ans = 0, l = questions.length / 2;
        for (int i = map.length - 1; i >= 0; i--) {
            if (map[i] != 0) {
                ans++;
                l -= map[i];

                if (l <= 0) break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().halfQuestions(new int[]{2,1,6,2}) == 1;
        assert new Solution().halfQuestions(new int[]{1,5,1,3,4,5,2,5,3,3,8,6}) == 2;
    }

}
