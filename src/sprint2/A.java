package sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class A {

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        int numberOfMatrixRows  = Integer.parseInt(reader.readLine());
        int numberOfMatrixColumns  = Integer.parseInt(reader.readLine());
        var matrix = getMatrix(reader, numberOfMatrixRows, numberOfMatrixColumns);
        var transposed = getTransposed(numberOfMatrixRows, numberOfMatrixColumns, matrix);
        var sb = getResultString(numberOfMatrixRows, numberOfMatrixColumns, transposed);
        System.out.println(sb);
    }

    private static StringBuffer getResultString(int numberOfMatrixRows, int numberOfMatrixColumns, int[][] transposed) {
        var sb = new StringBuffer();
        for (int i = 0; i < numberOfMatrixColumns; i++) {
            for (int j = 0; j < numberOfMatrixRows; j++) {
                sb.append(transposed[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb;
    }

    private static int[][] getTransposed(int numberOfMatrixRows, int numberOfMatrixColumns, int[][] matrix) {
        var transposed = new int[numberOfMatrixColumns][numberOfMatrixRows];
        for (int i = 0; i < numberOfMatrixColumns; i++) {
            for (int j = 0; j < numberOfMatrixRows; j++) {
                transposed[i][j] = matrix[j][i];
            }
        }
        return transposed;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static int[][] getMatrix(BufferedReader reader, int numberOfMatrixRows, int numberOfMatrixColumns) throws IOException {
        int[][] matrix = new int[numberOfMatrixRows][numberOfMatrixColumns];
        for (int i = 0; i < numberOfMatrixRows; i++) {
            var rows = Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            for (int j = 0; j < numberOfMatrixColumns; j++) {
                matrix[i][j] = rows.get(j);
            }
        }
        return matrix;
    }

}