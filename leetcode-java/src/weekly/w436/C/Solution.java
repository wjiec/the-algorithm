package weekly.w436.C;

/**
 * 3448. Count Substrings Divisible By Last Digit
 *
 * https://leetcode.cn/contest/weekly-contest-436/problems/count-substrings-divisible-by-last-digit/
 *
 * You are given a string s consisting of digits.
 *
 * Return the number of substrings of s divisible by their non-zero last digit.
 *
 * Note: A substring may contain leading zeros.
 */

public class Solution {

    public long countSubstrings(String s) {
        // 以 1 2 3 4 5 6 7 8 9 结尾的数字, 同时要被结尾数字整除, 有什么条件?
        long ans = 0;
        char[] chars = s.toCharArray();
        int[] mod3 = new int[3], mod9 = new int[9];
        for (int i = 0, sum = 0; i < chars.length; i++) {
            mod3[sum % 3]++; mod9[sum % 9]++;

            switch (chars[i]) {
                // 1, 2, 5 结尾的任意前缀都是可以的, 从 [0, i] 的所有任意子字符串
                case '1', '2', '5' -> ans += i + 1;
                // 数位和可以被 3 整除
                case '3', '6' -> ans += mod3[sum % 3];
                // 数位和可以被 9 整除
                case '9' -> ans += mod9[sum % 9];
                // 后缀是以 04 24 44 64 84 组成
                case '4' -> {
                    if (i - 1 >= 0 && in(chars[i - 1], '0', '2', '4', '6', '8')) ans += i + 1;
                    else ans++;
                }
                case '7' -> {} // ???
                // 后缀是以 004 048 088 168 组成
                case '8' -> {
                    if (i - 1 >= 0) {
                        switch (chars[i - 1]) {
                            case '0', '4', '8' -> {
                                ans += 2;
                                if (i - 2 >= 0 && chars[i - 2] == '0') ans += i - 2 + 1;
                            }
                            case '6' -> {
                                ans += 2;
                                if (i - 2 >= 0 && chars[i - 2] == '1') ans += i - 2 + 1;
                            }
                        }
                    }
                }
            }

            sum += chars[i] - '0';
        }

        System.out.println(ans);
        return ans;
    }

    private boolean in(char curr, char ...list) {
        for (var v : list) if (curr == v) return true;
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().countSubstrings("12936") == 11;
        assert new Solution().countSubstrings("5701283") == 18;
        assert new Solution().countSubstrings("1010101010") == 25;
    }

}
