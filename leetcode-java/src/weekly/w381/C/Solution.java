package weekly.w381.C;

import java.util.Arrays;

/**
 * 3016. Minimum Number of Pushes to Type Word II
 *
 * https://leetcode.cn/contest/weekly-contest-381/problems/minimum-number-of-pushes-to-type-word-ii/
 *
 * You are given a string word containing distinct lowercase English letters.
 *
 * Telephone keypads have keys mapped with distinct collections of lowercase
 * English letters, which can be used to form words by pushing them.
 *
 * For example, the key 2 is mapped with ["a","b","c"], we need to push the
 * key one time to type "a", two times to type "b", and three times to type "c" .
 *
 * It is allowed to remap the keys numbered 2 to 9 to distinct collections of letters.
 * The keys can be remapped to any amount of letters, but each letter must be mapped
 * to exactly one key. You need to find the minimum number of times the keys
 * will be pushed to type the string word.
 *
 * Return the minimum number of pushes needed to type word after remapping the keys.
 *
 * An example mapping of letters to keys on a telephone keypad is given below.
 *
 * Note that 1, *, #, and 0 do not map to any letters.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int minimumPushes(String word) {
        int[] freq = new int[26];
        for (var c : word.toCharArray()) freq[c - 'a']++;

        Integer[] sorted = new Integer[26];
        for (int i = 0; i < sorted.length; i++) sorted[i] = i;
        Arrays.sort(sorted, (a, b) -> freq[b] - freq[a]);

        int[] map = new int[128];
        for (int i = 0; i < 8; i++) map['a' + sorted[i]] = 1;
        for (int i = 8; i < 16; i++) map['a' + sorted[i]] = 2;
        for (int i = 16; i < 24; i++) map['a' + sorted[i]] = 3;
        for (int i = 24; i < 26; i++) map['a' + sorted[i]] = 4;

        int ans = 0;
        for (var c : word.toCharArray()) ans += map[c];
        return ans;
    }

    public static void main(String[] args) {
    }

}
