package weekly.w426.B;

import java.util.HashMap;
import java.util.Map;

/**
 * 3371. Identify the Largest Outlier in an Array
 *
 * https://leetcode.cn/contest/weekly-contest-426/problems/identify-the-largest-outlier-in-an-array/
 *
 * You are given an integer array nums. This array contains n elements, where exactly n - 2 elements
 * are special numbers. One of the remaining two elements is the sum of these special numbers, and
 * the other is an outlier.
 *
 * An outlier is defined as a number that is neither one of the original special numbers nor the
 * element representing the sum of those numbers.
 *
 * Note that special numbers, the sum element, and the outlier must have distinct indices, but may
 * share the same value.
 *
 * Return the largest potential outlier in nums.
 */

public class Solution {

    public int getLargestOutlier(int[] nums) {
        int sum = 0; Map<Integer, Integer> m = new HashMap<>();
        for (var v : nums) { sum += v; m.merge(v, 1, Integer::sum); }

        int ans = Integer.MIN_VALUE;
        // 枚举异常值: 既不是原始特殊数字之一, 也不是表示这些数字元素和的数字
        for (var e : m.entrySet()) {
            // 总和减去异常值之后就是两倍的元素和
            int remain = sum - e.getKey();
            if (remain % 2 != 0) continue;

            // 而且必须存在元素和这个数字
            if (!m.containsKey(remain / 2)) continue;

            // 如果元素和和异常值一样的话, 那得有2个
            if (remain / 2 == e.getKey() && e.getValue() == 1) continue;

            ans = Math.max(ans, e.getKey());
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().getLargestOutlier(new int[]{-108,-108,-517}) == -517;

        assert new Solution().getLargestOutlier(new int[]{2,3,5,10}) == 10;
        assert new Solution().getLargestOutlier(new int[]{-2,-1,-3,-6,4}) == 4;
        assert new Solution().getLargestOutlier(new int[]{1,1,1,1,1,5,5}) == 5;
    }

}
