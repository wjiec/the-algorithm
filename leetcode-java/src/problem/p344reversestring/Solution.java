package problem.p344reversestring;

/**
 * 344. Reverse String
 *
 * https://leetcode-cn.com/problems/reverse-string/
 *
 * Write a function that reverses a string. The input string is given as an array of characters s.
 */

public class Solution {

    public void reverseString(char[] s) {
        int sz = s.length, mid = sz / 2;
        for (int i = 0; i < mid; i++) {
            char t = s[i];
            s[i] = s[sz - i - 1];
            s[sz - i - 1] = t;
        }
    }

    public static void main(String[] args) {
        char[] s0 = new char[]{'h','e','l','l','o'};
        new Solution().reverseString(s0);
        System.out.println(s0);

        char[] s1 = new char[]{'H','a','n','n','a','h'};
        new Solution().reverseString(s1);
        System.out.println(s1);
    }

}
