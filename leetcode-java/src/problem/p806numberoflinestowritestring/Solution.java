package problem.p806numberoflinestowritestring;

import common.Checker;

/**
 * 806. Number of Lines To Write String
 *
 * https://leetcode-cn.com/problems/number-of-lines-to-write-string/
 *
 * You are given a string s of lowercase English letters and an array widths denoting
 * how many pixels wide each lowercase English letter is.
 * Specifically, widths[0] is the width of 'a', widths[1] is the width of 'b', and so on.
 *
 * You are trying to write s across several lines, where each line is no longer than 100 pixels.
 * Starting at the beginning of s, write as many letters on the first line such that
 * the total width does not exceed 100 pixels. Then, from where you stopped in s,
 * continue writing as many letters as you can on the second line.
 * Continue this process until you have written all of s.
 *
 * Return an array result of length 2 where:
 *
 * result[0] is the total number of lines.
 * result[1] is the width of the last line in pixels.
 */

public class Solution {

    public int[] numberOfLines(int[] widths, String s) {
        int l = 1, c = 0;
        for (var ch : s.toCharArray()) {
            int w = widths[ch - 'a'];
            if (c + w > 100) {
                l++;
                c = 0;
            }
            c += w;
        }
        return new int[]{l, c};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().numberOfLines(new int[]{
            10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10
        }, "abcdefghijklmnopqrstuvwxyz"), new int[]{3, 60});
        assert Checker.check(new Solution().numberOfLines(new int[]{
            4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10
        }, "bbbcccdddaaa"), new int[]{2, 4});
    }

}
