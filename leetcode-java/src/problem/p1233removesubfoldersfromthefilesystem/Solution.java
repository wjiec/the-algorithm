package problem.p1233removesubfoldersfromthefilesystem;

import common.Checker;

import java.util.*;

/**
 * 1233. Remove Sub-Folders from the Filesystem
 *
 * https://leetcode.cn/problems/remove-sub-folders-from-the-filesystem/
 *
 * Given a list of folders folder, return the folders after removing all sub-folders in those folders.
 * You may return the answer in any order.
 *
 * If a folder[i] is located within another folder[j], it is called a sub-folder of it.
 *
 * The format of a path is one or more concatenated strings of the form: '/' followed by one or
 * more lowercase English letters.
 *
 * For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.
 */

public class Solution {

    private static class FileSystem {
        private boolean leaf = false;
        private final Map<String, FileSystem> children = new HashMap<>();
        public FileSystem get(String p) { return children.get(p); }
        public FileSystem set(String p) { return children.computeIfAbsent(p, v -> new FileSystem()); }
    }

    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder, Comparator.comparingInt(String::length));

        FileSystem fs = new FileSystem();
        List<String> ans = new ArrayList<>();
        for (String dir : folder) {
            FileSystem curr = fs;
            boolean contains = false;
            for (var p : dir.split("/")) {
                curr = curr.set(p);
                contains = contains || curr.leaf;
            }

            curr.leaf = true;
            if (!contains) ans.add(dir);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().removeSubfolders(new String[]{
            "/a","/a/b","/c/d","/c/d/e","/c/f"
        }), List.of("/a","/c/d","/c/f"));

        assert Checker.anyOrder(new Solution().removeSubfolders(new String[]{
            "/a","/a/b/c","/a/b/d"
        }), List.of("/a"));

        assert Checker.anyOrder(new Solution().removeSubfolders(new String[]{
            "/a/b/c","/a/b/ca","/a/b/d"
        }), List.of("/a/b/c","/a/b/ca","/a/b/d"));
    }

}
