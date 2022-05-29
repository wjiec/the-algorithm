package weekly.w295.A;

/**
 * 6078. Rearrange Characters to Make Target String
 *
 * https://leetcode.cn/contest/weekly-contest-295/problems/rearrange-characters-to-make-target-string/
 *
 * You are given two 0-indexed strings s and target. You can take some letters
 * from s and rearrange them to form new strings.
 *
 * Return the maximum number of copies of target that can be formed by
 * taking letters from s and rearranging them.
 */

public class Solution {

    public int rearrangeCharacters(String s, String target) {
        int[] map = new int[256];
        for (var c : s.toCharArray()) map[c]++;

        int[] req = new int[256];
        for (var c : target.toCharArray()) req[c]++;

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < req.length; i++) {
            if (req[i] != 0) {
                ans = Math.min(ans, map[i] / req[i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
