package weekly.w406.A;

/**
 * 3216. Lexicographically Smallest String After a Swap
 *
 * https://leetcode.cn/contest/weekly-contest-406/problems/lexicographically-smallest-string-after-a-swap/
 *
 * Given a string s containing only digits, return the lexicographically smallest string that
 * can be obtained after swapping adjacent digits in s with the same parity at most once.
 *
 * Digits have the same parity if both are odd or both are even.
 * For example, 5 and 9, as well as 2 and 4, have the same parity, while 6 and 9 do not.
 */

public class Solution {

    public String getSmallestString(String s) {
        String ans = s;
        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            int a = chars[i - 1] - '0', b = chars[i] - '0';
            if (a % 2 == b % 2) {
                swap(chars, i, i - 1);
                String curr = new String(chars);
                if (curr.compareTo(ans) < 0) ans = curr;
                swap(chars, i, i - 1);
            }
        }

        return ans;
    }

    private void swap(char[] chars, int a, int b) {
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }

    public static void main(String[] args) {
    }

}
