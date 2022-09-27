package problem.p1943describethepainting;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 1943. Describe the Painting
 *
 * https://leetcode.cn/problems/describe-the-painting/
 *
 * There is a long and thin painting that can be represented by a number line.
 * The painting was painted with multiple overlapping segments where each segment was painted with a unique color.
 * You are given a 2D integer array segments, where segments[i] = [starti, endi, colori] represents the
 * half-closed segment [starti, endi) with colori as the color.
 *
 * The colors in the overlapping segments of the painting were mixed when it was painted. When
 * two or more colors mix, they form a new color that can be represented as a set of mixed colors.
 *
 * For example, if colors 2, 4, and 6 are mixed, then the resulting mixed color is {2,4,6}.
 * For the sake of simplicity, you should only output the sum of the elements in the set rather than the full set.
 *
 * You want to describe the painting with the minimum number of non-overlapping half-closed
 * segments of these mixed colors. These segments can be represented by the 2D array painting
 * where painting[j] = [leftj, rightj, mixj] describes a half-closed segment [leftj, rightj) with
 * the mixed color sum of mixj.
 *
 * For example, the painting created with segments = [[1,4,5],[1,7,7]] can be described
 * by painting = [[1,4,12],[4,7,7]] because:
 * [1,4) is colored {5,7} (with a sum of 12) from both the first and second segments.
 * [4,7) is colored {7} from only the second segment.
 * Return the 2D array painting describing the finished painting (excluding any parts that are
 * not painted). You may return the segments in any order.
 *
 * A half-closed segment [a, b) is the section of the number line between points a and b
 * including point a and not including point b.
 */

public class Solution {

    public List<List<Long>> splitPainting(int[][] segments) {
        long[] colors = new long[100_001];
        boolean[] eps = new boolean[100_001];
        for (var seg : segments) {
            colors[seg[0]] += seg[2];
            colors[seg[1]] -= seg[2];
            eps[seg[0]] = eps[seg[1]] = true;
        }

        int prev = -1;
        List<List<Long>> ans = new ArrayList<>();
        for (int i = 1; i < colors.length; i++) {
            colors[i] += colors[i - 1];
            if (eps[i]) {
                if (prev != -1 && colors[i - 1] != 0) {
                    ans.add(List.of((long) prev, (long) i, colors[i - 1]));
                }
                prev = i;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().splitPainting(new int[][]{{4,16,12},{9,10,15},{18,19,13},{3,13,20},{12,16,3},{2,10,10},{3,11,4},{13,16,6}}), List.of(
            List.of(2L,3L,10L),
            List.of(3L,4L,34L),
            List.of(4L,9L,46L),
            List.of(9L,10L,61L),
            List.of(10L,11L,36L),
            List.of(11L,12L,32L),
            List.of(12L,13L,35L),
            List.of(13L,16L,21L),
            List.of(18L,19L,13L)
        ));

        assert Checker.anyOrder(new Solution().splitPainting(new int[][]{{1,4,5},{4,7,7},{1,7,9}}), List.of(
            List.of(1L,4L,14L),
            List.of(4L,7L,16L)
        ));

        assert Checker.anyOrder(new Solution().splitPainting(new int[][]{{1,7,9},{6,8,15},{8,10,7}}), List.of(
            List.of(1L,6L,9L),
            List.of(6L,7L,24L),
            List.of(7L,8L,15L),
            List.of(8L,10L,7L)
        ));

        assert Checker.anyOrder(new Solution().splitPainting(new int[][]{{1,4,5},{1,4,7},{4,7,1},{4,7,11}}), List.of(
            List.of(1L,4L,12L),
            List.of(4L,7L,12L)
        ));
    }

}
