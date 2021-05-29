package problem.p605canplaceflowers;

/**
 * 605. Can Place Flowers
 *
 * https://leetcode-cn.com/problems/can-place-flowers/
 *
 * You have a long flowerbed in which some of the plots are planted, and some are not.
 * However, flowers cannot be planted in adjacent plots.
 *
 * Given an integer array flowerbed containing 0's and 1's,
 * where 0 means empty and 1 means not empty, and an integer n,
 * return if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.
 */

public class Solution {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int sz = flowerbed.length;
        for (int i = 0; i < sz && n > 0; i++) {
            if (flowerbed[i] == 0 && canPlace(flowerbed, i, sz)) {
                flowerbed[i] = 1;
                n--;
            }
        }

        return n == 0;
    }

    private boolean canPlace(int[] flowerbed, int i, int sz) {
        int p = i - 1, n = i + 1;
        return (p < 0 || flowerbed[p] == 0) && (n > sz - 1 || flowerbed[n] == 0);
    }

    public static void main(String[] args) {
        assert new Solution().canPlaceFlowers(new int[]{0}, 1);
        assert new Solution().canPlaceFlowers(new int[]{1,0,0,0,1,0,0}, 2);
        assert new Solution().canPlaceFlowers(new int[]{0,0,1,0,1}, 1);
        assert !new Solution().canPlaceFlowers(new int[]{1,0,0,0,0,1}, 2);
        assert new Solution().canPlaceFlowers(new int[]{1,0,0,0,1}, 1);
        assert !new Solution().canPlaceFlowers(new int[]{1,0,0,0,1}, 2);
    }

}
