package daily.d220518p668kthsmallestnumberinmultiplicationtable;

import common.TODO;

public class Solution {

    @TODO(tips = "binary")
    public int findKthNumber(int m, int n, int k) {
        int l = 0, r = m * n;
        while (l < r) {
            int x = l + (r - l) / 2;

            int count = x / n * n;
            for (int i = x / n + 1; i <= m; i++) {
                count += x / i;
            }

            if (count >= k) r = x;
            else l = x + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        assert new Solution().findKthNumber(3, 3, 5) == 3;
        assert new Solution().findKthNumber(2, 3, 6) == 6;
    }

}
