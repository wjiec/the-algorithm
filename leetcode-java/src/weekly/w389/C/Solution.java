package weekly.w389.C;

import java.util.ArrayList;
import java.util.List;

/**
 * 3085. Minimum Deletions to Make String K-Special
 *
 * https://leetcode.cn/contest/weekly-contest-389/problems/minimum-deletions-to-make-string-k-special/
 *
 * You are given a string word and an integer k.
 *
 * We consider word to be k-special if |freq(word[i]) - freq(word[j])| <= k for all indices i and j in the string.
 *
 * Here, freq(x) denotes the frequency of the character x in word, and |y| denotes the absolute value of y.
 *
 * Return the minimum number of characters you need to delete to make word k-special.
 */

public class Solution {

    public int minimumDeletions(String word, int k) {
        int[] freq = new int[128];
        for (var c : word.toCharArray()) freq[c]++;

        List<Integer> sort = new ArrayList<>();
        for (var f : freq) if (f != 0) sort.add(f);
        sort.sort(Integer::compare);

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < sort.size(); i++) {
            for (int j = i; j < sort.size(); j++) {
                ans = Math.min(ans, delete(sort, i, j, k));
                System.out.printf("(%d - %d): %d\n", i, j, ans);
            }
        }

        return ans;
    }

    private int delete(List<Integer> array, int l, int r, int k) {
        int ans = 0;
        for (int i = 0; i < array.size(); i++) {
            if (i < l || i > r) ans += array.get(i);
            else ans += Math.max(0, array.get(i) - array.get(l) - k);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumDeletions("aabcaba", 0) == 3;
        assert new Solution().minimumDeletions("dabdcbdcdcd", 2) == 2;
        assert new Solution().minimumDeletions("aaabaaa", 2) == 1;
    }

}
