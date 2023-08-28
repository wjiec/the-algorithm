package problem.p2060checkifanoriginalstringexistsgiventwoencodedstrings;

import java.util.HashSet;
import java.util.Set;

/**
 * 2060. Check if an Original String Exists Given Two Encoded Strings
 *
 * https://leetcode.cn/problems/check-if-an-original-string-exists-given-two-encoded-strings/
 *
 * An original string, consisting of lowercase English letters, can be encoded by the following steps:
 *
 * Arbitrarily split it into a sequence of some number of non-empty substrings.
 *
 * Arbitrarily choose some elements (possibly none) of the sequence, and replace
 * each with its length (as a numeric string).
 *
 * Concatenate the sequence as the encoded string.
 *
 * For example, one way to encode an original string "abcdefghijklmnop" might be:
 *
 * Split it as a sequence: ["ab", "cdefghijklmn", "o", "p"].
 * Choose the second and third elements to be replaced by their lengths, respectively.
 * The sequence becomes ["ab", "12", "1", "p"].
 * Concatenate the elements of the sequence to get the encoded string: "ab121p".
 * Given two encoded strings s1 and s2, consisting of lowercase English letters and digits 1-9 (inclusive),
 * return true if there exists an original string that could be encoded as both s1 and s2. Otherwise, return false.
 *
 * Note: The test cases are generated such that the number of consecutive digits in s1 and s2 does not exceed 3.
 */

public class Solution {

    @SuppressWarnings("unchecked")
    public boolean possiblyEquals(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        Set<Integer>[][] dp = new Set[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = new HashSet<>();
            }
        }

        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();

        dp[0][0].add(0);
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (var diff : dp[i][j]) {
                    int number = 0; // s1 为数字
                    for (int d = 0; d < 3 && i + d < m && Character.isDigit(cs1[i + d]); d++) {
                        number = number * 10 + (cs1[i + d] - '0');
                        dp[i + d + 1][j].add(diff + number);
                    }

                    number = 0; // s2为数字
                    for (int d = 0; d < 3 && j + d < n && Character.isDigit(cs2[j + d]); d++) {
                        number = number * 10 + (cs2[j + d] - '0');
                        dp[i][j + d + 1].add(diff - number);
                    }

                    // 数字匹配s1的字母
                    if (i < m && !Character.isDigit(cs1[i]) && diff < 0) {
                        dp[i + 1][j].add(diff + 1);
                    }

                    // 数字匹配s2字母
                    if (j < n && !Character.isDigit(cs2[j]) && diff > 0) {
                        dp[i][j + 1].add(diff - 1);
                    }

                    // 两个字母匹配
                    if (i < m && j < n && !Character.isDigit(cs1[i]) && cs1[i] == cs2[j] && diff == 0) {
                        dp[i + 1][j + 1].add(diff);
                    }
                }
            }
        }

        return dp[m][n].contains(0);
    }

    public static void main(String[] args) {
    }

}
