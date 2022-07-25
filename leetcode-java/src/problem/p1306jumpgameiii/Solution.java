package problem.p1306jumpgameiii;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1306. Jump Game III
 *
 * https://leetcode.cn/problems/jump-game-iii/
 *
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array.
 * When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
 *
 * Notice that you can not jump outside of the array at any time.
 */

public class Solution {

    public boolean canReach(int[] arr, int start) {
        if (arr[start] == 0) return true;

        boolean[] visited = new boolean[arr.length];
        visited[start] = true;

        Queue<Integer> queue = new ArrayDeque<>();
        for (queue.add(start); !queue.isEmpty(); ) {
            int curr = queue.remove();

            int l = curr - arr[curr], r = curr + arr[curr];
            if (l >= 0 && l < arr.length && !visited[l]) {
                if (arr[l] == 0) return true;
                queue.add(l); visited[l] = true;
            }
            if (r >= 0 && r < arr.length && !visited[r]) {
                if (arr[r] == 0) return true;
                queue.add(r); visited[r] = true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().canReach(new int[]{4,2,3,0,3,1,2}, 5);
        assert new Solution().canReach(new int[]{4,2,3,0,3,1,2}, 0);
        assert !new Solution().canReach(new int[]{3,0,2,1,2}, 2);
    }

}
