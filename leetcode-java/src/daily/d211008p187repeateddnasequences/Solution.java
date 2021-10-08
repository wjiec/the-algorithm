package daily.d211008p187repeateddnasequences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 187. Repeated DNA Sequences
 *
 * https://leetcode-cn.com/problems/repeated-dna-sequences/
 *
 * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 *
 * For example, "ACGAATTCCG" is a DNA sequence.
 * When studying DNA, it is useful to identify repeated sequences within the DNA.
 *
 * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings)
 * that occur more than once in a DNA molecule. You may return the answer in any order
 */

public class Solution {

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0, l = s.length() - 10; i <= l; i++) {
            String sub = s.substring(i, i + 10);
            map.merge(sub, 1, Integer::sum);
            if (map.get(sub) == 2) ans.add(sub);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(new Solution().findRepeatedDnaSequences("AAAAAAAAAAAAA"));
    }

}
