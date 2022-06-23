package problem.p1042flowerplantingwithnoadjacent;

import common.PrettyPrinter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1042. Flower Planting With No Adjacent
 *
 * https://leetcode.cn/problems/flower-planting-with-no-adjacent/
 *
 * You have n gardens, labeled from 1 to n, and an array paths where paths[i] = [xi, yi] describes
 * a bidirectional path between garden xi to garden yi. In each garden, you want to plant
 * one of 4 types of flowers.
 *
 * All gardens have at most 3 paths coming into or leaving it.
 *
 * Your task is to choose a flower type for each garden such that, for any two
 * gardens connected by a path, they have different types of flowers.
 *
 * Return any such a choice as an array answer, where answer[i] is the type of
 * flower planted in the (i+1)th garden. The flower types are denoted 1, 2, 3, or 4.
 *
 * It is guaranteed an answer exists.
 */

public class Solution {

    private final Map<Integer, Set<Integer>> map = new HashMap<>();

    public int[] gardenNoAdj(int n, int[][] paths) {
        for (var path : paths) {
            map.computeIfAbsent(path[0], v -> new HashSet<>()).add(path[1]);
            map.computeIfAbsent(path[1], v -> new HashSet<>()).add(path[0]);
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (ans[i] == 0) colorize(ans, i + 1);
        }

        return ans;
    }

    private void colorize(int[] ans, int curr) {
        if (ans[curr - 1] != 0) return;
        if (!map.containsKey(curr)) {
            ans[curr - 1] = 1;
            return;
        }

        int[] flowers = new int[5];
        for (var c : map.get(curr)) {
            if (ans[c - 1] != 0) flowers[ans[c - 1]] = 1;
        }

        ans[curr - 1] = any(flowers);
        for (var c : map.get(curr)) colorize(ans, c);
    }

    private int any(int[] flowers) {
        if (flowers[1] == 0) return 1;
        if (flowers[2] == 0) return 2;
        if (flowers[3] == 0) return 3;
        if (flowers[4] == 0) return 4;
        return 0;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().gardenNoAdj(5, new int[][]{
            {3,4}, {4,5}, {3,2}, {5, 1}, {1, 3}, {4, 2}
        }));
        PrettyPrinter.println(new Solution().gardenNoAdj(1, new int[][]{}));

        PrettyPrinter.println(new Solution().gardenNoAdj(3, new int[][]{{1,2},{2,3},{3,1}}));
        PrettyPrinter.println(new Solution().gardenNoAdj(4, new int[][]{{1,2},{3,4}}));
        PrettyPrinter.println(new Solution().gardenNoAdj(4, new int[][]{
            {1,2},{2,3},{3,4},{4,1},{1,3},{2,4}
        }));
    }

}
