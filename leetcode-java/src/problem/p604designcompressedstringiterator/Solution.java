package problem.p604designcompressedstringiterator;

/**
 * 604. Design Compressed String Iterator
 *
 * https://leetcode-cn.com/problems/design-compressed-string-iterator/
 *
 * Design and implement a data structure for a compressed string iterator.
 *
 * The given compressed string will be in the form of each letter followed by a positive integer
 * representing the number of this letter existing in the original uncompressed string.
 *
 * Implement the StringIterator class:
 *
 * next() Returns the next character if the original string
 * still has uncompressed characters, otherwise returns a white space.
 *
 * hasNext() Returns true if there is any letter needs to be
 * uncompressed in the original string, otherwise returns false.
 */

public class Solution {

    private static class StringIterator {
        private int i = 0;
        private int count = 0;
        private char curr = ' ';
        private final char[] compressed;
        public StringIterator(String compressedString) { compressed = compressedString.toCharArray(); }

        public char next() {
            if (!hasNext()) return ' ';
            if (count == 0) {
                curr = compressed[i++];
                while (i < compressed.length && Character.isDigit(compressed[i])) {
                    count = count * 10 + (compressed[i++] - '0');
                }
            }

            count--;
            return curr;
        }

        public boolean hasNext() { return count != 0 || i != compressed.length; }
    }

    @SuppressWarnings("AssertionCanBeIf")
    public static void main(String[] args) {
        StringIterator iterator = new StringIterator("L1e2t1C1o1d1");
        assert iterator.next() == 'L';
        //noinspection ConstantConditions
        assert iterator.next() == 'e';
        assert iterator.next() == 'e';
        assert iterator.next() == 't';
        assert iterator.next() == 'C';
        assert iterator.next() == 'o';
        assert iterator.hasNext();
        assert iterator.next() == 'd';
        assert !iterator.hasNext();
    }

}
