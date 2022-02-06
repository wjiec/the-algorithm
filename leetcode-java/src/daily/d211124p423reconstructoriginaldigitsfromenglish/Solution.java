package daily.d211124p423reconstructoriginaldigitsfromenglish;

/**
 * 423. Reconstruct Original Digits from English
 *
 * https://leetcode-cn.com/problems/reconstruct-original-digits-from-english/
 *
 * Given a string s containing an out-of-order English representation of digits 0-9,
 * return the digits in ascending order.
 */

public class Solution {

    /**
     * zero     //  z
     * one      // o | 0 2 4
     * two      // w
     * three    // t | 2
     * four     // u
     * five     // f | 4
     * six      // x
     * seven    // s | 6
     * eight    // g
     * nine     // i | 5 6
     */
    public String originalDigits(String s) {
        int[] chars = new int[255];
        for (var c : s.toCharArray()) {
            chars[c]++;
        }

        int[] map = new int[10];

        // unique

        if (chars['z'] != 0) { // 0
            map[0] = chars['z'];
            chars['z'] -= map[0];
            chars['e'] -= map[0];
            chars['r'] -= map[0];
            chars['o'] -= map[0];
        }

        if (chars['w'] != 0) {
            map[2] = chars['w']; // 2
            chars['t'] -= map[2];
            chars['w'] -= map[2];
            chars['o'] -= map[2];
        }

        if (chars['u'] != 0) {
            map[4] = chars['u']; // 4
            chars['f'] -= map[4];
            chars['o'] -= map[4];
            chars['u'] -= map[4];
            chars['r'] -= map[4];
        }

        if (chars['x'] != 0) {
            map[6] = chars['x']; // 6
            chars['s'] -= map[6];
            chars['i'] -= map[6];
            chars['x'] -= map[6];
        }

        if (chars['g'] != 0) {
            map[8] = chars['g']; // 8
            chars['e'] -= map[8];
            chars['i'] -= map[8];
            chars['g'] -= map[8];
            chars['h'] -= map[8];
            chars['t'] -= map[8];
        }

        // filter

        if (chars['o'] != 0) {
            map[1] = chars['o']; // 1
            chars['o'] -= map[1];
            chars['n'] -= map[1];
            chars['e'] -= map[1];
        }

        if (chars['t'] != 0) {
            map[3] = chars['t']; // 3
            chars['t'] -= map[3];
            chars['h'] -= map[3];
            chars['r'] -= map[3];
            chars['e'] -= map[3];
            chars['e'] -= map[3];
        }

        if (chars['f'] != 0) {
            map[5] = chars['f']; // 5
            chars['f'] -= map[5];
            chars['i'] -= map[5];
            chars['v'] -= map[5];
            chars['e'] -= map[5];
        }

        if (chars['s'] != 0) {
            map[7] = chars['s']; // 7
            chars['s'] -= map[7];
            chars['e'] -= map[7];
            chars['v'] -= map[7];
            chars['e'] -= map[7];
            chars['n'] -= map[7];
        }

        if (chars['i'] != 0) {
            map[9] = chars['i']; // 9
            chars['n'] -= map[9];
            chars['i'] -= map[9];
            chars['n'] -= map[9];
            chars['e'] -= map[9];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            sb.append(String.valueOf((char) ('0' + i)).repeat(map[i]));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().originalDigits("owoztneoer").equals("012");
        assert new Solution().originalDigits("fviefuro").equals("45");
    }

}
