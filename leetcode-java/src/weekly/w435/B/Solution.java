package weekly.w435.B;

public class Solution {

    public int maxDistance(String s, int k) {
        // 对答案的影响就是对向的移动, 要沿着当前的方向消除对向的移动
        int x = 0, y = 0, ans = 0, nc = 0, sc = 0, ec = 0, wc = 0;
        for (int i = 0; i < s.length(); i++) {
            int reverse = 0, vertical = 0;
            switch (s.charAt(i)) {
                case 'N' -> { y++; reverse = Math.min(++nc, sc); vertical = Math.min(ec, wc); }
                case 'S' -> { y--; reverse = Math.min(++sc, nc); vertical = Math.min(ec, wc); }
                case 'E' -> { x++; reverse = Math.min(++ec, wc); vertical = Math.min(nc, sc); }
                case 'W' -> { x--; reverse = Math.min(++wc, ec); vertical = Math.min(nc, sc); }
            }

            // 消除一个对向的移动可以带来 2 个效果, 否则只能消除垂直上的对向
            reverse = Math.min(reverse, k); vertical = Math.min(vertical, k - reverse);
            ans = Math.max(ans, Math.abs(x) + Math.abs(y) + reverse * 2 + vertical * 2);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxDistance("NSWWEW", 3) == 6;

        assert new Solution().maxDistance("WEES", 2) == 4;
        assert new Solution().maxDistance("NN", 2) == 2;
        assert new Solution().maxDistance("NN", 0) == 2;
        assert new Solution().maxDistance("NW", 2) == 2;
        assert new Solution().maxDistance("WEWE", 1) == 3;
        assert new Solution().maxDistance("NSES", 1) == 4;
        assert new Solution().maxDistance("NWSE", 1) == 3;
        assert new Solution().maxDistance("NSWWEW", 3) == 6;
    }

}
