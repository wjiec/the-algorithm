package offer2.p80;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 080. 含有 k 个元素的组合
 *
 * https://leetcode.cn/problems/uUsW3B/
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 */

public class Solution {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            if (Integer.bitCount(i) == k) {
                List<Integer> curr = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) != 0) {
                        curr.add(j + 1);
                    }
                }
                ans.add(curr);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().combine(4, 2), List.of(
            List.of(1, 2), List.of(1, 3), List.of(1, 4), List.of(2, 3), List.of(2, 4), List.of(3, 4)
        ));
    }

}
