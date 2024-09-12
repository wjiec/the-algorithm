package problem.p1278palindromepartitioningiii;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int palindromePartition(String s, int k) {
        return partition(s.toCharArray(), 0, s.length() - 1, k);
    }

    private final Map<Integer, Integer> memo = new HashMap<>();

    // 将 chars[l..r] 分为 n 个回文子串所需的最小修改量
    private int partition(char[] chars, int l, int r, int n) {
        int key = (l << 24) | (r << 16) | n;
        if (!memo.containsKey(key)) {
            if (n != 1) {
                int ans = chars.length;
                for (int i = l; i <= r - n + 1; i++) {
                    ans = Math.min(ans, palindrome(chars, l, i) + partition(chars, i + 1, r, n - 1));
                }

                memo.put(key, ans);
            } else memo.put(key, palindrome(chars, l, r));
        }

        return memo.get(key);
    }

    private int palindrome(char[] chars, int l, int r) {
        int ans = 0;
        while (l < r) {
            if (chars[l++] != chars[r--]) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().palindromePartition("abc", 2) == 1;
        assert new Solution().palindromePartition("aabbc", 3) == 0;
        assert new Solution().palindromePartition("leetcode", 8) == 0;
    }

}
