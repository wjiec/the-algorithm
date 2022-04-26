package problem.p609findduplicatefileinsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 609. Find Duplicate File in System
 *
 * https://leetcode-cn.com/problems/find-duplicate-file-in-system/
 *
 * Given a list paths of directory info, including the directory path, and all the files with contents
 * in this directory, return all the duplicate files in the file system in terms of their paths.
 *
 * You may return the answer in any order.
 *
 * A group of duplicate files consists of at least two files that have the same content.
 *
 * A single directory info string in the input list has the following format:
 *
 * "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
 * It means there are n files (f1.txt, f2.txt ... fn.txt) with content (f1_content, f2_content ... fn_content)
 * respectively in the directory "root/d1/d2/.../dm".
 * Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.
 *
 * The output is a list of groups of duplicate file paths. For each group, it contains all the file
 * paths of the files that have the same content. A file path is a string that has the following format:
 *
 * "directory_path/file_name.txt"
 */

public class Solution {

    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (var path : paths) {
            int idx = 0, n = path.length();
            StringBuilder dir = new StringBuilder();
            while (idx < n && path.charAt(idx) != ' ') dir.append(path.charAt(idx++));
            for (idx++; idx < n; idx += 2) {
                StringBuilder filename = new StringBuilder();
                while (idx < n && path.charAt(idx) != '(') filename.append(path.charAt(idx++));
                StringBuilder content = new StringBuilder();
                while (idx < n && path.charAt(idx) != ')') content.append(path.charAt(idx++));

                String contents = content.toString();
                if (!map.containsKey(contents)) map.put(contents, new ArrayList<>());
                map.get(contents).add(dir + "/" + filename);
            }
        }

        List<List<String>> ans = new ArrayList<>();
        for (var item : map.values()) {
            if (item.size() > 1) ans.add(item);
        }

        return ans;
    }

    public static void main(String[] args) {
        // [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
        System.out.println(new Solution().findDuplicate(new String[]{
            "root/a 1.txt(abcd) 2.txt(efgh)",
            "root/c 3.txt(abcd)",
            "root/c/d 4.txt(efgh)",
            "root 4.txt(efgh)"
        }));

        // [["root/a/2.txt","root/c/d/4.txt"],["root/a/1.txt","root/c/3.txt"]]
        System.out.println(new Solution().findDuplicate(new String[]{
            "root/a 1.txt(abcd) 2.txt(efgh)",
            "root/c 3.txt(abcd)",
            "root/c/d 4.txt(efgh)"
        }));
    }

}
