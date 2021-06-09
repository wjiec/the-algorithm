package problem.p771jewelsandstones;

/**
 * 771. Jewels and Stones
 *
 * https://leetcode-cn.com/problems/jewels-and-stones/
 *
 * You're given strings jewels representing the types of stones that are jewels,
 * and stones representing the stones you have. Each character in stones is a type of stone you have.
 * You want to know how many of the stones you have are also jewels.
 *
 * Letters are case sensitive, so "a" is considered a different type of stone from "A".
 */

public class Solution {

    public int numJewelsInStones(String jewels, String stones) {
        boolean[] map = new boolean[255];
        for (int i = 0, n = jewels.length(); i < n; i++) {
            map[jewels.charAt(i)] = true;
        }

        int ans = 0, n = stones.length();
        for (int i = 0; i < n; i++) {
            if (map[stones.charAt(i)]) {
                ans ++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numJewelsInStones("aA", "aAAbbbb") == 3;
        assert new Solution().numJewelsInStones("z", "ZZ") == 0;
    }

}
