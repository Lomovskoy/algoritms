package sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class H {

    private static final Map<Integer, List<Integer>> adjacencyUse = new HashMap<>();
    private static Color[] colors;
    private static int time = 0;
    private static int[] entry;
    private static int[] leave;


    public static void main(String[] args) throws Exception {
        BufferedReader reader = getReader();
        int[] pair = getPair(reader);

        int numberVertices = pair[0];   // количество вершин
        int numberRibs = pair[1];       // количество рёбер

        buildAdjacencyUse(reader, numberRibs);

        colors = new Color[numberVertices + 1];
        entry = new int[numberVertices + 1];
        leave = new int[numberVertices + 1];

        Arrays.fill(colors, Color.WHITE);
        int start = 1;
        dfs(start);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= numberVertices; i++) {
            stringBuilder.append(entry[i]).append(" ").append(leave[i]).append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static void dfs(int startVertex) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(startVertex);

        while (!stack.empty()) {
            // Получаем из стека очередную вершину.
            // Это может быть как новая вершина, так и уже посещённая однажды.
            int vertex = stack.pop();
            if (colors[vertex].equals(Color.WHITE)) {
                // Красим вершину в серый. И сразу кладём её обратно в стек:
                // это позволит алгоритму позднее вспомнить обратный путь по графу.
                colors[vertex] = Color.GRAY;
                // Запишем время входа.
                entry[vertex] = time;
                // При входе в вершину время (номер шага) увеличивается.
                time += 1;
                stack.push(vertex);
                // Теперь добавляем в стек все не посещённые соседние вершины,
                // вместо вызова рекурсии
                if (adjacencyUse.get(vertex) != null) {
                    List<Integer> connectedVertices = adjacencyUse.get(vertex);
                    Collections.sort(connectedVertices);
                    Collections.reverse(connectedVertices);
                    for (int currentVertex : connectedVertices) {
                        if (colors[currentVertex].equals(Color.WHITE)) {
                            stack.push(currentVertex);
                        }
                    }
                }
            } else if (colors[vertex].equals(Color.GRAY)) {
                // Серую вершину мы могли получить из стека только на обратном пути.
                // Следовательно, её следует перекрасить в чёрный.
                colors[vertex] = Color.BLACK;
                // Запишем время выхода.
                leave[vertex] = time;
                // Перед выходом из вершины время снова обновляется.
                time += 1;
            }
        }
    }

    private static void buildAdjacencyUse(BufferedReader reader, int size) throws IOException {
        for (int i = 0; i < size; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());

            if (adjacencyUse.get(start) == null) {
                ArrayList<Integer> set = new ArrayList<Integer>();
                set.add(end);
                adjacencyUse.put(start, set);
            } else {
                adjacencyUse.get(start).add(end);
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

    enum Color {
        WHITE, GRAY, BLACK
    }

}