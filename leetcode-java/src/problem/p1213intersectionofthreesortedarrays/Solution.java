package problem.p1213intersectionofthreesortedarrays;

import java.util.ArrayList;
import java.util.List;

/**
 * 1213. Intersection of Three Sorted Arrays
 *
 * https://leetcode-cn.com/problems/intersection-of-three-sorted-arrays/
 *
 * Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order,
 * return a sorted array of only the integers that appeared in all three arrays.
 */

public class Solution {

    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int a = 0, b = 0;
        List<Integer> ans = new ArrayList<>();
        for (int j : arr1) {
            while (a < arr2.length && arr2[a] < j) a++;
            while (b < arr3.length && arr3[b] < j) b++;
            if (a == arr2.length || b == arr3.length) break;
            if (j == arr2[a] && arr2[a] == arr3[b]) ans.add(j);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().arraysIntersection(new int[]{1,2,3,4,5},
            new int[]{1,2,5,7,9}, new int[]{1,3,4,5,8}));

        System.out.println(new Solution().arraysIntersection(new int[]{197,418,523,876,1356},
            new int[]{501,880,1593,1710,1870}, new int[]{521,682,1337,1395,1764}));
    }

}
