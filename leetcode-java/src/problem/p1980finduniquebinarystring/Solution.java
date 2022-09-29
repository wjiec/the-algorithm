package problem.p1980finduniquebinarystring;

import java.util.HashSet;
import java.util.Set;

/**
 * 1980. Find Unique Binary String
 *
 * https://leetcode.cn/problems/find-unique-binary-string/
 *
 * Given an array of strings nums containing n unique binary strings each of length n, return
 * a binary string of length n that does not appear in nums. If there are multiple answers, you
 * may return any of them.
 */

public class Solution {

    public String findDifferentBinaryString(String[] nums) {
        int n = nums[0].length();
        Set<Integer> uniq = new HashSet<>();
        for (var num : nums) uniq.add(Integer.parseInt(num, 2));

        for (int i = 1; i < (1 << n) - 1; i++) {
            if (!uniq.contains(i)) {
                String s = Integer.toBinaryString(i);
                while (s.length() != n) {
                    s = "0" + s;
                }
                return s;
            }
        }
        return "unreached";
    }

    public static void main(String[] args) {
        assert new Solution().findDifferentBinaryString(new String[]{"01","10"}).equals("11");
        assert new Solution().findDifferentBinaryString(new String[]{"00","01"}).equals("11");
        assert new Solution().findDifferentBinaryString(new String[]{"111","011","001"}).equals("101");
    }

}
