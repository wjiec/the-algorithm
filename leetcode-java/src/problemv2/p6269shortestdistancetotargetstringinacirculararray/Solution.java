package problemv2.p6269shortestdistancetotargetstringinacirculararray;

import java.util.ArrayList;
import java.util.List;

/**
 * 6269. Shortest Distance to Target String in a Circular Array
 *
 * https://leetcode.cn/problems/shortest-distance-to-target-string-in-a-circular-array/
 *
 * You are given a 0-indexed circular string array words and a string target.
 * A circular array means that the array's end connects to the array's beginning.
 *
 * Formally, the next element of words[i] is words[(i + 1) % n] and the previous
 * element of words[i] is words[(i - 1 + n) % n], where n is the length of words.
 *
 * Starting from startIndex, you can move to either the next word or the previous word
 * with 1 step at a time.
 *
 * Return the shortest distance needed to reach the string target. If the string target
 * does not exist in words, return -1.
 */

public class Solution {

    public int closetTarget(String[] words, String target, int startIndex) {
        if (words[startIndex].equals(target)) return 0;

        int n = words.length;
        List<Integer> found = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                found.add(i);
            }
        }
        if (found.isEmpty()) return -1;

        int ans = n;
        for (var idx : found) {
            ans = Math.min(ans, (idx - startIndex + n) % n);
            ans = Math.min(ans, (startIndex - idx + n) % n);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().closetTarget(new String[]{"hello","i","am","leetcode","hello"}, "hello", 1) == 1;
        assert new Solution().closetTarget(new String[]{"a","b","leetcode"}, "leetcode", 0) == 1;
        assert new Solution().closetTarget(new String[]{"i","eat","leetcode"}, "ate", 0) == -1;
    }

}
