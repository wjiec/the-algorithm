package weekly.w303.A;

import java.util.HashSet;
import java.util.Set;

/**
 * 6124. First Letter to Appear Twice
 *
 * https://leetcode.cn/contest/weekly-contest-303/problems/first-letter-to-appear-twice/
 *
 * Given a string s consisting of lowercase English letters, return the first letter to appear twice.
 *
 * Note:
 *
 * A letter a appears twice before another letter b if the second
 * occurrence of a is before the second occurrence of b.
 *
 * s will contain at least one letter that appears twice.
 */

public class Solution {

    public char repeatedCharacter(String s) {
        Set<Character> characters = new HashSet<>();
        for (var c : s.toCharArray()) {
            if (characters.contains(c)) {
                return c;
            }
            characters.add(c);
        }
        return '-';
    }

    public static void main(String[] args) {
    }

}
