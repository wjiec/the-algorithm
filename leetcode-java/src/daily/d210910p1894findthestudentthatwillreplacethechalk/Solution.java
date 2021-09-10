package daily.d210910p1894findthestudentthatwillreplacethechalk;

public class Solution {

    public int chalkReplacer(int[] chalk, int k) {
        long[] prefix = new long[chalk.length];
        for (int i = 0; i < chalk.length; i++) {
            prefix[i] = chalk[i];
            if (i != 0) prefix[i] += prefix[i - 1];
        }

        long remain = k % prefix[prefix.length - 1];
        for (int i = 0; i < prefix.length; i++) {
            if (prefix[i] > remain) return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        assert new Solution().chalkReplacer(new int[]{5,1,5}, 22) == 0;
        assert new Solution().chalkReplacer(new int[]{3,4,1,2}, 25) == 1;
    }

}
