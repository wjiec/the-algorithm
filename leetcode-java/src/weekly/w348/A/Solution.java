package weekly.w348.A;

import java.util.HashSet;
import java.util.Set;

/**
 * 2716. Minimize String Length
 *
 * https://leetcode.cn/contest/weekly-contest-348/problems/minimize-string-length/
 *
 * Given a 0-indexed string s, repeatedly perform the following operation any number of times:
 *
 * Choose an index i in the string, and let c be the character in position i.
 * Delete the closest occurrence of c to the left of i (if any) and the closest
 * occurrence of c to the right of i (if any).
 *
 * Your task is to minimize the length of s by performing the above operation any number of times.
 *
 * Return an integer denoting the length of the minimized string.
 */

public class Solution {

    public int minimizedStringLength(String s) {
        int ans = 0;
        Set<Character> set = new HashSet<>();
        for (var c : s.toCharArray()) {
            if (set.add(c)) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
