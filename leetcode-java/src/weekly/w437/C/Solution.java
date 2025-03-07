package weekly.w437.C;

import ability.Benchmark;

import java.util.*;

/**
 * 3458. Select K Disjoint Special Substrings
 *
 * https://leetcode.cn/contest/weekly-contest-437/problems/select-k-disjoint-special-substrings/
 *
 * Given a string s of length n and an integer k, determine whether it is possible to select k disjoint special substrings.
 *
 * A special substring is a substring where:
 *
 * Any character present inside the substring should not appear outside it in the string.
 * The substring is not the entire string s.
 * Note that all k substrings must be disjoint, meaning they cannot overlap.
 *
 * Return true if it is possible to select k such disjoint special substrings; otherwise, return false.
 */

/** @noinspection unchecked*/
public class Solution {

    public boolean maxSubstringLength(String s, int k) {
        if (s.length() < k) return false;
        List<Integer>[] index = new List[128];
        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(i) - 'a';
            if (index[curr] == null) index[curr] = new ArrayList<>();
            index[curr].add(i);
        }

        // 如果我们选择使用字母 c, 则在范围 [l[c], r[c]] 之间的所有字母的范围也必须属于这个范围
        //  - 也就是说字母 c 的实际范围会变大, 首先预处理出所有字母的实际范围
        int[][] ranges = new int[26][];
        for (int i = 0; i < 26; i++) {
            if (index[i] == null) continue;
            int l = index[i].get(0), r = index[i].get(index[i].size() - 1);

            // 找到字母 c 的实际左右边界
            for (int j = 0; j < 26; j++) {
                if (index[j] == null) continue;

                // 检查在字母 c 之间是否存在字母
                int found = Collections.binarySearch(index[j], l);
                if (found < 0) found = -found - 1;
                if (found < index[j].size() && index[j].get(found) < r) {
                    boolean enlarged = index[j].get(0) < l || index[j].get(index[j].size() - 1) > r;
                    // 如果扩大了, 那么要重新开始匹配
                    l = Math.min(l, index[j].get(0));
                    r = Math.max(r, index[j].get(index[j].size() - 1));
                    if (enlarged) j = -1; // 重新找区域
                }
            }

            ranges[i] = new int[]{l, r};
        }

        // 在 ranges 中找出 k 个不重叠的区间
        return dfs(ranges, 0, k, s.length());
    }

    private final Set<Long> seen = new HashSet<>();

    private boolean dfs(int[][] ranges, long mask, long k, int n) {
        if (k == 0) {
            // 所选择的不能是整个字符串
            if (mask != 0 && (mask & (mask - 1)) == 0) {
                var used = ranges[63 - Long.numberOfLeadingZeros(mask)];
                return used[0] != 0 || used[1] != n - 1;
            }
            return true;
        }
        if (!seen.add(((k << 32) | mask))) return false;

        for (int i = 0; i < ranges.length; i++) {
            if (ranges[i] == null) continue;

            // 我们考虑选择使用 ranges[i], 那么要求所选择的区间与已选择过的区间没有重叠
            if (!overlap(ranges, i, mask) && dfs(ranges, mask | (1L << i), k - 1, n)) return true;
        }
        return false;
    }

    private boolean overlap(int[][] ranges, int i, long mask) {
        for (int j = 0; j < ranges.length; j++) {
            if (ranges[j] != null && (mask & (1L << j)) != 0 && overlap(ranges[i], ranges[j])) {
                return true;
            }
        }
        return false;
    }

    private boolean overlap(int[] a, int[] b) { return Math.min(a[1], b[1]) >= Math.max(a[0], b[0]); }

    public static void main(String[] args) {
        Benchmark.benchmark("", () -> {
            assert !new Solution().maxSubstringLength("cbcaba", 1);
            assert !new Solution().maxSubstringLength("abcdefghijklmnopqrstuv", 24);
            assert new Solution().maxSubstringLength("wwo", 1);
            assert !new Solution().maxSubstringLength("ddjlopbgngpoenkqktvuuvpygctyquoeqglszijjiifljfiswiladdfwzislzdd", 6);

            assert new Solution().maxSubstringLength("abcdbaefab", 2);
            assert !new Solution().maxSubstringLength("cdefdc", 3);
            assert new Solution().maxSubstringLength("abeabe", 0);
        });
    }

}
