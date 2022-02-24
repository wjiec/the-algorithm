package problem.p157readncharactersgivenread4;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 157. Read N Characters Given Read4
 *
 * https://leetcode-cn.com/problems/read-n-characters-given-read4/
 *
 * Given a file and assume that you can only read the file using a given method read4,
 * implement a method to read n characters.
 *
 * Method read4:
 *
 * The API read4 reads four consecutive characters from file,
 * then writes those characters into the buffer array buf4.
 *
 * The return value is the number of actual characters read.
 *
 * Note that read4() has its own file pointer, much like FILE *fp in C.
 */

public class Solution {

    private static class Reader4 {
        private boolean first = true;
        public int read4(char[] buf4) {
            if (first) {
                first = false;
                buf4[0] = 'a';
                buf4[1] = 'b';
                buf4[2] = 'c';
                buf4[3] = 'd';
                return 4;
            }

            buf4[0] = 'x';
            buf4[1] = 'y';
            buf4[2] = 'z';
            return 3;
        }
    }

    private static class Reader extends Reader4 {
        /**
         * @param buf Destination buffer
         * @param n   Number of characters to read
         * @return    The number of actual characters read
         */
        public int read(char[] buf, int n) {
            int actual = 0; char[] buf4 = new char[4];
            while (actual < n) {
                int curr = Math.min(read4(buf4), n - actual);
                for (int i = 0; i < curr; i++) buf[actual++] = buf4[i];
                if (curr != 4) break;
            }
            return actual;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Reader().read(new char[3], 3));
        System.out.println(new Reader().read(new char[7], 7));
    }

}
