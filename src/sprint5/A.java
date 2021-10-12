package sprint5;

public class A {

    public static int treeSolution(Node head) {
        if (head.left == null && head.right == null) {
            return head.value;
        } else {
            if (head.left != null && head.right != null) {
                int max = Math.max(treeSolution(head.left), treeSolution(head.right));
                return Math.max(max, head.value);
            } else {
                if (head.left != null) {
                    return Math.max(treeSolution(head.left), head.value);
                } else {
                    return Math.max(treeSolution(head.right), head.value);
                }
            }
        }
    }

    public static class Node {
        int value;  
        Node left;  
        Node right;  
    
        Node(int value) {  
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(2);
        node4.left = node3;
        assert treeSolution(node4) == 3;
        System.out.println(treeSolution(node4) + " = " + 3);
    }
}
