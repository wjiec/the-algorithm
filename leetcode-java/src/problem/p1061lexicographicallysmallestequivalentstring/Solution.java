package problem.p1061lexicographicallysmallestequivalentstring;

/**
 * 1061. Lexicographically Smallest Equivalent String
 *
 * https://leetcode.cn/problems/lexicographically-smallest-equivalent-string/
 *
 * You are given two strings of the same length s1 and s2 and a string baseStr.
 *
 * We say s1[i] and s2[i] are equivalent characters.
 *
 * For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.
 * Equivalent characters follow the usual rules of any equivalence relation:
 *
 * Reflexivity: 'a' == 'a'.
 * Symmetry: 'a' == 'b' implies 'b' == 'a'.
 * Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.
 * For example, given the equivalency information from s1 = "abc" and s2 = "cde",
 * "acd" and "aab" are equivalent strings of baseStr = "eed", and "aab" is the
 * lexicographically smallest equivalent string of baseStr.
 *
 * Return the lexicographically smallest equivalent string of baseStr by using the
 * equivalency information from s1 and s2.
 */

public class Solution {

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        for (int i = 0; i < s1.length(); i++) {
            union(s1.charAt(i), s2.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        for (var c : baseStr.toCharArray()) {
            sb.append((char) find(c));
        }
        return sb.toString();
    }

    private final int[] parents = new int[128];
    {
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    private void union(int a, int b) {
        int pa = find(a), pb = find(b);
        parents[pa] = parents[pb] = Math.min(pa, pb);
    }

    private int find(int x) {
        return parents[x] == x ? x : (parents[x] = find(parents[x]));
    }

    public static void main(String[] args) {
        assert new Solution().smallestEquivalentString("parker", "morris", "parser").equals("makkek");
        assert new Solution().smallestEquivalentString("hello", "world", "hold").equals("hdld");
        assert new Solution().smallestEquivalentString("leetcode", "programs", "sourcecode").equals("aauaaaaada");
    }

}
