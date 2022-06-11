package problem.p984stringwithoutaaaorbbb;

/**
 * 984. String Without AAA or BBB
 *
 * https://leetcode.cn/problems/string-without-aaa-or-bbb/
 *
 * Given two integers a and b, return any string s such that:
 *
 * s has length a + b and contains exactly a 'a' letters, and exactly b 'b' letters,
 * The substring 'aaa' does not occur in s, and
 * The substring 'bbb' does not occur in s.
 */

public class Solution {

    public String strWithout3a3b(int a, int b) {
        StringBuilder sb = new StringBuilder();
        for (int la = 0, lb = 0; a > 0 || b > 0; ) {
            if ((a > b && la < 2) || (lb == 2)) {
                sb.append('a');
                a--;
                la++;
                lb = 0;
                continue;
            }

            sb.append('b');
            b--;
            lb++;
            la = 0;
        }

        return sb.toString();
    }

    private static class Greedy {
        public String strWithout3a3b(int a, int b) {
            StringBuilder sb = new StringBuilder();
            while (a > 0 && b > 0) {
                if (a > b) {
                    sb.append("aab");
                    a -= 2; b -= 1;
                } else if (a < b) {
                    sb.append("bba");
                    a -= 1; b -= 2;
                } else {
                    sb.append("ab");
                    a--; b--;
                }
            }

            if (a > 0) sb.append("a".repeat(a));
            if (b > 0) sb.append("b".repeat(b));

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().strWithout3a3b(1, 3));

        System.out.println(new Solution().strWithout3a3b(1, 2));
        System.out.println(new Solution().strWithout3a3b(4, 1));


        System.out.println(new Greedy().strWithout3a3b(1, 3));
        System.out.println(new Greedy().strWithout3a3b(1, 2));
        System.out.println(new Greedy().strWithout3a3b(4, 1));
    }

}
