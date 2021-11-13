package problem.p1784checkifbinarystringhasatmostonesegmentofones;

import javax.security.auth.login.CredentialNotFoundException;

/**
 * 1784. Check if Binary String Has at Most One Segment of Ones
 *
 * https://leetcode-cn.com/problems/check-if-binary-string-has-at-most-one-segment-of-ones/
 *
 * Given a binary string s ​​​​​without leading zeros, return true​​​ if s contains at most one contiguous segment of ones.
 *
 * Otherwise, return false.
 */

public class Solution {

    public boolean checkOnesSegment(String s) {
        boolean zero = false;
        for (var c : s.toCharArray()) {
            if (c == '0') zero = true;
            else if (c == '1' && zero) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert !new Solution().checkOnesSegment("1001");
        assert new Solution().checkOnesSegment("110");
    }

}
