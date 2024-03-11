package weekly.w388.A;

import java.util.Arrays;

/**
 * 100233. Apple Redistribution into Boxes
 *
 * https://leetcode.cn/contest/weekly-contest-388/problems/apple-redistribution-into-boxes/
 *
 * You are given an array apple of size n and an array capacity of size m.
 *
 * There are n packs where the ith pack contains apple[i] apples. There are m boxes
 * as well, and the ith box has a capacity of capacity[i] apples.
 *
 * Return the minimum number of boxes you need to select to redistribute these n packs of apples into boxes.
 *
 * Note that, apples from the same pack can be distributed into different boxes.
 */

public class Solution {

    public int minimumBoxes(int[] apple, int[] capacity) {
        Arrays.sort(capacity);

        int sum = 0, n = capacity.length;
        for (var v : apple) sum += v;
        for (int i = n - 1; i >= 0; i--) {
            sum -= capacity[i];
            if (sum <= 0) return n - i;
        }
        return n;
    }

    public static void main(String[] args) {
    }

}
