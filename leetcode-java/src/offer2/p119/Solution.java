package offer2.p119;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer II 119. 最长连续序列
 *
 * https://leetcode.cn/problems/WhsWhI/
 *
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 */

public class Solution {

    public int longestConsecutive(int[] nums) {
        Set<Integer> seq = new HashSet<>();
        for (var v : nums) seq.add(v);

        int ans = 0;
        for (var v : seq) {
            if (!seq.contains(v - 1)) {
                int curr = v, cnt = 1;
                while (seq.contains(curr + 1)) {
                    curr++; cnt++;
                }
                ans = Math.max(ans, cnt);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestConsecutive(new int[]{100,4,200,1,3,2}) == 4;
        assert new Solution().longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}) == 9;
    }

}
