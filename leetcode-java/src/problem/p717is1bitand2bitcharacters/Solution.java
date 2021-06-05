package problem.p717is1bitand2bitcharacters;

/**
 * 717. 1-bit and 2-bit Characters
 *
 * https://leetcode-cn.com/problems/1-bit-and-2-bit-characters/
 *
 * We have two special characters:
 *
 * The first character can be represented by one bit 0.
 * The second character can be represented by two bits (10 or 11).
 * Given a binary array bits that ends with 0, return true if the last character must be a one-bit character.
 */

public class Solution {

    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length - 1, idx = 0;
        while (idx < n) {
            idx += bits[idx] + 1;
        }
        return idx == n;
    }

    public static void main(String[] args) {
        assert !new Solution().isOneBitCharacter(new int[]{1,1,1,1,1,1,1,1,1,0,0,1,0,1,0,1,1,1,0,0,0,1,1,1,0,0,0,1,1,1,0,1,0});
        assert new Solution().isOneBitCharacter(new int[]{1,0,0});
        assert !new Solution().isOneBitCharacter(new int[]{1, 1, 1, 0});
    }

}
