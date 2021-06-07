package problem.p744findsmallestlettergreaterthantarget;

/**
 * 744. Find Smallest Letter Greater Than Target
 *
 * https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/
 *
 * Given a characters array letters that is sorted in non-decreasing order and a character target,
 * return the smallest character in the array that is larger than target.
 *
 * Note that the letters wrap around.
 *
 * For example, if target == 'z' and letters == ['a', 'b'], the answer is 'a'.
 */

public class Solution {

    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        if (letters[n - 1] <= target || letters[0] > target) return letters[0];
        for (int i = 1; i < n; i++) {
            if (letters[i] > target) {
                return letters[i];
            }
        }
        return '-'; // unreached
    }

    public static void main(String[] args) {
        assert new Solution().nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'a') == 'c';
        assert new Solution().nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'c') == 'f';
        assert new Solution().nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'd') == 'f';
        assert new Solution().nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'g') == 'j';
        assert new Solution().nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'j') == 'c';
        assert new Solution().nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'k') == 'c';
    }

}
