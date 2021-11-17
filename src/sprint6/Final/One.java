package sprint6.Final;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
ID 58283375

-- ПРИНЦИП РАБОТЫ --
    Этот алгоритм ищет максимальное остовное дерево, путем обхода графа с помощью алгоритма Прима.
    1. Алгоритму не важно, с какой вершины начинать, так как в итоге все вершины попадут в максимальное остовное дерево.
    Поэтому берётся любая вершина графа.
    2. Рассмотрим все рёбра, исходящие из этой вершины. Возьмём ребро с максимальным весом и добавим в остов ребро и
    вершину, в которую оно входило.
    3. Добавим ко множеству потенциально добавляемых рёбер все, которые исходят из новой вершины и входят в вершины,
    ещё не включённые в остов.
    4. Повторяем пункты 2 и 3 до тех пор, пока в остовном дереве не будет n вершин и, соответственно, n-1 рёбер.

    Так как на каждой итерации цикла мы добавляем ровно одно ребро и одну вершину, нам потребуется n−1 итерация.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Пусть граф G был связным, т.е. ответ существует. Обозначим через T остов, найденный алгоритмом Прима, а через S — максимальный остов.
    Очевидно, что T действительно является остовом (т.е. поддеревом графа G). Покажем, что веса S и T совпадают.

    Рассмотрим первый момент времени, когда в T происходило добавление ребра, не входящего в оптимальный остов S.
    Обозначим это ребро через e, концы его — через a и b, а множество входящих на тот момент в остов вершин — через V.
    В оптимальном остове S вершины a и b соединяются каким-то путём P.

    Найдём в этом пути любое ребро g, один конец которого лежит в V, а другой — нет.
    Поскольку алгоритм Прима выбрал ребро e вместо ребра g, то это значит, что вес ребра g больше либо равен весу ребра e.

    Удалим теперь из S ребро g, и добавим ребро e. По только что сказанному, вес остова в результате
    не мог увеличиться (уменьшиться он тоже не мог, поскольку S было оптимальным).
    Кроме того, S не перестало быть остовом (в том, что связность не нарушилась, нетрудно убедиться:
    мы замкнули путь P в цикл, и потом удалили из этого цикла одно ребро).

    Итак, мы показали, что можно выбрать оптимальный остов S таким образом, что он будет включать ребро e.
    Повторяя эту процедуру необходимое число раз, мы получаем, что можно выбрать оптимальный остов S так,
    чтобы он совпадал с T. Следовательно, вес построенного алгоритмом Прима T максимален, что и требовалось доказать.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Сложность этого алгоритма приблизительно.
    O(|V|*|E|)
    Алгоритму требуется число шагов, пропорциональное количеству вершин.
    На каждом шаге мы находим максимальное по весу ребро.
    На поиск максимального ребра нам требуется в худшем случае перебрать все рёбра.

    Благодаря приоритетной очереди сложность алгоритма
    Прима стала O(∣E∣*log∣V∣)
    где |E| — количество рёбер в графе, а |V| — количество вершин.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Сложность этого алгоритма приблизительно.
    O(n * 2)
 */
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
            // Подразумеваем, что extractMaximum извлекает максимальное ребро
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
