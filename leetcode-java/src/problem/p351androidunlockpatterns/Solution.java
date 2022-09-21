package problem.p351androidunlockpatterns;

import common.TODO;

import java.util.Arrays;

/**
 * 351. Android Unlock Patterns
 *
 * https://leetcode.cn/problems/android-unlock-patterns/
 *
 * Android devices have a special lock screen with a 3 x 3 grid of dots. Users can set an "unlock pattern" by
 * connecting the dots in a specific sequence, forming a series of joined line segments where each segment's
 * endpoints are two consecutive dots in the sequence. A sequence of k dots is a valid unlock pattern if both
 * of the following are true:
 *
 * All the dots in the sequence are distinct.
 * If the line segment connecting two consecutive dots in the sequence passes through the center of any other
 * dot, the other dot must have previously appeared in the sequence. No jumps through the center
 * non-selected dots are allowed.
 * For example, connecting dots 2 and 9 without dots 5 or 6 appearing beforehand is valid because the line
 * from dot 2 to dot 9 does not pass through the center of either dot 5 or 6.
 * However, connecting dots 1 and 3 without dot 2 appearing beforehand is invalid because the line
 * from dot 1 to dot 3 passes through the center of dot 2.
 *
 * Here are some example valid and invalid unlock patterns:
 *
 * The 1st pattern [4,1,3,6] is invalid because the line connecting dots 1 and 3 pass through dot 2, but
 * dot 2 did not previously appear in the sequence.
 * The 2nd pattern [4,1,9,2] is invalid because the line connecting dots 1 and 9 pass through dot 5, but
 * dot 5 did not previously appear in the sequence.
 * The 3rd pattern [2,4,1,3,6] is valid because it follows the conditions. The line connecting dots 1 and 3 meets
 * the condition because dot 2 previously appeared in the sequence.
 * The 4th pattern [6,5,4,1,9,2] is valid because it follows the conditions. The line connecting dots 1 and 9 meets
 * the condition because dot 5 previously appeared in the sequence.
 * Given two integers m and n, return the number of unique and valid unlock patterns of the Android grid lock screen
 * that consist of at least m keys and at most n keys.
 *
 * Two unlock patterns are considered unique if there is a dot in one sequence that is not in the other, or the
 * order of the dots is different.
 */

public class Solution {

    private final boolean[] used = new boolean[9];

    @TODO
    public int numberOfPatterns(int m, int n) {
        int ans = 0;
        for (int i = m; i <= n; i++) {
            ans += countOfPattern(-1, i);
            Arrays.fill(used, false);
        }
        return ans;
    }

    private int countOfPattern(int prev, int len) {
        if (len == 0) return 1;

        int ans = 0;
        for (int i = 0; i < 9; i++) {
            if (isValid(i, prev)) {
                used[i] = true;
                ans += countOfPattern(i, len - 1);
                used[i] = false;
            }
        }
        return ans;
    }

    private boolean isValid(int curr, int prev) {
        if (used[curr]) return false;
        if (prev == -1) return true;
        if ((curr + prev) % 2 == 1) return true;

        int mid = (curr + prev) / 2;
        if (mid == 4) return used[mid];
        if ((curr % 3 != prev % 3) && (curr / 3 != prev / 3)) return true;
        return used[mid];
    }

    public static void main(String[] args) {
        assert new Solution().numberOfPatterns(1, 1) == 9;
        assert new Solution().numberOfPatterns(1, 2) == 65;
    }

}
