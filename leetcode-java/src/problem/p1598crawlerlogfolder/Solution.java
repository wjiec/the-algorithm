package problem.p1598crawlerlogfolder;

/**
 * 1598. Crawler Log Folder
 *
 * https://leetcode-cn.com/problems/crawler-log-folder/
 *
 * The Leetcode file system keeps a log each time some user performs a change folder operation.
 *
 * The operations are described below:
 *
 * "../" : Move to the parent folder of the current folder. (If you are already in the main folder, remain in the same folder).
 * "./" : Remain in the same folder.
 * "x/" : Move to the child folder named x (This folder is guaranteed to always exist).
 * You are given a list of strings logs where logs[i] is the operation performed by the user at the ith step.
 *
 * The file system starts in the main folder, then the operations in logs are performed.
 *
 * Return the minimum number of operations needed to go back to the main folder after the change folder operations.
 */

public class Solution {

    public int minOperations(String[] logs) {
        int ans = 0;
        for (var log : logs) {
            if (log.equals("../")) {
                if (ans != 0) ans--;
            } else if (!log.equals("./")) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(new String[]{"./","../","./"}) == 0;

        assert new Solution().minOperations(new String[]{"d1/","d2/","../","d21/","./"}) == 2;
        assert new Solution().minOperations(new String[]{"d1/","d2/","./","d3/","../","d31/"}) == 3;
        assert new Solution().minOperations(new String[]{"d1/","../","../","../"}) == 0;
    }

}
