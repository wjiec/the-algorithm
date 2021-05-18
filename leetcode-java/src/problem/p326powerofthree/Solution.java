package problem.p326powerofthree;

public class Solution {

    public boolean isPowerOfThree(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }

    public static void main(String[] args) {
        assert new Solution().isPowerOfThree(27);
        assert !new Solution().isPowerOfThree(0);
        assert new Solution().isPowerOfThree(9);
        assert !new Solution().isPowerOfThree(45);
    }

}
