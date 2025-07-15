package weekly.w454.A;

/**
 * Q1. Generate Tag for Video Caption
 *
 * https://leetcode.cn/contest/weekly-contest-454/problems/generate-tag-for-video-caption/
 *
 * You are given a string caption representing the caption for a video.
 *
 * The following actions must be performed in order to generate a valid tag for the video:
 *
 * Combine all words in the string into a single camelCase string prefixed with '#'.
 * A camelCase string is one where the first letter of all words except the first
 * one is capitalized. All characters after the first character in each word must be lowercase.
 *
 * Remove all characters that are not an English letter, except the first '#'.
 *
 * Truncate the result to a maximum of 100 characters.
 *
 * Return the tag after performing the actions on caption.
 */

public class Solution {

    public String generateTag(String caption) {
        boolean upper = false;
        StringBuilder sb = new StringBuilder("#");
        for (var c : caption.trim().toLowerCase().toCharArray()) {
            if (c != ' ') {
                if (upper) {
                    sb.append((char) (c - 32));
                    upper = false;
                } else sb.append(c);
            } else upper = true;
        }
        return sb.length() > 100 ? sb.substring(0, 100) : sb.toString();
    }

    public static void main(String[] args) {
    }

}
