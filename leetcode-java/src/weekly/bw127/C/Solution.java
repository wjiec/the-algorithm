package weekly.bw127.C;

public class Solution {

    public int minimumSubarrayLength(int[] nums, int k) {
        int[] bits = new int[32]; int val = 0;
        int ans = Integer.MAX_VALUE;
        for (int l = 0, r = 0; r < nums.length; r++) {
            for (int i = 0; i < 32; i++) {
                if ((nums[r] & (1 << i)) != 0) {
                    if (bits[i]++ == 0) val |= 1 << i;
                }
            }

            for (; val >= k && l <= r; l++) {
                ans = Math.min(ans, r - l + 1);
                for (int i = 0; i < 32; i++) {
                    if ((nums[l] & (1 << i)) != 0) {
                        if (--bits[i] == 0) val &= ~(1 << i);
                    }
                }
            }
        }
        return ans > nums.length ? -1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumSubarrayLength(new int[]{1,2,3}, 2) == 1;
        assert new Solution().minimumSubarrayLength(new int[]{2,1,8}, 10) == 3;
        assert new Solution().minimumSubarrayLength(new int[]{1,2}, 0) == 1;
    }

}
