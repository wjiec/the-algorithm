package problem.p2085countcommonwordswithoneoccurrence;

import java.util.HashMap;
import java.util.Map;

/**
 * 2085. Count Common Words With One Occurrence
 *
 * https://leetcode-cn.com/problems/count-common-words-with-one-occurrence/
 *
 * Given two string arrays words1 and words2, return the number of strings
 * that appear exactly once in each of the two arrays.
 */

public class Solution {

    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
        for (var word : words1) map1.merge(word, 1, Integer::sum);
        for (var word : words2) map2.merge(word, 1, Integer::sum);

        int ans = 0;
        for (var e : map1.entrySet()) {
            if (e.getValue() == 1 && map2.getOrDefault(e.getKey(), 0) == 1)
                ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countWords(new String[]{"leetcode","is","amazing","as","is"}, new String[]{"amazing","leetcode","is"}) == 2;
        assert new Solution().countWords(new String[]{"b","bb","bbb"}, new String[]{"a","aa","aaa"}) == 0;
        assert new Solution().countWords(new String[]{"a","ab"}, new String[]{"a","a","a","ab"}) == 1;
    }

}
