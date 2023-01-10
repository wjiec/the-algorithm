package daily.d230110p753crackingthesafe;

import java.util.HashSet;
import java.util.Set;

/**
 * 753. Cracking the Safe
 *
 * https://leetcode.cn/problems/cracking-the-safe/
 *
 * There is a safe protected by a password. The password is a sequence of n
 * digits where each digit can be in the range [0, k - 1].
 *
 * The safe has a peculiar way of checking the password. When you enter
 * in a sequence, it checks the most recent n digits that were entered
 * each time you type a digit.
 *
 * For example, the correct password is "345" and you enter in "012345":
 * After typing 0, the most recent 3 digits is "0", which is incorrect.
 * After typing 1, the most recent 3 digits is "01", which is incorrect.
 * After typing 2, the most recent 3 digits is "012", which is incorrect.
 * After typing 3, the most recent 3 digits is "123", which is incorrect.
 * After typing 4, the most recent 3 digits is "234", which is incorrect.
 * After typing 5, the most recent 3 digits is "345", which is correct and the safe unlocks.
 *
 * Return any string of minimum length that will unlock the safe at some point of entering it.
 */

public class Solution {

    private final StringBuilder ans = new StringBuilder();
    private final Set<Integer> visited = new HashSet<>();

    public String crackSafe(int n, int k) {
        dfs(0, k, (int) Math.pow(10, n - 1));
        ans.append("0".repeat(Math.max(0, n - 1)));
        return ans.toString();
    }

    private void dfs(int curr, int k, int mod) {
        for (int i = 0; i < k; i++) {
            int next = curr * 10 + i;
            if (visited.add(next)) {
                dfs(next % mod, k, mod);
                ans.append(i);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().crackSafe(1, 2));
        System.out.println(new Solution().crackSafe(2, 2));
        System.out.println(new Solution().crackSafe(2, 3)); // 0221120100
    }

}
