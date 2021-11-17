package sprint6.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Two {
    private static int[][] graph;
    private static Color[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int numberVertices = getSize(reader);   // количество вершин
        graph = new int[numberVertices][numberVertices];
        visited = new Color[numberVertices];

        buildAdjacencyUse(reader, numberVertices);
        Arrays.fill(visited, Color.WHITE);

        boolean result = false;
        for (int i = 0; i < numberVertices; i++) {
            if (visited[i] == Color.WHITE) {
                if (dfs(i)) {
                    result = true;
                    break;
                }
            }
        }

        System.out.println(result ? "NO" : "YES");
    }

    private static boolean dfs(int startVertex) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(startVertex);

        while (!stack.empty()) {
            int vertex = stack.pop();

            if (visited[vertex] == Color.WHITE) {
                visited[vertex] = Color.GRAY;
                stack.push(vertex);

                for (int i = 0; i < graph[vertex].length; i++) {
                    if (graph[vertex][i] != 0) {
                        if (visited[i] == Color.GRAY) {
                            return true;
                        } else if (visited[i] == Color.WHITE){
                            stack.push(i);
                        }
                    }
                }
            } else if (visited[vertex] == Color.GRAY) {
                visited[vertex] = Color.BLACK;
            }
        }
        return false;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static int getSize(BufferedReader reader) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        return Integer.parseInt(tokenizer.nextToken());
    }

    private static void buildAdjacencyUse(BufferedReader reader, int size) throws IOException {
        for (int i = 0; i < size - 1; i++) {
            char[] color = reader.readLine().toCharArray();
            for (int j = 1; j < color.length + 1; j++) {
                if (color[j - 1] == 'R') {
                    graph[i][j + i] = 1;
                } else {
                    graph[j + i][i] = 1;
                }
            }
        }
    }

    private enum Color {
        WHITE, GRAY, BLACK
    }
}

