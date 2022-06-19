package weekly.w298.A;

import java.util.HashSet;
import java.util.Set;

/**
 * 5242. Greatest English Letter in Upper and Lower Case
 *
 * https://leetcode.cn/contest/weekly-contest-298/problems/greatest-english-letter-in-upper-and-lower-case/
 *
 * Given a string of English letters s, return the greatest English letter which occurs as both a lowercase
 * and uppercase letter in s. The returned letter should be in uppercase.
 *
 * If no such letter exists, return an empty string.
 *
 * An English letter b is greater than another letter a if b appears after a in the English alphabet.
 */

public class Solution {

    public String greatestLetter(String s) {
        Set<Character> set = new HashSet<>();
        for (var c : s.toCharArray()) set.add(c);
        for (char c = 'Z'; c >= 'A'; c--) {
            if (set.contains(c) && set.contains((char) (c + 32))) {
                return String.valueOf(c);
            }
        }
        return "";
    }

    public static void main(String[] args) {
    }

}
