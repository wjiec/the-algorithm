package problem.p1003checkifwordisvalidaftersubstitutions;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1003. Check If Word Is Valid After Substitutions
 *
 * https://leetcode.cn/problems/check-if-word-is-valid-after-substitutions/
 *
 * Given a string s, determine if it is valid.
 *
 * A string s is valid if, starting with an empty string t = "", you can transform t into s after
 * performing the following operation any number of times:
 *
 * Insert string "abc" into any position in t. More formally, t becomes tleft + "abc" + tright,
 * where t == tleft + tright. Note that tleft and tright may be empty.
 *
 * Return true if s is a valid string, otherwise, return false.
 */

public class Solution {

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (var c : s.toCharArray()) {
            if (c == 'c') {
                if (stack.isEmpty() || stack.pop() != 'b') return false;
                if (stack.isEmpty() || stack.pop() != 'a') return false;
            } else stack.push(c);
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        assert new Solution().isValid("aabcbc");
        assert new Solution().isValid("abcabcababcc");
        assert !new Solution().isValid("abccba");
    }

}
