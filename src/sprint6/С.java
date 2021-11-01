package sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class С {

    private static final Map<Integer, List<Integer>> adjacencyUse = new HashMap<>();
    private static Color[] colors;


    public static void main(String[] args) throws Exception {
        var reader = getReader();
        int[] pair = getPair(reader);

        int numberVertices = pair[0];   // количество вершин
        int numberRibs = pair[1];       // количество рёбер

        buildAdjacencyUse(reader, numberRibs);
        colors = new Color[numberVertices + 1];
        Arrays.fill(colors, Color.WHITE);
        int start = Integer.parseInt(reader.readLine());
        if (numberVertices == 1) {
            System.out.println(start);
        } else {
            dfs(start);
        }
    }

    private static void dfs(int startVertex) {
        var stack = new Stack<Integer>();
        stack.push(startVertex);

        while (!stack.empty()) {
            // Получаем из стека очередную вершину.
            // Это может быть как новая вершина, так и уже посещённая однажды.
            var vertex = stack.pop();
            if (colors[vertex].equals(Color.WHITE)) {
                System.out.print(vertex + " ");
                // Красим вершину в серый. И сразу кладём её обратно в стек:
                // это позволит алгоритму позднее вспомнить обратный путь по графу.
                colors[vertex] = Color.GRAY;
                stack.push(vertex);
                // Теперь добавляем в стек все не посещённые соседние вершины,
                // вместо вызова рекурсии
                if (adjacencyUse.get(vertex) != null) {
                    var connectedVertices  = adjacencyUse.get(vertex);
                    Collections.sort(connectedVertices);
                    Collections.reverse(connectedVertices);
                    for (var currentVertex : connectedVertices) {
                        if (colors[currentVertex].equals(Color.WHITE)) {
                            stack.push(currentVertex);
                        }
                    }
                }
            } else if (colors[vertex].equals(Color.GRAY)) {
                // Серую вершину мы могли получить из стека только на обратном пути.
                // Следовательно, её следует перекрасить в чёрный.
                colors[vertex] = Color.BLACK;
            }
        }
    }

    private static void buildAdjacencyUse(BufferedReader reader, int size) throws IOException {
        for (int i = 0; i < size; i++) {
            var tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());

            if (adjacencyUse.get(start) == null) {
                var set = new ArrayList<Integer>();
                set.add(end);
                adjacencyUse.put(start, set);
            } else {
                adjacencyUse.get(start).add(end);
            }
            if (adjacencyUse.get(end) == null) {
                var set = new ArrayList<Integer>();
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
        var tokenizer = new StringTokenizer(reader.readLine());
        return new int[]{Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())};
    }

    enum Color {
        WHITE, GRAY, BLACK
    }

}