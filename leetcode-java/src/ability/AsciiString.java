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

    public static void swap(char[] chars, int a, int b) {
        char stash = chars[a];
        chars[a] = chars[b];
        chars[b] = stash;
    }

}
