package weekly.w312.A;

import java.util.Arrays;

/**
 * 6188. Sort the People
 *
 * https://leetcode.cn/contest/weekly-contest-312/problems/sort-the-people/
 *
 * You are given an array of strings names, and an array heights that consists of distinct
 * positive integers. Both arrays are of length n.
 *
 * For each index i, names[i] and heights[i] denote the name and height of the ith person.
 *
 * Return names sorted in descending order by the people's heights.
 */

public class Solution {

    private record People(String name, int height) {}

    public String[] sortPeople(String[] names, int[] heights) {
        People[] people = new People[names.length];
        for (int i = 0; i < names.length; i++) {
            people[i] = new People(names[i], heights[i]);
        }
        Arrays.sort(people, (a, b) -> b.height - a.height);

        for (int i = 0; i < names.length; i++) {
            names[i] = people[i].name;
        }
        return names;
    }

    public static void main(String[] args) {
    }

}
