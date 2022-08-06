package problem.p1415thekthlexicographicalstringofallhappystringsoflengthn;

/**
 * 1415. The k-th Lexicographical String of All Happy Strings of Length n
 *
 * https://leetcode.cn/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/
 *
 * A happy string is a string that:
 *
 * consists only of letters of the set ['a', 'b', 'c'].
 * s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
 * For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and
 * strings "aa", "baa" and "ababbc" are not happy strings.
 *
 * Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.
 *
 * Return the kth string of this list or return an empty string if there are less than k happy strings of length n.
 */

public class Solution {

    public String getHappyString(int n, int k) {
        if (k > (3 * (1 << (n - 1)))) return "";

        char[] chars = new char[n];
        for (int i = 0; i < n; i++) {
            char pre = i == 0 ? ' ' : chars[i - 1];
            chars[i] = pre == 'a' ? 'b' : 'a';

            int count = 1 << (n - i - 1);
            while (k > count) {
                if (++chars[i] == pre) {
                    chars[i]++;
                }
                k -= count;
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        assert new Solution().getHappyString(1, 3).equals("c");
        assert new Solution().getHappyString(1, 4).equals("");
        assert new Solution().getHappyString(3, 9).equals("cab");
        assert new Solution().getHappyString(2, 7).equals("");
        assert new Solution().getHappyString(10, 100).equals("abacbabacb");
    }

}
