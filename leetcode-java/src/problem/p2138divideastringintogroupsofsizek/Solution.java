package problem.p2138divideastringintogroupsofsizek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2138. Divide a String Into Groups of Size k
 *
 * https://leetcode-cn.com/problems/divide-a-string-into-groups-of-size-k/
 *
 * A string s can be partitioned into groups of size k using the following procedure:
 *
 * The first group consists of the first k characters of the string, the second group consists of
 * the next k characters of the string, and so on. Each character can be a part of exactly one group.
 *
 * For the last group, if the string does not have k characters remaining,
 * a character fill is used to complete the group.
 *
 * Note that the partition is done so that after removing the fill character from the last group (if it exists)
 * and concatenating all the groups in order, the resultant string should be s.
 *
 * Given the string s, the size of each group k and the character fill, return a string array denoting
 * the composition of every group s has been divided into, using the above procedure.
 */

public class Solution {

    public String[] divideString(String s, int k, char fill) {
        if (s.length() <= k) return new String[]{s + String.valueOf(fill).repeat(k - s.length())};
        if (k == 1) return s.split("");

        StringBuilder sb = new StringBuilder();
        List<String> ans = new ArrayList<>();
        for (var c : s.toCharArray()) {
            sb.append(c);
            if (sb.length() == k) {
                ans.add(sb.toString());
                sb = new StringBuilder();
            }
        }

        while (sb.length() != 0 && sb.length() < k) {
            sb.append(fill);
        }
        if (sb.length() != 0) ans.add(sb.toString());

        return ans.toArray(new String[0]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().divideString("abcdefghi", 3, 'x')));
        System.out.println(Arrays.toString(new Solution().divideString("abcdefghij", 3, 'x')));
        System.out.println(Arrays.toString(new Solution().divideString("abcdefghij", 1, 'x')));
        System.out.println(Arrays.toString(new Solution().divideString("abcdefghij", 100, 'x')));
    }

}
