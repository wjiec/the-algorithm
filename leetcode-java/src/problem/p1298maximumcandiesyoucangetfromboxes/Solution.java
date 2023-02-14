package problem.p1298maximumcandiesyoucangetfromboxes;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1298. Maximum Candies You Can Get from Boxes
 *
 * https://leetcode.cn/problems/maximum-candies-you-can-get-from-boxes/
 *
 * You have n boxes labeled from 0 to n - 1. You are given four arrays:
 * status, candies, keys, and containedBoxes where:
 *
 * 	status[i] is 1 if the ith box is open and 0 if the ith box is closed,
 * 	candies[i] is the number of candies in the ith box,
 * 	keys[i] is a list of the labels of the boxes you can open after opening the ith box.
 * 	containedBoxes[i] is a list of the boxes you found inside the ith box.
 *
 *
 * You are given an integer array initialBoxes that contains the labels of the boxes
 * you initially have. You can take all the candies in any open box and you can use
 * the keys in it to open new boxes and you also can use the boxes you find in it.
 *
 * Return the maximum number of candies you can get following the rules above.
 */

public class Solution {

    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = candies.length, ans = 0;
        boolean[] visited = new boolean[n];
        boolean[] contains = new boolean[n];

        Queue<Integer> queue = new ArrayDeque<>();
        for (var v : initialBoxes) { queue.add(v); contains[v] = true; }

        while (!queue.isEmpty()) {
            int box = queue.remove();
            if (!visited[box] && status[box] == 1) {
                visited[box] = true;
                ans += candies[box];

                for (var acqBox : containedBoxes[box]) {
                    contains[acqBox] = true;
                    if (status[acqBox] == 1) queue.add(acqBox);
                }

                for (var acqKey : keys[box]) {
                    status[acqKey] = 1;
                    if (contains[acqKey] && status[acqKey] == 1) queue.add(acqKey);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxCandies(
            new int[]{1,1,1},
            new int[]{100,1,100},
            new int[][]{{}, {0,2},{}},
            new int[][]{{},{},{}},
            new int[]{1}
        ) == 1;
    }

}
