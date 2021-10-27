package sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class A {

    public static void main(String[] args) throws Exception {
        var reader = getReader();
        int[] pair = getPair(reader);
        var list = getListList(reader, pair[1]);
        var resultList = buildListOfEdges(pair[0], list);
        var stringBuilder = getStringBuilder(resultList);
        System.out.println(stringBuilder);
    }

    private static StringBuilder getStringBuilder(List<List<Integer>> resultList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (var val : resultList) {
            for (var el : val) {
                stringBuilder.append(el).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder;
    }

    private static List<List<Integer>> buildListOfEdges(int size, List<List<Integer>> list) {
        var resultList = new ArrayList<List<Integer>>();

        for (int i = 1; i <= size; i++) {
            int finalI = i;
            var filterList = list.stream().filter(val -> val.get(0) == finalI).collect(Collectors.toList());
            var subList = new ArrayList<Integer>();
            subList.add(filterList.size());
            for (var fl : filterList) {
                subList.add(fl.get(1));
            }
            resultList.add(subList);
        }
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