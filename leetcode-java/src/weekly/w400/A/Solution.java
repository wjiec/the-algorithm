package weekly.w400.A;

/**
 * 100307. Minimum Number of Chairs in a Waiting Room
 *
 * https://leetcode.cn/contest/weekly-contest-400/problems/minimum-number-of-chairs-in-a-waiting-room/
 *
 * You are given a string s. Simulate events at each second i:
 *
 * If s[i] == 'E', a person enters the waiting room and takes one of the chairs in it.
 * If s[i] == 'L', a person leaves the waiting room, freeing up a chair.
 *
 * Return the minimum number of chairs needed so that a chair is available for every
 * person who enters the waiting room given that it is initially empty.
 */

public class Solution {

    public int minimumChairs(String s) {
        int ans = 0, curr = 0;
        for (var c : s.toCharArray()) {
            switch (c) {
                case 'E' -> curr++;
                case 'L' -> curr--;
            }
            ans = Math.max(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
