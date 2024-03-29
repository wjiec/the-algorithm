package weekly.bw104.A;

/**
 * 6366. Number of Senior Citizens
 *
 * https://leetcode.cn/contest/biweekly-contest-104/problems/number-of-senior-citizens/
 *
 * You are given a 0-indexed array of strings details. Each element of details provides
 * information about a given passenger compressed into a string of length 15.
 *
 * The system is such that:
 *
 * The first ten characters consist of the phone number of passengers.
 * The next character denotes the gender of the person.
 * The following two characters are used to indicate the age of the person.
 * The last two characters determine the seat allotted to that person.
 * Return the number of passengers who are strictly more than 60 years old.
 */

public class Solution {

    public int countSeniors(String[] details) {
        int ans = 0;
        for (var detail : details) {
            if (Integer.parseInt(detail.substring(11, 13)) > 60) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
