package daily.d220221p838pushdominoes;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 838. Push Dominoes
 *
 * https://leetcode-cn.com/problems/push-dominoes/
 *
 * There are n dominoes in a line, and we place each domino vertically upright. In the beginning,
 * we simultaneously push some of the dominoes either to the left or to the right.
 *
 * After each second, each domino that is falling to the left pushes the adjacent domino on the left.
 * Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
 *
 * When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
 *
 * For the purposes of this question, we will consider that a falling domino expends no additional
 * force to a falling or already fallen domino.
 *
 * You are given a string dominoes representing the initial state where:
 *
 * dominoes[i] = 'L', if the ith domino has been pushed to the left,
 * dominoes[i] = 'R', if the ith domino has been pushed to the right, and
 * dominoes[i] = '.', if the ith domino has not been pushed.
 * Return a string representing the final state.
 */

public class Solution {

    public String pushDominoes(String dominoes) {
        char[] chars = dominoes.toCharArray();
        int n = chars.length; char left = 'L';
        for (int l = 0; l < n; ) {
            int r = l; // 找到直立的所有连续多米诺骨牌
            while (r < n && chars[r] == '.') r++;

            char right = r == n ? 'R' : chars[r]; // 第一个不是站立的骨牌
            if (left == right) { // 前后一致则全部倒下相同方向
                while (l < r) chars[l++] = left;
            } else if (left == 'R' && right == 'L') { // 前后一起往中间倒
                for (int p = l, q = r - 1; p < q; p++, q--) {
                    chars[p] = left;
                    chars[q] = right;
                }
            }

            left = right;
            l = r + 1;
        }
        return new String(chars);
    }

    public String pushDominoesSlow(String dominoes) {
        char[] chars = dominoes.toCharArray();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '.') {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Set<Integer> next = new HashSet<>();
            while (!queue.isEmpty()) {
                int i = queue.remove();
                if (chars[i] == 'L' && i > 0) {
                    switch (chars[i - 1]) {
                        case '.' -> {
                            chars[i - 1] = 'l';
                            next.add(i - 1);
                        }
                        case 'r' -> {
                            chars[i - 1] = '.';
                            next.remove(i - 1);
                        }
                    }
                } else if (chars[i] == 'R' && i < chars.length - 1) {
                    switch (chars[i + 1]) {
                        case '.' -> {
                            chars[i + 1] = 'r';
                            next.add(i + 1);
                        }
                        case 'l' -> {
                            chars[i + 1] = '.';
                            next.remove(i + 1);
                        }
                    }
                }
            }

            queue.addAll(next);
            for (int i = 0; i < chars.length; i++) {
                switch (chars[i]) {
                    case 'l' -> chars[i] = 'L';
                    case 'r' -> chars[i] = 'R';
                }
            }
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        assert new Solution().pushDominoes("RR.L").equals("RR.L");
        assert new Solution().pushDominoes(".L.R...LR..L..").equals("LL.RR.LLRRLL..");

        assert new Solution().pushDominoesSlow("RR.L").equals("RR.L");
        assert new Solution().pushDominoesSlow(".L.R...LR..L..").equals("LL.RR.LLRRLL..");
    }

}
