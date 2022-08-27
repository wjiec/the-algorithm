package problem.p1653minimumdeletionstomakestringbalanced;

/**
 * 1653. Minimum Deletions to Make String Balanced
 *
 * https://leetcode.cn/problems/minimum-deletions-to-make-string-balanced/
 *
 * You are given a string s consisting only of characters 'a' and 'b'.
 *
 * You can delete any number of characters in s to make s balanced. s is balanced
 * if there is no pair of indices (i,j) such that i < j and s[i] = 'b' and s[j]= 'a'.
 *
 * Return the minimum number of deletions needed to make s balanced.
 */

public class Solution {

    public int minimumDeletions(String s) {
        int tb = 0, n = s.length();
        char[] chars = s.toCharArray();
        for (var c : chars) tb += c - 'a';

        int ans = n, a = 0, b = 0;
        for (var c : chars) {
            if (c == 'a') a++; else b++;
            // 把前面所有的b删掉，再删掉后面所有的a
            // 如果当前字符是b, 则最优的选择是不删除当前的b, 所以
            // 需要检查并加回来
            ans = Math.min(ans, b + n - tb - a - (c - 'a'));
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumDeletions("bbbbbbbaabbbbbaaabbbabbbbaabbbbbbaabbaaabaabbbaaaabaaababbbabbabbaaaabbbabbbbbaabbababbbaaaaaababaaababaabbabbbaaaabbbbbabbabaaaabbbaba") == 60;
        assert new Solution().minimumDeletions("ababaaaabbbbbaaababbbbbbaaabbaababbabbbbaabbbbaabbabbabaabbbababaa") == 25;
        assert new Solution().minimumDeletions("aababbab") == 2;
        assert new Solution().minimumDeletions("bbaaaaabb") == 2;
    }

}
