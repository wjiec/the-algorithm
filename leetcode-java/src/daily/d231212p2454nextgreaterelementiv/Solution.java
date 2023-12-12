package daily.d231212p2454nextgreaterelementiv;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    public int[] secondGreaterElement(int[] nums) {
        int[] ans = new int[nums.length];
        PriorityQueue<Integer> first = new PriorityQueue<>(Comparator.comparingInt(i -> nums[i]));
        PriorityQueue<Integer> second = new PriorityQueue<>(Comparator.comparingInt(i -> nums[i]));
        for (int i = 0; i < nums.length; i++) {
            while (!second.isEmpty() && nums[second.peek()] < nums[i]) {
                ans[second.remove()] = nums[i];
            }
            while (!first.isEmpty() && nums[first.peek()] < nums[i]) {
                second.add(first.remove());
            }
            first.add(i);
        }
        while (!first.isEmpty()) ans[first.remove()] = -1;
        while (!second.isEmpty()) ans[second.remove()] = -1;
        return ans;
    }

    public static void main(String[] args) {
    }

}
