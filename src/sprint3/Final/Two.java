package sprint3.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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