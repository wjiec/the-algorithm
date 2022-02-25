package problem.p760findanagrammappings;

import common.Checker;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * 760. Find Anagram Mappings
 *
 * https://leetcode-cn.com/problems/find-anagram-mappings/
 *
 * You are given two integer arrays nums1 and nums2 where nums2 is an anagram of nums1.
 *
 * Both arrays may contain duplicates.
 *
 * Return an index mapping array mapping from nums1 to nums2 where mapping[i] = j
 * means the ith element in nums1 appears in nums2 at index j.
 *
 * If there are multiple answers, return any of them.
 *
 * An array "a" is an anagram of an array b means b is made by randomizing the order of the elements in "a".
 */

public class Solution {

    public int[] anagramMappings(int[] nums1, int[] nums2) {
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            if (!map.containsKey(nums2[i])) {
                map.put(nums2[i], new ArrayDeque<>());
            }
            map.get(nums2[i]).add(i);
        }

        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]).remove();
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().anagramMappings(new int[]{12,28,46,32,50},
            new int[]{50,12,32,46,28}), new int[]{1,4,3,2,0});

        assert Checker.check(new Solution().anagramMappings(new int[]{84,46},
            new int[]{84,46}), new int[]{0,1});
    }

}
