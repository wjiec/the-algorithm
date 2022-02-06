package daily.d210901p165compareversionnumbers;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 165. Compare Version Numbers
 *
 * https://leetcode-cn.com/problems/compare-version-numbers/
 *
 * Given two version numbers, version1 and version2, compare them.
 *
 * Version numbers consist of one or more revisions joined by a dot '.'.
 * Each revision consists of digits and may contain leading zeros.
 * Every revision contains at least one character.
 * Revisions are 0-indexed from left to right, with the leftmost revision being revision 0,
 * the next revision being revision 1, and so on. For example 2.5.33 and 0.1 are valid version numbers.
 *
 * To compare version numbers, compare their revisions in left-to-right order.
 * Revisions are compared using their integer value ignoring any leading zeros.
 * This means that revisions 1 and 001 are considered equal.
 * If a version number does not specify a revision at an index,
 * then treat the revision as 0. For example,
 * version 1.0 is less than version 1.1 because their revision 0s are the same,
 * but their revision 1s are 0 and 1 respectively, and 0 < 1.
 *
 * Return the following:
 *
 * If version1 < version2, return -1.
 * If version1 > version2, return 1.
 *
 * Otherwise, return 0.
 */

public class Solution {

    public int compareVersion(String version1, String version2) {
        Queue<Integer> v1 = buildVersion(version1), v2 = buildVersion(version2);
        while (!v1.isEmpty() && !v2.isEmpty()) {
            int c = Integer.compare(v1.remove(), v2.remove());
            if (c != 0) return c;
        }

        while (!v1.isEmpty()) {
            if (v1.remove() != 0) return 1;
        }

        while (!v2.isEmpty()) {
            if (v2.remove() != 0) return -1;
        }

        return 0;
    }

    private Queue<Integer> buildVersion(String version) {
        Queue<Integer> versions = new ArrayDeque<>();

        int number = 0;
        for (var c : version.toCharArray()) {
            if (c == '.') {
                versions.add(number);
                number = 0;
            } else {
                number = number * 10 + (c - '0');
            }
        }

        versions.add(number);
        return versions;
    }

    public static void main(String[] args) {
        assert new Solution().compareVersion("1.01", "1.001") == 0;
        assert new Solution().compareVersion("1.0", "1.0.0") == 0;
        assert new Solution().compareVersion("0.1", "1.1") == -1;
        assert new Solution().compareVersion("1.0.1", "1") == 1;
        assert new Solution().compareVersion("7.5.2.4", "7.5.3") == -1;
    }

}
