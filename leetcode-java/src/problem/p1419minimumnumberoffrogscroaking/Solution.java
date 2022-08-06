package problem.p1419minimumnumberoffrogscroaking;

/**
 * 1419. Minimum Number of Frogs Croaking
 *
 * https://leetcode.cn/problems/minimum-number-of-frogs-croaking/
 *
 * You are given the string croakOfFrogs, which represents a combination of the string "croak"
 * from different frogs, that is, multiple frogs can croak at the same time, so
 * multiple "croak" are mixed.
 *
 * Return the minimum number of different frogs to finish all the croaks in the given string.
 *
 * A valid "croak" means a frog is printing five letters 'c', 'r', 'o', 'a', and 'k' sequentially.
 * The frogs have to print all five letters to finish a croak. If the given string is not a
 * combination of a valid "croak" return -1.
 */

public class Solution {

    public int minNumberOfFrogs(String croakOfFrogs) {
        int ans = 0;
        int[] states = new int[5];
        for (var c : croakOfFrogs.toCharArray()) {
            int curr = state(c);
            states[curr]++;
            if (curr != 0 && --states[curr - 1] < 0) return -1;
            ans = Math.max(ans, states[0] + states[1] + states[2] + states[3]);
        }
        for (int i = 0; i < 4; i++) {
            if (states[i] != 0) return -1;
        }
        return ans;
    }

    private int state(char c) {
        switch (c) {
            case 'c' -> { return 0; }
            case 'r' -> { return 1; }
            case 'o' -> { return 2; }
            case 'a' -> { return 3; }
            case 'k' -> { return 4; }
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().minNumberOfFrogs("croakcroa") == -1;

        assert new Solution().minNumberOfFrogs("croakcroak") == 1;
        assert new Solution().minNumberOfFrogs("crcoakroak") == 2;
        assert new Solution().minNumberOfFrogs("croakcrook") == -1;
    }

}
