package problem.p277findthecelebrity;

/**
 * 277. Find the Celebrity
 *
 * https://leetcode.cn/problems/find-the-celebrity/
 *
 * Suppose you are at a party with n people labeled from 0 to n - 1 and among them, there
 * may exist one celebrity. The definition of a celebrity is that all the other n - 1 people
 * know the celebrity, but the celebrity does not know any of them.
 *
 * Now you want to find out who the celebrity is or verify that there is not one. The only thing
 * you are allowed to do is ask questions like: "Hi, A. Do you know B?" to get information about
 * whether A knows B. You need to find out the celebrity (or verify there is not one) by asking
 * as few questions as possible (in the asymptotic sense).
 *
 * You are given a helper function bool knows(a, b) that tells you whether A knows B. Implement a
 * function int findCelebrity(n). There will be exactly one celebrity if they are at the party.
 *
 * Return the celebrity's label if there is a celebrity at the party.
 * If there is no celebrity, return -1.
 */

public class Solution {

    public static class Relation {
        private final int[][] graph;
        public Relation(int[][] graph) { this.graph = graph; }
        public boolean knows(int a, int b) { return graph[a][b] == 1; }
    }

    public static class FindCelebrity extends Relation {
        public FindCelebrity(int[][] graph) { super(graph); }

        public int findCelebrity(int n) {
            int celebrity = 0;
            for (int i = 1; i < n; i++) {
                // 如果当前候选名人知道 i, 说明候选人肯定不是名人
                // 而 i 则成为新的候选名人
                if (knows(celebrity, i)) {
                    celebrity = i;
                }
            }

            // 根据得到的候选名人进行校验
            for (int i = 0; i < n; i++) {
                if (i == celebrity) continue;

                // 如果候选名人知道一个人, 则说明候选名人不正确
                // 如果有人不知道候选名人, 则说明候选名人不正确
                if (knows(celebrity, i) || !knows(i, celebrity)) return -1;
            }
            return celebrity;
        }
    }

    public static void main(String[] args) {
        assert new FindCelebrity(new int[][]{{1,1,0}, {0,1,0}, {1,1,1}}).findCelebrity(3) == 1;
        assert new FindCelebrity(new int[][]{{1,0,1}, {1,1,0}, {0,1,1}}).findCelebrity(3) == -1;
    }

}
