package weekly.w380.B;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * 3006. Find Beautiful Indices in the Given Array I
 *
 * https://leetcode.cn/contest/weekly-contest-380/problems/find-beautiful-indices-in-the-given-array-i/
 *
 * You are given a 0-indexed string s, a string a, a string b, and an integer k.
 *
 * An index i is beautiful if:
 *
 * 0 <= i <= s.length - a.length
 * s[i..(i + a.length - 1)] == a
 * There exists an index j such that:
 * 0 <= j <= s.length - b.length
 * s[j..(j + b.length - 1)] == b
 * |j - i| <= k
 *
 * Return the array that contains beautiful indices in sorted order from smallest to largest.
 */

@SuppressWarnings({"DuplicatedCode", "UnusedReturnValue"})
public class Solution {

    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        char[] ss = s.toCharArray();
        List<Integer> aList = kmp(ss, a.toCharArray());
        if (a.equals(b)) return aList;

        List<Integer> ans = new ArrayList<>();
        TreeSet<Integer> bList = new TreeSet<>(kmp(ss, b.toCharArray()));
        for (var idx : aList) {
            Integer lo = bList.ceiling(idx);
            Integer hi = bList.floor(idx);
            if ((lo != null && Math.abs(lo - idx) <= k) || (hi != null && Math.abs(hi - idx) <= k)) {
                ans.add(idx);
            }
        }
        return ans;
    }

    private List<Integer> kmp(char[] array, char[] target) {
        if (target.length == 0) return Collections.emptyList();
        if (target.length > array.length) return Collections.emptyList();

        int[] next = lps(target);
        List<Integer> list = new ArrayList<>();
        for (int i = 0, j = 0; i < array.length; ) {
            if (array[i] == target[j]) {
                i++; j++;
                if (j == target.length) {
                    list.add(i - j);

                    int prev = next[j - 1];
                    i -= target.length - prev - 1;
                    j = prev;
                }
            } else {
                if (j != 0) j = next[j - 1];
                else i++;
            }
        }

        return list;
    }

    private int[] lps(char[] array) {
        int[] next = new int[array.length];
        for (int i = 1, j = 0; i < array.length; ) {
            if (array[i] == array[j]) {
                next[i++] = ++j;
            } else {
                if (j != 0) j = next[j - 1];
                else next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String s = "a".repeat(399900);
        String a = "a".repeat(199950);
        String b = "a".repeat(199950);

        new Solution().beautifulIndices(s, a, b, 399900);
    }

}
