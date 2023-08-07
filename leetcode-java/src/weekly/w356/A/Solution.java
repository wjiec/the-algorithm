package weekly.w356.A;

/**
 * 2798. Number of Employees Who Met the Target
 *
 * https://leetcode.cn/contest/weekly-contest-356/problems/number-of-employees-who-met-the-target/
 *
 * There are n employees in a company, numbered from 0 to n - 1.
 * Each employee i has worked for hours[i] hours in the company.
 *
 * The company requires each employee to work for at least target hours.
 *
 * You are given a 0-indexed array of non-negative integers hours of length n and a non-negative integer target.
 *
 * Return the integer denoting the number of employees who worked at least target hours.
 */

public class Solution {

    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int ans = 0;
        for (var v : hours) if (v >= target) ans++;
        return ans;
    }

    public static void main(String[] args) {
    }

}
