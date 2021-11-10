package sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class K {

    private static final Map<Integer, List<Pair>> graph = new HashMap<>();
    private static int[] dist;
    private static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = getReader();
        int[] pair = getPair(reader);

        int numberVertices = pair[0];   // количество вершин
        int numberRibs = pair[1];       // количество рёбер

        dist = new int[numberVertices + 1];
        visited = new boolean[numberVertices + 1];

        buildAdjacencyUse(reader, numberRibs);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < visited.length; i++) {
            algorithmDijkstra(i);
            for (int j = 1; j < visited.length; j++) {
                stringBuilder.append(dist[j] == Integer.MAX_VALUE ? -1 : dist[j]).append(" ");
            }
            stringBuilder.append("\n");
            // Список статусов посещённости вершин.
            Arrays.fill(visited, false);
        }
        System.out.println(stringBuilder);
    }

    private static void algorithmDijkstra(int s) {
        Arrays.fill(dist, -1);

        // Для каждой вершины v из graph.vertices:
        for (Map.Entry<Integer, List<Pair>> v : graph.entrySet()) {
            // Задаём расстояние по умолчанию.
            dist[v.getKey()] = Integer.MAX_VALUE;
        }

        // Расстояние от вершины до самой себя 0.
        dist[s] = 0;

        // Пока существуют непосещённые вершины с расстоянием не равным бесконечности
        while (true) {
            Integer u = getMinDistNotVisitedVertex();
            if (u == null) break;

            visited[u] = true;

            // Из множества рёбер графа выбираем те, которые исходят из вершины u
            List<Pair> neighbours = graph.get(u);

            // Для каждого ребра (u, v) среди рёбер к соседним вершинам neighbours
            for (Pair v : neighbours) {
                relax(u, v);
            }
        }
    }

    private static void relax(int u, Pair v) {
        if (dist[v.getVertex()] > v.getDistance() + dist[u]) {
            dist[v.getVertex()] = dist[u] + v.getDistance();
        }
    }

    private static Integer getMinDistNotVisitedVertex() {
        // Находим ещё непосещённую вершину с минимальным расстоянием от s.
        int current_minimum = Integer.MAX_VALUE;
        Integer current_minimum_vertex = null;

        // Для каждой вершины v из graph.vertices:
        for (Map.Entry<Integer, List<Pair>> v : graph.entrySet()) {
            if (!visited[v.getKey()] && (dist[v.getKey()] < current_minimum)) {
                current_minimum = dist[v.getKey()];
                current_minimum_vertex = v.getKey();
            }
        }
        return current_minimum_vertex;
    }

    private static void buildAdjacencyUse(BufferedReader reader, int size) throws IOException {
        for (int i = 0; i < size; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int distance = Integer.parseInt(tokenizer.nextToken());

            if (graph.get(start) == null) {
                ArrayList<Pair> set = new ArrayList<Pair>();
                set.add(new Pair(end, distance));
                graph.put(start, set);
            } else {
                graph.get(start).add(new Pair(end, distance));
            }
            if (graph.get(end) == null) {
                ArrayList<Pair> set = new ArrayList<Pair>();
                set.add(new Pair(start, distance));
                graph.put(end, set);
            } else {
                graph.get(end).add(new Pair(start, distance));
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

    public static class Pair {
        private final int vertex;
        private final int distance;

        public Pair(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public int getVertex() {
            return vertex;
        }

        public int getDistance() {
            return distance;
        }
    }
}