package lcci.s10.p10rankfromstreamlcci;

/**
 * 面试题 10.10. 数字流的秩
 *
 * https://leetcode.cn/problems/rank-from-stream-lcci/
 *
 * 假设你正在读取一串整数。每隔一段时间，你希望能找出数字 x 的秩(小于或等于 x 的值的个数)。
 *
 * 请实现数据结构和算法来支持这些操作，也就是说：
 *
 * 实现 track(int x) 方法，每读入一个数字都会调用该方法；
 *
 * 实现 getRankOfNumber(int x) 方法，返回小于或等于 x 的值的个数。
 *
 * 注意：本题相对原题稍作改动
 */

public class Solution {

    private static class StreamRank {
        private final int[] tree = new int[100_100];
        public StreamRank() {}

        public void track(int x) {
            for (x++; x < tree.length; x += x & -x) tree[x]++;
        }

        public int getRankOfNumber(int x) {
            int ans = 0;
            for (x++; x > 0; x -= x & -x) {
                ans += tree[x];
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        StreamRank rank = new StreamRank();
        assert rank.getRankOfNumber(1) == 0;
        rank.track(0);
        assert rank.getRankOfNumber(0) == 1;
    }

}
