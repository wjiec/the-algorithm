package problem.p1189maximumnumberofballoons;

/**
 * 1189. Maximum Number of Balloons
 *
 * https://leetcode-cn.com/problems/maximum-number-of-balloons/
 *
 * Given a string text, you want to use the characters
 * of text to form as many instances of the word "balloon" as possible.
 *
 * You can use each character in text at most once. Return the maximum number of instances that can be formed.
 */

public class Solution {

    public int maxNumberOfBalloons(String text) {
        int[] chars = new int[255];
        for (var c : text.toCharArray()) chars[c]++;

        int ans = Integer.MAX_VALUE;
        ans = Math.min(ans, chars['b']);
        ans = Math.min(ans, chars['a']);
        ans = Math.min(ans, chars['l'] / 2);
        ans = Math.min(ans, chars['o'] / 2);
        ans = Math.min(ans, chars['n']);

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxNumberOfBalloons("nlaebolko") == 1;
        assert new Solution().maxNumberOfBalloons("loonbalxballpoon") == 2;
        assert new Solution().maxNumberOfBalloons("leetcode") == 0;
    }

}
