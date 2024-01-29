package problem.p1948deleteduplicatefoldersinsystem;

import common.PrettyPrinter;

import java.util.*;

/**
 * 1948. Delete Duplicate Folders in System
 *
 * https://leetcode.cn/problems/delete-duplicate-folders-in-system
 *
 * Due to a bug, there are many duplicate folders in a file system.
 * You are given a 2D array paths, where paths[i] is an array representing
 * an absolute path to the ith folder in the file system.
 *
 * For example, ["one", "two", "three"] represents the path "/one/two/three".
 * Two folders (not necessarily on the same level) are identical if they contain
 * the same non-empty set of identical subfolders and underlying subfolder structure.
 *
 * The folders do not need to be at the root level to be identical. If two or more
 * folders are identical, then mark the folders as well as all their subfolders.
 *
 * For example, folders "/a" and "/b" in the file structure below are identical.
 * They (as well as their subfolders) should all be marked:
 * /a
 * /a/x
 * /a/x/y
 * /a/z
 * /b
 * /b/x
 * /b/x/y
 * /b/z
 * However, if the file structure also included the path "/b/w", then the folders "/a"
 * and "/b" would not be identical. Note that "/a/x" and "/b/x" would still be considered
 * identical even with the added folder.
 *
 * Once all the identical folders and their subfolders have been marked, the file system
 * will delete all of them. The file system only runs the deletion once, so any folders
 * that become identical after the initial deletion are not deleted.
 *
 * Return the 2D array ans containing the paths of the remaining folders after deleting
 * all the marked folders. The paths may be returned in any order.
 */

public class Solution {

    private static class TreeNode {
        private String hash;
        private final String name;
        private final Map<String, TreeNode> children = new HashMap<>();
        public TreeNode(String name) { this.name = name; }
        public TreeNode next(String name) {
            return children.computeIfAbsent(name, TreeNode::new);
        }
    }

    private final List<List<String>> ans = new ArrayList<>();

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        TreeNode root = new TreeNode("/");
        for (var path : paths) {
            var curr = root;
            for (var item : path) curr = curr.next(item);
        }

        buildTree(root); dfs(root, new ArrayDeque<>());
        return ans;
    }

    private final Map<String, Integer> freq = new HashMap<>();

    private void buildTree(TreeNode node) {
        if (node.children.isEmpty()) return;

        List<String> ss = new ArrayList<>();
        for (var st : node.children.values()) {
            buildTree(st); ss.add(st.name + "(" + st.hash + ")");
        }

        ss.sort(String::compareTo);
        node.hash = String.join("", ss);
        freq.merge(node.hash, 1, Integer::sum);
    }

    private void dfs(TreeNode node, Deque<String> path) {
        if (freq.getOrDefault(node.hash, 0) > 1) return;
        if (!path.isEmpty()) ans.add(new ArrayList<>(path));

        for (var next : node.children.values()) {
            path.addLast(next.name);
            dfs(next, path);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().deleteDuplicateFolder(List.of(
            List.of("c", "a", "b", "a"),
            List.of("b", "a", "a"),
            List.of("p", "a", "a")
        )));

        PrettyPrinter.println(new Solution().deleteDuplicateFolder(List.of(
            List.of("a"),
            List.of("a","x"),
            List.of("a","x","y"),
            List.of("a","z"),
            List.of("b"),
            List.of("b","x"),
            List.of("b","x","y"),
            List.of("b","z")
        )));

        PrettyPrinter.println(new Solution().deleteDuplicateFolder(List.of(
            List.of("a"),
            List.of("a","x"),
            List.of("a","x","y"),
            List.of("a","z"),
            List.of("b"),
            List.of("b","x"),
            List.of("b","x","y"),
            List.of("b","z"),
            List.of("b","w")
        )));

        PrettyPrinter.println(new Solution().deleteDuplicateFolder(List.of(
            List.of("a"),
            List.of("c"),
            List.of("d"),
            List.of("a", "b"),
            List.of("c", "b"),
            List.of("d", "a")
        )));
        
        PrettyPrinter.println(new Solution().deleteDuplicateFolder(List.of(
            List.of("a"),
            List.of("c"),
            List.of("a", "b"),
            List.of("c", "b"),
            List.of("a", "b", "x"),
            List.of("a", "b", "x", "y"),
            List.of("w"),
            List.of("w", "y")
        )));
    }

}
