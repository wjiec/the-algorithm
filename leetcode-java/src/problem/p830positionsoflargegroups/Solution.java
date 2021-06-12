package problem.p830positionsoflargegroups;

import java.util.ArrayList;
import java.util.List;

/**
 * 830. Positions of Large Groups
 *
 * https://leetcode-cn.com/problems/positions-of-large-groups/
 *
 * In a string s of lowercase letters, these letters form consecutive groups of the same character.
 *
 * For example, a string like s = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z", and "yy".
 *
 * A group is identified by an interval [start, end], where start and end denote the start and end
 * indices (inclusive) of the group. In the above example, "xxxx" has the interval [3,6].
 *
 * A group is considered large if it has 3 or more characters.
 *
 * Return the intervals of every large group sorted in increasing order by start index.
 */

public class Solution {

    public List<List<Integer>> largeGroupPositions(String s) {
        s += ".";

        List<List<Integer>> ans = new ArrayList<>();
        int n = s.length(), l = 1, c = s.charAt(0), start = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == c) {
                l++;
            } else {
                if (l >= 3) {
                    ans.add(List.of(start, start + l - 1));
                }

                l = 1;
                c = s.charAt(i);
                start = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().largeGroupPositions("abbxxxxzzy").equals(List.of(List.of(3,6)));
        assert new Solution().largeGroupPositions("abc").equals(List.of());
        assert new Solution().largeGroupPositions("abcdddeeeeaabbbcd").equals(List.of(List.of(3,5),List.of(6,9),List.of(12,14)));
        assert new Solution().largeGroupPositions("aba").equals(List.of());
    }

}
