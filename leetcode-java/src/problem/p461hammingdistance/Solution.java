package problem.p461hammingdistance;

/**
 * 461. Hamming Distance
 *
 * https://leetcode-cn.com/problems/hamming-distance/
 *
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 *
 * Given two integers x and y, return the Hamming distance between them.
 */

public class Solution {

    public int hammingDistance(int x, int y) {
        if (x == y) return 0;

        int cnt = 0;
        for (int i = 0; i < 32; i++) {
            if (((x >> i) & 0x1) != ((y >> i) & 0x1)) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        assert new Solution().hammingDistance(1, 4) == 2;
        assert new Solution().hammingDistance(3, 1) == 1;
    }

}
