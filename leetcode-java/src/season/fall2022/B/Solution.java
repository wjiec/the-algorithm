package season.fall2022.B;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 2. 交通枢纽
 *
 * https://leetcode.cn/contest/season/2022-fall/problems/D9PW8w/
 *
 * 为了缓解「力扣嘉年华」期间的人流压力，组委会在活动期间开设了一些交通专线。path[i] = [a, b] 表示有一条从地点 a通往地点 b 的 单向 交通专线。
 * 若存在一个地点，满足以下要求，我们则称之为 交通枢纽：
 *
 * 所有地点（除自身外）均有一条 单向 专线 直接 通往该地点；
 * 该地点不存在任何 通往其他地点 的单向专线。
 * 请返回交通专线的 交通枢纽。若不存在，则返回 -1。
 *
 * 注意：
 *
 * 对于任意一个地点，至少被一条专线连通。
 */

public class Solution {

    // a -> b
    public int transportationHub(int[][] path) {
        int[] in = new int[1001], out = new int[1001];
        Arrays.fill(in, 0); Arrays.fill(out, 0);

        Set<Integer> set = new HashSet<>();
        for (var p : path) { in[p[1]]++; out[p[0]]++; set.add(p[0]); set.add(p[1]); }

        int n = set.size() - 1;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == n && out[i] == 0) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().transportationHub(new int[][]{{0,1}, {0,3}, {1,3}, {2,0}, {2,3}}) == 3;
    }

}
