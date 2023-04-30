package problem.p379designphonedirectory;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 379. Design Phone Directory
 *
 * https://leetcode.cn/problems/design-phone-directory/
 *
 * Design a phone directory that initially has maxNumbers empty slots that can store numbers.
 * The directory should store numbers, check if a certain slot is empty or not, and empty a given slot.
 *
 * Implement the PhoneDirectory class:
 *
 * PhoneDirectory(int maxNumbers) Initializes the phone directory with the number of available slots maxNumbers.
 * int get() Provides a number that is not assigned to anyone. Returns -1 if no number is available.
 * bool check(int number) Returns true if the slot number is available and false otherwise.
 * void release(int number) Recycles or releases the slot number.
 */

public class Solution {

    private static class PhoneDirectory {
        private final Set<Integer> assigned = new HashSet<>();
        private final Queue<Integer> usable = new ArrayDeque<>();
        public PhoneDirectory(int maxNumbers) { for (int i = 0; i < maxNumbers; i++) usable.add(i); }
        public boolean check(int number) { return !assigned.contains(number); }
        public int get() { if (usable.isEmpty()) return -1; int v = usable.remove(); assigned.add(v); return v; }
        public void release(int number) { if (assigned.remove(number)) usable.add(number); }
    }

}
