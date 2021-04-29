package problem.p83removeduplicatesfromsortedlist;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    public String toString() {
        return String.format("[val=%d, next=%s]", val, next);
    }
}
