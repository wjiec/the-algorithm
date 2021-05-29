package weekly.bw53.p0;

public class Solution {

    public int countGoodSubstrings(String s) {
        int n = s.length(), ans = 0;
        if (n < 3) return 0;

        char pp = s.charAt(0), p = s.charAt(1), c;
        for (int i = 2; i < n; i++) {
            c = s.charAt(i);
            if (pp != p && pp != c && p != c) {
                ans++;
            }
            pp = p; p = c;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countGoodSubstrings("xyzzaz") == 1;
        assert new Solution().countGoodSubstrings("aababcabc") == 4;
    }

}
