package problem.p93restoreipaddresses;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. Restore IP Addresses
 *
 * https://leetcode-cn.com/problems/restore-ip-addresses/
 *
 * A valid IP address consists of exactly four integers separated by single dots.
 *
 * Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 *
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312"
 * and "192.168@1.1" are invalid IP addresses.
 *
 * Given a string s containing only digits, return all possible valid IP addresses that can be
 * formed by inserting dots into s. You are not allowed to reorder or remove any digits in s.
 *
 * You may return the valid IP addresses in any order.
 */

public class Solution {

    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        dfs(s.toCharArray(), ans, new int[4], 0, 0);
        return ans;
    }

    private void dfs(char[] chars, List<String> ans, int[] addr, int i, int c) {
        if (c == 4) {
            if (i == chars.length) {
                ans.add(String.valueOf(addr[0]) + '.' + addr[1] + '.' + addr[2] + '.' + addr[3]);
            }
            return;
        }

        if (i == chars.length) return;

        addr[c] = 0;
        if (chars[i] == '0') {
            dfs(chars, ans, addr, i + 1, c + 1);
            return;
        }

        for (int j = 0; j < 3 && i + j < chars.length; j++) {
            addr[c] = addr[c] * 10 + (chars[i + j] - '0');
            if (addr[c] <= 255) {
                dfs(chars, ans, addr, i + j + 1, c + 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().restoreIpAddresses("25525511135"));
        System.out.println(new Solution().restoreIpAddresses("0000"));
        System.out.println(new Solution().restoreIpAddresses("101023"));
    }

}
