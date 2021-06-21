package problem.p1018binaryprefixdivisibleby5;

import java.util.ArrayList;
import java.util.List;

/**
 * 1018. Binary Prefix Divisible By 5
 *
 * https://leetcode-cn.com/problems/binary-prefix-divisible-by-5/
 *
 * You are given a binary array nums (0-indexed).
 *
 * We define xi as the number whose binary representation is the subarray nums[0..i]
 * (from most-significant-bit to least-significant-bit).
 *
 * For example, if nums = [1,0,1], then x0 = 1, x1 = 2, and x2 = 5.
 * Return an array of booleans answer where answer[i] is true if xi is divisible by 5.
 */

public class Solution {

    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0, l = nums.length, v = 0, sum = 0; i < l; i++) {
            v = ((v << 1) | nums[i]) & 0xf;
            ans.add((sum += v) % 5 == 0);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().prefixesDivBy5(new int[]{
            0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
        }).equals(List.of(
            true,false,false,false,true,false,false,false,
            true,false,false,false,true,false,false,false,
            true,false,false,false,true,false,false,false,
            true,false,false,false,true,false,false,false,
            true,false,false,false,true,false,false,false,
            true,false,false,false,true,false,false,false,
            true,false,false,false,true,false,false,false,
            true,false,false,false,true,false,false,false,
            true,false,false,false,true,false,false,false,
            true,false,false,false,true,false,false,false,
            true,false,false,false,true,false,false,false,
            true,false,false,false
        ));
        assert new Solution().prefixesDivBy5(new int[]{0,1,1}).equals(List.of(true, false, false));
        assert new Solution().prefixesDivBy5(new int[]{1,1,1}).equals(List.of(false, false, false));
        assert new Solution().prefixesDivBy5(new int[]{0,1,1,1,1,1}).equals(List.of(true, false, false, false, true, false));
        assert new Solution().prefixesDivBy5(new int[]{1,1,1,0,1}).equals(List.of(false, false, false, false, false));
    }

}
