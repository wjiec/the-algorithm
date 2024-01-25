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
        private final String name;
        private final TreeNode parent;
        private final Map<String, TreeNode> children = new HashMap<>();
        public TreeNode(String name) { this(name, null); }
        public TreeNode(String name, TreeNode parent) {
            this.name = name; this.parent = parent;
        }
        public TreeNode next(String name) {
            return children.computeIfAbsent(name, v -> new TreeNode(v, this));
        }
        public boolean equals(TreeNode other) {
            return other != null && children.keySet().containsAll(other.children.keySet());
        }

        @Override public String toString() { return String.format("<%s count=%d>", name, children.size()); }
    }

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        TreeNode root = new TreeNode("/");
        for (var path : paths) {
            var curr = root;
            for (var item : path) curr = curr.next(item);
        }

        List<TreeNode> candidates = new ArrayList<>();
        for (var next : root.children.entrySet()) {
            var f = find(next.getValue(), root, 0);
            if (f.node != null) candidates.add(f.node);
        }

        while (!candidates.isEmpty()) {
            Set<Integer> removed = new HashSet<>();
            for (int i = 0; i < candidates.size(); i++) {
                for (int j = i + 1; j < candidates.size(); j++) {
                    if (candidates.get(i).equals(candidates.get(j))) {
                        removed.add(i); removed.add(j);
                    }
                }
            }

            List<TreeNode> next = new ArrayList<>();
            for (var rmi : removed) {
                var node = candidates.get(rmi);
                if (node.parent != null) {
                    node.parent.children.remove(node.name);
                    next.add(node.parent);
                }
            }
            candidates = next;
        }

        dfs(root, new ArrayDeque<>());
        return ans;
    }

    private record Found(int depth, TreeNode node) {}

    private Found find(TreeNode node, TreeNode parent, int depth) {
        if (node.children.isEmpty()) {
            return new Found(depth, parent);
        }

        Found max = new Found(0, null);
        for (var next : node.children.entrySet()) {
            var f = find(next.getValue(), node, depth + 1);
            if (f.depth > max.depth) max = f;
        }
        return max;
    }

    private final List<List<String>> ans = new ArrayList<>();

    private void dfs(TreeNode node, Deque<String> curr) {
        if (!curr.isEmpty()) ans.add(new ArrayList<>(curr));
        for (var next : node.children.entrySet()) {
            curr.addLast(next.getKey());
            dfs(next.getValue(), curr);
            curr.removeLast();
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
