package problem.p1925countsquaresumtriples;

/**
 * 1925. Count Square Sum Triples
 *
 * https://leetcode-cn.com/problems/count-square-sum-triples/
 *
 * A square triple (a,b,c) is a triple where a, b, and c are integers and a2 + b2 = c2.
 *
 * Given an integer n, return the number of square triples such that 1 <= a, b, c <= n.
 */

public class Solution {

    public int countTriples(int n) {
        int ans = 0;
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                int c = (int) Math.sqrt(a * a + b * b);
                if (c <= n && a * a + b * b == c * c) ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countTriples(5) == 2;
        assert new Solution().countTriples(10) == 4;
    }

}
