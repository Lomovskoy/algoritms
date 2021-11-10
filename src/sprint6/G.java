package sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G {

    private static final Map<Integer, List<Integer>> adjacencyUse = new HashMap<>();
    private static Color[] colors;
    private static int[] distance;
    private static int[] previous;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = getReader();
        int[] pair = getPair(reader);

        int numberVertices = pair[0];   // количество вершин
        int numberRibs = pair[1];       // количество рёбер

        buildAdjacencyUse(reader, numberRibs);

        colors = new Color[numberVertices + 1];
        distance = new int[numberVertices + 1];
        previous = new int[numberVertices + 1];

        Arrays.fill(colors, Color.WHITE);
        int start = Integer.parseInt(reader.readLine());
        if (numberVertices == 1) {
            System.out.println(0);
        } else {
            dfs(start);
            int max = Arrays.stream(distance).max().getAsInt();
            System.out.println(max);
        }
    }

    private static void dfs(int startVertex) {
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
        deque.push(startVertex);
        distance[startVertex] = 0;

        while (!deque.isEmpty()) {
            // Получаем из стека очередную вершину.
            // Это может быть как новая вершина, так и уже посещённая однажды.
            int vertex = deque.pop();
            if (colors[vertex].equals(Color.WHITE)) {
                // Красим вершину в серый. И сразу кладём её обратно в стек:
                // это позволит алгоритму позднее вспомнить обратный путь по графу.
                colors[vertex] = Color.GRAY;
                deque.push(vertex);
                // Теперь добавляем в стек все не посещённые соседние вершины,
                // вместо вызова рекурсии
                if (adjacencyUse.get(vertex) != null) {
                    List<Integer> connectedVertices = adjacencyUse.get(vertex);
                    Collections.sort(connectedVertices);
                    for (int currentVertex : connectedVertices) {
                        if (colors[currentVertex].equals(Color.WHITE)) {
                            previous[currentVertex] = vertex;
                            deque.addLast(currentVertex);
                            if (distance[currentVertex] == 0) {
                                distance[currentVertex] = distance[vertex] + 1;
                            }
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
            if (adjacencyUse.get(end) == null) {
                ArrayList<Integer> set = new ArrayList<Integer>();
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

    enum Color {WHITE, GRAY, BLACK}

}