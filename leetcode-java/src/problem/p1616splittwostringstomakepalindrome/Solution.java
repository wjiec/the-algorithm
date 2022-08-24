package problem.p1616splittwostringstomakepalindrome;

/**
 * 1616. Split Two Strings to Make Palindrome
 *
 * https://leetcode.cn/problems/split-two-strings-to-make-palindrome/
 *
 * You are given two strings a and b of the same length. Choose an index and split both strings
 * at the same index, splitting a into two strings: aprefix and asuffix where a = aprefix + asuffix, and
 * splitting b into two strings: bprefix and bsuffix where b = bprefix + bsuffix.
 * Check if aprefix + bsuffix or bprefix + asuffix forms a palindrome.
 *
 * When you split a string s into sprefix and ssuffix, either ssuffix or sprefix is allowed to be empty.
 * For example, if s = "abc", then "" + "abc", "a" + "bc", "ab" + "c" , and "abc" + "" are valid splits.
 *
 * Return true if it is possible to form a palindrome string, otherwise return false.
 *
 * Notice that x + y denotes the concatenation of strings x and y.
 */

public class Solution {

    public boolean checkPalindromeFormation(String a, String b) {
        char[] cs1 = a.toCharArray(), cs2 = b.toCharArray();
        return check(cs1, cs2) || check(cs2, cs1);
    }

    private boolean check(char[] a, char[] b) {
        int l = 0, r = a.length - 1;
        while (l <= r && a[l] == b[r]) {
            l++; r--;
        }
        return l >= r || check(a, l, r) || check(b, l, r);
    }

    private boolean check(char[] a, int l, int r) {
        while (l <= r && a[l] == a[r]) {
            l++; r--;
        }
        return l >= r;
    }

    public static void main(String[] args) {
        assert new Solution().checkPalindromeFormation("aejbaalflrmkswrydwdkdwdyrwskmrlfqizjezd", "uvebspqckawkhbrtlqwblfwzfptanhiglaabjea");

        assert new Solution().checkPalindromeFormation("yjgpzbezspnnpszebzmhvp","pvhmupgqeltozftlmfjjde");
        assert new Solution().checkPalindromeFormation("pvhmupgqeltozftlmfjjde", "yjgpzbezspnnpszebzmhvp");

        assert new Solution().checkPalindromeFormation("x", "y");
        assert new Solution().checkPalindromeFormation("abdef", "fecab");
        assert new Solution().checkPalindromeFormation("ulacfd", "jizalu");
    }

}
