package weekly.w357.A;

/**
 * 2810. Faulty Keyboard
 *
 * https://leetcode.cn/contest/weekly-contest-357/problems/faulty-keyboard/
 *
 * Your laptop keyboard is faulty, and whenever you type a character 'i' on it, it reverses
 * the string that you have written. Typing other characters works as expected.
 *
 * You are given a 0-indexed string s, and you type each character of s using your faulty keyboard.
 *
 * Return the final string that will be present on your laptop screen.
 */

public class Solution {

    public String finalString(String s) {
        int j = 0;
        char[] chars = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'i') {
                for (int l = 0, r = j - 1; l < r; l++, r--) {
                    char temp = chars[l];
                    chars[l] = chars[r];
                    chars[r] = temp;
                }
            } else {
                chars[j++] = s.charAt(i);
            }
        }
        return new String(chars, 0, j);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().finalString("string"));
        System.out.println(new Solution().finalString("poiinter"));
        System.out.println(new Solution().finalString("iiii"));
    }

}
