package problem.p2053kthdistinctstringinanarray;

import java.util.HashMap;
import java.util.Map;

/**
 * 2053. Kth Distinct String in an Array
 *
 * https://leetcode-cn.com/problems/kth-distinct-string-in-an-array/
 *
 * A distinct string is a string that is present only once in an array.
 *
 * Given an array of strings arr, and an integer k, return the kth distinct string present in arr.
 *
 * If there are fewer than k distinct strings, return an empty string "".
 *
 * Note that the strings are considered in the order in which they appear in the array.
 */

public class Solution {

    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (var s : arr) map.merge(s, 1, Integer::sum);

        for (var s : arr) if (map.get(s) == 1 && --k == 0) return s;
        return "";
    }

    public static void main(String[] args) {
        assert new Solution().kthDistinct(new String[]{"d","b","c","b","c","a"}, 2).equals("a");
        assert new Solution().kthDistinct(new String[]{"aaa","aa","a"}, 1).equals("aaa");
        assert new Solution().kthDistinct(new String[]{"a","b","a"}, 3).equals("");
    }

}
