package problem.p2092findallpeoplewithsecret;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 2092. Find All People With Secret
 *
 * https://leetcode.cn/problems/find-all-people-with-secret/
 *
 * You are given an integer n indicating there are n people numbered from 0 to n - 1.
 * You are also given a 0-indexed 2D integer array meetings where meetings[i] = [xi, yi, timei]
 * indicates that person xi and person yi have a meeting at timei. A person may attend multiple
 * meetings at the same time. Finally, you are given an integer firstPerson.
 *
 * Person 0 has a secret and initially shares the secret with a person firstPerson at time 0.
 * This secret is then shared every time a meeting takes place with a person that has the secret.
 * More formally, for every meeting, if a person xi has the secret at timei, then they will share
 * the secret with person yi, and vice versa.
 *
 * The secrets are shared instantaneously. That is, a person may receive the secret and share
 * it with people in other meetings within the same time frame.
 *
 * Return a list of all the people that have the secret after all the meetings have taken place.
 * You may return the answer in any order.
 */

public class Solution {

    private void union(int[] parent, int a, int b) { parent[find(parent, a)] = find(parent, b); }
    private int find(int[] parent, int v) { return parent[v] == v ? v : (parent[v] = find(parent, parent[v])); }

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        int[] real = new int[n], weak = new int[n];
        for (int i = 0; i < n; i++) real[i] = i;
        union(real, firstPerson, 0);

        Arrays.sort(meetings, Comparator.comparingInt(v -> v[2]));
        for (int l = 0, r = 0; r <= meetings.length; r++) {
            // 找到同一个时间的所有会议, 会议里的专家会和对方分享秘密
            if (r == meetings.length || meetings[r][2] != meetings[l][2]) {
                System.arraycopy(real, 0, weak, 0, n);
                for (int i = l; i < r; i++) union(weak, meetings[i][0], meetings[i][1]);

                int ref = find(weak, 0);
                for (int i = l; i < r; i++) {
                    if (find(weak, meetings[i][0]) == ref) union(real, meetings[i][0], 0);
                    if (find(weak, meetings[i][1]) == ref) union(real, meetings[i][1], 0);
                }

                l = r; // 重新设置一个新的区间
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (find(real, i) == find(real, 0)) ans.add(i);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findAllPeople(3, new int[][]{{1,2,1}}, 1).equals(List.of(0, 1, 2));
    }

}
