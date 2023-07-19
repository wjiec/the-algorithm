package weekly.w354.A;

public class Solution {

    public int sumOfSquares(int[] nums) {
        int sum = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (n % (i + 1) == 0) sum += nums[i] * nums[i];
        }
        return sum;
    }

    public static void main(String[] args) {
    }

}
