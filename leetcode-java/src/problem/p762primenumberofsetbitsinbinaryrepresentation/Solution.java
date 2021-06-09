package problem.p762primenumberofsetbitsinbinaryrepresentation;

/**
 * 762. Prime Number of Set Bits in Binary Representation
 *
 * https://leetcode-cn.com/problems/prime-number-of-set-bits-in-binary-representation/
 *
 * Given two integers left and right, find the count of numbers in the range [left, right] (inclusive)
 * having a prime number of set bits in their binary representation.
 *
 * (Recall that the number of set bits an integer has is the number of 1s present when written in binary.
 * For example, 21 written in binary is 10101 which has 3 set bits. Also, 1 is not a prime.)
 */

public class Solution {

    private static int[] primes = new int[32];

    static {
        primes[2] = primes[3] = primes[5] = primes[7] = 1;
        primes[11] = primes[13] = primes[17] = primes[19] = 1;
    }

    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            ans += primes[Integer.bitCount(i)];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countPrimeSetBits(6, 10) == 4;
        assert new Solution().countPrimeSetBits(10, 15) == 5;
    }

}
