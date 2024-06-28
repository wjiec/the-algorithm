package problem.p2147numberofwaystodividealongcorridor;

/**
 * 2147. Number of Ways to Divide a Long Corridor
 *
 * https://leetcode.cn/problems/number-of-ways-to-divide-a-long-corridor/
 *
 * Along a long library corridor, there is a line of seats and decorative plants.
 * You are given a 0-indexed string corridor of length n consisting of letters 'S' and 'P' where
 * each 'S' represents a seat and each 'P' represents a plant.
 *
 * One room divider has already been installed to the left of index 0, and another to the
 * right of index n - 1. Additional room dividers can be installed. For each position between
 * indices i - 1 and i (1 <= i <= n - 1), at most one divider can be installed.
 *
 * Divide the corridor into non-overlapping sections, where each section has exactly two seats
 * with any number of plants. There may be multiple ways to perform the division.
 * Two ways are different if there is a position with a room divider installed in the first
 * way but not in the second way.
 *
 * Return the number of ways to divide the corridor. Since the answer may be very large, return
 * it modulo 109 + 7. If there is no way, return 0.
 */

public class Solution {

    public int numberOfWays(String corridor) {
        final int MOD = 1_000_000_007;
        char[] chars = corridor.toCharArray();

        long ans = 1, sn = 0;
        for (int i = 0, p = -1; i < chars.length; i++) {
            if (chars[i] == 'S') {
                if (++sn % 2 == 1 && sn > 2) {
                    ans = (ans * (i - p)) % MOD;
                }
                p = i;
            }
        }

        return (sn < 2 || (sn & 1) == 1) ? 0 : (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().numberOfWays("SPPPPPPPSPPPSPSSSPPPPPPPPPPPPPPPPPSPPPPPPPPPPPPPPPPSPPPPPSPSPPPPPPSPSPPSPSPPPSPSPPSSPPPPPSPPSSPP") == 0;
        assert new Solution().numberOfWays("PP") == 0;
        assert new Solution().numberOfWays("SSPPSPS") == 3;
        assert new Solution().numberOfWays("PPSPSP") == 1;
        assert new Solution().numberOfWays("S") == 0;
    }

}
