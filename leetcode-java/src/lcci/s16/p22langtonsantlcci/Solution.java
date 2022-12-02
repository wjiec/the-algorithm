package lcci.s16.p22langtonsantlcci;

import common.Checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 面试题 16.22. 兰顿蚂蚁
 *
 * https://leetcode.cn/problems/langtons-ant-lcci/
 *
 * 一只蚂蚁坐在由白色和黑色方格构成的无限网格上。开始时，网格全白，蚂蚁面向右侧。每行走一步，蚂蚁执行以下操作。
 *
 * (1) 如果在白色方格上，则翻转方格的颜色，向右(顺时针)转 90 度，并向前移动一个单位。
 * (2) 如果在黑色方格上，则翻转方格的颜色，向左(逆时针方向)转 90 度，并向前移动一个单位。
 *
 * 编写程序来模拟蚂蚁执行的前 K 个动作，并返回最终的网格。
 *
 * 网格由数组表示，每个元素是一个字符串，代表网格中的一行，黑色方格由 'X' 表示，白色方格由 '_' 表示，
 * 蚂蚁所在的位置由 'L', 'U', 'R', 'D' 表示，分别表示蚂蚁 左、上、右、下 的朝向。
 *
 * 只需要返回能够包含蚂蚁走过的所有方格的最小矩形。
 */

public class Solution {

    public List<String> printKMoves(int K) {
        final char COLOR_WHITE = '_';
        final char COLOR_BLAKE = 'X';

        // right: 0, top: 90, left: 180, down: 270
        int minX = 0, maxX = 0, minY = 0, maxY = 0, x = 0, y = 0, d = 0;
        Map<Integer, Map<Integer, Character>> grid = new HashMap<>();
        for (int i = 0; i < K; i++) {
            char curr = grid.computeIfAbsent(x, v -> new HashMap<>())
                .getOrDefault(y, COLOR_WHITE);
            switch (curr) {
                case COLOR_WHITE -> {
                    d -= 90;
                    grid.computeIfAbsent(x, v -> new HashMap<>())
                        .put(y, COLOR_BLAKE);
                }
                case COLOR_BLAKE -> {
                    d += 90;
                    grid.computeIfAbsent(x, v -> new HashMap<>())
                        .put(y, COLOR_WHITE);
                }
            }

            switch ((d = (d + 360) % 360)) {
                case 0 -> y++;
                case 90 -> x--;
                case 180 -> y--;
                case 270 -> x++;
            }

            minX = Math.min(minX, x); maxX = Math.max(maxX, x);
            minY = Math.min(minY, y); maxY = Math.max(maxY, y);
        }

        List<String> ans = new ArrayList<>();
        for (int i = minX; i <= maxX; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = minY; j <= maxY; j++) {
                if (i == x && j == y) {
                    switch (d) {
                        case 0 -> sb.append('R');
                        case 90 -> sb.append('U');
                        case 180 -> sb.append('L');
                        case 270 -> sb.append('D');
                    }
                } else {
                    sb.append(grid.computeIfAbsent(i, v -> new HashMap<>())
                        .getOrDefault(j, COLOR_WHITE));
                }
            }
            ans.add(sb.toString());
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().printKMoves(0), List.of("R"));
        assert Checker.anyOrder(new Solution().printKMoves(2), List.of(
            "_X",
            "LX"
        ));

        assert Checker.anyOrder(new Solution().printKMoves(5), List.of(
            "_U",
            "X_",
            "XX"
        ));
    }

}
