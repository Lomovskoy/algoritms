package sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B {

    public static void main(String[] args) throws Exception {
        var reader = getReader();
        int[] pair = getPair(reader);
        var list = getListList(reader, pair[1]);
        int[][] resultList = buildToMatrix(pair[0], list);
        var stringBuilder = getStringBuilder(resultList);
        System.out.println(stringBuilder);
    }

    private static StringBuilder getStringBuilder(int[][] resultList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (var val : resultList) {
            for (var el : val) {
                stringBuilder.append(el).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder;
    }

    private static int[][] buildToMatrix(int size, List<List<Integer>> list) {
        var resultList = new int[size][size];
        for (var val : list) resultList[val.get(0) - 1][val.get(1) - 1] = 1;
        return resultList;
    }

    private static int[] getPair(BufferedReader reader) throws IOException {
        var tokenizer = new StringTokenizer(reader.readLine());
        return new int[]{Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())};
    }

    private static List<List<Integer>> getListList(BufferedReader reader, int size) throws IOException {
        List<List<Integer>> listList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            var tokenizer = new StringTokenizer(reader.readLine());
            listList.add(List.of(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
        }
        return listList;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

}