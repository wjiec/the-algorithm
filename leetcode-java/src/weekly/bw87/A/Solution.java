package weekly.bw87.A;

/**
 * 6184. Count Days Spent Together
 *
 * https://leetcode.cn/contest/biweekly-contest-87/problems/count-days-spent-together/
 *
 * Alice and Bob are traveling to Rome for separate business meetings.
 *
 * You are given 4 strings arriveAlice, leaveAlice, arriveBob, and leaveBob.
 * Alice will be in the city from the dates arriveAlice to leaveAlice (inclusive), while
 * Bob will be in the city from the dates arriveBob to leaveBob (inclusive).
 *
 * Each will be a 5-character string in the format "MM-DD", corresponding to
 * the month and day of the date.
 *
 * Return the total number of days that Alice and Bob are in Rome together.
 *
 * You can assume that all dates occur in the same calendar year, which is not a leap year.
 * Note that the number of days per month can be represented as: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31].
 */

public class Solution {

    private final int[] days = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int aa = toDays(arriveAlice), la = toDays(leaveAlice);
        int ab = toDays(arriveBob), lb = toDays(leaveBob);

        boolean[] visited = new boolean[366];
        for (int i = aa; i <= la; i++) visited[i] = true;

        int ans = 0;
        for (int i = ab; i <= lb; i++) if (visited[i]) ans++;
        return ans;
    }


    private int toDays(String arrive) {
        int m = Integer.parseInt(arrive, 0, 2, 10);
        int d = Integer.parseInt(arrive, 3, 5, 10);

        int ans = 0;
        for (int i = 0; i < m; i++) ans += days[i];
        return ans + d;
    }

    public static void main(String[] args) {
    }

}
