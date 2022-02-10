package problem.p1491averagesalaryexcludingtheminimumandmaximumsalary;

/**
 * 1491. Average Salary Excluding the Minimum and Maximum Salary
 *
 * https://leetcode-cn.com/problems/average-salary-excluding-the-minimum-and-maximum-salary/
 *
 * Given an array of unique integers salary where salary[i] is the salary of the employee i.
 *
 * Return the average salary of employees excluding the minimum and maximum salary.
 */

public class Solution {

    public double average(int[] salary) {
        int total = 0, min = Integer.MAX_VALUE, max = 0;
        for (var n : salary) {
            total += n;
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        return ((double) (total - min - max)) / (salary.length - 2);
    }

    public static void main(String[] args) {
        assert new Solution().average(new int[]{4000,3000,1000,2000}) == 2500.00000;
        assert new Solution().average(new int[]{1000,2000,3000}) == 2000.00000;
        assert new Solution().average(new int[]{6000,5000,4000,3000,2000,1000}) == 3500.00000;
        assert new Solution().average(new int[]{8000,9000,2000,3000,6000,1000}) == 4750.00000;
    }

}
