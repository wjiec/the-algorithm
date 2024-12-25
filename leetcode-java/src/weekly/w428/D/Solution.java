package weekly.w428.D;

/**
 * 3389. Minimum Operations to Make Character Frequencies Equal
 *
 * https://leetcode.cn/contest/weekly-contest-428/problems/minimum-operations-to-make-character-frequencies-equal/
 *
 * You are given a string s.
 *
 * A string t is called good if all characters of t occur the same number of times.
 *
 * You can perform the following operations any number of times:
 *
 * Delete a character from s.
 * Insert a character in s.
 * Change a character in s to its next letter in the alphabet.
 * Note that you cannot change 'z' to 'a' using the third operation.
 *
 * Return the minimum number of operations required to make s good.
 */

public class Solution {

    public int makeStringGood(String s) {
        int[] freq = new int[26];
        for (var c : s.toCharArray()) freq[c - 'a']++;

        // 我们需要把所有字母的频率变成相同的某个数, 或者直接变成 0
        //  - 我们可以直接枚举处理完成之后的频率值

        // 操作三: 将一个字母变成字母表中的下一个字母
        //  - 相当于使用操作一删除当前字母, 再使用操作二添加下一个. 但是节省了一次操作
        //  - 是否需要考虑将当前字母变成字母表中的下下个字母?
        //      - 不需要. 因为从一个字母变成另一个字母的最大代价就是执行操作一+操作二, 只需要 2 次操作

        int maxFreq = 0, ans = 0;
        for (var v : freq) { ans += v; maxFreq = Math.max(maxFreq, v); }

        // 枚举所有可能的字符频率
        for (; maxFreq > 0; maxFreq--) {
            // 由于我们存在从前一个字母变成字母表中下一个字母的操作, 我们使用 dp 来求解最佳的处理方式
            // dp[i] 表示将 [a + i, z] 这个字母变为 maxFreq 或者 0 所需的最小操作次数
            int[] dp = new int[27];
            // 由于 z 无法执行操作三, 所以要么变成 0, 要么变成 maxFreq
            dp[25] = Math.min(freq[25], Math.abs(freq[25] - maxFreq));
            // 从后往前进行递推
            for (int i = 24; i >= 0; i--) {
                // 直接执行操作一或者操作二
                dp[i] = dp[i + 1] + Math.min(freq[i], Math.abs(freq[i] - maxFreq));
                // 检查我们是否可以通过操作三来减少操作次数
                if (freq[i + 1] < maxFreq) {
                    // 如果当前数的频率小于 maxFreq, 则可以考虑抛弃当前数来提高下一个字母的频率
                    if (freq[i] <= maxFreq) {
                        int delta = Math.min(freq[i], maxFreq - freq[i + 1]);
                        dp[i] = Math.min(dp[i], dp[i + 2] + freq[i] + Math.abs(maxFreq - (freq[i + 1] + delta)));
                    }

                    // 如果当前字母频率大于 maxFreq, 则可以考虑把当前字母频率降到 maxFreq, 多的转移给下一个字母
                    if (freq[i] > maxFreq) {
                        // 执行操作三需要计算差额有多少, 不够了只能再执行操作一和操作二了
                        int delta = Math.min(freq[i] - maxFreq, maxFreq - freq[i + 1]);
                        dp[i] = Math.min(dp[i], dp[i + 2] + delta + Math.abs(maxFreq - (freq[i] - delta)) + Math.abs(maxFreq - (freq[i + 1] + delta)));
                    }
                }
            }

            ans = Math.min(ans, dp[0]);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().makeStringGood("gigigjjggjjgg") == 3;
    }

}
