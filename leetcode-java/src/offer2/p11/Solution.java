package offer2.p11;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 011. 0 和 1 个数相同的子数组
 *
 * https://leetcode.cn/problems/A1NYOS/
 *
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 */

public class Solution {

    public int findMaxLength(int[] nums) {
        // {x: y} 表示和为 x 时的下标为 y
        Map<Integer, Integer> map = new HashMap<>(); map.put(0, -1);

        int ans = 0, sum = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += (nums[i] == 0 ? -1 : 1);
            if (map.containsKey(sum)) {
                ans = Math.max(ans, i - map.get(sum));
            } else map.put(sum, i);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findMaxLength(new int[]{0,1}) == 2;
        assert new Solution().findMaxLength(new int[]{0,1,0}) == 2;
    }

}
