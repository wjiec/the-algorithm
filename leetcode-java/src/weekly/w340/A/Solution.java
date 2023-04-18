package weekly.w340.A;

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int diagonalPrime(int[][] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (isPrime(nums[i][i])) ans = Math.max(ans, nums[i][i]);
            if (isPrime(nums[i][n - i - 1])) ans = Math.max(ans, nums[i][n - i - 1]);
        }
        return ans;
    }

    private boolean isPrime(int n) {
        if (n < 3 || n % 2 == 0) return n == 2;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
