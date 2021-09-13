package problem.p1370increasingdecreasingstring;

/**
 * 1370. Increasing Decreasing String
 *
 * https://leetcode-cn.com/problems/increasing-decreasing-string/
 *
 * Given a string s. You should re-order the string using the following algorithm:
 *
 * Pick the smallest character from s and append it to the result.
 * Pick the smallest character from s which is greater than the last appended character to the result and append it.
 * Repeat step 2 until you cannot pick more characters.
 * Pick the largest character from s and append it to the result.
 * Pick the largest character from s which is smaller than the last appended character to the result and append it.
 * Repeat step 5 until you cannot pick more characters.
 * Repeat the steps from 1 to 6 until you pick all characters from s.
 *
 * In each step, If the smallest or the largest character appears more than once
 * you can choose any occurrence and append it to the result.
 *
 * Return the result string after sorting s with this algorithm.
 */

public class Solution {

    public String sortString(String s) {
        int[] map = new int[255];
        for (var c : s.toCharArray()) map[c]++;

        StringBuilder sb = new StringBuilder();
        while (sb.length() < s.length()) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (map[c] > 0) {
                    sb.append(c);
                    map[c]--;
                }
            }
            for (char c = 'z'; c >= 'a'; c--) {
                if (map[c] > 0) {
                    sb.append(c);
                    map[c]--;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().sortString("aaaabbbbcccc").equals("abccbaabccba");
        assert new Solution().sortString("rat").equals("art");
        assert new Solution().sortString("leetcode").equals("cdelotee");
        assert new Solution().sortString("ggggggg").equals("ggggggg");
        assert new Solution().sortString("spo").equals("ops");
    }

}
