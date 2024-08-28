package weekly.w412.D;

import java.util.*;

/**
 * 3267. Count Almost Equal Pairs II
 *
 * https://leetcode.cn/problems/count-almost-equal-pairs-ii
 *
 * Attention: In this version, the number of operations that can be performed, has been increased to twice.
 *
 * You are given an array nums consisting of positive integers.
 *
 * We call two integers x and y almost equal if both integers can become equal
 * after performing the following operation at most twice:
 *
 * Choose either x or y and swap any two digits within the chosen number.
 * Return the number of indices i and j in nums where i < j such that nums[i] and nums[j] are almost equal.
 *
 * Note that it is allowed for an integer to have leading zeros after performing an operation.
 */

public class Solution {

    public int countPairs(int[] nums) {
        // 根据长度进行排序, 这样使得长的数字可以变成短的数字
        String[] ns = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ns[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(ns, Comparator.comparingInt(String::length));

        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        // 枚举所有可能的变换, 并叠加所有之前出现的值
        for (String s : ns) {
            char[] curr = s.toCharArray();
            Set<Integer> set = new HashSet<>();
            set.add(toNumber(curr));

            for (int i = 0; i < curr.length; i++) {
                for (int j = i + 1; j < curr.length; j++) {
                    swap(curr, i, j); // 第一次交换
                    set.add(toNumber(curr));

                    for (int k = i + 1; k < curr.length; k++) {
                        for (int l = k + 1; l < curr.length; l++) {
                            swap(curr, k, l); // 第二次交换
                            set.add(toNumber(curr));
                            swap(curr, k, l);
                        }
                    }

                    swap(curr, i, j);
                }
            }

            for (var elem : set) ans += map.getOrDefault(elem, 0);
            map.merge(toNumber(curr), 1, Integer::sum);
        }

        return ans;
    }

    private static void swap(char[] chars, int a, int b) {
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }

    private int toNumber(char[] chars) {
        int ans = 0;
        for (var c : chars) ans = ans * 10 + (c - '0');
        return ans;
    }

    public static void main(String[] args) {
    }

}
