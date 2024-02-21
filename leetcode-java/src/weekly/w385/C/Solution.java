package weekly.w385.C;

import ability.Prime;

import java.time.LocalDateTime;
import java.util.TreeMap;

/**
 * 3044. Most Frequent Prime
 *
 * https://leetcode.cn/contest/weekly-contest-385/problems/most-frequent-prime/
 *
 * You are given a m x n 0-indexed 2D matrix mat. From every cell, you can create numbers in the following way:
 *
 * There could be at most 8 paths from the cells namely: east, south-east, south,
 * south-west, west, north-west, north, and north-east.
 *
 * Select a path from them and append digits in this path to the number being
 * formed by traveling in this direction.
 *
 * Note that numbers are generated at every step, for example, if the digits
 * along the path are 1, 9, 1, then there will be three numbers generated
 * along the way: 1, 19, 191.
 *
 * Return the most frequent prime number greater than 10 out of all the
 * numbers created by traversing the matrix or -1 if no such prime number exists.
 *
 * If there are multiple prime numbers with the highest frequency, then return the largest among them.
 *
 * Note: It is invalid to change the direction during the move.
 */

public class Solution {

    private final TreeMap<Integer, Integer> map = new TreeMap<>();

    public int mostFrequentPrime(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                generate(mat, i, j);
            }
        }

        int ans = -1, count = 0;
        for (var kv : map.entrySet()) {
            if (!Prime.isPrime(kv.getKey())) continue;
            if (kv.getValue() >= count) {
                ans = kv.getKey();
                count = kv.getValue();
            }
        }
        return ans;
    }

    private final int[][] dirs = new int[][]{
        {0, 1}, {1, 0}, {0, -1}, {-1, 0},
        {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    private void generate(int[][] mat, int x, int y) {
        for (var dir : dirs) generate(mat, x, y, dir[0], dir[1]);
    }

    private void generate(int[][] mat, int x, int y, int dx, int dy) {
        int curr = 0, m = mat.length, n = mat[0].length;
        while (x >= 0 && y >= 0 && x < m && y < n) {
            curr = curr * 10 + mat[x][y];
            if (curr > 10) map.merge(curr, 1, Integer::sum);
            x += dx; y += dy;
        }
    }

    public static void main(String[] args) {
        System.out.printf("start ... %s\n", LocalDateTime.now());
        assert new Solution().mostFrequentPrime(new int[][]{{3},{4},{9}}) == 349;
        System.out.printf("end ... %s\n", LocalDateTime.now());

        System.out.printf("start ... %s\n", LocalDateTime.now());
        assert new Solution().mostFrequentPrime(new int[][]{{9,8,7,8,9,7},{8,9,8,8,7,7},{9,7,9,7,9,7},{8,8,9,9,7,8},{9,9,8,9,9,9},{8,8,9,9,7,8}}) == 89;
        System.out.printf("end ... %s\n", LocalDateTime.now());
    }

}
