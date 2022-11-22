package offer2.p109;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 剑指 Offer II 109. 开密码锁
 *
 * https://leetcode.cn/problems/zlDJc7/
 *
 * 一个密码锁由 4 个环形拨轮组成，每个拨轮都有 10 个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 *
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *
 * 字符串 target 代表可以解锁的数字，请给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int openLock(String[] deadends, String target) {
        int[] visited = new int[10000];
        Arrays.fill(visited, Integer.MAX_VALUE);
        for (var dead : deadends) visited[Integer.parseInt(dead)] = -1;
        if (visited[0] == -1) return -1;

        Queue<Integer> queue = new ArrayDeque<>();
        visited[0] = 0; queue.add(0);

        while (!queue.isEmpty()) {
            int curr = queue.remove();
            int step = visited[curr] + 1;
            for (var next : rotate(curr)) {
                if (step < visited[next]) {
                    queue.add(next);
                    visited[next] = step;
                }
            }
        }

        int ans = visited[Integer.parseInt(target)];
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int[] rotate(int curr) {
        int[] digits = new int[4];
        for (int i = 3; curr != 0; i--, curr /= 10) {
            digits[i] = curr % 10;
        }

        return new int[]{
            rotate(digits, 0, 1),
            rotate(digits, 0, -1),
            rotate(digits, 1, 1),
            rotate(digits, 1, -1),
            rotate(digits, 2, 1),
            rotate(digits, 2, -1),
            rotate(digits, 3, 1),
            rotate(digits, 3, -1),
        };
    }

    private int rotate(int[] digits, int i, int d) {
        int ans = 0, n = digits.length;
        for (int j = 0; j < n; j++) {
            int digit = i == j ? ((digits[j] + 10 + d) % 10) : digits[j];
            ans = ans * 10 + digit;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202") == 6;
        assert new Solution().openLock(new String[]{"8888"}, "0009") == 1;
        assert new Solution().openLock(new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"}, "8888") == -1;
        assert new Solution().openLock(new String[]{"0000"}, "8888") == -1;
    }

}
