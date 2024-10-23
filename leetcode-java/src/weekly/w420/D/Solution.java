package weekly.w420.D;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Solution {

    private char[] chars = null;
    private final Map<Integer, TreeSet<Integer>> g = new HashMap<>();

    public boolean[] findAnswer(int[] parent, String s) {
        chars = s.toCharArray();
        for (int i = 0; i < parent.length; i++) {
            g.computeIfAbsent(parent[i], k -> new TreeSet<>()).add(i);
        }

        boolean[] ans = new boolean[s.length()];
        dfs(0, ans);

        return ans;
    }

    private String dfs(int node, boolean[] ans) {
        if (!g.containsKey(node)) {
            ans[node] = true;
            return String.valueOf(chars[node]);
        }

        var curr = new StringBuilder();
        for (var sub : g.get(node)) {
            curr.append(dfs(sub, ans));
        }
        curr.append(chars[node]);

        ans[node] = isPalindrome(curr.toString().toCharArray());
        return curr.toString();
    }

    private boolean isPalindrome(char[] chars) {
        for (int l = 0, r = chars.length - 1; l < r; l++, r--) {
            if (chars[l] != chars[r]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
