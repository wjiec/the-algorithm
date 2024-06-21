package problem.p1847closestroom;

import common.Checker;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 1847. Closest Room
 *
 * https://leetcode.cn/problems/closest-room/
 *
 * There is a hotel with n rooms. The rooms are represented by a 2D integer array rooms
 * where rooms[i] = [roomIdi, sizei] denotes that there is a room with room number roomIdi
 * and size equal to sizei. Each roomIdi is guaranteed to be unique.
 *
 * You are also given k queries in a 2D array queries where queries[j] = [preferredj, minSizej].
 *
 * The answer to the jth query is the room number id of a room such that:
 *
 * The room has a size of at least minSizej, and
 * abs(id - preferredj) is minimized, where abs(x) is the absolute value of x.
 * If there is a tie in the absolute difference, then use the room with the smallest such id.
 * If there is no such room, the answer is -1.
 *
 * Return an array answer of length k where answer[j] contains the answer to the jth query.
 */

public class Solution {

    public int[] closestRoom(int[][] rooms, int[][] queries) {
        Integer[] sorted = new Integer[queries.length];
        for (int i = 0; i < sorted.length; i++) sorted[i] = i;
        Arrays.sort(sorted, (a, b) -> queries[b][1] - queries[a][1]);
        Arrays.sort(rooms, (a, b) -> b[1] - a[1]);

        int[] ans = new int[queries.length];
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0, j = 0; i < sorted.length; i++) {
            int idx = sorted[i];
            int preferred = queries[idx][0];
            int minSize = queries[idx][1];

            while (j < rooms.length && rooms[j][1] >= minSize) set.add(rooms[j++][0]);

            Integer l = set.floor(preferred);
            Integer r = set.ceiling(preferred);
            if (l == null && r == null) ans[idx] = -1;
            else if (l == null) ans[idx] = r;
            else if (r == null) ans[idx] = l;
            else ans[idx] = Math.abs(l - preferred) <= Math.abs(r - preferred) ? l : r;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().closestRoom(new int[][]{{2,2},{1,2},{3,2}}, new int[][]{{3,1},{3,3},{5,2}}), new int[]{3,-1,3});
        assert Checker.check(new Solution().closestRoom(new int[][]{{1,4},{2,3},{3,5},{4,1},{5,2}}, new int[][]{{2,3},{2,4},{2,5}}), new int[]{2,1,3});
    }

}
