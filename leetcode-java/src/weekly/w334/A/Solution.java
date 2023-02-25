package weekly.w334.A;

public class Solution {

    public int[] leftRigthDifference(int[] nums) {
        int[] left = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            left[i] += left[i - 1] + nums[i - 1];
        }

        int[] right = new int[nums.length];
        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] += right[i + 1] + nums[i + 1];
        }

        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = Math.abs(left[i] - right[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
