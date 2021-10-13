package sprint5;

public class B {

    public static boolean treeSolution(Node head) {
        return isBalancedRecursive(head, -1).isBalanced;
    }

    private static Result isBalancedRecursive(Node tree, int depth) {
        if (tree == null) {
            return new Result(true, -1);
        }

        Result leftSubtreeResult = isBalancedRecursive(tree.left, depth + 1);
        Result rightSubtreeResult = isBalancedRecursive(tree.right, depth + 1);

        boolean isBalanced = Math.abs(leftSubtreeResult.height - rightSubtreeResult.height) <= 1;
        boolean subtreesAreBalanced = leftSubtreeResult.isBalanced && rightSubtreeResult.isBalanced;
        int height = Math.max(leftSubtreeResult.height, rightSubtreeResult.height) + 1;

        return new Result(isBalanced && subtreesAreBalanced, height);
    }

    private static class Result {
        private final boolean isBalanced;
        private final int height;

        private Result(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    /** Comment it before submitting **/
    private static class Node {
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
        Node node4 = new Node(10);
        Node node5 = new Node(2);
        node5.left = node3;
        node5.right = node4;
        assert treeSolution(node5);
        System.out.println(treeSolution(node5));
    }
}
