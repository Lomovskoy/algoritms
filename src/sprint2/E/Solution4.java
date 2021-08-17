package sprint2.E;

class Node<V> {
    public V value;
    public Node<V> next;
    public Node<V> prev;

    public Node(V value, Node<V> next, Node<V> prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}

public class Solution4 {
    public static Node<String> solution(Node<String> head) {
        Node<String> node = head;

        // Заполняем prev
        while (node != null) {
            if (node.next != null)
                node.next.prev = node;
            node = node.next;
        }

        Node<String> left = head, right = head;

        while (right.next != null)  // Обходим список и устанавливаем правый указатель на
            right = right.next;     // конец списка

        // Поменять местами данные левого и правого указателя и переместить
        // их навстречу друг другу, пока они не встретятся или
        // пересекаем друг друга
        while (left != right && left.prev != right) {
            var current = left.value;       // Поменять местами данные левого и правого указателя
            left.value = right.value;
            right.value = current;
            left = left.next;       // Переместить левый указатель
            right = right.prev;     // Предварительный указатель вправо
        }
        return head;
    }

    public static void test() {
        Node<String> node3 = new Node<String>("node3", null, null);
        Node<String> node2 = new Node<String>("node2", node3, null);
        Node<String> node1 = new Node<String>("node1", node2, null);
        Node<String> node0 = new Node<String>("node0", node1, null);
        Node<String> newNode = solution(node0);
        /*
        result is : newNode == node3
        node3.next == node2
        node2.next == node1
        node2.prev == node3
        node1.next == node0
        node1.prev == node2
        node0.prev == node1
        */
    }
}
