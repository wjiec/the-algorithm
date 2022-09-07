package problem.p1781sumofbeautyofallsubstrings;

/**
 * 1781. Sum of Beauty of All Substrings
 *
 * https://leetcode.cn/problems/sum-of-beauty-of-all-substrings/
 *
 * The beauty of a string is the difference in frequencies between the most
 * frequent and least frequent characters.
 *
 * For example, the beauty of "abaacc" is 3 - 1 = 2.
 * Given a string s, return the sum of beauty of all of its substrings.
 */

public class Solution {

    public int beautySum(String s) {
        int ans = 0, n = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            int[] count = new int[128];
            for (int j = i; j < n; j++) {
                count[chars[j]]++;

                int mx = 0, mi = n + 1;
                for (int k = 'a'; k <= 'z'; k++) {
                    if (count[k] > mx) mx = count[k];
                    if (count[k] > 0 && count[k] < mi) mi = count[k];
                }
                ans += mx - mi;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().beautySum("aabcb") == 5;
        assert new Solution().beautySum("aabcbaa") == 17;
    }

}
