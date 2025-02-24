package weekly.w435.A;

public class Solution {

    public int maxDifference(String s) {
        int[] count = new int[128];
        for (var c : s.toCharArray()) count[c]++;

        int ans = Integer.MIN_VALUE;
        for (int c1 = 'a'; c1 <= 'z'; c1++) {
            if (count[c1] == 0) continue;
            for (int c2 = c1 + 1; c2 <= 'z'; c2++) {
                if (count[c2] == 0) continue;
                if (count[c1] % 2 != count[c2] % 2) {
                    if (count[c1] % 2 == 1) ans = Math.max(ans, count[c1] - count[c2]);
                    else ans = Math.max(ans, count[c2] - count[c1]);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxDifference("aaaaabbc") == 3;
        assert new Solution().maxDifference("abcabcab") == 1;
    }

}
