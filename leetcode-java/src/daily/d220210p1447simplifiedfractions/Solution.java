package daily.d220210p1447simplifiedfractions;

import java.util.ArrayList;
import java.util.List;

/**
 * 1447. Simplified Fractions
 *
 * https://leetcode-cn.com/problems/simplified-fractions/
 *
 * Given an integer n, return a list of all simplified fractions between 0 and 1 (exclusive) such that
 * the denominator is less-than-or-equal-to n. You can return the answer in any order.
 */

public class Solution {

    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (gcd(i, j) == 1) {
                    ans.add(j + "/" + i);
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return a % b == 0 ? b : gcd(b, a % b);
    }

    public static void main(String[] args) {
        for (int i = 1; i < 20; i++) {
            System.out.println(new Solution().simplifiedFractions(i));
        }
    }

}
