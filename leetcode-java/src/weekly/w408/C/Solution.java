package weekly.w408.C;

import java.util.ArrayList;
import java.util.List;

/**
 * 3234. Count the Number of Substrings With Dominant Ones
 *
 * https://leetcode.cn/contest/weekly-contest-408/problems/count-the-number-of-substrings-with-dominant-ones/
 *
 * You are given a binary string s.
 *
 * Return the number of substrings with dominant ones.
 *
 * A string has dominant ones if the number of ones in the string is greater than
 * or equal to the square of the number of zeros in the string.
 */

public class Solution {

    public int numberOfSubstrings(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        List<Integer> zeros = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (chars[i] == '0') zeros.add(i);
        }
        // 增加一个哨兵, 适配不是以 0 结尾的字符串
        zeros.add(n);

        // 剩余的 1 的数量
        int zn = zeros.size();
        int tot1 = n - zn + 1;

        int ans = 0, firstZero = 0;
        // 枚举所有的左端点, 找到所有符合条件的子串
        for (int left = 0; left < n; left++) {
            // 特殊处理没有 0 的情况, 则所有从 left 开始
            // 到 left+1, left+2, ..., firstZero - 1 的子串都是满足要求的
            ans += zeros.get(firstZero) - left;

            // 然后枚举所有可能的 0 的数量, 如果剩余的 0 的数量不够了, 那就直接退出
            for (int cnt0 = 1; firstZero + cnt0 < zn; cnt0++) {
                // 如果剩余的 1 的数量不够了, 那就直接退出
                if (cnt0 * cnt0 > tot1) break;

                // 找到需要包含的一个 0 的在字符串 s 中的位置
                int a = zeros.get(firstZero + cnt0 - 1);
                // 再找到下一个 0 在字符串 s 中的位置
                int b = zeros.get(firstZero + cnt0);

                // 先计算在 [left, a) 之间 1 的数量
                int cnt1 = a - left + 1 - cnt0;

                // 然后在 [a, b) 之间找到能够第一个能满足条件的子串结尾的位置
                int validEnd = Math.max(a, a + (cnt0 * cnt0 - cnt1));

                // 所有从 left 开始到 validEnd, validEnd + 1, ..., b - 1 的子串都是满足要求的
                // 如果第一个满足条件的子串的结尾位置超过了 b 的位置, 那这种方案是无效的
                ans += Math.max(b - validEnd, 0);
            }

            if (chars[left] == '1') tot1--;
            else firstZero++;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numberOfSubstrings("000001111") == 15;
        // 1 10 101 01 1
        assert new Solution().numberOfSubstrings("101") == 5;

        assert new Solution().numberOfSubstrings("00011") == 5;
        assert new Solution().numberOfSubstrings("101101") == 16;
    }

}
