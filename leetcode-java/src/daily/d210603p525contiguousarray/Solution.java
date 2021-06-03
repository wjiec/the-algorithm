package daily.d210603p525contiguousarray;

import java.util.HashMap;
import java.util.Map;

/**
 * 525. Contiguous Array
 *
 * https://leetcode-cn.com/problems/contiguous-array/
 *
 * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
 */

public class Solution {

    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>() {{ put(0, -1); }};
        int ans = 0, n = nums.length, cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += nums[i] == 1 ? 1 : -1;
            if (map.containsKey(cnt)) {
                ans = Math.max(ans, i - map.get(cnt));
            } else {
                map.put(cnt, i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findMaxLength(new int[]{0,0,0,1}) == 2;
        assert new Solution().findMaxLength(new int[]{0,1,0,1}) == 4;
        assert new Solution().findMaxLength(new int[]{0,1}) == 2;
        assert new Solution().findMaxLength(new int[]{0,1,0}) == 2;
    }

}
