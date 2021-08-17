package sprint2.C;

class Node<V> {
    public V value;
    public Node<V> next;

    public Node(V value, Node<V> next) {
        this.value = value;
        this.next = next;
    }
}

public class Solution2 {
    public static Node<String> solution(Node<String> head, int idx) {
        if (idx == 0) return head.next;

        var node = head;
        while (idx >= 0) {
            if (idx == 1) {
                node.next = node.next.next;
                return head;
            } else {
                node = node.next;
            }
            --idx;
        }
        return head;
    }

    public static void test() {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        Node<String> newHead = solution(node0, 1);
        System.out.println();
    }
}