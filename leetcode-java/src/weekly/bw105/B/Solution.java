package weekly.bw105.B;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    private int minLen = 100, maxLen = 0;
    private final Set<String> words = new HashSet<>();

    public int minExtraChar(String s, String[] dictionary) {
        for (var word : dictionary) {
            words.add(word);
            minLen = Math.min(minLen, word.length());
            maxLen = Math.max(maxLen, word.length());
        }
        return dfs(s.toCharArray(), 0, s.length());
    }

    private int dfs(char[] chars, int l, int r) {
        int ans = r - l;
        if (ans < minLen) return ans;

        for (int i = l; i < r; i++) {
            for (int j = l; j < r; j++) {
                if (words.contains(String.valueOf(chars, i, j - i))) {
                    ans = Math.min(ans, dfs(chars, l, i) + dfs(chars, j + 1, r));
                } else {
                    ans = Math.min(ans, dfs(chars, l, i) + dfs(chars, i, j + 1) + dfs(chars, j + 1, r));
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minExtraChar("leetscode", new String[]{"leet","code","leetcode"}) == 1;
        assert new Solution().minExtraChar("sayhelloworld", new String[]{"hello","world"}) == 3;
    }

}
