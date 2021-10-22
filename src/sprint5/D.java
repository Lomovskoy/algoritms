package sprint5;

public class D {

    public static boolean compareTrees(Node root1, Node root2) {
        if ((root1 == null && root2 != null) || (root1 != null && root2 == null)) {
            return false;
        }

        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1.value != root2.value) {
            return false;
        }

        return compareTrees(root1.left, root2.left) && compareTrees(root1.right, root2.right);
    }

    public static boolean treeSolution(Node head1, Node head2) {
        return compareTrees(head1, head2);
    }

    /** Comment it before submitting     **/
    private static class Node {
        int value;  
        Node left;  
        Node right;  
    
        Node(int value) {  
            this.value = value;
            this.left = null;
            this.right = null;
        }

        Node(int value, Node left, Node right) {  
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    
    public static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3,  node1,  node2);
        Node node4 = new Node(1);
        Node node5 = new Node(2);
        Node node6 = new Node(3,  node4,  node5);
//        System.out.println(treeSolution(node3, node6));
        assert treeSolution(node3, node6);
    }
}
