package sprint5;

public class E {

    /**
     * 1. Если в узле дерева записано значение Х, то в левом поддереве располагаются только узлы со значениями меньшими или равными Х.<br>
     * 2. Если в узле дерева записано значение Х, то в правом поддереве располагаются только узлы со значениями большими или равными Х.<br>
     * 3. Левое и правое поддеревья отвечают тем же двум условиям. Это рекурсивное правило. Из него следует, что любое поддерево в дереве поиска будет являться деревом поиска.<br>
     * @param head корень дерева
     * @return Функция должна вернуть True, если дерево является деревом поиска, иначе - False.
     */
    public static boolean treeSolution(Node head) {

        boolean isLeftOk = true;
        boolean isRightOk = true;

        if (head.left != null) {
            isLeftOk = head.left.value <= head.value;
            if(isLeftOk) isLeftOk = treeSolution(head.left);
        }
        if (head.right != null) {
            isRightOk = head.right.value > head.value;
            if(isRightOk) isRightOk = treeSolution(head.right);
        }

        return isLeftOk && isRightOk;
    }

    /** Comment it before submitting */
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
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);
        assert treeSolution(node5);
        System.out.println(treeSolution(node5));
        node2.value = 5;
        assert !treeSolution(node5);
        System.out.println(treeSolution(node5));
    }
}
