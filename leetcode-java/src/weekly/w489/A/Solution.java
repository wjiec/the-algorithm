package weekly.w489.A;

import java.util.ArrayList;
import java.util.List;

/**
 * Q1. Toggle Light Bulbs
 *
 * https://leetcode.cn/contest/weekly-contest-489/problems/toggle-light-bulbs/
 *
 * You are given an array bulbs of integers between 1 and 100.
 *
 * There are 100 light bulbs numbered from 1 to 100. All of them are switched off initially.
 *
 * For each element bulbs[i] in the array bulbs:
 *
 * If the bulbs[i]th light bulb is currently off, switch it on.
 * Otherwise, switch it off.
 *
 * Return the list of integers denoting the light bulbs that are on in the end, sorted in ascending order.
 * If no bulb is on, return an empty list.
 */

public class Solution {

    public List<Integer> toggleLightBulbs(List<Integer> bulbs) {
        boolean[] status = new boolean[101];
        for (var v : bulbs) status[v] = !status[v];

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < status.length; i++) {
            if (status[i]) ans.add(i);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
