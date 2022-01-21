package daily.d220121p1345jumpgameiv;

import java.util.*;

/**
 * 1345. Jump Game IV
 *
 * https://leetcode-cn.com/problems/jump-game-iv/
 *
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 *
 * In one step you can jump from index i to index:
 *
 * i + 1 where: i + 1 < arr.length.
 * i - 1 where: i - 1 >= 0.
 * j where: arr[i] == arr[j] and i != j.
 *
 * Return the minimum number of steps to reach the last index of the array.
 *
 * Notice that you can not jump outside of the array at any time.
 */

public class Solution {

    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0}); // index, steps
        Set<Integer> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            int[] value = queue.remove();
            int idx = value[0], next = value[1] + 1;
            if (idx == arr.length - 1) return value[1];

            if (idx != 0 && visited.add(idx - 1)) {
                queue.add(new int[]{idx - 1, next});
            }

            if (idx + 1 < arr.length && visited.add(idx + 1)) {
                queue.add(new int[]{idx + 1, next});
            }

            if (map.containsKey(arr[idx])) {
                for (var same : map.get(arr[idx])) {
                    if (visited.add(same)) {
                        queue.add(new int[]{same, next});
                    }
                }
                map.remove(arr[idx]);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().minJumps(new int[]{100,-23,-23,404,100,23,23,23,3,404}) == 3;
        assert new Solution().minJumps(new int[]{7}) == 0;
        assert new Solution().minJumps(new int[]{7,6,9,6,9,6,9,7}) == 1;
        assert new Solution().minJumps(new int[]{6,1,9}) == 2;
        assert new Solution().minJumps(new int[]{11,22,7,7,7,7,7,7,7,22,13}) == 3;
    }

}
