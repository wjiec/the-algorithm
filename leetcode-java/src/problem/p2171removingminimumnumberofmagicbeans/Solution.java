package problem.p2171removingminimumnumberofmagicbeans;

import java.util.Arrays;

/**
 * 2171. Removing Minimum Number of Magic Beans
 *
 * https://leetcode.cn/problems/removing-minimum-number-of-magic-beans/
 *
 * You are given an array of positive integers beans, where each integer represents
 * the number of magic beans found in a particular magic bag.
 *
 * Remove any number of beans (possibly none) from each bag such that the number of
 * beans in each remaining non-empty bag (still containing at least one bean) is equal.
 *
 * Once a bean has been removed from a bag, you are not allowed to return it to any of the bags.
 *
 * Return the minimum number of magic beans that you have to remove.
 */

public class Solution {

    public long minimumRemoval(int[] beans) {
        Arrays.sort(beans);

        long prefix = 0, suffix = 0;
        for (var v : beans) suffix += v;

        long ans = Long.MAX_VALUE;
        for (int i = 0; i < beans.length; i++) {
            suffix -= beans[i];
            ans = Math.min(ans, prefix + suffix - (long) (beans.length - i - 1) * beans[i]);
            prefix += beans[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumRemoval(new int[]{4,1,6,5}) == 4;
        assert new Solution().minimumRemoval(new int[]{2,10,3,2}) == 7;
    }

}
