package sprint8.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
ID 60801692

-- ПРИНЦИП РАБОТЫ --
    Сначала сохраняем строку из ввода.
    Потом берём Бор и сохраняем туда строки из которых мы должны составить исходную строку.
    Сохраняем их тура реверсивно, потом, что это облегчает поиск.
    Почему сохраняем реверсивно.
    Есть два варианта, как сравнивать нашу строку с бором для каждого i.
        1. Последовательно отступать влево от i-го символа и прикладывать бор из прямых слов, начиная с j-го, в надежде,
        что когда мы окажемся на i-ом символе, то узел внутри бора будет терминальным.
        2. Прикладывать бор из перевернутых слов, начиная от i-го символа влево (как на видео).
        Проверяя каждый терминальный узел, не равно ли соответствующее dp значению true.

    В первом случае мы проходим по бору много раз (для каждого j проходим по бору).

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    1. Складываем внутрь бора перевернутые слова.
    2. Идём индексом i по всем символам заданной строки.
    3. Берём корень нашего бора. Будем стартовать с него.
    4. Создаём индекс j=i. Им будем двигаться влево от i.
    5. Пока не уткнулись в начало строки (j > 0) и пока узел бора не равен null делаем следующее:
    6. Если узел не null, и j >=0 и узел.endNode==True и dp[j] == True, то dp[i] = true и можно прерывать while по break’у.
    7. берём следующий узел.

    Таким образом, мы обеспечиваем поиск всех вариантов, какими можно создать исходную строку из подстрок,
    конечный отчет дял всех вариантов будет находиться в последней ячейке массива dp.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    O(n) Где n — длина текста

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    O(n * 2) Где n — длина текста

 */
public class Two {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        String string = reader.readLine();

        int size = Integer.parseInt(reader.readLine());
        TreeNode treeNode = getTreeNode(reader, size);

        boolean[] dp = searchForPossibilityOfComposingStringFromSubstrings(string, treeNode);
        System.out.println(dp[dp.length - 1] ? "YES" : "NO");
    }

    private static boolean[] searchForPossibilityOfComposingStringFromSubstrings(String string, TreeNode treeNode) {

        boolean[] dp = new boolean[string.length() + 1];
        dp[0] = true;

        for (int i = 0; i < string.length(); i++) {

            TreeNode currentTreeNode = treeNode;
            int j = i;

            while (j >= 0) {
                char c = string.charAt(j);
                currentTreeNode = currentTreeNode.children.get(c);

                if (currentTreeNode != null) {
                    if (currentTreeNode.endWord && dp[j]) {
                        dp[i + 1] = true;
                        break;
                    }
                } else {
                    break;
                }
                j--;
            }
        }
        return dp;
    }

    private static TreeNode getTreeNode(BufferedReader reader, int size) throws IOException {
        TreeNode treeNode = new TreeNode(' ');
        for (int i = 0; i < size; i++) {
            treeNode.insert(new StringBuilder(reader.readLine()).reverse().toString());
        }
        return treeNode;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    static class TreeNode {
        char value;
        boolean endWord;
        Map<Character, TreeNode> children;

        public TreeNode(char value) {
            this.value = value;
            children = new HashMap<>();
        }

        public void insert(String data) {

            TreeNode currentTreeNode = this;
            for (int i = 0; i < data.length(); i++) {
                char c = data.charAt(i);
                TreeNode child = getNodeByChar(currentTreeNode, c);

                if (child == null) {
                    child = new TreeNode(c);
                    currentTreeNode.children.put(c, child);
                }

                currentTreeNode = currentTreeNode.children.get(c);

                if (i == data.length() - 1) {
                    child.endWord = true;
                }
            }
        }

        private TreeNode getNodeByChar(TreeNode current, char c) {
            return current.children.get(c);
        }

    }
}