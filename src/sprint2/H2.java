package sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class H2 {

    private static final String OPEN_SQUARE_BRACKET = "[";
    private static final String OPEN_ROUND_BRACKET = "(";
    private static final String OPEN_FIGURAL_BRACKET = "{";

    private static final String CLOSED_SQUARE_BRACKET = "]";
    private static final String CLOSED_ROUND_BRACKET = ")";
    private static final String CLOSED_FIGURAL_BRACKET = "}";

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var array = getStringArray(reader);
        boolean result = array.size() == 1 && array.get(0).isEmpty() ? Boolean.TRUE : isCorrectBracketSeq(array);
        System.out.println(result ? "True" : "False");
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static List<String> getStringArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split("")).collect(Collectors.toList());
    }

    private static boolean isCorrectBracketSeq(List<String> arr) {
        for (int i = 0; i < arr.size(); i++) {
            var open = arr.get(i);
            for (int j = 1; j < arr.size(); j++) {
                var close = arr.get(j);
                if (open.equals(OPEN_ROUND_BRACKET) && close.equals(CLOSED_ROUND_BRACKET)) {
                    arr.remove(i);
                    arr.remove(j - 1);
                    --i;
                    break;
                } else if (open.equals(OPEN_SQUARE_BRACKET) && close.equals(CLOSED_SQUARE_BRACKET)) {
                    arr.remove(i);
                    arr.remove(j - 1);
                    --i;
                    break;
                } else if (open.equals(OPEN_FIGURAL_BRACKET) && close.equals(CLOSED_FIGURAL_BRACKET)) {
                    arr.remove(i);
                    arr.remove(j - 1);
                    --i;
                    break;
                }
            }
        }
        return arr.isEmpty();
    }
}