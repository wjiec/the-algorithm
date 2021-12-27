package lcci.p1isuniquelcci;

/**
 * 面试题 01.01. 判定字符是否唯一
 *
 * https://leetcode-cn.com/problems/is-unique-lcci/
 *
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 */

public class Solution {

    public boolean isUnique(String astr) {
        boolean[] map = new boolean[128];
        for (var c : astr.toCharArray()) {
            if (map[c]) return false;
            map[c] = true;
        }
        return true;
    }

    public static void main(String[] args) {
        assert !new Solution().isUnique("leetcode");
        assert new Solution().isUnique("abc");
    }

}
