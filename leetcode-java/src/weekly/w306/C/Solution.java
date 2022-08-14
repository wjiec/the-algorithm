package weekly.w306.C;

/**
 * 6150. Construct Smallest Number From DI String
 *
 * https://leetcode.cn/contest/weekly-contest-306/problems/construct-smallest-number-from-di-string/
 *
 * You are given a 0-indexed string pattern of length n consisting of the
 * characters 'I' meaning increasing and 'D' meaning decreasing.
 *
 * A 0-indexed string num of length n + 1 is created using the following conditions:
 *
 * num consists of the digits '1' to '9', where each digit is used at most once.
 * If pattern[i] == 'I', then num[i] < num[i + 1].
 * If pattern[i] == 'D', then num[i] > num[i + 1].
 * Return the lexicographically smallest possible string num that meets the conditions.
 */

public class Solution {

    private String ans = "999999999";

    public String smallestNumber(String pattern) {
        char[] chars = pattern.toCharArray();
        boolean[] visited = new boolean[10];
        dfs(chars, -1, new char[chars.length + 1], visited);
        return ans;
    }

    private void dfs(char[] chars, int curr, char[] build, boolean[] visited) {
        if (curr == build.length - 1) {
            String s = new String(build);
            if (s.compareTo(ans) < 0) ans = s;
            return;
        }

        if (curr == -1) {
            for (int i = 1; i <= 9; i++) {
                build[curr + 1] = (char) ('0' + i);
                visited[i] = true;
                dfs(chars, curr + 1, build, visited);
                visited[i] = false;
            }
        } else {
            int last = build[curr] - '0';
            for (int i = 1; i <= 9; i++) {
                if (!visited[i]) {
                    if (chars[curr] == 'I') {
                        if (i > last) {
                            build[curr + 1] = (char) ('0' + i);
                            visited[i] = true;
                            dfs(chars, curr + 1, build, visited);
                            visited[i] = false;
                        }
                    } else {
                        if (i < last) {
                            build[curr + 1] = (char) ('0' + i);
                            visited[i] = true;
                            dfs(chars, curr + 1, build, visited);
                            visited[i] = false;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        assert new Solution().smallestNumber("IIIDIDDD").equals("123549876");
        assert new Solution().smallestNumber("DDD").equals("4321");
    }

}
