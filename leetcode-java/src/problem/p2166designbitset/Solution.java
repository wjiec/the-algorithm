package problem.p2166designbitset;

import java.util.HashSet;

/**
 * 2166. Design Bitset
 *
 * https://leetcode.cn/problems/design-bitset/
 *
 * A Bitset is a data structure that compactly stores bits.
 *
 * Implement the Bitset class:
 *
 * Bitset(int size) Initializes the Bitset with size bits, all of which are 0.
 *
 * void fix(int idx) Updates the value of the bit at the index idx to 1.
 * If the value was already 1, no change occurs.
 *
 * void unfix(int idx) Updates the value of the bit at the index idx to 0.
 * If the value was already 0, no change occurs.
 *
 * void flip() Flips the values of each bit in the Bitset. In other words, all bits
 * with value 0 will now have value 1 and vice versa.
 *
 * boolean all() Checks if the value of each bit in the Bitset is 1. Returns true
 * if it satisfies the condition, false otherwise.
 *
 * boolean one() Checks if there is at least one bit in the Bitset with value 1.
 * Returns true if it satisfies the condition, false otherwise.
 *
 * int count() Returns the total number of bits in the Bitset which have value 1.
 *
 * String toString() Returns the current composition of the Bitset.
 * Note that in the resultant string, the character at the ith index
 * should coincide with the value at the ith bit of the Bitset.
 */

public class Solution {

    private static class Bitset {
        private final int n;
        private HashSet<Integer> t = new HashSet<>();
        private HashSet<Integer> f = new HashSet<>();
        public Bitset(int size) { n = size; for (int i = 0; i < size; i++) f.add(i); }
        public void flip() { HashSet<Integer> temp = t; t = f; f = temp; }
        public boolean all() { return f.isEmpty(); }
        public boolean one() { return !t.isEmpty(); }
        public int count() { return t.size(); }

        public void fix(int idx) {
            if (f.contains(idx)) {
                t.add(idx);
                f.remove(idx);
            }
        }

        public void unfix(int idx) {
            if (t.contains(idx)) {
                f.add(idx);
                t.remove(idx);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(t.contains(i) ? '1' : '0');
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Bitset bs = new Bitset(5);
        bs.fix(3);
        bs.fix(1);
        bs.flip();
        assert !bs.all();
        bs.unfix(0);
        bs.flip();
        assert bs.one();
        bs.unfix(0);
        assert bs.count() == 2;
        assert bs.toString().equals("01010");
    }

}
