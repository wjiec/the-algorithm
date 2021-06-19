package daily.d210619p1239maximumlengthofaconcatenatedstringwithuniquecharacters;

import java.util.ArrayList;
import java.util.List;

/**
 * 1239. Maximum Length of a Concatenated String with Unique Characters
 *
 * https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 *
 * Given an array of strings arr. String s is a concatenation of
 * a sub-sequence of arr which have unique characters.
 *
 * Return the maximum possible length of s.
 */

public class Solution {

    public int maxLength(List<String> arr) {
        int ans = 0;
        List<Integer> vals = new ArrayList<>(); vals.add(0);
        for (var s : arr) {
            int val = 0;
            for (var c : s.toCharArray()) {
                int n = c - 'a' + 1;
                if (((val >> n) & 1) == 1) {
                    val = 0; break;
                }
                val |= 1 << (c - 'a' + 1);
            }

            if (val == 0) continue;
            for (int i = 0, n = vals.size(); i < n; i++) {
                if ((val & vals.get(i)) == 0) {
                    vals.add((val | vals.get(i)));
                    ans = Math.max(ans, Integer.bitCount((val | vals.get(i))));
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxLength(List.of("yy","bkhwmpbiisbldzknpm")) == 0;
        assert new Solution().maxLength(List.of(
            "ab","ba","cd","dc","ef","fe","gh","hg","ij","ji","kl","lk","mn","nm","op","po"
        )) == 16;
        assert new Solution().maxLength(List.of("un","iq","ue")) == 4;
        assert new Solution().maxLength(List.of("cha","r","act","ers")) == 6;
        assert new Solution().maxLength(List.of("abcdefghijklmnopqrstuvwxyz")) == 26;
    }

}
