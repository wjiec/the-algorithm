package problem.p2125numberoflaserbeamsinabank;

/**
 * 2125. Number of Laser Beams in a Bank
 *
 * https://leetcode.cn/problems/number-of-laser-beams-in-a-bank/
 *
 * Anti-theft security devices are activated inside a bank. You are given a 0-indexed binary string
 * array bank representing the floor plan of the bank, which is an m x n 2D matrix. bank[i] represents
 * the ith row, consisting of '0's and '1's. '0' means the cell is empty, while'1' means the cell
 * has a security device.
 *
 * There is one laser beam between any two security devices if both conditions are met:
 *
 * The two devices are located on two different rows: r1 and r2, where r1 < r2.
 * For each row i where r1 < i < r2, there are no security devices in the ith row.
 * Laser beams are independent, i.e., one beam does not interfere nor join with another.
 *
 * Return the total number of laser beams in the bank.
 */

public class Solution {

    public int numberOfBeams(String[] bank) {
        int ans = 0, prev = 0;
        for (String b : bank) {
            int curr = 0;
            for (var c : b.toCharArray()) {
                if (c == '1') curr++;
            }

            if (curr != 0) {
                if (prev != 0) ans += prev * curr;
                prev = curr;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numberOfBeams(new String[]{"011001","000000","010100","001000"}) == 8;
        assert new Solution().numberOfBeams(new String[]{"000","111","000"}) == 0;
    }

}
