package daily.d211117p318maximumproductofwordlengths;

import java.util.Arrays;
import java.util.Map;

/**
 * 318. Maximum Product of Word Lengths
 *
 * https://leetcode-cn.com/problems/maximum-product-of-word-lengths/
 *
 * Given a string array words, return the maximum value of length(word[i]) * length(word[j])
 * where the two words do not share common letters. If no such two words exist, return 0.
 */

public class Solution {

    public int maxProduct(String[] words) {
        long[] map = new long[words.length];
        for (int i = 0; i < words.length; i++) {
            map[i] = ((long)words[i].length()) << 32;
            for (var c : words[i].toCharArray()) {
                map[i] |= 1 << (c - 'a');
            }
        }
        Arrays.sort(map);

        long ans = 0;
        final long MOD = ((long) 1 << 32) - 1;
        for (int i = map.length - 1; i >= 0; i--) {
            if ((map[i] >> 32) * (map[i] >> 32) <= ans) break;
            for (int j = i - 1; j >= 0; j--) {
                if ((map[i] & map[j] & MOD) == 0) {
                    ans = Math.max(ans, (map[i] >> 32) * (map[j] >> 32));
                }
            }
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxProduct(new String[]{"abcw","baz","foo","bar","xtfn","abcdef"}) == 16;
        assert new Solution().maxProduct(new String[]{"a","ab","abc","d","cd","bcd","abcd"}) == 4;
        assert new Solution().maxProduct(new String[]{"a","aa","aaa","aaaa"}) == 0;
    }

}
