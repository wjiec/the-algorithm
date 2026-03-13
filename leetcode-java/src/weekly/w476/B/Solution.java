package weekly.w476.B;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Q2. Minimum String Length After Balanced Removals
 *
 * https://leetcode.cn/contest/weekly-contest-476/problems/minimum-string-length-after-balanced-removals/
 *
 * You are given a string s consisting only of the characters 'a' and 'b'.
 *
 * You are allowed to repeatedly remove any substring where the number of 'a' characters
 * is equal to the number of 'b' characters. After each removal, the remaining parts of
 * the string are concatenated together without gaps.
 *
 * Return an integer denoting the minimum possible length of the string
 * after performing any number of such operations.
 */

public class Solution {

    public int minLengthAfterRemovals(String s) {
        Deque<Integer> st = new ArrayDeque<>();
        for (var c : s.toCharArray()) {
            int v = c == 'a' ? 1 : -1;
            if (!st.isEmpty()) {
                int nv = st.pop() + v;
                if (nv != 0) st.push(nv);
            } else st.push(v);
        }

        int ans = 0;
        while (!st.isEmpty()) ans += Math.abs(st.pop());
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minLengthAfterRemovals("bb") == 2;

        assert new Solution().minLengthAfterRemovals("aabbab") == 0;
        assert new Solution().minLengthAfterRemovals("aaaa") == 4;
        assert new Solution().minLengthAfterRemovals("aaabb") == 1;
    }

}
