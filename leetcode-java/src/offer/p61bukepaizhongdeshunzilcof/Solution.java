package offer.p61bukepaizhongdeshunzilcof;

import jdk.jshell.spi.SPIResolutionException;

/**
 * 剑指 Offer 61. 扑克牌中的顺子
 *
 * https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof/
 *
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 *
 * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 */

public class Solution {

    public boolean isStraight(int[] nums) {
        int max = 0;
        int[] map = new int[15];
        for (var n : nums) {
            map[n]++;
            max = Math.max(max, n);
        }

        for (int i = 0; i < 5; i++) {
            if (map[max - i] > 1) return false;
            if (map[max - i] == 0 && --map[0] < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert !new Solution().isStraight(new int[]{11,8,12,8,10});
        assert new Solution().isStraight(new int[]{0,0,5,7,8,9});

        assert new Solution().isStraight(new int[]{1,2,3,4,5});
        assert new Solution().isStraight(new int[]{0,0,1,2,5});
    }

}
