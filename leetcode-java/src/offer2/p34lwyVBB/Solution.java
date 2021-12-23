package offer2.p34lwyVBB;

/**
 * 剑指 Offer II 034. 外星语言是否排序
 *
 * https://leetcode-cn.com/problems/lwyVBB/
 *
 * 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
 *
 * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
 */

public class Solution {

    public boolean isAlienSorted(String[] words, String order) {
        int[] sort = new int[128];
        for (int i = 0; i < order.length(); i++) sort[order.charAt(i)] = i;

        for (int i = 1; i < words.length; i++) {
            if (!check(sort, words[i - 1], words[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean check(int[] sort, String a, String b) {
        for (int i = 0; i < a.length() && i < b.length(); i++) {
            int cmp = sort[a.charAt(i)] - sort[b.charAt(i)];
            if (cmp > 0) return false;
            else if (cmp < 0) return true;
        }
        return a.length() <= b.length();
    }

    public static void main(String[] args) {
        assert new Solution().isAlienSorted(new String[]{"hello","hello"}, "abcdefghijklmnopqrstuvwxyz");

        assert new Solution().isAlienSorted(new String[]{"hello","leetcode"}, "hlabcdefgijkmnopqrstuvwxyz");
        assert !new Solution().isAlienSorted(new String[]{"word","world","row"}, "worldabcefghijkmnpqstuvxyz");
        assert !new Solution().isAlienSorted(new String[]{"apple","app"}, "abcdefghijklmnopqrstuvwxyz");
    }

}
