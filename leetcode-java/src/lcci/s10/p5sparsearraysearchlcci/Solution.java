package lcci.s10.p5sparsearraysearchlcci;

/**
 * 面试题 10.05. 稀疏数组搜索
 *
 * https://leetcode-cn.com/problems/sparse-array-search-lcci/
 *
 * 稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。
 */

public class Solution {

    public int findString(String[] words, String s) {
        int l = 0, r = words.length;
        while (l < r) {
            int mid = l + (r - l) / 2, t = mid;
            while (mid < r && words[mid].length() == 0) mid++;
            if (mid == r) { r = t; continue; }

            if (words[mid].equals(s)) return mid;
            else if (words[mid].compareTo(s) > 0) r = mid;
            else l = mid + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().findString(new String[]{"CitZMIXZKoFbxvOlaza", "hBlKXdKJfBD"}, "hBlKXdKJfBD") == 1;

        assert new Solution().findString(new String[]{"at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""}, "ta") == -1;
        assert new Solution().findString(new String[]{"at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""}, "ball") == 4;
    }

}
