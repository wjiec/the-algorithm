package daily.d211015p38countandsay;

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
 * To determine how you "say" a digit string, split it into the minimal number of groups
 * so that each group is a contiguous section all of the same character.
 *
 * Then for each group, say the number of characters, then say the character.
 *
 * To convert the saying into a digit string, replace the counts with a number and concatenate every saying.
 */

public class Solution {

    public String countAndSay(int n) {
        String s = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder sb = new StringBuilder();

            int val = 0, cnt = 0;
            for (var c : s.toCharArray()) {
                if (val != c) {
                    if (cnt != 0) sb.append(cnt).append((char) val);

                    val = c;
                    cnt = 1;
                } else cnt++;
            }

            s = sb.append(cnt).append((char) val).toString();
        }
        return s;
    }

    public static void main(String[] args) {
        assert new Solution().countAndSay(1).equals("1");
        assert new Solution().countAndSay(2).equals("11");
        assert new Solution().countAndSay(3).equals("21");
        assert new Solution().countAndSay(4).equals("1211");
    }

}
