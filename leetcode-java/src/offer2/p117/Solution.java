package offer2.p117;

import ability.Ability;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer II 117. 相似的字符串
 *
 * https://leetcode.cn/problems/H6lPxb/
 *
 * 如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。
 * 如果这两个字符串本身是相等的，那它们也是相似的。
 *
 * 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，
 * 但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。
 *
 * 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。
 * 注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。形式上，对每个组而言，
 * 要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
 *
 * 给定一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个 字母异位词 。
 *
 * 请问 strs 中有多少个相似字符串组？
 *
 * 字母异位词（anagram），一种把某个字符串的字母的位置（顺序）加以改换所形成的新词。
 */

public class Solution {

    public int numSimilarGroups(String[] strs) {
        Ability.UnionFind uf = new Ability.UnionFind(strs.length);
        for (int i = 0; i < strs.length; i++) {
            char[] a = strs[i].toCharArray();
            for (int j = i + 1; j < strs.length; j++) {
                if (uf.find(i) == uf.find(j)) continue;
                if (similar(a, strs[j].toCharArray())) {
                    uf.union(i, j);
                }
            }
        }

        Set<Integer> groups = new HashSet<>();
        for (int i = 0; i < strs.length; i++) {
            groups.add(uf.find(i));
        }
        return groups.size();
    }

    private boolean similar(char[] a, char[] b) {
        int diff = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                if (++diff > 2) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().numSimilarGroups(new String[]{"abc","abc"}) == 1;

        assert new Solution().numSimilarGroups(new String[]{"tars","rats","arts","star"}) == 2;
        assert new Solution().numSimilarGroups(new String[]{"omv","ovm"}) == 1;
    }

}
