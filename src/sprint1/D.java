package sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class D {

    /**
     * 7
     * -1 -10 -8 0 2 0 5
     */
    public static void main(String[] args) throws IOException {
        int result = getResult();
        System.out.println(result);
    }

    private static int getResult() throws IOException {
        int result = 0;
        var reader = getReader();
        int numberOfDays  = Integer.parseInt(reader.readLine());

        if (numberOfDays == 1) return 1;

        var days  = getIntegerList(reader);

        for (int i = 0; i < numberOfDays; i++) {
            if (i == 0) {
                if (days.get(i) > days.get(i + 1)) {
                    result++;
                }
            } else if (i == numberOfDays - 1) {
                if (days.get(i) > days.get(i - 1)) {
                    result++;
                }
            } else {
                if (days.get(i) > days.get(i - 1) && days.get(i) > days.get(i + 1)) {
                    result++;
                }
            }
        }
        return result;
    }

    private static List<Integer> getIntegerList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
