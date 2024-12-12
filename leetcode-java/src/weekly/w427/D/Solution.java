package weekly.w427.D;

import common.Tag;

import java.util.*;

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

/** @noinspection DataFlowIssue, SameParameterValue , unchecked */
public class Solution {

    @Tag({"二维数点问题", "离线查询"})
    public long maxRectangleArea(int[] xCoord, int[] yCoord) {
        Map<Integer, TreeSet<Integer>> xAxis = new HashMap<>();
        Map<Integer, TreeSet<Integer>> yAxis = new HashMap<>();
        for (int i = 0; i < xCoord.length; i++) {
            xAxis.computeIfAbsent(xCoord[i], k -> new TreeSet<>()).add(yCoord[i]);
            yAxis.computeIfAbsent(yCoord[i], k -> new TreeSet<>()).add(xCoord[i]);
        }

        // 枚举每一个点作为矩形的左下角 (x1, y1), 然后往右边找到最近的点 (x2, y1)
        // 往上边找到最近的点 (x1, y2), 同时也意味着找到了右上角的点为 (x2, y2)
        List<int[]> rectangles = new ArrayList<>(); // [x1, y1, x2, y2]
        for (int i = 0; i < xCoord.length; i++) {
            int x1 = xCoord[i], y1 = yCoord[i];
            if (xAxis.get(x1).higher(y1) == null) continue;
            if (yAxis.get(y1).higher(x1) == null) continue;

            // 检查 (x2, y2) 这个点是否合法, 我们判断这个点底下的左边和下边是否是我们所选择的点
            int x2 = yAxis.get(y1).higher(x1), y2 = xAxis.get(x1).higher(y1);
            if (!xAxis.get(x2).contains(y2)) continue;
            if (!yAxis.get(y2).contains(x2)) continue;
            if (!xAxis.get(x2).lower(y2).equals(y1)) continue;
            if (!yAxis.get(y2).lower(x2).equals(x1)) continue;

            // 现在我们就有了一个以左下角 (x1, y1), 右上角 (x2, y2) 组成的合适的矩形
            rectangles.add(new int[]{x1, y1, x2, y2});
        }
        if (rectangles.isEmpty()) return -1;

        // 现在我们还需要判断每个矩形内部有几个点, 再对满足要求的矩形的面积取最大值
        // 这里采用离线算法进行求解二维数点问题
        //  - 我们将所有矩形按照 x 坐标进行排序, 使用 BIT 来处理 y 的问题

        // 将所有的矩形转换为查询
        List<Integer> allX = new ArrayList<>(xAxis.keySet()); allX.sort(Integer::compare);
        List<Integer> allY = new ArrayList<>(yAxis.keySet()); allY.sort(Integer::compare);
        List<int[]>[] queries = new List[allX.size()]; // [idx, sign, y1, y2]
        Arrays.setAll(queries, i -> new ArrayList<>());
        for (int i = 0; i < rectangles.size(); i++) {
            var rectangle = rectangles.get(i);
            // 对值域 x 进行离散化, 使得后续遍历变得更为方便
            int x1 = Collections.binarySearch(allX, rectangle[0]);
            int x2 = Collections.binarySearch(allX, rectangle[2]);
            // 对值域 y 进行离散化, 同时避免 BIT 出现 0 的问题
            int y1 = Collections.binarySearch(allY, rectangle[1]) + 1;
            int y2 = Collections.binarySearch(allY, rectangle[3]) + 1;

            // 矩形的左侧为 -1, 右侧为 +1, 由于我们坐标起始位置为 0, 所以我们需要移动下坐标轴
            //  - 我们求的是 x 在 [x1, x2] 区间内同时 y 在 [y1, y2] 区间内的点的个数
            //  - 利用 BIT 我们需要使用 BIT(x2) - BIT(x1) 来得到 x 区间的点数, 所以我们使用 sign 来标记
            if (x1 > 0) queries[x1 - 1].add(new int[]{i, -1, y1, y2});
            queries[x2].add(new int[]{i, 1, y1, y2});
        }

        tree = new int[allY.size() + 1];
        int[] pts = new int[rectangles.size()];
        // 从小到大迭代所有 x 坐标并执行查询
        for (int i = 0; i < allX.size(); i++) {
            // 将当前这根坐标轴上的所有 y 点加入到 BIT 中
            for (var y : xAxis.get(allX.get(i))) update(Collections.binarySearch(allY, y) + 1, 1);
            // 执行离线查询
            for (var query : queries[i]) {
                // 我们需要根据 sign 来决定当前统计的是子区间的哪个部分
                pts[query[0]] += query[1] * (query(query[3]) - query(query[2] - 1));
            }
        }

        long ans = -1;
        for (int i = 0; i < rectangles.size(); i++) {
            var rect = rectangles.get(i);
            if (pts[i] == 4) ans = Math.max(ans, (long) (rect[2] - rect[0]) * (rect[3] - rect[1]));
        }

        return ans;
    }

    private int[] tree = null;

    private void update(int idx, int v) {
        while (idx < tree.length) {
            tree[idx] += v;
            idx += lowbit(idx);
        }
    }

    private int query(int idx) {
        int ans = 0;
        while (idx > 0) {
            ans += tree[idx];
            idx -= lowbit(idx);
        }
        return ans;
    }

    private static int lowbit(int v) { return v & -v; }

    public static void main(String[] args) {
        assert new Solution().maxRectangleArea(
            new int[]{92,31,92,31,4,29,83,90,61,62,39,97,7,34,41,87,29,43,70,84},
            new int[]{33,11,11,33,80,66,26,58,85,27,89,15,63,26,11,81,29,63,51,29}
        ) == -1;

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
