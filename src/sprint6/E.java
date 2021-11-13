package sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class E {
    private static final Map<Integer, TreeSet<Integer>> order = new HashMap<>();
    private static final Map<Integer, List<Integer>> adjacencyUse = new HashMap<>();
    private static final Stack<Integer> stack = new Stack<>();
    private static int[] colors;
    private static int componentCount = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = getReader();
        int[] pair = getPair(reader);

        int numberVertices = pair[0];
        int numberRibs = pair[1];

        buildAdjacencyUse(reader, numberRibs);
        colors = new int[numberVertices + 1];

        Arrays.fill(colors, -1);

        for (int i = 1; i < colors.length; i++) {
            if (colors[i] == -1) {
                componentCount++;
                dfs(i);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(order.size()).append("\n");

        for (Map.Entry<Integer, TreeSet<Integer>> entry : order.entrySet()) {
            for (Integer integer : entry.getValue()) {
                stringBuilder.append(integer).append(" ");
            }
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder);
    }

    private static void dfs(int startVertex) {
        stack.push(startVertex);

        while (!stack.empty()) {
            int vertex = stack.pop();
            if (colors[vertex] == -1) {
                colors[vertex] = componentCount;
                stack.push(vertex);
                if (adjacencyUse.get(vertex) != null) {
                    List<Integer> connectedVertices = adjacencyUse.get(vertex);
                    for (int currentVertex : connectedVertices) {
                        if (colors[currentVertex] == -1) {
                            stack.push(currentVertex);
                        }
                    }
                }
            } else if (colors[vertex] == componentCount) {
                colors[vertex] = componentCount;
                if (order.get(componentCount) == null) {
                    TreeSet<Integer> list = new TreeSet<>();
                    list.add(vertex);
                    order.put(componentCount, list);
                } else {
                    order.get(componentCount).add(vertex);
                }
            }
        }
    }

    private static void buildAdjacencyUse(BufferedReader reader, int size) throws IOException {
        StringTokenizer tokenizer;
        for (int i = 0; i < size; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());

            if (adjacencyUse.get(start) == null) {
                List<Integer> set = new ArrayList<>();
                set.add(end);
                adjacencyUse.put(start, set);
            } else {
                adjacencyUse.get(start).add(end);
            }
            if (adjacencyUse.get(end) == null) {
                List<Integer> set = new ArrayList<>();
                set.add(start);
                adjacencyUse.put(end, set);
            } else {
                adjacencyUse.get(end).add(start);
            }
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static int[] getPair(BufferedReader reader) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        return new int[]{Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())};
    }

}
