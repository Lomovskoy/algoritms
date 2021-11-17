package sprint6.Final;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class One {
    private static final String MESSAGE = "Oops! I did it again";
    private static final Map<Integer, PriorityQueue<Pair>> graph = new HashMap<>();
    // Рёбра, составляющие MST.
    private static int maximumSpanningTree = 0;
    // Множество вершины, ещё не добавленных в остов.
    private static boolean[] notAdded;
    // Массив рёбер, исходящих из остовного дерева.
    private static final PriorityQueue<Pair> edges = new PriorityQueue<>();
    private static int notAddedSize;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int[] pair = getPair(reader);

        int numberVertices = pair[0];   // количество вершин
        int numberRibs = pair[1];       // количество рёбер
        notAddedSize = numberVertices;
        notAdded = new boolean[numberVertices + 1];

        buildAdjacencyUse(reader, numberRibs);
        Integer sum = findMST();

        if (sum == null) {
            if (numberRibs == 0 && numberVertices == 1) {
                System.out.println(0);
            } else {
                System.out.println(MESSAGE);
            }
        } else {
            System.out.println(sum);
        }
    }

    private static Integer findMST() {

        if (graph.isEmpty()) {
            return null;
        }
        // Берём первую попавшуюся вершину.
        addVertex(1);

        // пока notAdded не пуст и edges не пуст:
        while (notAddedSize > 0 && !edges.isEmpty()) {
            // Подразумеваем, что extractMaximum извлекает минимальное ребро
            // из массива рёбер и больше данного ребра в массива не будет.
            Pair element = extractMaximum();
            if (!notAdded[element.getEnd()]) {
                maximumSpanningTree += element.getEdge();
                addVertex(element.getEnd());
            }
        }

        if (notAddedSize > 0) {
            return null;
        } else {
            return maximumSpanningTree;
        }
    }

    private static Pair extractMaximum() {
        return edges.poll();
    }

    private static void addVertex(int vertex) {
        notAdded[vertex] = true;
        notAddedSize--;

        // Добавляем все рёбра, которые инцидентны v, но их конец ещё не в остове.
        for (Pair val : graph.get(vertex)) {
            if (!notAdded[val.getEnd()]) {
                edges.add(val);
            }
        }
    }

    public static class Pair implements Comparable<Pair> {
        private final int end;
        private final int edge;

        public Pair(int end, int edge) {
            this.end = end;
            this.edge = edge;
        }

        public int getEnd() {
            return end;
        }

        public int getEdge() {
            return edge;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;

            Pair pair = (Pair) o;

            if (getEnd() != pair.getEnd()) return false;
            return getEdge() == pair.getEdge();
        }

        @Override
        public int hashCode() {
            int result = getEnd();
            result = 31 * result + getEdge();
            return result;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(o.getEdge(), this.edge);
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static int[] getPair(BufferedReader reader) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        return new int[]{Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())};
    }

    private static void buildAdjacencyUse(BufferedReader reader, int size) throws IOException {
        for (int i = 0; i < size; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int distance = Integer.parseInt(tokenizer.nextToken());

            if (start != end) {
                if (graph.get(start) == null) {
                    graph.put(start, new PriorityQueue<>() {{ add(new Pair(end, distance)); }});
                } else {
                    addVertex(start, end, distance);
                }
                if (graph.get(end) == null) {
                    graph.put(end, new PriorityQueue<>() {{ add(new Pair(start, distance)); }});
                } else {
                    addVertex(end, start, distance);
                }
            }
        }
    }

    private static void addVertex(int start, int end, int distance) {
        graph.get(start).add(new Pair(end, distance));
    }
}
