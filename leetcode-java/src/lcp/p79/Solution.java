package lcp.p79;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LCP 79. 提取咒文
 *
 * https://leetcode.cn/problems/kjpLFZ/
 *
 * 随着兽群逐渐远去，一座大升降机缓缓的从地下升到了远征队面前。借由这台升降机，他们将能够到达地底的永恒至森。
 * 在升降机的操作台上，是一个由魔法符号组成的矩阵，为了便于辨识，我们用小写字母来表示。
 * matrix[i][j] 表示矩阵第 i 行 j 列的字母。该矩阵上有一个提取装置，可以对所在位置的字母提取。
 * 提取装置初始位于矩阵的左上角 [0,0]，可以通过每次操作移动到上、下、左、右相邻的 1 格位置中。
 * 提取装置每次移动或每次提取均记为一次操作。
 *
 * 远征队需要按照顺序，从矩阵中逐一取出字母以组成 mantra，才能够成功的启动升降机。
 *
 * 请返回他们 最少 需要消耗的操作次数。如果无法完成提取，返回 -1。
 *
 * 注意：
 *
 * 提取装置可对同一位置的字母重复提取，每次提取一个
 * 提取字母时，需按词语顺序依次提取
 */

@SuppressWarnings("unchecked")
public class Solution {

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int extractMantra(String[] matrix, String mantra) {
        int m = matrix.length, n = matrix[0].length(), l = mantra.length();

        boolean[][][] seen = new boolean[m][n][l];
        seen[0][0][0] = true;

        // [x, y, ci]
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 0});

        char[] chars = mantra.toCharArray();
        char[][] map = new char[m][];
        for (int i = 0; i < m; i++) {
            map[i] = matrix[i].toCharArray();
        }

        for (int ans = 1; !queue.isEmpty(); ans++) {
            for (int i = 0, s = queue.size(); i < s; i++) {
                int[] curr = queue.remove();
                if (map[curr[0]][curr[1]] == chars[curr[2]]) {
                    if (curr[2] == l - 1) return ans;
                    else queue.add(new int[]{curr[0], curr[1], curr[2] + 1});
                } else {
                    for (var dir : dirs) {
                        int dx = curr[0] + dir[0], dy = curr[1] + dir[1];
                        if (dx >= 0 && dx < m && dy >= 0 && dy < n && !seen[dx][dy][curr[2]]) {
                            seen[dx][dy][curr[2]] = true;
                            queue.add(new int[]{dx, dy, curr[2]});
                        }
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().extractMantra(new String[]{
            "hydfpyqaoir",
            "ixpjveolliy",
            "hidhpqciygm",
            "icnefohovue",
            "qcwopbcbxbn",
            "dvahetjbfqg",
            "uiwjsukwofm",
            "spzjegbovxo",
            "aflruwmvkdp"
        }, "nrahvcpkmqgsyrcpmgfpmaxvydbp") == 132;
    }

}
