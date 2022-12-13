package problem.p2502designmemoryallocator;

/**
 * 2502. Design Memory Allocator
 *
 * https://leetcode.cn/problems/design-memory-allocator/
 *
 * You are given an integer n representing the size of a 0-indexed memory array.
 * All memory units are initially free.
 *
 * You have a memory allocator with the following functionalities:
 *
 * Allocate a block of size consecutive free memory units and assign it the id mID.
 * Free all memory units with the given id mID.
 * Note that:
 *
 * Multiple blocks can be allocated to the same mID.
 * You should free all the memory units with mID, even if they were allocated in different blocks.
 * Implement the Allocator class:
 *
 * Allocator(int n) Initializes an Allocator object with a memory array of size n.
 *
 * int allocate(int size, int mID) Find the leftmost block of size consecutive free memory
 * units and allocate it with the id mID. Return the block's first index. If such a block
 * does not exist, return -1.
 *
 * int free(int mID) Free all memory units with the id mID. Return the number of memory
 * units you have freed.
 */

public class Solution {

    private static class Allocator {
        private final int[] memory;
        public Allocator(int n) { memory = new int[n]; }

        public int allocate(int size, int mID) {
            int n = memory.length, curr = 0;
            for (int i = 0; i < n; i++) {
                if (memory[i] == 0 && ++curr == size) {
                    for (; size > 0; size--) {
                        memory[i--] = mID;
                    }
                    return i + 1;
                } else if (memory[i] != 0) curr = 0;
            }
            return -1;
        }

        public int free(int mID) {
            int ans = 0;
            for (int i = 0; i < memory.length; i++) {
                if (memory[i] == mID) {
                    ans++; memory[i] = 0;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Allocator loc = new Allocator(10);
        assert loc.allocate(1, 1) == 0;
        assert loc.allocate(1, 2) == 1;
        assert loc.allocate(1, 3) == 2;
        assert loc.free(2) == 1;
        assert loc.allocate(3, 4) == 3;
        assert loc.allocate(1, 1) == 1;
        assert loc.allocate(1, 1) == 6;
        assert loc.free(1) == 3;
        assert loc.allocate(10, 2) == -1;
        assert loc.free(7) == 0;
    }

}
