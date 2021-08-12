package sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class C {

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        int numberOfMatrixRows = Integer.parseInt(reader.readLine());
        int numberOfMatrixColumns  = Integer.parseInt(reader.readLine());

        var matrix = new ArrayList<List<Integer>>();
        for (int i = 0; i < numberOfMatrixRows; i++) {
            matrix.add(getIntegerList(reader));
        }

        var coordinates = new int[2];
        coordinates[0] = Integer.parseInt(reader.readLine());
        coordinates[1] = Integer.parseInt(reader.readLine());

        var down = coordinates[0] + 1 > numberOfMatrixRows - 1 ? null : coordinates[0] + 1;
        var right = coordinates[1] + 1 > numberOfMatrixColumns - 1 ? null : coordinates[1] + 1;
        var up = coordinates[0] - 1 < 0 ? null : coordinates[0] - 1;
        var left = coordinates[1] - 1 < 0 ? null : coordinates[1] - 1;

        var result = new ArrayList<Integer>();
        if (up != null)
            result.add(matrix.get(up).get(coordinates[1]));
        if (left != null)
            result.add(matrix.get(coordinates[0]).get(left));
        if (down != null)
            result.add(matrix.get(down).get(coordinates[1]));
        if (right != null)
            result.add(matrix.get(coordinates[0]).get(right));
        result.sort(Integer::compareTo);

        var builder = new StringBuilder();
        for (var val : result) builder.append(val).append(" ");
        System.out.println(builder.toString().trim());
    }

    private static List<Integer> getIntegerList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
