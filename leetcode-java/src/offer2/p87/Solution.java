package offer2.p87;

import common.Checker;

import java.util.*;

/**
 * 剑指 Offer II 087. 复原 IP
 *
 * https://leetcode.cn/problems/0on3uN/
 *
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 *
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 */

public class Solution {

    private final List<String> ans = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12) return Collections.emptyList();
        dfs(s.toCharArray(), 0, new ArrayDeque<>());
        return ans;
    }

    private void dfs(char[] chars, int idx, Deque<Integer> curr) {
        if (idx == chars.length || curr.size() > 4) {
            if (curr.size() == 4) {
                StringJoiner sj = new StringJoiner(".");
                for (var v : curr) sj.add(String.valueOf(v));
                ans.add(sj.toString());
            }
            return;
        }

        int v = chars[idx] - '0';

        curr.addLast(v);
        dfs(chars, idx + 1, curr);
        curr.removeLast();

        if (!curr.isEmpty() && curr.peekLast() != 0) {
            int top = curr.removeLast();

            if (top * 10 + v <= 255) {
                curr.addLast(top * 10 + v);
                dfs(chars, idx + 1, curr);
                curr.removeLast();
            }

            curr.addLast(top);
        }
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().restoreIpAddresses("25525511135"), List.of("255.255.11.135","255.255.111.35"));
        assert Checker.anyOrder(new Solution().restoreIpAddresses("0000"), List.of("0.0.0.0"));
        assert Checker.anyOrder(new Solution().restoreIpAddresses("1111"), List.of("1.1.1.1"));
        assert Checker.anyOrder(new Solution().restoreIpAddresses("010010"), List.of("0.10.0.10","0.100.1.0"));
        assert Checker.anyOrder(new Solution().restoreIpAddresses("10203040"), List.of("10.20.30.40","102.0.30.40","10.203.0.40"));
    }

}
