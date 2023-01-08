package weekly.w327.C;

/**
 * 6284. Make Number of Distinct Characters Equal
 *
 * https://leetcode.cn/contest/weekly-contest-327/problems/make-number-of-distinct-characters-equal/
 *
 * You are given two 0-indexed strings word1 and word2.
 *
 * A move consists of choosing two indices i and j such that 0 <= i < word1.length
 * and 0 <= j < word2.length and swapping word1[i] with word2[j].
 *
 * Return true if it is possible to get the number of distinct characters in word1
 * and word2 to be equal with exactly one move. Return false otherwise.
 */

public class Solution {

    public boolean isItPossible(String word1, String word2) {
        int[] a = new int[128], b = new int[128];
        for (var c : word1.toCharArray()) a[c]++;
        for (var c : word2.toCharArray()) b[c]++;

        for (char x = 'a'; x <= 'z'; x++) {
            if (a[x] == 0) continue;
            for (char y = 'a'; y <= 'z'; y++) {
                if (b[y] == 0) continue;

                a[x]--; a[y]++; b[x]++; b[y]--;
                if (count(a) == count(b)) return true;
                a[x]++; a[y]--; b[x]--; b[y]++;
            }
        }
        return false;
    }

    private int count(int[] map) {
        int ans = 0;
        for (char i = 'a'; i <= 'z'; i++) {
            if (map[i] != 0) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
