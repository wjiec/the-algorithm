package weekly.bw89.C;

public class Solution {

    public int minimizeArrayValue(int[] nums) {
        long l = 0, r = 0, ans = 0;
        for (var n : nums) r = Math.max(r, n);

        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (check(nums, mid)) {
                ans = mid;
                r = mid - 1;
            } else l = mid + 1;
        }
        return (int) ans;
    }

    private boolean check(int[] nums, long target) {
        long carry = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] + carry > target) {
                carry = nums[i] + carry - target;
            } else carry = 0;
        }
        return nums[0] + carry <= target;
    }

    public static void main(String[] args) {
        assert new Solution().minimizeArrayValue(new int[]{3,7,1,6}) == 5;
    }

}
