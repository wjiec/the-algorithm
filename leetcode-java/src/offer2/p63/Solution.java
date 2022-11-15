package offer2.p63;

import ability.Ability;

import java.util.List;

/**
 * 剑指 Offer II 063. 替换单词
 *
 * https://leetcode.cn/problems/UhWRSj/
 *
 * 在英语中，有一个叫做 词根(root) 的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。
 * 例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 *
 * 现在，给定一个由许多词根组成的词典和一个句子，需要将句子中的所有继承词用词根替换掉。
 * 如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 *
 * 需要输出替换之后的句子。
 */

public class Solution {

    public String replaceWords(List<String> dictionary, String sentence) {
        Ability.AlphaTrie root = new Ability.AlphaTrie();
        for (var word : dictionary) {
            root.set(word).asLeaf();
        }

        StringBuilder sb = new StringBuilder();
        for (var word : sentence.split(" ")) {
            int index = 0;
            Ability.AlphaTrie curr = root;
            while (index < word.length()) {
                curr = curr.get(word.charAt(index++));
                if (curr == null || curr.isLeaf()) break;
            }
            if (curr == null || !curr.isLeaf()) sb.append(word);
            else sb.append(word, 0, index);
            sb.append(" ");
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().replaceWords(List.of("cat","bat","rat"),
            "the cattle was rattled by the battery"
        ).equals("the cat was rat by the bat");

        assert new Solution().replaceWords(List.of("a","b","c"),
            "aadsfasf absbs bbab cadsfafs"
        ).equals("a a b c");

        assert new Solution().replaceWords(List.of("a", "aa", "aaa", "aaaa"),
            "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"
        ).equals("a a a a a a a a bbb baba a");

        assert new Solution().replaceWords(List.of("catt","cat","bat","rat"),
            "the cattle was rattled by the battery"
        ).equals("the cat was rat by the bat");

        assert new Solution().replaceWords(List.of("ac","ab"),
            "it is abnormal that this solution is accepted"
        ).equals("it is ab that this solution is ac");
    }

}
