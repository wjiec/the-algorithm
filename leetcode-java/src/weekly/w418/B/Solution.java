package weekly.w418.B;

import ability.Ability.UnionFind;

import java.util.*;

/**
 * 3310. Remove Methods From Project
 *
 * https://leetcode.cn/contest/weekly-contest-418/problems/remove-methods-from-project/
 *
 * You are maintaining a project that has n methods numbered from 0 to n - 1.
 *
 * You are given two integers n and k, and a 2D integer array invocations, where
 * invocations[i] = [ai, bi] indicates that method ai invokes method bi.
 *
 * There is a known bug in method k. Method k, along with any method invoked
 * by it, either directly or indirectly, are considered suspicious and we aim to remove them.
 *
 * A group of methods can only be removed if no method outside the group invokes any methods within it.
 *
 * Return an array containing all the remaining methods after removing all the
 * suspicious methods. You may return the answer in any order. If it is not
 * possible to remove all the suspicious methods, none should be removed.
 */

public class Solution {

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (var edge : invocations) g.computeIfAbsent(edge[0], i -> new HashSet<>()).add(edge[1]);

        UnionFind uf = new UnionFind(n);
        Queue<Integer> q = new ArrayDeque<>(); q.add(k);
        while (!q.isEmpty()) {
            var curr = q.remove();
            if (g.containsKey(curr)) {
                for (var next : g.get(curr)) {
                    if (uf.find(next) != uf.find(k)) {
                        uf.union(k, next);
                        q.add(next);
                    }
                }
            }
        }

        // 检查这组方法是否被这组方法之外的方法调用了
        int kp = uf.find(k); boolean remove = true;
        for (var edge : invocations) {
            int p1 = uf.find(edge[0]);
            int p2 = uf.find(edge[1]);
            if ((p1 == kp && p2 != kp) || (p2 == kp && p1 != kp)) {
                // 被调用了, 那么就只能返回全部
                remove = false;
                break;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!remove || uf.find(i) != kp) ans.add(i);
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
