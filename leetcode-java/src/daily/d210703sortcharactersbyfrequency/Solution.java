package daily.d210703sortcharactersbyfrequency;

import java.util.Arrays;

/**
 * 451. Sort Characters By Frequency
 *
 * https://leetcode-cn.com/problems/sort-characters-by-frequency/
 *
 * Given a string s, sort it in decreasing order based on the frequency of characters, and return the sorted string.
 */

public class Solution {

    public String frequencySort(String s) {
        int[] chars = new int[255];
        for (var c : s.toCharArray()) chars[c] = (chars[c] + 0x100) | c;
        Arrays.sort(chars);

        StringBuilder sb = new StringBuilder();
        for (var n : chars) {
            if (n == 0) continue;
            sb.append(String.valueOf((char) (n & 0xff)).repeat(n >> 8));
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        assert new Solution().frequencySort("tree").equals("eetr");
        assert new Solution().frequencySort("cccaaa").equals("cccaaa");
        assert new Solution().frequencySort("Aabb").equals("bbaA");
    }

}
