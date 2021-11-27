package daily.d211127p519randomflipmatrix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 519. Random Flip Matrix
 *
 * https://leetcode-cn.com/problems/random-flip-matrix/
 *
 * There is an m x n binary grid matrix with all the values set 0 initially.
 *
 * Design an algorithm to randomly pick an index (i, j) where matrix[i][j] == 0 and flips it to 1.
 *
 * All the indices (i, j) where matrix[i][j] == 0 should be equally likely to be returned.
 *
 * Optimize your algorithm to minimize the number of calls made to the built-in random function of
 * your language and optimize the time and space complexity.
 *
 * Implement the Solution class:
 *
 * Solution(int m, int n) Initializes the object with the size of the binary matrix m and n.
 * int[] flip() Returns a random index [i, j] of the matrix where matrix[i][j] == 0 and flips it to 1.
 * void reset() Resets all the values of the matrix to be 0.
 */

public class Solution {

    private final int m;
    private final int n;
    private int total;
    private final Map<Integer, Integer> map;
    private final ThreadLocalRandom random;

    public Solution(int m, int n) {
        this.m = m;
        this.n = n;
        this.total = m * n;
        map = new HashMap<>();
        random = ThreadLocalRandom.current();
    }

    public int[] flip() {
        int x = random.nextInt(total);
        total--;
        int idx = map.getOrDefault(x, x);
        map.put(x, map.getOrDefault(total, total));
        return new int[]{idx / n, idx % n};
    }

    public void reset() {
        total = m * n;
        map.clear();
    }

    public static void main(String[] args) {
        Solution solution = new Solution(3, 1);
        System.out.println(Arrays.toString(solution.flip()));
        System.out.println(Arrays.toString(solution.flip()));
        System.out.println(Arrays.toString(solution.flip()));

        solution.reset();
        System.out.println(Arrays.toString(solution.flip()));
        System.out.println(Arrays.toString(solution.flip()));
    }

}
