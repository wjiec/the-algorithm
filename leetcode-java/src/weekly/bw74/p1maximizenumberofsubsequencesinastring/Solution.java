package weekly.bw74.p1maximizenumberofsubsequencesinastring;

/**
 * 6021. Maximize Number of Subsequences in a String
 *
 * https://leetcode-cn.com/problems/maximize-number-of-subsequences-in-a-string/
 *
 * You are given a 0-indexed string text and another 0-indexed string pattern of length 2,
 * both of which consist of only lowercase English letters.
 *
 * You can add either pattern[0] or pattern[1] anywhere in text exactly once. Note that the character
 * can be added even at the beginning or at the end of text.
 *
 * Return the maximum number of times pattern can occur as a subsequence of the modified text.
 *
 * A subsequence is a string that can be derived from another string by deleting some or no characters
 * without changing the order of the remaining characters.
 */

public class Solution {

    public long maximumSubsequenceCount(String text, String pattern) {
        long sum = 0;
        int n = text.length();
        char a = pattern.charAt(0), b = pattern.charAt(1);
        int[] al = new int[n + 1], bl = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            al[i] = al[i - 1];
            if (text.charAt(i - 1) == a) al[i]++;
        }
        for (int i = n - 1; i >= 0; i--) {
            bl[i] = bl[i + 1];
            if (text.charAt(i) == b) {
                bl[i]++;
                sum += al[i];
            }
        }

        long ans = 0;
        for (int i = 0; i <= n; i++) {
            ans = Math.max(ans, sum + Math.max(al[i], bl[i]));
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumSubsequenceCount("zigfj", "ju") == 1;
        assert new Solution().maximumSubsequenceCount("lzz", "lj") == 1;

        assert new Solution().maximumSubsequenceCount("fwymvreuftzgrcrxczjacqovduqaiig", "yy") == 1;
        assert new Solution().maximumSubsequenceCount("vnedkpkkyxelxqptfwuzcjhqmwagvrglkeivowvbjdoyydnjrqrqejoyptzoklaxcjxbrrfmpdxckfjzahparhpanwqfjrpbslsyiwbldnpjqishlsuagevjmiyktgofvnyncizswldwnngnkifmaxbmospdeslxirofgqouaapfgltgqxdhurxljcepdpndqqgfwkfiqrwuwxfamciyweehktaegynfumwnhrgrhcluenpnoieqdivznrjljcotysnlylyswvdlkgsvrotavnkifwmnvgagjykxgwaimavqsxuitknmbxppgzfwtjdvegapcplreokicxcsbdrsyfpustpxxssnouifkypwqrywprjlyddrggkcglbgcrbihgpxxosmejchmzkydhquevpschkpyulqxgduqkqgwnsowxrmgqbmltrltzqmmpjilpfxocflpkwithsjlljxdygfvstvwqsyxlkknmgpppupgjvfgmxnwmvrfuwcrsadomyddazlonjyjdeswwznkaeaasyvurpgyvjsiltiykwquesfjmuswjlrphsdthmuqkrhynmqnfqdlwnwesdmiiqvcpingbcgcsvqmsmskesrajqwmgtdoktreqssutpudfykriqhblntfabspbeddpdkownehqszbmddizdgtqmobirwbopmoqzwydnpqnvkwadajbecmajilzkfwjnpfyamudpppuxhlcngkign", "rr") == 496;

        // a c c
        assert new Solution().maximumSubsequenceCount("abdcdbc", "ac") == 4;
        // a a b b
        assert new Solution().maximumSubsequenceCount("aabb", "ab") == 6;
        // m p m p
        assert new Solution().maximumSubsequenceCount("iekbksdsmuuzwxbpmcngsfkjvpzuknqguzvzik", "mp") == 5;
    }

}
