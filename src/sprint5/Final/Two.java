package sprint5.Final;

public class Two {
    private static Node rootNode;

    public static Node remove(Node root, int key) {
        if (root == null) return null;
        rootNode = root;
        Node currentNode = rootNode;
        Node parentNode = rootNode;
        boolean isLeftChild = true;

        while (currentNode.getValue() != key) {
            parentNode = currentNode;
            if (key < currentNode.getValue()) {
                isLeftChild = true;
                currentNode = currentNode.getLeft();
            } else {
                isLeftChild = false;
                currentNode = currentNode.getRight();
            }
            if (currentNode == null)
                return root;
        }

        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            nodeIsSimplyRemoved(currentNode, parentNode, isLeftChild);
        } else if (currentNode.getRight() == null) {
            nodeIsReplacedByLeftSubtree(currentNode, parentNode, isLeftChild);
        } else if (currentNode.getLeft() == null) {
            nodeIsReplacedByRightSubtree(currentNode, parentNode, isLeftChild);
        } else {
            nodeIsReplacedBySuccessor(currentNode, parentNode, isLeftChild);
        }

        return rootNode;
    }

    private static void nodeIsSimplyRemoved(Node currentNode, Node parentNode, boolean isLeftChild) {
        if (currentNode == rootNode) {
            rootNode = null;
        } else if (isLeftChild) {
            parentNode.setLeft(null);
        } else {
            parentNode.setRight(null);
        }
    }

    private static void nodeIsReplacedByLeftSubtree(Node currentNode, Node parentNode, boolean isLeftChild) {
        if (currentNode == rootNode)
            rootNode = currentNode.getLeft();
        else if (isLeftChild)
            parentNode.setLeft(currentNode.getLeft());
        else
            parentNode.setRight(currentNode.getLeft());
    }

    private static void nodeIsReplacedByRightSubtree(Node currentNode, Node parentNode, boolean isLeftChild) {
        if (currentNode == rootNode)
            rootNode = currentNode.getRight();
        else if (isLeftChild)
            parentNode.setLeft(currentNode.getRight());
        else
            parentNode.setRight(currentNode.getRight());
    }

    private static void nodeIsReplacedBySuccessor(Node currentNode, Node parentNode, boolean isLeftChild) {
        Node heir = receiveHeir(currentNode);
        if (currentNode == rootNode)
            rootNode = heir;
        else if (isLeftChild)
            parentNode.setLeft(heir);
        else
            parentNode.setRight(heir);
    }

    private static Node receiveHeir(Node node) {
        Node parentNode = node;
        Node heirNode = node;
        Node currentNode = node.getRight();

        while (currentNode != null) {
            parentNode = heirNode;
            heirNode = currentNode;
            currentNode = currentNode.getLeft();
        }

        if (heirNode != node.getRight()) {
            parentNode.setLeft(heirNode.getRight());
            heirNode.setRight(node.getRight());
        }

        heirNode.setLeft(parentNode.getLeft());
        heirNode.setLeft(node.getLeft());
        return heirNode;
    }

    /** Comment it before submitting */
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    public static void test() {
        Node node10 = new Node(null, null, 932);
        Node node9 = new Node(null, node10, 912);
        Node node8 = new Node(null, null, 822);
        Node node7 = new Node(node8, node9, 870);
        Node node6 = new Node(null, null, 701);
        Node node5 = new Node(node6, node7, 702);
        Node node4 = new Node(null, null, 266);
        Node node3 = new Node(null, node4, 191);
        Node node2 = new Node(node3, null, 298);
        Node node1 = new Node(node2, node5, 668);

        Node newHead = remove(node1, 545);
    }
}