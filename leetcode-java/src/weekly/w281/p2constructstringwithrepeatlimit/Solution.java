package weekly.w281.p2constructstringwithrepeatlimit;

/**
 * 6014. Construct String With Repeat Limit
 *
 * https://leetcode-cn.com/contest/weekly-contest-281/problems/construct-string-with-repeat-limit/
 *
 * You are given a string s and an integer repeatLimit.
 *
 * Construct a new string repeatLimitedString using the characters of s such that no letter appears
 * more than repeatLimit times in a row. You do not have to use all characters from s.
 *
 * Return the lexicographically largest repeatLimitedString possible.
 *
 * A string a is lexicographically larger than a string b if in the first position where a and b differ,
 * string a has a letter that appears later in the alphabet than the corresponding letter in b.
 *
 * If the first min(a.length, b.length) characters do not differ,
 * then the longer string is the lexicographically larger one.
 */

public class Solution {

    public String repeatLimitedString(String s, int repeatLimit) {
        int[] map = new int[128];
        for (var c : s.toCharArray()) map[c]++;

        StringBuilder sb = new StringBuilder();
        for (int i = 0, l = s.length(); i < l; i++) {
            boolean appended = false;
            for (int c = 'z'; c >= 'a'; c--) {
                if (map[c] != 0) {
                    int n = sb.length(), j = n - repeatLimit;
                    if (j < 0) {
                        sb.append((char) c);
                        appended = true;
                        map[c]--;
                        break;
                    }

                    while (j < n && sb.charAt(j) == c) j++;
                    if (j != n) {
                        sb.append((char) c);
                        appended = true;
                        map[c]--;
                        break;
                    }
                }
            }
            if (!appended) break;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // zzzzyyyyxvvuuuuttssssssrqqqpponnmmmmmmlmkmjjiihhhhgggggfffeeeeddddccccbbbbbbab
        // zzzzyyyyxvvuuuuttssssssrqqqpponnmmmmmmlmmkjjiihhhhgggggfffeeeeddddccccbbbbbbab
        assert new Solution().repeatLimitedString("mapzhptjmudmgdutgqcyscceybfzyqmmmmbdkgzssqnfyoxmebniribeubudsegsflsvhcgbhvzhjb", 6)
            .equals("zzzzyyyyxvvuuuuttssssssrqqqpponnmmmmmmlmmkjjiihhhhgggggfffeeeeddddccccbbbbbbab");

        assert new Solution().repeatLimitedString("cczazcc", 3).equals("zzcccac");
        assert new Solution().repeatLimitedString("aababab", 2).equals("bbabaa");
    }

}
