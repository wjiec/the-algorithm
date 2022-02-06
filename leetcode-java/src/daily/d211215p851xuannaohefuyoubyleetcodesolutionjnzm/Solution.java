package daily.d211215p851xuannaohefuyoubyleetcodesolutionjnzm;

import common.Checker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 851. Loud and Rich
 *
 * https://leetcode-cn.com/problems/loud-and-rich/
 *
 * There is a group of n people labeled from 0 to n - 1 where each person has a different
 * amount of money and a different level of quietness.
 *
 * You are given an array richer where richer[i] = [ai, bi] indicates that ai has more
 * money than bi and an integer array quiet where quiet[i] is the quietness of the ith person.
 *
 * All the given data in richer are logically correct (i.e., the data will not lead you to a situation
 * where x is richer than y and y is richer than x at the same time).
 *
 * Return an integer array answer where answer[x] = y if y is the least quiet person (that is,
 * the person y with the smallest value of quiet[y]) among all people who definitely have
 * equal to or more money than the person x.
 */

public class Solution {

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < quiet.length; i++) map.add(new ArrayList<>());
        for (var rich : richer) map.get(rich[1]).add(rich[0]);

        int[] ans = new int[quiet.length];
        Arrays.fill(ans, -1);

        for (int i = 0; i < quiet.length; i++) {
            dfs(i, quiet, map, ans);
        }
        return ans;
    }

    private void dfs(int i, int[] quite, List<List<Integer>> map, int[] ans) {
        if (ans[i] != -1) return;

        ans[i] = i;
        for (int p : map.get(i)) {
            dfs(p, quite, map, ans);
            if (quite[ans[p]] < quite[ans[i]]) {
                ans[i] = ans[p];
            }
        }
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().loudAndRich(new int[][]{
            {1,0},{2,1},{3,1},{3,7},{4,3},{5,3},{6,3}
        }, new int[]{3,2,5,4,6,1,7,0}), new int[]{5,5,2,5,4,5,6,7});

        assert Checker.check(new Solution().loudAndRich(new int[][]{}, new int[]{0}), new int[]{0});
    }

}
