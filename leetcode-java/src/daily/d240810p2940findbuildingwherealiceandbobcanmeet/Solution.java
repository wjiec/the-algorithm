package daily.d240810p2940findbuildingwherealiceandbobcanmeet;

import common.Checker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2940. Find Building Where Alice and Bob Can Meet
 *
 * https://leetcode.cn/problems/find-building-where-alice-and-bob-can-meet/
 *
 * You are given a 0-indexed array heights of positive integers, where
 * heights[i] represents the height of the ith building.
 *
 * If a person is in building i, they can move to any other building j if
 * and only if i < j and heights[i] < heights[j].
 *
 * You are also given another array queries where queries[i] = [ai, bi].
 *
 * On the ith query, Alice is in building ai while Bob is in building bi.
 *
 * Return an array ans where ans[i] is the index of the leftmost building where
 * Alice and Bob can meet on the ith query. If Alice and Bob cannot move to
 * a common building on query i, set ans[i] to -1.
 */

@SuppressWarnings("unchecked")
public class Solution {

    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        List<int[]>[] query = new List[heights.length];
        Arrays.setAll(query, i -> new ArrayList<>());

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int a = queries[i][0], b = queries[i][1];
            if (a > b) { int t = a; a = b; b = t; } // swap
            if (a == b || heights[a] < heights[b]) ans[i] = b;
            else query[b].add(new int[]{i, heights[a]});
        }

        int top = -1;
        List<Integer> st = new ArrayList<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            for (int j = 0; j < query[i].size(); j++) {
                int q = query[i].get(j)[0];
                int val = query[i].get(j)[1];
                if (top == -1 || heights[st.get(0)] <= val) {
                    ans[q] = -1;
                    continue;
                }

                int l = 0, r = top;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (heights[st.get(mid)] > val) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                ans[q] = st.get(r);
            }

            while (top >= 0 && heights[st.get(top)] <= heights[i]) {
                st.remove(st.size() - 1);
                top--;
            }
            st.add(i);
            top++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().leftmostBuildingQueries(new int[]{6,4,8,5,2,7}, new int[][]{{0,1},{0,3},{2,4},{3,4},{2,2}}), new int[]{2,5,-1,5,2});
        assert Checker.check(new Solution().leftmostBuildingQueries(new int[]{5,3,8,2,6,1,4,6}, new int[][]{{0,7},{3,5},{5,2},{3,0},{1,6}}), new int[]{7,6,-1,4,6});
    }

}
