package daily.d240116p2719countofintegers;

import java.util.Arrays;

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final static int MOD = 1_000_000_007;

    public int count(String num1, String num2, int min_sum, int max_sum) {
        char[] s1 = subtract(num1.toCharArray()), s2 = num2.toCharArray();
        long l = dfs(s1, 0, true, false, max_sum) - dfs(s1, 0, true, false, min_sum - 1);
        long r = dfs(s2, 0, true, false, max_sum) - dfs(s2, 0, true, false, min_sum - 1);
        return (int) ((((r - l) % MOD) + MOD) % MOD);
    }

    private final long[][] memo = new long[30][401];

    private long dfs(char[] digits, int curr, boolean limited, boolean valid, int remain) {
        if (curr == 0) for (var row : memo) Arrays.fill(row, -1);
        if (curr == digits.length) return valid ? 1 : 0;
        if (!limited && valid && memo[curr][remain] != -1) return memo[curr][remain];

        long ans = 0;
        if (!valid) ans += dfs(digits, curr + 1, false, false, remain);
        for (int l = valid ? 0 : 1, r = limited ? (digits[curr] - '0') : 9; l <= r; l++) {
            if (l <= remain) ans += dfs(digits, curr + 1, limited && l == r, true, remain - l);
        }
        if (!limited && valid) memo[curr][remain] = ans % MOD;

        return ans % MOD;
    }

    private char[] subtract(char[] digits) {
        int start = digits.length - 1;
        while (start >= 0 && digits[start] == '0') start--;
        for (; start < digits.length; start++) if (--digits[start] < '0') digits[start] = '9';
        for (start = 0; start < digits.length && digits[start] == '0'; ) start++;
        return new String(digits).substring(start).toCharArray();
    }

    public static void main(String[] args) {
        assert new Solution().count("1012640017461217236611", "9234552128261772272769", 67, 148) == 683479047;
        assert new Solution().count("4859473643", "30159981595", 58, 59) == 972251498;
        assert new Solution().count("1479192516", "5733987233", 36, 108) == 519488312;

        assert new Solution().count("1", "12", 1, 8) == 11;
        assert new Solution().count("1", "5", 1, 5) == 5;
    }

}
