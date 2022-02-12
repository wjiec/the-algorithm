package problem.p434numberofsegmentsinastring;

/**
 * 434. Number of Segments in a String
 *
 * https://leetcode-cn.com/problems/number-of-segments-in-a-string/
 *
 * You are given a string s, return the number of segments in the string. 
 *
 * A segment is defined to be a contiguous sequence of non-space characters.
 */

public class Solution {

    public int countSegments(String s) {
        s = s.trim();
        if (s.length() == 0) return 0;
        return s.split("\\s+").length;
    }

    public static void main(String[] args) {
        assert new Solution().countSegments("Hello, my name is John") == 5;
        assert new Solution().countSegments("Hello") == 1;
        assert new Solution().countSegments("love live! mu'sic forever") == 4;
        assert new Solution().countSegments("") == 0;
        assert new Solution().countSegments(" foo    bar") == 2;
    }

}
