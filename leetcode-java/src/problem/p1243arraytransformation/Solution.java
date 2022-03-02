package problem.p1243arraytransformation;

import java.util.Arrays;
import java.util.List;

/**
 * 1243. Array Transformation
 *
 * https://leetcode-cn.com/problems/array-transformation/
 *
 * Given an initial array arr, every day you produce a new array using the array of the previous day.
 *
 * On the i-th day, you do the following operations on the array of day i-1 to produce the array of day i:
 *
 * If an element is smaller than both its left neighbor and its right neighbor, then this element is incremented.
 * If an element is bigger than both its left neighbor and its right neighbor, then this element is decremented.
 * The first and last elements never change.
 * After some days, the array does not change. Return that final array.
 */

public class Solution {

    public List<Integer> transformArray(int[] arr) {
        if (arr.length > 2) {
            boolean changed;
            int n = arr.length - 1;

            do {
                changed = false;
                for (int i = 1, r = i + 1, l = arr[0]; i < n; i++, r++) {
                    if (arr[i] < l && arr[i] < arr[r]) {
                        l = arr[i]++;
                        changed = true;
                    } else if (arr[i] > l && arr[i] > arr[r]) {
                        l = arr[i]--;
                        changed = true;
                    } else l = arr[i];
                }
            } while (changed);
        }
        return Arrays.stream(arr).boxed().toList();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().transformArray(new int[]{6,2,3,4}));
        System.out.println(new Solution().transformArray(new int[]{1,6,3,4,3,5}));
    }

}
