package weekly.biweekly52.p3;

import java.util.Arrays;

public class Solution {

    public char[][] rotateTheBox(char[][] box) {
        int line = box.length, column = box[0].length;
        for (int i = 0; i < line; i++) {
            int right = column - 1;
            for (int j = column - 1; j >= 0; j--) {
                if (box[i][j] == '*') {
                    right = j - 1;
                } else if (box[i][j] == '#') {
                    box[i][j] = '.';
                    box[i][right--] = '#';
                }
            }
        }

        char[][] rs = new char[column][line];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                rs[j][line - i - 1] = box[i][j];
            }
        }

        return rs;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().rotateTheBox(new char[][]{
            {'#','.','#'},
        })));
        System.out.println();
        System.out.println(Arrays.deepToString(new Solution().rotateTheBox(new char[][]{
            {'#','.','*','.'},
            {'#','#','*','.'},
        })));
        System.out.println();
        System.out.println(Arrays.deepToString(new Solution().rotateTheBox(new char[][]{
            {'#','#','*','.','*','.'},
            {'#','#','#','*','.','.'},
            {'#','#','#','.','#','.'},
        })));
    }

}
