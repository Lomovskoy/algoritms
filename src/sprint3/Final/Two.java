package sprint3.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
-- ПРИНЦИП РАБОТЫ --
Этот алгоритм состоит из 3х алгоритмов.

1. Сначала, при получении данных мы создаем, лист объектов.
Каждый объект содержит имя, очки и штраф.

2. Быстрая сортировка этого листа, эта сортировка не создает отдельный лист
по тому имеет пространственную сложность O(n).
Сортировка принимает:
    1. Лист объектов дял сортировки
    2. Индекс начала сортировки
    3. Индекс конца сортировки
    4. Флаг, прямая должны быть сортировка или обратная
    5. Уровень сортировки - он определяет по какому полю мы будем сортировать объекты.

3. Выборка неотсортированных элементов, проходится по уже отсортированному массиву,
и находит элементы который нужно отсортировать по следующему уровню.
Сортировка принимает:
    1. Лист объектов дял сортировки
    2. Уровень сортировки - он определяет по какому полю мы будем сортировать объекты.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Следующая группа алгоритмов, делает следующее.
1. Собирает входные данных в лист объектов.
2. Сортирует этот лист, по полю очки, в обратном порядке.
3. Ищет нет ли неотсортированных элементов, по полю очки, если есть создает ласт листов из пар:
    0 - начало неотсортированной последовательности.
    1- конец неотсортированной последовательности.
4. Сортирует не отсортированные части на основе предыдущих данных из п3, по полю штраф, в прямом порядке.
5. Ищет нет ли неотсортированных элементов, по полю штраф + очки, если есть создает ласт листов из пар:
    0 - начало неотсортированной последовательности.
    1- конец неотсортированной последовательности.
6. Сортирует не отсортированные части на основе предыдущих данных из п5, по полю им, в прямом порядке.

Чем соответствует всем требованиям задачи.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Амортизированная сложность алгоритма составляет лучшая O(n * log(n)), худшая O(n^2).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Амортизированная пространственная сложность алгоритма составляет O(n).

*/
public class Two {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int size = Integer.parseInt(reader.readLine());

        List<Participant> users = getNameScoresMap(reader, size);
        quickSort(users, 0, users.size() - 1, true, (byte) 0);

        List<List<Integer>> sortIndex = getSortIndex(users, true);
        sortIndex.forEach(i-> quickSort(users, i.get(0), i.get(1), false, (byte) 1));

        sortIndex = getSortIndex(users, false);
        sortIndex.forEach(i-> quickSort(users, i.get(0), i.get(1), false, (byte) 2));

        StringBuffer stringBuffer = new StringBuffer();
        users.forEach(user -> stringBuffer.append(user.getName()).append("\n"));
        System.out.println(stringBuffer);

    }

    private static List<List<Integer>> getSortIndex(List<Participant> users, boolean firstLevel) {
        List<List<Integer>> sortIndex = new ArrayList<>();
        boolean record = true;
        for (int i = 1; i < users.size(); i++) {
            if (firstLevel) {
                if (users.get(i).getScore() == users.get(i - 1).getScore()) {
                    if (record) {
                        List<Integer> index = new ArrayList<>(2);
                        index.add(i - 1);
                        sortIndex.add(index);
                        record = false;
                    }
                } else {
                    if (!record) {
                        sortIndex.get(sortIndex.size() - 1).add(i - 1);
                        record = true;
                    }
                }
            } else {
                if (users.get(i).getFines() == users.get(i - 1).getFines()
                        && users.get(i).getScore() == users.get(i - 1).getScore()) {
                    if (record) {
                        List<Integer> index = new ArrayList<>(2);
                        index.add(i - 1);
                        sortIndex.add(index);
                        record = false;
                    }
                } else {
                    if (!record) {
                        sortIndex.get(sortIndex.size() - 1).add(i - 1);
                        record = true;
                    }
                }
            }

            if (i == users.size() - 1 && !record) {
                sortIndex.get(sortIndex.size() - 1).add(i);
            }
        }
        return sortIndex;
    }

    public static void quickSort(List<Participant> array, int begin, int end, boolean reverse, byte level) {
        if (end <= begin) return;
        int pivot = partition(array, begin, end, reverse, level);
        quickSort(array, begin, pivot - 1, reverse, level);
        quickSort(array, pivot + 1, end, reverse, level);
    }

    static int partition(List<Participant> array, int begin, int end, boolean reverse, byte level) {
        int counter = begin;

        for (int i = begin; i < end; i++) {
            if (reverse) {
                if (level == 0) {
                    if (array.get(i).getScore() > array.get(end).getScore()) {
                        counter = getCounter(array, counter, i);
                    }
                }
                if (level == 1){
                    if (array.get(i).getFines() > array.get(end).getFines()) {
                        counter = getCounter(array, counter, i);
                    }
                }
                if (level == 2) {
                    if ((array.get(i).getName().compareTo(array.get(end).getName()) > 0)
                            && (array.get(i).getScore() == array.get(end).getScore())) {
                        counter = getCounter(array, counter, i);
                    }
                }
            } else {
                if (level == 0) {
                    if (array.get(i).getScore() < array.get(end).getScore()) {
                        counter = getCounter(array, counter, i);
                    }
                }
                if (level == 1){
                    if (array.get(i).getFines() < array.get(end).getFines()) {
                        counter = getCounter(array, counter, i);
                    }
                }
                if (level == 2) {
                    if ((array.get(i).getName().compareTo(array.get(end).getName()) < 0)
                            && (array.get(i).getScore() == array.get(end).getScore())) {
                        counter = getCounter(array, counter, i);
                    }
                }
            }

        }

        Participant temp = array.get(end);
        array.set(end, array.get(counter));
        array.set(counter, temp);

        return counter;
    }

    private static int getCounter(List<Participant> array, int counter, int i) {
        Participant temp = array.get(counter);
        array.set(counter, array.get(i));
        array.set(i, temp);
        counter++;
        return counter;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static List<Participant> getNameScoresMap(BufferedReader reader, int size) throws IOException {
        List<Participant> participants = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            participants.add(
                    new Participant(
                            tokenizer.nextToken(),
                            Integer.parseInt(tokenizer.nextToken()),
                            Integer.parseInt(tokenizer.nextToken())
                    )
            );
        }

        return participants;
    }

    static class Participant {
        private final String name;
        private final int score;
        private final int fines;

        public Participant(String name, int score, int fines) {
            this.name = name;
            this.score = score;
            this.fines = fines;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        public int getFines() {
            return fines;
        }

    }
}