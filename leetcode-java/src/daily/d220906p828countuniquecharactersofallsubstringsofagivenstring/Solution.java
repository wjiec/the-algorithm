package daily.d220906p828countuniquecharactersofallsubstringsofagivenstring;

import common.TODO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 828. Count Unique Characters of All Substrings of a Given String
 *
 * https://leetcode.cn/problems/count-unique-characters-of-all-substrings-of-a-given-string/
 *
 * Let's define a function countUniqueChars(s) that returns the number of unique characters on s.
 *
 * For example, calling countUniqueChars(s) if s = "LEETCODE" then "L", "T", "C", "O", "D" are the
 * unique characters since they appear only once in s, therefore countUniqueChars(s) = 5.
 * Given a string s, return the sum of countUniqueChars(t) where t is a substring of s. The test
 * cases are generated such that the answer fits in a 32-bit integer.
 *
 * Notice that some substrings can be repeated so in this case you have to count the repeated ones too.
 */

public class Solution {

    @TODO(tips = "单独计算每个字符的贡献")
    public int uniqueLetterString(String s) {
        char[] chars = s.toCharArray();
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            map.computeIfAbsent(chars[i], v -> new ArrayList<>(){{ add(-1); }}).add(i);
        }

        int ans = 0;
        for (var entry : map.entrySet()) {
            List<Integer> index = entry.getValue();
            index.add(chars.length);
            for (int i = 1; i < index.size() - 1; i++) {
                ans += (index.get(i) - index.get(i - 1)) * (index.get(i + 1) - index.get(i));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().uniqueLetterString("ABC") == 10;
        assert new Solution().uniqueLetterString("ABA") == 8;
        assert new Solution().uniqueLetterString("LEETCODE") == 92;
    }

}
