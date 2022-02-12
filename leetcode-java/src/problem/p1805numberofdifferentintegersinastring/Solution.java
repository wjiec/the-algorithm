package problem.p1805numberofdifferentintegersinastring;

import java.util.HashSet;
import java.util.Set;

/**
 * 1805. Number of Different Integers in a String
 *
 * https://leetcode-cn.com/problems/number-of-different-integers-in-a-string/
 *
 * You are given a string word that consists of digits and lowercase English letters.
 *
 * You will replace every non-digit character with a space.
 *
 * For example, "a123bc34d8ef34" will become " 123  34 8  34".
 *
 * Notice that you are left with some integers that are separated by at least one space: "123", "34", "8", and "34".
 *
 * Return the number of different integers after performing the replacement operations on word.
 *
 * Two integers are considered different if their decimal representations without any leading zeros are different.
 */

public class Solution {

    public int numDifferentIntegers(String word) {
        word += "a";
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (var c : word.toCharArray()) {
            if ('0' <= c && c <= '9') {
                sb.append(c);
            } else if (sb.length() != 0) {
                while (sb.length() > 1 && sb.charAt(0) == '0') {
                    sb.deleteCharAt(0);
                }

                set.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        assert new Solution().numDifferentIntegers("0a0") == 1;
        assert new Solution().numDifferentIntegers("1a0") == 2;
        assert new Solution().numDifferentIntegers("035985750011523523129774573439111590559325a1554234973") == 2;

        assert new Solution().numDifferentIntegers("a123bc34d8ef34") == 3;
        assert new Solution().numDifferentIntegers("leet1234code234") == 2;
        assert new Solution().numDifferentIntegers("a1b01c001") == 1;
    }

}
