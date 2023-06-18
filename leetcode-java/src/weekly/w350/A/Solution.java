package weekly.w350.A;

/**
 * 6901. Total Distance Traveled
 *
 * https://leetcode.cn/contest/weekly-contest-350/problems/total-distance-traveled/
 *
 * A truck has two fuel tanks. You are given two integers, mainTank representing the fuel present
 * in the main tank in liters and additionalTank representing the fuel present in the additional
 * tank in liters.
 *
 * The truck has a mileage of 10 km per liter. Whenever 5 liters of fuel get used up in the
 * main tank, if the additional tank has at least 1 liters of fuel, 1 liters of fuel will be
 * transferred from the additional tank to the main tank.
 *
 * Return the maximum distance which can be traveled.
 *
 * Note: Injection from the additional tank is not continuous.
 * It happens suddenly and immediately for every 5 liters consumed.
 */

public class Solution {

    public int distanceTraveled(int mainTank, int additionalTank) {
        int ans = 0;
        for (int i = 1; i <= mainTank; i++) {
            if (i % 5 == 0 && additionalTank > 0) {
                mainTank++; additionalTank--;
            }
            ans += 10;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
