package weekly.w245.A;

public class Solution {

    public boolean makeEqual(String[] words) {
        if (words.length == 1) return true;

        int max = 0;
        int[] map = new int[26];
        for (var word : words) {
            for (var c : word.toCharArray()) {
                max = Math.max(max, ++map[c - 'a']);
            }
        }

        for (int value : map) {
            if (value != 0 && value % words.length != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().makeEqual(new String[]{"caaaaa","aaaaaaaaa","a","bbb","bbbbbbbbb","bbb","cc","cccccccccccc","ccccccc","ccccccc","cc","cccc","c","cccccccc","c"});
        assert !new Solution().makeEqual(new String[]{"a","b"});
        assert new Solution().makeEqual(new String[]{"abc","aabc","bc"});
        assert !new Solution().makeEqual(new String[]{"ab","a"});
    }

}
