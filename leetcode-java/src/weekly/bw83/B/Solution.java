package weekly.bw83.B;

public class Solution {

    public long zeroFilledSubarray(int[] nums) {
        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int c = 1;
                while (i < nums.length && nums[i] == 0) {
                    ans += c;
                    c++; i++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().zeroFilledSubarray(new int[]{1,3,0,0,2,0,0,4}) == 6;
        assert new Solution().zeroFilledSubarray(new int[]{0,0,0,2,0,0}) == 9;
        assert new Solution().zeroFilledSubarray(new int[]{2,10,2019}) == 0;
    }

}
