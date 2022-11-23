package offer2.p113;

import common.Checker;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 剑指 Offer II 113. 课程顺序
 *
 * https://leetcode.cn/problems/QA2IGt/
 *
 * 现在总共有 numCourses 门课需要选，记为 0 到 numCourses-1。
 *
 * 给定一个数组 prerequisites ，它的每一个元素 prerequisites[i] 表示两门课程之间的先修顺序。
 * 例如 prerequisites[i] = [ai, bi] 表示想要学习课程 ai ，需要先完成课程 bi 。
 *
 * 请根据给出的总课程数  numCourses 和表示先修顺序的 prerequisites 得出一个可行的修课序列。
 *
 * 可能会有多个正确的顺序，只要任意返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 */

@SuppressWarnings({"unchecked", "DuplicatedCode"})
public class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] degree = new int[numCourses];
        Set<Integer>[] map = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) map[i] = new HashSet<>();
        for (var req : prerequisites) {
            degree[req[0]]++;
            map[req[1]].add(req[0]);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) queue.add(i);
        }

        int idx = 0;
        int[] ans = new int[numCourses];
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            ans[idx++] = curr;
            for (var next : map[curr]) {
                if (--degree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        return idx == numCourses ? ans : new int[0];
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findOrder(2, new int[][]{{1,0}}), new int[]{0,1});
        assert Checker.check(new Solution().findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}}), new int[]{0,1,2,3});
        assert Checker.check(new Solution().findOrder(1, new int[][]{}), new int[]{0});
    }

}
