package weekly.w424.D;

public class Solution {

    public int minDifference(int[] nums) {
        int l = 0, r = Integer.MAX_VALUE, ans = 0;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(nums, mid)) {
                ans = mid; l = mid + 1;
            } else r = mid;
        }
        return ans;
    }

    // 判断是否可以通过使用数对 (x, y) 填充 nums 中的所有 -1, 使得最大值为 max
    private boolean check(int[] nums, int max) {
        return false;
    }

    public static void main(String[] args) {
    }

}
