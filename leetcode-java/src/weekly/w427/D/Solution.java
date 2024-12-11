package weekly.w427.D;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * 3382. Maximum Area Rectangle With Point Constraints II
 *
 * https://leetcode.cn/contest/weekly-contest-427/problems/maximum-area-rectangle-with-point-constraints-ii/
 *
 * There are n points on an infinite plane. You are given two integer arrays xCoord and yCoord
 * where (xCoord[i], yCoord[i]) represents the coordinates of the ith point.
 *
 * Your task is to find the maximum area of a rectangle that:
 *
 * Can be formed using four of these points as its corners.
 * Does not contain any other point inside or on its border.
 * Has its edges parallel to the axes.
 *
 * Return the maximum area that you can obtain or -1 if no such rectangle is possible.
 */

public class Solution {

    public long maxRectangleArea(int[] xCoord, int[] yCoord) {
        TreeMap<Integer, List<Integer>> xAxis = new TreeMap<>();
        TreeMap<Integer, List<Integer>> yAxis = new TreeMap<>();
        for (int i = 0; i < xCoord.length; i++) {
            xAxis.computeIfAbsent(xCoord[i], k -> new ArrayList<>()).add(yCoord[i]);
            yAxis.computeIfAbsent(yCoord[i], k -> new ArrayList<>()).add(xCoord[i]);
        }

        for (var v : xAxis.values()) v.sort(Integer::compare);
        for (var v : yAxis.values()) v.sort(Integer::compare);

        PrettyPrinter.println(xAxis);
        PrettyPrinter.println(yAxis);

        return Math.max(maxRectangleArea(xAxis, yAxis), maxRectangleArea(yAxis, xAxis));
    }

    private long maxRectangleArea(TreeMap<Integer, List<Integer>> xAxis, TreeMap<Integer, List<Integer>> yAxis) {
        long ans = -1; int prevX = -1;
        // 枚举所有的相邻只有 2 个点的 x 坐标, 检查这一段区间之内是否有其他的点
        for (var e : xAxis.entrySet()) {
            if (e.getValue().size() >= 2) {
                int currX = e.getKey();
                var currLine = e.getValue();

                if (prevX != -1) {
                    var prevLine = xAxis.get(prevX);
                    // 现在找到了两组 x 坐标, 但是还需要确认这两组点之间没有其他的点存在
                    // 并且还需要有平行于坐标轴的 y 坐标
                    for (int l = 0, r = 1; r < e.getValue().size(); l++, r++) {
                        int currY1 = currLine.get(l), currY2 = currLine.get(r);
                        if (prevLine.contains(currY1) && prevLine.contains(currY2)) {
                            // 这两组 y 之间不能有其他的 x 处于 (prevX, currX) 范围内
                            Integer y1Higher = higher(yAxis.get(currY1), prevX);
                            Integer y2Higher = higher(yAxis.get(currY2), prevX);
                            if (y1Higher != null && y1Higher == currX && y2Higher != null && y2Higher == currX) {
                                ans = Math.max(ans, (long) (currX - prevX) * (currY2 - currY1));
                            }
                        }
                    }
                }

                prevX = currX;
            } else if (prevX != -1) {
                int prevY1 = xAxis.get(prevX).get(0), prevY2 = xAxis.get(prevX).get(xAxis.get(prevX).size() - 1);
                Integer y1Higher = ceiling(e.getValue(), prevY1);
                // 如果当前项有 y 值处于 [prevY1, prevY2] 之间, 则说明不合法
                if (y1Higher != null && (prevY1 <= y1Higher && y1Higher <= prevY2)) prevX = -1;
            }
        }

        return ans;
    }

    private Integer higher(List<Integer> list, int target) {
        int index = Collections.binarySearch(list, target + 1);
        if (index < 0) index = -index - 1;
        return index >= list.size() ? null : list.get(index);
    }

    private Integer ceiling(List<Integer> list, int target) {
        return higher(list, target - 1);
    }

    public static void main(String[] args) {
        assert new Solution().maxRectangleArea(
            new int[]{78,9,78,9,9,93,93,79,56,56,46,29,40,20,90,99,64,57,76,91},
            new int[]{66,91,91,66,7,36,95,98,51,3,53,15,4,21,29,14,61,19,33,66}
        ) == 1725;

        assert new Solution().maxRectangleArea(
            new int[]{1,1,3,3,2},
            new int[]{1,3,1,3,2}
        ) == -1;

        assert new Solution().maxRectangleArea(
            new int[]{89,55,89,55,0,34,17,71,98,90,63,49,76,72,4,46,67,94,52,6},
            new int[]{58,69,69,58,100,36,14,40,13,41,29,23,47,52,95,49,37,77,54,59}
        ) == 374;
    }

}
