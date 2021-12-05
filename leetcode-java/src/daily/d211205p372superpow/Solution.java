package daily.d211205p372superpow;

/**
 * 372. Super Pow
 *
 * https://leetcode-cn.com/problems/super-pow/
 *
 * Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely
 * large positive integer given in the form of an array.
 */

public class Solution {

    private final int MOD = 1337;

    public int superPow(int a, int[] b) {
        if (a == 0 || a == 1) return a;

        int ans = 1;
        for (int i = b.length - 1; i >= 0; i--) {
            ans = (int) ((long) ans * pow(a, b[i]) % MOD);
            a = pow(a, 10);
        }
        return ans;
    }

    private int pow(int x, int n) {
        int r = 1;
        for (; n != 0; n /= 2) {
            if (n % 2 != 0) r = (int) ((long) r * x % MOD);
            x = (int) ((long) x * x % MOD);
        }
        return r;
    }

    public static void main(String[] args) {
        assert new Solution().superPow(2, new int[]{3}) == 8;
        assert new Solution().superPow(2, new int[]{1,0}) == 1024;
        assert new Solution().superPow(1, new int[]{4,3,3,8,5,2}) == 1;
        assert new Solution().superPow(2147483647, new int[]{2,0,0}) == 1198;
    }

}
