package problem.p1797designauthenticationmanager;

import java.util.*;

/**
 * 1797. Design Authentication Manager
 *
 * https://leetcode.cn/problems/design-authentication-manager/
 *
 * There is an authentication system that works with authentication tokens.
 * For each session, the user will receive a new authentication token that
 * will expire timeToLive seconds after the currentTime.
 *
 * If the token is renewed, the expiry time will be extended to expire
 * timeToLive seconds after the (potentially different) currentTime.
 *
 * Implement the AuthenticationManager class:
 *
 * AuthenticationManager(int timeToLive) constructs the AuthenticationManager
 * and sets the timeToLive.
 * generate(string tokenId, int currentTime) generates a new token with the
 * given tokenId at the given currentTime in seconds.
 * renew(string tokenId, int currentTime) renews the unexpired token with the
 * given tokenId at the given currentTime in seconds. If there are no unexpired
 * tokens with the given tokenId, the request is ignored, and nothing happens.
 * countUnexpiredTokens(int currentTime) returns the number of unexpired tokens
 * at the given currentTime.
 *
 * Note that if a token expires at time t, and another action happens on
 * time t (renew or countUnexpiredTokens), the expiration takes place
 * before the other actions.
 */

public class Solution {

    private static class AuthenticationManager {
        private final int ttl;
        private final Map<String, Integer> end = new HashMap<>();
        private final TreeMap<Integer, Set<String>> groups = new TreeMap<>();
        public AuthenticationManager(int timeToLive) { ttl = timeToLive; }
        private void roundExpired(int curr) {
            if (groups.isEmpty()) return;
            int k = groups.firstKey();
            while (k <= curr) {
                for (var id : groups.get(k)) {
                    if (end.get(id) == k) {
                        end.remove(id);
                    } else {
                        groups.computeIfAbsent(end.get(id), v -> new HashSet<>())
                            .add(id);
                    }
                }
                groups.remove(k);
                if (groups.isEmpty()) return;
                k = groups.firstKey();
            }
        }

        public void generate(String tokenId, int currentTime) {
            roundExpired(currentTime);
            end.put(tokenId, currentTime + ttl);
            groups.computeIfAbsent(currentTime + ttl, v -> new HashSet<>()).add(tokenId);
        }

        public void renew(String tokenId, int currentTime) {
            roundExpired(currentTime);
            if (end.containsKey(tokenId)) {
                end.put(tokenId, currentTime + ttl);
            }
        }

        public int countUnexpiredTokens(int currentTime) {
            roundExpired(currentTime);
            return end.size();
        }
    }

    public static void main(String[] args) {
        AuthenticationManager authenticationManager = new AuthenticationManager(5);
        authenticationManager.renew("aaa", 1);
        authenticationManager.generate("aaa", 2);
        assert authenticationManager.countUnexpiredTokens(6) == 1;
        authenticationManager.generate("bbb", 7);
        authenticationManager.renew("aaa", 8);
        authenticationManager.renew("bbb", 10);
        assert authenticationManager.countUnexpiredTokens(15) == 0;

        authenticationManager = new AuthenticationManager(13);
        authenticationManager.renew("ajvy", 1);
        assert authenticationManager.countUnexpiredTokens(3) == 0;
        assert authenticationManager.countUnexpiredTokens(4) == 0;
        authenticationManager.generate("fuzxq", 5); // fuzxq 18
        authenticationManager.generate("izmry", 7); // izmry 20
        authenticationManager.renew("puv", 12);
        authenticationManager.generate("ybiqb", 13); // ybiqb 26
        authenticationManager.generate("gm", 14);    // gb 27
        assert authenticationManager.countUnexpiredTokens(15) == 4;
        assert authenticationManager.countUnexpiredTokens(18) == 3;
        assert authenticationManager.countUnexpiredTokens(19) == 3;
        authenticationManager.renew("ybiqb", 21);    // ybiqb 34
        assert authenticationManager.countUnexpiredTokens(23) == 2;
        assert authenticationManager.countUnexpiredTokens(25) == 2;
        assert authenticationManager.countUnexpiredTokens(26) == 2;
        authenticationManager.generate("aqdm", 28);  // aqdm 41
        assert authenticationManager.countUnexpiredTokens(29) == 2;
        authenticationManager.renew("puv", 30);
    }

}
