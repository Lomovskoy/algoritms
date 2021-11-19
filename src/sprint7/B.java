package sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B {

    private static final List<Meeting> meetings = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int size = Integer.parseInt(reader.readLine());
        getMeeting(reader, size);
        meetings.sort(Meeting::compareTo);

        int count = 0;
        List<Meeting> resultMeeting = new ArrayList<>();

        for (Meeting meeting : meetings) {
            if (resultMeeting.isEmpty()) {
                resultMeeting.add(meeting);
            } else {
                if (resultMeeting.get(count).getEnd() <= meeting.getStart()) {
                    resultMeeting.add(meeting);
                    count++;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(resultMeeting.size()).append("\n");

        for (Meeting meeting : resultMeeting) {
            stringBuilder.append(fmt(meeting.getStart())).append(" ").append(fmt(meeting.getEnd())).append("\n");
        }
        System.out.println(stringBuilder);
    }

    public static String fmt(float d) {
        if (d == (long) d) {
            return String.format("%d", (long) d);
        } else {
            return String.format("%s", d);
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void getMeeting(BufferedReader reader, int size) throws IOException {
        StringTokenizer tokenizer;
        for (int i = 0; i < size; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            meetings.add(new Meeting(Float.parseFloat(tokenizer.nextToken()), Float.parseFloat(tokenizer.nextToken())));
        }
    }

    public static class Meeting implements Comparable<Meeting> {
        private final float start;
        private final float end;

        public Meeting(float start, float end) {
            this.start = start;
            this.end = end;
        }

        public float getStart() {
            return start;
        }

        public float getEnd() {
            return end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Meeting)) return false;

            Meeting meeting = (Meeting) o;

            if (getStart() != meeting.getStart()) return false;
            return getEnd() == meeting.getEnd();
        }

        @Override
        public int hashCode() {
            int result = (getStart() != +0.0f ? Float.floatToIntBits(getStart()) : 0);
            result = 31 * result + (getEnd() != +0.0f ? Float.floatToIntBits(getEnd()) : 0);
            return result;
        }

        @Override
        public int compareTo(Meeting o) {
            int compare = Float.compare(this.end, o.getEnd());
            if (compare == 0) {
                compare = Float.compare(this.start, o.getStart());
            }
            return compare;
        }
    }
}