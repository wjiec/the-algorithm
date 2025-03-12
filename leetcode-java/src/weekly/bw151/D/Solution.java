package weekly.bw151.D;

import common.Checker;
import common.PrettyPrinter;

public class Solution {

    public int[] permute(int n, long k) {
        if (k > (1L << (n - 1))) return new int[0];
        // 最后一位必须要是 0 1 0 1 或者 1 0 1 0 的形式
        //  - 如果 n 是奇数则只能是 1 0 1 0 1 这种形式
        //  - 如果 k 超过 1 << x, 则是 1 0 1 0 的形式, 否则应该是 0 1 0 1 的形式
        //
        // 剩下的 x 位则需要按字典序进行, 就是 从 0001 0010 0011 ... 进行排列
        int[] shifted = new int[n];
        for (int i = 1; i <= n; i++) shifted[i - 1] = i >> 1;
        PrettyPrinter.println(shifted);

        return new int[]{1};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().permute(4, 6), new int[]{3, 4, 1, 2});
        assert Checker.check(new Solution().permute(3, 2), new int[]{3, 2, 1});
        assert Checker.check(new Solution().permute(2, 3), new int[0]);
    }

}
