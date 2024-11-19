package weekly.w424.A;

public class Solution {

    public int countValidSelections(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (validSelection(nums, i , 1)) ans++;
                if (validSelection(nums, i , -1)) ans++;
            }
        }
        return ans;
    }

    private boolean validSelection(int[] nums, int i, int dx) {
        int[] curr = new int[nums.length];
        System.arraycopy(nums, 0, curr, 0, nums.length);

        for (; i >= 0 && i < curr.length; i += dx) {
            if (curr[i] > 0) {
                curr[i]--;
                dx *= -1;
            }
        }

        for (var v : curr) if (v != 0) return false;
        return true;
    }

    public static void main(String[] args) {
    }

}
