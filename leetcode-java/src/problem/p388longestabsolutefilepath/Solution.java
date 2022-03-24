package problem.p388longestabsolutefilepath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 388. Longest Absolute File Path
 *
 * https://leetcode-cn.com/problems/longest-absolute-file-path/
 *
 * Here, we have dir as the only directory in the root.
 * dir contains two subdirectories, subdir1 and subdir2.
 * subdir1 contains a file file1.ext and subdirectory subsubdir1.
 * subdir2 contains a subdirectory subsubdir2, which contains a file file2.ext.
 *
 * In text form, it looks like this (with ⟶ representing the tab character):
 *
 * dir
 * ⟶ subdir1
 * ⟶ ⟶ file1.ext
 * ⟶ ⟶ subsubdir1
 * ⟶ subdir2
 * ⟶ ⟶ subsubdir2
 * ⟶ ⟶ ⟶ file2.ext
 *
 * If we were to write this representation in code, it will look like this:
 * "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext".
 * Note that the '\n' and '\t' are the new-line and tab characters.
 *
 * Every file and directory has a unique absolute path in the file system,
 * which is the order of directories that must be opened to reach the file/directory itself,
 * all concatenated by '/'s. Using the above example, the absolute path to file2.ext is
 * "dir/subdir2/subsubdir2/file2.ext".
 *
 * Each directory name consists of letters, digits, and/or spaces.
 * Each file name is of the form name.extension, where name and extension
 * consist of letters, digits, and/or spaces.
 *
 * Given a string input representing the file system in the explained format,
 * return the length of the longest absolute path to a file in the abstracted file system.
 *
 * If there is no file in the system, return 0.
 */

public class Solution {

    public int lengthLongestPath(String input) {
        int ans = 0;
        int[] map = new int[1024];
        for (var line : input.split("\n")) {
            int indent = 0, n = line.length(), dot = line.lastIndexOf('.');
            while (indent < n && line.charAt(indent) == '\t') indent++;
            if (dot == -1) { // is directory
                if (indent == 0) map[0] = line.length();
                else map[indent] = map[indent - 1] + line.length() - indent + 1;
            } else { // file
                if (indent == 0) ans = Math.max(ans, line.length());
                else ans = Math.max(ans, map[indent - 1] + line.length() - indent + 1);
            }
        }
        return ans;
    }

    public int lengthLongestPathV2(String input) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (var line : input.split("\n")) {
            int indent = 0, n = line.length(), dot = line.lastIndexOf('.');
            while (indent < n && line.charAt(indent) == '\t') indent++;
            if (dot == -1) { // is directory
                if (indent == 0) map.put(0, line.length());
                else map.put(indent, map.get(indent - 1) + line.length() - indent + 1);
            } else { // file
                if (indent == 0) ans = Math.max(ans, line.length());
                else ans = Math.max(ans, map.get(indent - 1) + line.length() - indent + 1);
            }
        }
        return ans;
    }

    public int lengthLongestPathV1(String input) {
        int ans = 0;
        List<String> roots = new ArrayList<>();
        for (var line : input.split("\n")) {
            int indent = 0, n = line.length(), dot = line.lastIndexOf('.');
            while (indent < n && line.charAt(indent) == '\t') indent++;
            if (dot == -1) { // is directory
                if (indent == 0) {
                    if (roots.isEmpty()) roots.add(line);
                    else roots.set(indent, line);
                } else if (indent >= roots.size()) {
                    roots.add(indent, roots.get(indent - 1) + "/" + line.substring(indent));
                } else {
                    roots.set(indent, roots.get(indent - 1) + "/" + line.substring(indent));
                }
            } else {
                if (indent == 0) ans = Math.max(ans, line.length());
                else ans = Math.max(ans, (roots.get(indent - 1) + "/" + line).length() - indent);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().lengthLongestPath("a\n\tb\n\t\tc.txt") == 9;

        assert new Solution().lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext") == 20;
        assert new Solution().lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext") == 32;
        assert new Solution().lengthLongestPath("a") == 0;
        assert new Solution().lengthLongestPath("file1.txt\nfile2.txt\nlongfile.txt") == 12;
    }

}
