package problem.p1461checkifastringcontainsallbinarycodesofsizek;

import java.util.HashSet;
import java.util.Set;

/**
 * 1461. Check If a String Contains All Binary Codes of Size K
 *
 * https://leetcode.cn/problems/check-if-a-string-contains-all-binary-codes-of-size-k/
 *
 * Given a binary string s and an integer k, return true if every binary code of
 * length k is a substring of s. Otherwise, return false.
 */

public class Solution {

    public boolean hasAllCodes(String s, int k) {
        int mask = (1 << k) - 1;
        char[] chars = s.toCharArray();
        int curr = 0, n = chars.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            curr = ((curr << 1) | (chars[i] == '1' ? 1 : 0)) & mask;
            if (i >= k - 1) set.add(curr);
        }
        return (1 << k) == set.size();
    }

    public static void main(String[] args) {
        assert new Solution().hasAllCodes("00110110", 2);
        assert new Solution().hasAllCodes("0110", 1);
        assert !new Solution().hasAllCodes("0110", 2);
    }

}
