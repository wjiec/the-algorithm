package problem.p1974minimumtimetotypewordusingspecialtypewriter;

/**
 * 1974. Minimum Time to Type Word Using Special Typewriter
 *
 * https://leetcode-cn.com/problems/minimum-time-to-type-word-using-special-typewriter/
 *
 * There is a special typewriter with lowercase English letters 'a' to 'z' arranged in a circle with a pointer.
 *
 * A character can only be typed if the pointer is pointing to that character.
 *
 * The pointer is initially pointing to the character 'a'.
 */

public class Solution {

    public int minTimeToType(String word) {
        int ans = 0;
        char curr = 'a';
        for (var c : word.toCharArray()) {
            int dist = Math.abs(c - curr);
            ans += (dist <= 13 ? dist : (26 - dist)) + 1;
            curr = c;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minTimeToType("abc") == 5;
        assert new Solution().minTimeToType("bza") == 7;
        assert new Solution().minTimeToType("zjpc") == 34;
    }

}
