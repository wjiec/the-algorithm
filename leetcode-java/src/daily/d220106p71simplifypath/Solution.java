package daily.d220106p71simplifypath;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringJoiner;

/**
 * 71. Simplify Path
 *
 * https://leetcode-cn.com/problems/simplify-path/
 *
 * Given a string path, which is an absolute path (starting with a slash '/') to a file or
 * directory in a Unix-style file system, convert it to the simplified canonical path.
 *
 * In a Unix-style file system, a period '.' refers to the current directory,
 * a double period '..' refers to the directory up a level,
 * and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'.
 *
 * For this problem, any other format of periods such as '...' are treated as file/directory names.
 *
 * The canonical path should have the following format:
 *
 * The path starts with a single slash '/'.
 * Any two directories are separated by a single slash '/'.
 * The path does not end with a trailing '/'.
 *
 * The path only contains the directories on the path from the root directory to the target file or directory
 * (i.e., no period '.' or double period '..')
 *
 * Return the simplified canonical path.
 */

public class Solution {

    public String simplifyPath(String path) {
        Deque<String> queue = new ArrayDeque<>();
        for (var s : path.split("/")) {
            if (s.length() != 0) {
                if (s.equals("..")) {
                    if (!queue.isEmpty())
                        queue.removeLast();
                } else if (!s.equals(".")) {
                    queue.add(s);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (var q : queue) {
            sb.append(q).append("/");
        }

        if (sb.length() != 0) sb.deleteCharAt(sb.length() - 1);
        return "/"+sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().simplifyPath("/home/").equals("/home");
        assert new Solution().simplifyPath("/../").equals("/");
        assert new Solution().simplifyPath("/home//foo/").equals("/home/foo");
        assert new Solution().simplifyPath("/a/./b/../../c/").equals("/c");
    }

}
