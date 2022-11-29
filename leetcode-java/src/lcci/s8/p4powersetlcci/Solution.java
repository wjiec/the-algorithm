package lcci.s8.p4powersetlcci;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.04. 幂集
 *
 * https://leetcode.cn/problems/power-set-lcci/
 *
 * 幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
 *
 * 说明：解集不能包含重复的子集。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < (1 << nums.length); i++) {
            ans.add(subset(nums, i));
        }
        return ans;
    }

    private List<Integer> subset(int[] nums, int state) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if ((state & (1 << i)) != 0) {
                ans.add(nums[i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().subsets(new int[]{1,2,3}));
    }

}
