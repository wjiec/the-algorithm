package weekly.w396.A;

/**
 * 3136. Valid Word
 *
 * https://leetcode.cn/contest/weekly-contest-396/problems/valid-word/
 *
 * A word is considered valid if:
 *
 * It contains a minimum of 3 characters.
 * It consists of the digits 0-9, and the uppercase and lowercase English letters. (Not necessary to have all of them.)
 * It includes at least one vowel.
 * It includes at least one consonant.
 * You are given a string word.
 *
 * Return true if word is valid, otherwise, return false.
 *
 * Notes:
 *
 * 'a', 'e', 'i', 'o', 'u', and their uppercases are vowels.
 * A consonant is an English letter that is not a vowel.
 */

public class Solution {

    public boolean isValid(String word) {
        int digits = 0, letter = 0, vowel = 0;
        for (var c : word.toCharArray()) {
            switch (c) {
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> digits++;
                case 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' -> vowel++;
                case '@', '#', '$' -> { return false; }
                default -> letter++;
            }
        }
        return word.length() >= 3 && vowel != 0 && letter != 0;
    }

    public static void main(String[] args) {
    }

}
