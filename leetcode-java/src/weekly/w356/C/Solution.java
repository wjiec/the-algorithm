package weekly.w356.C;

/**
 * 2800. Shortest String That Contains Three Strings
 *
 * https://leetcode.cn/contest/weekly-contest-356/problems/shortest-string-that-contains-three-strings/
 *
 * Given three strings a, b, and c, your task is to find a string that has the
 * minimum length and contains all three strings as substrings.
 * If there are multiple such strings, return the lexicographically smallest one.
 *
 * Return a string denoting the answer to the problem.
 *
 * Notes
 *
 * A string a is lexicographically smaller than a string b (of the same length) if in the
 * first position where a and b differ, string a has a letter that appears earlier in the
 * alphabet than the corresponding letter in b.
 *
 * A substring is a contiguous sequence of characters within a string.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public String minimumString(String a, String b, String c) {
        String ans = concat(a, b, c);
        ans = min(ans, concat(a, c, b));
        ans = min(ans, concat(b, c, a));
        ans = min(ans, concat(b, a, c));
        ans = min(ans, concat(c, a, b));
        ans = min(ans, concat(c, b, a));
        return ans;
    }

    private String min(String a, String b) {
        if (a.length() < b.length()) return a;
        if (a.length() > b.length()) return b;
        return a.compareTo(b) < 0 ? a : b;
    }

    private String concat(String a, String b, String c) {
        String x = concat(a, b);
        String y = concat(x, c);
        return y;
    }

    private String concat(String a, String b) {
        if (a.contains(b)) return a;
        int dup = 0, al = a.length(), bl = b.length();
        for (int i = 1; i <= al && i <= bl; i++) {
            if (a.substring(al - i).equals(b.substring(0, i))) {
                dup = i;
            }
        }
        return a + b.substring(dup);
    }

    public static void main(String[] args) {
        assert new Solution().minimumString("cab", "a", "b").equals("cab");

        assert new Solution().minimumString("abc", "bca", "aaa").equals("aaabca");
        assert new Solution().minimumString("ab", "ba", "aba").equals("aba");
    }

}
