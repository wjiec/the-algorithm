package weekly.w359.A;

import java.util.List;

/**
 * 2828. Check if a String Is an Acronym of Words
 *
 * https://leetcode.cn/contest/weekly-contest-359/problems/check-if-a-string-is-an-acronym-of-words/
 *
 * Given an array of strings words and a string s, determine if s is an acronym of words.
 *
 * The string s is considered an acronym of words if it can be formed by concatenating the first character of
 * each string in words in order. For example, "ab" can be formed from ["apple", "banana"], but it can't be
 * formed from ["bear", "aardvark"].
 *
 * Return true if s is an acronym of words, and false otherwise.
 */

public class Solution {

    public boolean isAcronym(List<String> words, String s) {
        if (s.length() != words.size()) return false;
        for (int i = 0; i < words.size(); i++) {
            if (s.charAt(i) != words.get(i).charAt(0)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
