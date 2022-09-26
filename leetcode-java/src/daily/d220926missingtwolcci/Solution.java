package daily.d220926missingtwolcci;

import common.PrettyPrinter;
import common.Tag;

/**
 * 17.19. Missing Two LCCI
 *
 * https://leetcode.cn/problems/missing-two-lcci/
 *
 * You are given an array with all the numbers from 1 to N appearing exactly once, except for
 * two number that is missing. How can you find the missing number in O(N) time and 0(1) space?
 *
 * You can return the missing numbers in any order.
 */

public class Solution {

    @Tag({"消失的数字"})
    public int[] missingTwo(int[] nums) {
        int xor = 0, n = nums.length + 2;
        for (int i = 1; i <= n; i++) xor ^= i;
        for (var v : nums) xor ^= v;

        int lsb = xor & (-xor), a = 0, b = 0;
        for (var v : nums) {
            if ((v & lsb) == 0) a ^= v;
            else b ^= v;
        }

        for (int i = 1; i <= n; i++) {
            if ((i & lsb) == 0) a ^= i;
            else b ^= i;
        }

        return new int[]{a, b};
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().missingTwo(new int[]{1, 2, 3, 4, 6, 7, 9, 10}));

        PrettyPrinter.println(new Solution().missingTwo(new int[]{1}));
        PrettyPrinter.println(new Solution().missingTwo(new int[]{2,3}));
        PrettyPrinter.println(new Solution().missingTwo(new int[]{2,3,1}));
        PrettyPrinter.println(new Solution().missingTwo(new int[]{2,3,6,5,4}));
        PrettyPrinter.println(new Solution().missingTwo(new int[]{2}));
    }

}
