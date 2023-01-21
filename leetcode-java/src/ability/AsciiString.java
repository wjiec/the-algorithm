package ability;

import java.util.ArrayList;
import java.util.List;

// 基于 ASCII 码的字符串实用工具类
public class AsciiString {

    // 标记 ASCII 字符集中的所有元音字母
    private final static boolean[] vowels = new boolean[128];
    static { vowels['a'] = vowels['e'] = vowels['i'] = vowels['o'] = vowels['u'] = true; }
    static { vowels['A'] = vowels['E'] = vowels['I'] = vowels['O'] = vowels['U'] = true; }

    // 判断字符是否是元音字符
    public static boolean isVowel(char c) { return vowels[c]; }

    // 求一个不重复字符串的所有排列组合
    public static List<String> permutation(char[] chars) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                swap(chars, i, j);
                ans.add(new String(chars));
                swap(chars, i, j);
            }
        }
        return ans;
    }

    // 交换字符数组中的两个位置
    public static void swap(char[] chars, int a, int b) {
        char stash = chars[a];
        chars[a] = chars[b];
        chars[b] = stash;
    }

    // 检查字符数组是否是一个回文串
    public static boolean isPalindrome(char[] chars) {
        return isPalindrome(chars, 0, chars.length - 1);
    }

    // 检查字符数组在[l, r]范围内是否是一个回文串
    public static boolean isPalindrome(char[] chars, int l, int r) {
        while (l < r && chars[l] == chars[r]) { l++; r--; }
        return l >= r;
    }

    // 返回一个 [rl, rr] 表示在这个范围内的字符数组是一个回文串
    // 如果返回一个 null 表示无法组成一个回文串
    public static int[] expandPalindrome(char[] chars, int l, int r) {
        if (chars[l] != chars[r]) return null;

        int n = chars.length;
        while (l - 1 >= 0 && r + 1 < n && chars[l - 1] == chars[r + 1]) { l--; r++; }
        return new int[]{l, r};
    }

}
