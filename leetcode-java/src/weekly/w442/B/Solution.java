package weekly.w442.B;

import ability.Ability.UnionFind;

import java.util.HashSet;
import java.util.Set;

/**
 * 3493. Properties Graph
 *
 * https://leetcode.cn/contest/weekly-contest-442/problems/properties-graph/
 *
 * You are given a 2D integer array properties having dimensions n x m and an integer k.
 *
 * Define a function intersect(a, b) that returns the number of distinct integers common to both arrays a and b.
 *
 * Construct an undirected graph where each index i corresponds to properties[i].
 * There is an edge between node i and node j if and only if intersect(properties[i], properties[j]) >= k,
 * where i and j are in the range [0, n - 1] and i != j.
 *
 * Return the number of connected components in the resulting graph.
 */

public class Solution {

    public int numberOfComponents(int[][] properties, int k) {
        UnionFind uf = new UnionFind(properties.length);
        for (int i = 0; i < properties.length; i++) {
            for (int j = i + 1; j < properties.length; j++) {
                if (intersect(properties[i], properties[j], k)) {
                    uf.union(i, j);
                }
            }
        }

        Set<Integer> ans = new HashSet<>();
        for (int i = 0; i < properties.length; i++) {
            ans.add(uf.find(i));
        }
        return ans.size();
    }

    private boolean intersect(int[] a, int[] b, int k) {
        Set<Integer> uniq = new HashSet<>();
        for (var v : a) uniq.add(v);
        for (var v : b) if (uniq.remove(v)) if (--k == 0) return true;
        return false;
    }

    public static void main(String[] args) {
    }

}
