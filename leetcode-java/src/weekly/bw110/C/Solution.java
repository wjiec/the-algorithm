package weekly.bw110.C;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2808. Minimum Seconds to Equalize a Circular Array
 *
 * https://leetcode.cn/contest/biweekly-contest-110/problems/minimum-seconds-to-equalize-a-circular-array/
 *
 * You are given a 0-indexed array nums containing n integers.
 *
 * At each second, you perform the following operation on the array:
 *
 * For every index i in the range [0, n - 1], replace nums[i] with either nums[i],
 * nums[(i - 1 + n) % n], or nums[(i + 1) % n].
 *
 * Note that all the elements get replaced simultaneously.
 *
 * Return the minimum number of seconds needed to make all elements in the array nums equal.
 */

public class Solution {

    public int minimumSeconds(List<Integer> nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            map.computeIfAbsent(nums.get(i), v -> new ArrayList<>()).add(i);
        }

        int ans = nums.size();
        for (var v : map.values()) {
            int curr = 0;
            int prev = v.get(v.size() - 1) - nums.size();
            for (var x : v) {
                curr = Math.max(curr, (x - prev) / 2);
                prev = x;
            }
            ans = Math.min(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumSeconds(List.of(1,2,1,2)) == 1;
        assert new Solution().minimumSeconds(List.of(2,1,3,3,2)) == 2;
        assert new Solution().minimumSeconds(List.of(5,5,5,5)) == 0;
    }

}
