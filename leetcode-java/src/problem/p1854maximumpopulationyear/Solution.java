package problem.p1854maximumpopulationyear;

/**
 * 1854. Maximum Population Year
 *
 * https://leetcode-cn.com/problems/maximum-population-year/
 *
 * You are given a 2D integer array logs where each logs[i] = [birthi, deathi]
 * indicates the birth and death years of the ith person.
 *
 * The population of some year x is the number of people alive during that year.
 *
 * The ith person is counted in year x's population if x is in the inclusive range [birthi, deathi - 1].
 *
 * Note that the person is not counted in the year that they die.
 *
 * Return the earliest year with the maximum population.
 */

public class Solution {

    public int maximumPopulation(int[][] logs) {
        int[] years = new int[2051];
        for (var log : logs) {
            years[log[0]] += 1;
            years[log[1]] += -1;
        }

        int max = 0, year = 0, people = 0;
        for (int i = 1950; i < years.length; i++) {
            people += years[i];
            if (people > max) {
                max = people;
                year = i;
            }
        }
        return year;
    }

    public static void main(String[] args) {
        assert new Solution().maximumPopulation(new int[][]{
            {2025,2041},
            {1988,2007},
            {2003,2046},
            {2045,2049},
            {2025,2027},
            {2014,2040},
            {2014,2027},
            {2011,2027},
            {1972,2019}
        }) == 2025;

        assert new Solution().maximumPopulation(new int[][]{
            {1993,1999},
            {2000,2010}
        }) == 1993;

        assert new Solution().maximumPopulation(new int[][]{
            {1950,1961},
            {1960,1971},
            {1970,1981},
        }) == 1960;
    }

}
