package problem.p2296designatexteditor;

/**
 * 2296. Design a Text Editor
 *
 * https://leetcode.cn/problems/design-a-text-editor
 *
 * Design a text editor with a cursor that can do the following:
 *
 * Add text to where the cursor is.
 * Delete text from where the cursor is (simulating the backspace key).
 * Move the cursor either left or right.
 * When deleting text, only characters to the left of the cursor will be deleted.
 * The cursor will also remain within the actual text and cannot be moved beyond it.
 * More formally, we have that 0 <= cursor.position <= currentText.length always holds.
 *
 * Implement the TextEditor class:
 *
 * TextEditor() Initializes the object with empty text.
 *
 * void addText(string text) Appends text to where the cursor is. The cursor ends to the right of text.
 *
 * int deleteText(int k) Deletes k characters to the left of the cursor.
 * Returns the number of characters actually deleted.
 *
 * string cursorLeft(int k) Moves the cursor to the left k times. Returns the last
 * min(10, len) characters to the left of the cursor, where len is the number of characters
 * to the left of the cursor.
 *
 * string cursorRight(int k) Moves the cursor to the right k times. Returns the last min(10, len)
 * characters to the left of the cursor, where len is the number of characters to the left of the cursor.
 */

public class Solution {

    private static class TextEditor {

        private static class Node {
            private final char value;
            private Node prev, next;
            public Node(char c) { value = c; }

            @Override
            public String toString() {
                Node pp = this;
                while (pp.prev != null) pp = pp.prev;
                StringBuilder sb = new StringBuilder();
                for (; pp != null; pp = pp.next) {
                    sb.append(pp.value);
                    if (value == '|' && pp == prev) sb.append('|');
                }
                return sb.toString();
            }
        }

        private final Node head, tail, curr;
        public TextEditor() {
            head = new Node('^'); tail = new Node('$'); curr = new Node('|');
            curr.prev = head; curr.next = tail; head.next = tail; tail.prev = head;
        }
        private void movePrev() {
            if (curr.prev == head) return;
            Node prev = curr.prev;
            curr.prev = prev.prev;
            curr.next = prev;
        }
        private void moveNext() {
            if (curr.next == tail) return;
            Node next = curr.next;
            curr.prev = next;
            curr.next = next.next;
        }
        private void push(char c) {
            Node node = new Node(c);
            node.prev = curr.prev;
            node.next = curr.next;
            curr.prev.next = node;
            curr.next.prev = node;
            curr.prev = node;
        }
        private char pop() {
            if (curr.prev == head) return '\0';
            var removed = curr.prev;
            removed.prev.next = curr.next;
            curr.next.prev = removed.prev;
            curr.prev = removed.prev;

            return removed.value;
        }
        private String peek() {
            Node seen = curr.prev;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10 && seen != head; i++) {
                sb.append(seen.value);
                seen = seen.prev;
            }
            return sb.reverse().toString();
        }

        public void addText(String text) { for (var c : text.toCharArray()) push(c); }
        public int deleteText(int k) { int ans = 0; while (ans < k && pop() != '\0') ans++; return ans; }
        public String cursorLeft(int k) { for (; k > 0; k--) movePrev(); return peek(); }
        public String cursorRight(int k) { for (; k > 0; k--) moveNext(); return peek(); }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        textEditor.addText("leetcode");
        assert textEditor.deleteText(4) == 4;
        textEditor.addText("practice");
        assert textEditor.cursorRight(3).equals("etpractice");
        assert textEditor.cursorLeft(8).equals("leet");
        assert textEditor.deleteText(10) == 4;
        assert textEditor.cursorLeft(2).equals("");
        assert textEditor.cursorRight(6).equals("practi");
    }

}
