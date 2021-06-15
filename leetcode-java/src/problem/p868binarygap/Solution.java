package problem.p868binarygap;

/**
 * 868. Binary Gap
 *
 * https://leetcode-cn.com/problems/binary-gap/
 *
 * Given a positive integer n, find and return the longest distance between
 * any two adjacent 1's in the binary representation of n. If there are no two adjacent 1's, return 0.
 *
 * Two 1's are adjacent if there are only 0's separating them (possibly no 0's).
 * The distance between two 1's is the absolute difference between their bit positions.
 * For example, the two 1's in "1001" have a distance of 3.
 */

public class Solution {

    public int binaryGap(int n) {
        int ans = 0;
        for (int curr = 1, last = -1; n != 0; n >>= 1, curr++) {
            if ((n & 0x1) == 1) {
                if (last > 0) ans = Math.max(ans, curr - last);
                last = curr;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().binaryGap(22) == 2;
        assert new Solution().binaryGap(5) == 2;
        assert new Solution().binaryGap(6) == 1;
        assert new Solution().binaryGap(8) == 0;
        assert new Solution().binaryGap(1) == 0;
    }

}
