package weekly.bw152.C;

import common.Checker;
import common.PrettyPrinter;

/**
 * 3485. Longest Common Prefix of K Strings After Removal
 *
 * https://leetcode.cn/contest/biweekly-contest-152/problems/longest-common-prefix-of-k-strings-after-removal/
 *
 * You are given an array of strings words and an integer k.
 *
 * For each index i in the range [0, words.length - 1], find the length of the longest
 * common prefix among any k strings (selected at distinct indices) from the remaining
 * array after removing the ith element.
 *
 * Return an array answer, where answer[i] is the answer for ith element. If removing
 * the ith element leaves the array with fewer than k strings, answer[i] is 0.
 */

public class Solution {

    private static class Trie {
        private final int[] count = new int[26];
        private final Trie[] subtree = new Trie[26];
        public void set(char[] chars) {
            Trie curr = this;
            for (var c : chars) {
                curr.count[c - 'a']++;
                if (curr.subtree[c - 'a'] == null) {
                    curr.subtree[c - 'a'] = new Trie();
                }
                curr = curr.subtree[c - 'a'];
            }
        }
    }

    public int[] longestCommonPrefix(String[] words, int k) {
        int m = words.length, n = words[0].length();
        // 使用字典树保存所有的字符串, 如果不删除某个字符串, 则就是找到最长的
        // Trie 节点, 其中每个节点的次数需要大于等于 k
        //
        // 如果将每一层字典树的最大值抽出来得到一个非递增数组, 就变成了找 >= k 的最大位置
        //
        // 如果需要删除一个第 i 个字符串, 则可能会导致最大值发生变化, 进而影响后续的所有数
        //  - 由于只删除一个字符串, 所以每一个层级最多只会减少 1
        //  - 如果最大值受到了影响, 则可以从最大值 - 1 和次大值中选较大的那个
        //      - 只有次大和最大相等才有取次大的意义
        Trie root = new Trie();
        for (var word : words) root.set(word.toCharArray());

        int[][] max = new int[n + 1][2];
        char[][] used = new char[n + 1][2];
        dfs(root, max, used, 0);

        int[] ans = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            int first = binarySearch(max, k);
            if (first < words[i].length() && words[i].charAt(first) == used[first][0]) {
                ans[i] = Math.max(max[first][0], max[first][1] + 1);
                if (first > 0) ans[i] = Math.max(ans[i], max[first - 1][0] + 1);
            } else ans[i] = max[first][0] + 1;
        }

        PrettyPrinter.println(ans);
        return ans;
    }

    private int binarySearch(int[][] array, int target) {
        int l = 0, r = array.length;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (array[mid][0] < target) r = mid;
            else l = mid;
        }
        return l;
    }

    private void dfs(Trie curr, int[][] max, char[][] used, int depth) {
        for (int i = 0; i < 26; i++) {
            int v = curr.count[i]; char c = (char) ('a' + i);
            if (v > max[depth][0]) {
                max[depth][1] = max[depth][0];
                max[depth][0] = v;
                used[depth][1] = used[depth][0];
                used[depth][0] = c;
            } else if (v > max[depth][1]) {
                max[depth][1] = v;
                used[depth][1] = c;
            }
        }

        for (var subtree : curr.subtree) {
            if (subtree != null) dfs(subtree, max, used, depth + 1);
        }
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().longestCommonPrefix(new String[]{"jump","run","run","jump","run"}, 2), new int[]{3,4,4,3,4});
        assert Checker.check(new Solution().longestCommonPrefix(new String[]{"dog","racer","car"}, 2), new int[]{0,0,0});
    }

}
