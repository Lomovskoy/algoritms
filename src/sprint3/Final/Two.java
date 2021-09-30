package sprint3.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
ID 53695340

-- ПРИНЦИП РАБОТЫ --
Этот алгоритм состоит из 2х алгоритмов.

1. Сначала, при получении данных мы создаем, лист объектов.
Каждый объект содержит имя, очки и штраф.

2. Быстрая сортировка этого листа, эта сортировка не создает отдельный лист
по тому имеет пространственную сложность O(n).
Сортировка принимает:
    1. Лист объектов дял сортировки
    2. Индекс начала сортировки
    3. Индекс конца сортировки

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Следующая группа алгоритмов, делает следующее.
1. Собирает входные данных в лист объектов.
2. Сортирует этот лист, по полю очки, в обратном порядке.
3. Досортировывает одинаковые элементы по полю штраф в прямом порядке
4. Оставшиеся элементы сортирует по имени в лексикографическом порядке.
Все эти условия сортировки описаны в переопределённом компараторе.

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

        List<Participant> users = getParticipants(reader, size);
        quickSort(users, 0, users.size() - 1);

        StringBuffer stringBuffer = new StringBuffer();
        users.forEach(user -> stringBuffer.append(user.getName()).append("\n"));
        System.out.println(stringBuffer);

    }

    public static void quickSort(List<Participant> array, int begin, int end) {
        if (end <= begin) return;

        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot - 1);
        quickSort(array, pivot + 1, end);
    }

    static int partition(List<Participant> array, int begin, int end) {
        int counter = begin;

        for (int i = begin; i < end; i++) {

            if (array.get(i).compareTo(array.get(end)) < 0) {
                counter = getCounter(array, counter, i);
            }

        }

        Collections.swap(array, end, counter);
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

    private static List<Participant> getParticipants(BufferedReader reader, int size) throws IOException {
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

    static class Participant implements Comparable<Participant> {
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

        @Override
        public int compareTo(Participant o) {
            if (this.getScore() != o.getScore()) {
                return o.getScore() - this.getScore();
            } else {
                if (this.getFines() != o.getFines()) {
                    return this.getFines() - o.getFines();
                } else {
                    return this.getName().compareTo(o.getName());
                }
            }
        }
    }
}