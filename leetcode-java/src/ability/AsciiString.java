package ability;

// 基于 ASCII 码的字符串实用工具类
public class AsciiString {

    // 标记 ASCII 字符集中的所有元音字母
    private final static boolean[] vowels = new boolean[128];
    static { vowels['a'] = vowels['e'] = vowels['i'] = vowels['o'] = vowels['u'] = true; }
    static { vowels['A'] = vowels['E'] = vowels['I'] = vowels['O'] = vowels['U'] = true; }

    // 判断字符是否是元音字符
    public static boolean isVowel(char c) { return vowels[c]; }

}
