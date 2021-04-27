package problem.p38countandsay;

/**
 * 38. Count and Say
 *
 * https://leetcode-cn.com/problems/count-and-say/
 *
 * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
 *
 * countAndSay(1) = "1"
 * countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1),
 * which is then converted into a different digit string.
 *
 * To determine how you "say" a digit string, split it into the minimal number of groups so that
 * each group is a contiguous section all of the same character. Then for each group,
 * say the number of characters, then say the character. To convert the saying into a digit string,
 * replace the counts with a number and concatenate every saying.
 *
 * Given a positive integer n, return the nth term of the count-and-say sequence.
 */

public class Solution {

    public String countAndSay(int n) {
        String rs = "1";
        for (var t = 1; t < n; t++) {
            StringBuilder sb = new StringBuilder(2 * rs.length());
            int count = 1;
            char c = rs.charAt(0);
            for (var i = 1; i < rs.length(); i++) {
                if (rs.charAt(i) == c) {
                    count += 1;
                } else {
                    sb.append((char)('0' + count));
                    sb.append(c);
                    c = rs.charAt(i);
                    count = 1;
                }
            }

            sb.append((char)('0' + count));
            sb.append(c);
            rs = sb.toString();
        }

        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().countAndSay(1).equals("1");
        assert new Solution().countAndSay(2).equals("11");
        assert new Solution().countAndSay(3).equals("21");
        assert new Solution().countAndSay(4).equals("1211");
        assert new Solution().countAndSay(5).equals("111221");
        assert new Solution().countAndSay(6).equals("312211");
    }
}
