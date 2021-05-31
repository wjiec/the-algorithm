package problem.p696countbinarysubstrings;

import java.util.ArrayList;
import java.util.List;

/**
 * 696. Count Binary Substrings
 *
 * https://leetcode-cn.com/problems/count-binary-substrings/
 *
 * Give a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's,
 * and all the 0's and all the 1's in these substrings are grouped consecutively.
 *
 * Substrings that occur multiple times are counted the number of times they occur.
 */

public class Solution {

    public int countBinarySubstrings(String s) {
        List<Integer> types = new ArrayList<>();
        int n = s.length(), ans = 0, b = s.charAt(0), cnt = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) != b) {
                types.add(cnt);

                cnt = 1;
                b = s.charAt(i);
            } else {
                cnt++;
            }
        }
        types.add(cnt);

        for (int i = 1; i < types.size(); i++) {
            ans += Math.min(types.get(i - 1), types.get(i));
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countBinarySubstrings("00110011") == 6;
        assert new Solution().countBinarySubstrings("10101") == 4;
    }

}
