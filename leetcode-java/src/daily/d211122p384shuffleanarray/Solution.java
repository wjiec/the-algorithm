package daily.d211122p384shuffleanarray;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 384. Shuffle an Array
 *
 * https://leetcode-cn.com/problems/shuffle-an-array/
 *
 * Given an integer array nums, design an algorithm to randomly shuffle the array.
 *
 * All permutations of the array should be equally likely as a result of the shuffling.
 *
 * Implement the Solution class:
 *
 * Solution(int[] nums) Initializes the object with the integer array nums.
 * int[] reset() Resets the array to its original configuration and returns it.
 * int[] shuffle() Returns a random shuffling of the array.
 */

public class Solution {

    private static class Shuffle {

        private int len;
        private int[] sources;
        private int[] randoms;

        public Shuffle(int[] nums) {
            len = nums.length;
            sources = nums;
            randoms = new int[nums.length];
            System.arraycopy(nums, 0, randoms, 0, nums.length);
        }

        public int[] reset() {
            return sources;
        }

        public int[] shuffle() {
            for (int i = 0; i < len; i++) {
                int j = i + ThreadLocalRandom.current().nextInt(len - i);
                int t = randoms[i];
                randoms[i] = randoms[j];
                randoms[j] = t;
            }
            return randoms;
        }
    }

    public static void main(String[] args) {
        Shuffle shuffle = new Shuffle(new int[]{1,2,3});
        System.out.println(Arrays.toString(shuffle.shuffle()));
        System.out.println(Arrays.toString(shuffle.shuffle()));
        System.out.println(Arrays.toString(shuffle.reset()));
        System.out.println(Arrays.toString(shuffle.shuffle()));
        System.out.println(Arrays.toString(shuffle.reset()));
        System.out.println(Arrays.toString(shuffle.shuffle()));
    }

}
