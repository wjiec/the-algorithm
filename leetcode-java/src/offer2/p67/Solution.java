package offer2.p67;

import common.TODO;
import common.Tag;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer II 067. 最大的异或
 *
 * https://leetcode.cn/problems/ms70jA/
 *
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 */

public class Solution {

    @TODO
    @Tag({"数组元素最大异或"})
    public int findMaximumXOR(int[] nums) {
        int ans = 0;
        for (int i = 30; i >= 0; i--) {
            Set<Integer> seen = new HashSet<>();
            for (var v : nums) seen.add(v >> i);

            int x = (ans << 1) + 1;
            boolean found = false;

            for (var v : nums) {
                if (seen.contains(x ^ (v >> i))) {
                    found = true;
                    break;
                }
            }

            ans = x - (found ? 0 : 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findMaximumXOR(new int[]{3,10,5,25,2,8}) == 28;
        assert new Solution().findMaximumXOR(new int[]{14,70,53,83,49,91,36,80,92,51,66,70}) == 127;
    }

}
