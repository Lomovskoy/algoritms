package sprint6.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
ID 58272069

-- ПРИНЦИП РАБОТЫ --
    Этот алгоритм DFS, который ищет цикл в графе.
    1. Алгоритму запускает поиск в глубину с любой не посещённой вершины.
    2. Создает стек и кладём туда 1ю вершину
    3. Пока стек не пуст, достаем вершину, если она белая, красим её в серый, кладём обратно в стек
    4. Ищем всех её потомков, если потомок серый цикл найден, если нет кладем его в стек
    5. Если же вершина из п.3, то красим её в черный.
    6. Если DFS закончен, а цикл не найден запускает следующую белую вершину, если есть.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Произведём серию поисков в глубину в графе. Т.е. из каждой вершины, в которую мы ещё ни разу не приходили,
    запустим поиск в глубину, который при входе в вершину будет красить её в серый цвет, а при выходе - в чёрный.
    И если поиск в глубину пытается пойти в серую вершину, то это означает, что мы нашли цикл
    (если граф неориентированный, то случаи, когда поиск в глубину из какой-то вершины пытается пойти в предка, не считаются).

    Сам цикл можно восстановить проходом по массиву предков.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Сложность этого алгоритма приблизительно.
    O(n + e), где е количество вершин которые повторно встречаются при проходе из другой вершины.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Сложность этого алгоритма приблизительно.
    O(n * 2)
 */
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

