package problem.p535encodeanddecodetinyurl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 535. Encode and Decode TinyURL
 *
 * https://leetcode-cn.com/problems/encode-and-decode-tinyurl/
 *
 * Note: This is a companion problem to the System Design problem: Design TinyURL.
 *
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl
 * and it returns a short URL such as http://tinyurl.com/4e9iAk.
 *
 * Design a class to encode a URL and decode a tiny URL.
 *
 * There is no restriction on how your encode/decode algorithm should work.
 * You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL
 * can be decoded to the original URL.
 *
 * Implement the Solution class:
 *
 * Solution() Initializes the object of the system.
 * String encode(String longUrl) Returns a tiny URL for the given longUrl.
 * String decode(String shortUrl) Returns the original long URL for the given shortUrl.
 * It is guaranteed that the given shortUrl was encoded by the same object.
 */

public class Solution {

    private static class Codec {
        private static final Map<String, String> urls = new HashMap<>();
        private static final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            String shorten = randomURL();
            urls.put(shorten, longUrl);
            return shorten;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return urls.get(shortUrl);
        }

        private String randomURL() {
            StringBuilder sb = new StringBuilder("https://short.com/");
            for (int i = 0; i < 8; i++) {
                sb.append(chars.charAt(ThreadLocalRandom.current().nextInt(chars.length())));
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        String raw = "https://leetcode-cn.com/problems/encode-and-decode-tinyurl/";
        String encoded = codec.encode(raw);
        assert raw.equals(codec.decode(encoded));
    }

}
