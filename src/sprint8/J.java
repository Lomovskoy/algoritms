package sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class J {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();

        int stringsSize = Integer.parseInt(reader.readLine());
        TreeNode root = getTreeNode(reader, stringsSize);
        int patternSize = Integer.parseInt(reader.readLine());

        for (int i = 0; i < patternSize; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            List<String> extracted = new ArrayList<>();
            root.getAllNumbers("", extracted, tokenizer.hasMoreTokens() ? tokenizer.nextToken() : "", 0);
            if (!extracted.isEmpty()) {
                extracted.sort(Comparator.naturalOrder());
                for (String s : extracted) {
                    System.out.println(s);
                }
            } else {
                System.out.println();
            }
        }
    }

    private static TreeNode getTreeNode(BufferedReader reader, int size) throws IOException {
        TreeNode root = new TreeNode(' ');
        for (int i = 0; i < size; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            root.insert(tokenizer.nextToken());
        }
        return root;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    static class TreeNode {
        char value;
        TreeNode[] children;

        public TreeNode(char value) {
            this.value = value;
        }

        public void insert(String data) {
            if (data.isEmpty()) {
                return;
            }
            if (children == null) {
                children = new TreeNode[1];
            }
            char c = data.charAt(0);
            TreeNode child = findNodeByChar(c);
            if (child == null) {
                child = new TreeNode(c);
                children[children.length - 1] = child;
                expandArray();
            }
            child.insert(data.substring(1));
        }

        private void expandArray() {
            TreeNode[] treeNodes = new TreeNode[children.length + 1];
            System.arraycopy(children, 0, treeNodes, 0, children.length);
            children = treeNodes;
        }

        private TreeNode findNodeByChar(char c) {
            if (children != null) {
                for (TreeNode node : children) {
                    if (node != null && node.value == c) {
                        return node;
                    }
                }
            }
            return null;
        }

        public void getAllNumbers(String path, List<String> result, String chars, int index) {
            if (value != ' ') {
                path = path + value;
                if (index < chars.length() && chars.charAt(index) == value) {
                    index++;
                } else if (Character.isUpperCase(value)) {
                    if (chars.length() > index)
                        return;
                }
            }
            if (children != null) {
                for (TreeNode node : children) {
                    if (node != null) {
                        node.getAllNumbers(path, result, chars, index);
                    }
                }
            } else {
                if (chars.length() == index) {
                    result.add(path);
                }
            }
        }

    }
}