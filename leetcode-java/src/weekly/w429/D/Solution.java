package weekly.w429.D;

import java.util.ArrayList;
import java.util.List;

/**
 * 3399. Smallest Substring With Identical Characters II
 *
 * https://leetcode.cn/contest/weekly-contest-429/problems/smallest-substring-with-identical-characters-ii/
 *
 * You are given a binary string s of length n and an integer numOps.
 *
 * You are allowed to perform the following operation on s at most numOps times:
 *
 * Select any index i (where 0 <= i < n) and flip s[i]. If s[i] == '1', change s[i] to '0' and vice versa.
 * You need to minimize the length of the longest substring of s such that all the characters in the substring are identical.
 *
 * Return the minimum length after the operations.
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    public int minLength(String s, int numOps) {
        int n = s.length();
        char[] chars = s.toCharArray();
        // 让连续的 0 或者连续的 1 的长度最小, 最理想情况是 0 1 0 1 ... 这样相互间隔
        int xor = 0;
        for (int i = 0; i < n; i++) {
            if ((((chars[i] - '0') ^ i) & 1) == 1) xor++;
        }
        if (numOps >= Math.min(xor, n - xor)) return 1;

        // 首先对字符串进行分组
        List<Integer> groups = new ArrayList<>();
        for (int i = 1, l = 0; i <= n; i++) {
            if (i == n || chars[i] != chars[l]) {
                groups.add(i - l); l = i;
            }
        }

        // 使用二分检查是否能将所有的分组在 numOps 次之内使其最大分段为 mid
        int l = 1, r = n; // 左边无法做到, 右边可以做到
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (split(groups, mid) <= numOps) r = mid;
            else l = mid;
        }
        return r;
    }

    private int split(List<Integer> groups, int maxLength) {
        int ans = 0;
        for (var elem : groups) ans += elem / (maxLength + 1);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minLength("0000", 1) == 2;
        assert new Solution().minLength("00000", 2) == 1;
        assert new Solution().minLength("101010010010", 6) == 1;
        assert new Solution().minLength("0110", 2) == 1;
        assert new Solution().minLength("11", 1) == 1;
        assert new Solution().minLength("00", 1) == 1;
        assert new Solution().minLength("00", 0) == 2;

        assert new Solution().minLength("000001", 1) == 2;
        assert new Solution().minLength("0000", 2) == 1;
        assert new Solution().minLength("0101", 0) == 1;
    }

}
