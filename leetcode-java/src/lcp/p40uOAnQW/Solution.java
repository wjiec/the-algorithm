package lcp.p40uOAnQW;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LCP 40. 心算挑战
 *
 * https://leetcode-cn.com/problems/uOAnQW/
 *
 * 「力扣挑战赛」心算项目的挑战比赛中，要求选手从 N 张卡牌中选出 cnt 张卡牌，
 * 若这 cnt 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 cnt 张卡牌数字总和。
 *
 * 给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。
 *
 * 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。
 */

public class Solution {

    public int maxmiumScore(int[] cards, int cnt) {
        int l = cards.length, even = 0;
        for (var card : cards) if (card % 2 == 0) even++;

        Arrays.sort(cards);
        int[] sEven = new int[even + 1], sOdd = new int[l - even + 1];
        for (int o = 1, e = 1, i = cards.length - 1; i >= 0; i--) {
            if (cards[i] % 2 == 0) {
                sEven[e] = cards[i] + sEven[e - 1];
                e++;
            } else {
                sOdd[o] = cards[i] + sOdd[o - 1];
                o++;
            }
        }

        int ans = 0;
        for (int i = 0; i < sOdd.length; i += 2) {
            if (0 <= cnt - i && cnt - i < sEven.length) {
                ans = Math.max(ans, sOdd[i] + sEven[cnt - i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxmiumScore(new int[]{1,2,8,9}, 3) == 18;
        assert new Solution().maxmiumScore(new int[]{3,3,1}, 1) == 0;
    }

}
