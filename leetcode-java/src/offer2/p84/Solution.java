package offer2.p84;

import java.util.*;

/**
 * 剑指 Offer II 084. 含有重复元素集合的全排列
 *
 * https://leetcode.cn/problems/7p8L0Z/
 *
 * 给定一个可包含重复数字的整数集合 nums ，按任意顺序 返回它所有不重复的全排列。
 */

public class Solution {

    private final List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var v : nums) map.merge(v, 1, Integer::sum);

        int idx = 0;
        int[][] unique = new int[map.size()][];
        for (var entry : map.entrySet()) unique[idx++] = new int[]{entry.getKey(), entry.getValue()};
        dfs(unique, new ArrayDeque<>(), nums.length);
        return ans;
    }

    private void dfs(int[][] array, Deque<Integer> curr, int n) {
        if (curr.size() == n) { ans.add(new ArrayList<>(curr)); return; }

        for (var item : array) {
            if (item[1] != 0) {
                curr.addLast(item[0]); item[1]--;
                dfs(array, curr, n);
                curr.removeLast(); item[1]++;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().permuteUnique(new int[]{1, 1, 2}));
        System.out.println(new Solution().permuteUnique(new int[]{1, 2, 3}));
    }

}
