package weekly.bw74.p1;

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
