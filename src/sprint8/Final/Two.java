package sprint8.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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