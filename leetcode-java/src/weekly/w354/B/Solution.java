package weekly.w354.B;

import java.util.Arrays;

public class Solution {

    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);

        int ans = 1, n = nums.length;
        for (int l = 0, r = 0, v = 0; v < nums[n - 1]; v++) {
            while (r < n && nums[r] <= v + k) r++;
            while (l < r && nums[l] < v - k) l++;
            ans = Math.max(ans, r - l);
        }
        return ans;
    }

}
