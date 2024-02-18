package lcci.s17.p17multisearchlcci;

import common.Checker;
import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 17.17. 多次搜索
 *
 * https://leetcode.cn/problems/multi-search-lcci/
 *
 * 给定一个较长字符串big和一个包含较短字符串的数组smalls，设计一个方法，根据smalls中的每一个较短字符串，对big进行搜索。
 * 输出smalls中的字符串在big里出现的所有位置positions，其中positions[i]为smalls[i]出现的所有位置。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int[][] multiSearch(String big, String[] smalls) {
        char[] target = big.toCharArray();
        int[][] ans = new int[smalls.length][];
        for (int i = 0; i < smalls.length; i++) {
            ans[i] = kmp(target, smalls[i].toCharArray());
        }
        return ans;
    }

    private int[] kmp(char[] array, char[] target) {
        if (target.length == 0) return new int[0];
        if (target.length > array.length) return new int[0];

        int[] next = lps(target);
        List<Integer> list = new ArrayList<>();
        for (int i = 0, j = 0; i < array.length; ) {
            if (array[i] == target[j]) {
                i++; j++;
            } else {
                if (j != 0) j = next[j - 1];
                else i++;
            }

            if (j == target.length) {
                list.add(i - j);
                i -= j - 1;
                j = 0;
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
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
        PrettyPrinter.println(new Solution().multiSearch("aaabaaa", new String[]{"aa"}));
        assert Checker.check(new Solution().multiSearch("mississippi",
            new String[]{"is","ppi","hi","sis","i","ssippi"}
        ), new int[][]{
            {1,4},{8},{},{3},{1,4,7,10},{5}
        });
    }

}
