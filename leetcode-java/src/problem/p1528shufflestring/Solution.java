package problem.p1528shufflestring;

/**
 * 1528. Shuffle String
 *
 * https://leetcode-cn.com/problems/shuffle-string/
 *
 * Given a string s and an integer array indices of the same length.
 *
 * The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.
 *
 * Return the shuffled string.
 */

public class Solution {

    public String restoreString(String s, int[] indices) {
        char[] chars = new char[s.length()];
        for (int i = 0; i < indices.length; i++) {
            chars[indices[i]] = s.charAt(i);
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        assert new Solution().restoreString("codeleet", new int[]{4,5,6,7,0,2,1,3}).equals("leetcode");
        assert new Solution().restoreString("abc", new int[]{0,1,2}).equals("abc");
        assert new Solution().restoreString("aiohn", new int[]{3,1,4,2,0}).equals("nihao");
        assert new Solution().restoreString("aaiougrt", new int[]{4,0,2,6,7,3,1,5}).equals("arigatou");
        assert new Solution().restoreString("art", new int[]{1,0,2}).equals("rat");
    }

}
