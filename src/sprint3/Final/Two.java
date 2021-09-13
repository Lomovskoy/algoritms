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
            if (this.getScore() > o.getScore()) {
                return -1;
            } else if (this.getScore() < o.getScore()) {
                return 1;
            } else {
                if (this.getFines() > o.getFines()) {
                    return 1;
                } else if (this.getFines() < o.getFines()) {
                    return -1;
                } else {
                    return this.getName().compareTo(o.getName());
                }
            }
        }
    }
}