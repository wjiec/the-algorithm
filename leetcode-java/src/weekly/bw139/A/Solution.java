package weekly.bw139.A;

import java.util.ArrayList;
import java.util.List;

/**
 * 3285. Find Indices of Stable Mountains
 *
 * https://leetcode.cn/contest/biweekly-contest-139/problems/find-indices-of-stable-mountains/
 *
 * There are n mountains in a row, and each mountain has a height. You are given an integer array
 * height where height[i] represents the height of mountain i, and an integer threshold.
 *
 * A mountain is called stable if the mountain just before it (if it exists) has a height
 * strictly greater than threshold. Note that mountain 0 is not stable.
 *
 * Return an array containing the indices of all stable mountains in any order.
 */

public class Solution {

    public List<Integer> stableMountains(int[] height, int threshold) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < height.length; i++) {
            if (height[i - 1] > threshold) ans.add(i);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
