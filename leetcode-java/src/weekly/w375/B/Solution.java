package weekly.w375.B;

import java.util.ArrayList;
import java.util.List;

/**
 * 2961. Double Modular Exponentiation
 *
 * https://leetcode.cn/contest/weekly-contest-375/problems/double-modular-exponentiation/
 *
 * You are given a 0-indexed 2D array variables where variables[i] = [ai, bi, ci, mi], and an integer target.
 *
 * An index i is good if the following formula holds:
 *
 * 0 <= i < variables.length
 * ((ai^bi % 10)^ci) % mi == target
 *
 * Return an array consisting of good indices in any order.
 */

public class Solution {

    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < variables.length; i++) {
            int a = variables[i][0], b = variables[i][1],
                c = variables[i][2], m = variables[i][3];
            if (pow(pow(a, b, 10), c, m) == target) ans.add(i);
        }
        return ans;
    }

    private int pow(int a, int b, int m) {
        int ans = 1;
        while (b > 0) {
            if ((b & 1) != 0) {
                ans = (ans * a) % m;
            }

            a = (a * a) % m;
            b >>= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
