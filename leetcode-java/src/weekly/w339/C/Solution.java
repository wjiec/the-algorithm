package weekly.w339.C;

import java.util.Arrays;

/**
 * 2611. Mice and Cheese
 *
 * https://leetcode.cn/contest/weekly-contest-339/problems/mice-and-cheese/
 *
 * There are two mice and n different types of cheese, each type of cheese should be eaten by exactly one mouse.
 *
 * A point of the cheese with index i (0-indexed) is:
 *
 * reward1[i] if the first mouse eats it.
 * reward2[i] if the second mouse eats it.
 * You are given a positive integer array reward1, a positive integer array reward2, and a non-negative integer k.
 *
 * Return the maximum points the mice can achieve if the first mouse eats exactly k types of cheese.
 */

public class Solution {

    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        Integer[] sorted = new Integer[reward1.length];
        for (int i = 0; i < reward1.length; i++) sorted[i] = i;
        Arrays.sort(sorted, (a, b) -> (reward1[b] - reward2[b]) - (reward1[a] - reward2[a]));

        int ans = 0;
        for (int i = 0; i < k; i++) ans += reward1[sorted[i]];
        for (int i = k; i < reward1.length; i++) ans += reward2[sorted[i]];
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().miceAndCheese(new int[]{1,4,2,1,1}, new int[]{5,6,1,2,5}, 3) == 17;
    }

}
