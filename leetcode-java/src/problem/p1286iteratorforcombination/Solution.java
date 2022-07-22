package problem.p1286iteratorforcombination;

/**
 * 1286. Iterator for Combination
 *
 * https://leetcode.cn/problems/iterator-for-combination/
 *
 * Design the CombinationIterator class:
 *
 * CombinationIterator(string characters, int combinationLength) Initializes the object with a string
 * characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
 * next() Returns the next combination of length combinationLength in lexicographical order.
 * hasNext() Returns true if and only if there exists a next combination.
 */

public class Solution {

    private static class CombinationIterator {
        private final int sl, il;
        private final char[] chars;
        private final int[] indexes;
        public CombinationIterator(String characters, int combinationLength) {
            il = combinationLength;
            sl = characters.length();
            chars = characters.toCharArray();
            indexes = new int[combinationLength];
            for (int i = 0; i < combinationLength; i++) indexes[i] = i;
        }

        public String next() {
            StringBuilder sb = new StringBuilder();
            for (var idx : indexes) sb.append(chars[idx]);
            if (hasNext()) {
                for (int i = il - 1, si = sl; i >= 0; i--, si--) {
                    if (++indexes[i] < si) {
                        for (int j = i + 1; j < il; j++) {
                            indexes[j] = indexes[j - 1] + 1;
                        }
                        break;
                    }
                }
            }
            return sb.toString();
        }

        public boolean hasNext() {
            for (int i = il - 1, x = sl - 1; i >= 0; i--, x--) {
                if (indexes[i] <= x) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        CombinationIterator it1 = new CombinationIterator("abcd", 3);
        while (it1.hasNext()) {
            System.out.println(it1.next());
        }

        CombinationIterator it2 = new CombinationIterator("abcde", 4);
        while (it2.hasNext()) {
            System.out.println(it2.next());
        }

        CombinationIterator iterator = new CombinationIterator("abc", 2);
        assert iterator.next().equals("ab");
        assert iterator.hasNext();
        assert iterator.next().equals("ac");
        assert iterator.hasNext();
        assert iterator.next().equals("bc");
        assert !iterator.hasNext();
    }

}
