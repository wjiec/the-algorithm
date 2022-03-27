package problem.p406queuereconstructionbyheight;

import common.PrettyPrinter;

import java.util.Arrays;

/**
 * 406. Queue Reconstruction by Height
 *
 * https://leetcode-cn.com/problems/queue-reconstruction-by-height/
 *
 * You are given an array of people, people, which are the attributes of some people
 * in a queue (not necessarily in order). Each people[i] = [hi, ki] represents the ith person of height hi
 * with exactly ki other people in front who have a height greater than or equal to hi.
 *
 * Reconstruct and return the queue that is represented by the input array people. The returned queue
 * should be formatted as an array queue, where queue[j] = [hj, kj] is the attributes of the jth person
 * in the queue (queue[0] is the person at the front of the queue).
 */

public class Solution {

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return b[1] - a[1];
        });

        int[][] ans = new int[people.length][];
        for (var person : people) {
            int spaces = person[1] + 1;
            for (int i = 0; i < people.length; i++) {
                if (ans[i] == null) {
                    if (--spaces == 0) {
                        ans[i] = person;
                        break;
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().reconstructQueue(new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}}));
        PrettyPrinter.println(new Solution().reconstructQueue(new int[][]{{6,0},{5,0},{4,0},{3,2},{2,2},{1,4}}));
    }

}
