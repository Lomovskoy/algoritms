package sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class F {

    private static Set<Integer> setSum = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int size = Integer.parseInt(reader.readLine());
        String[] string = reader.readLine().split(" ");
        String[] sortString = getSortString(size, string);
        Map<String, LinkedHashSet<Integer>> map = getStringSetMap(size, sortString);
        List<List<Integer>> listList = getLists(map);
        addElement(size, listList);
        listList.sort(Comparator.comparing(o -> o.get(0)));
        StringBuilder stringBuilder = getStringBuilder(listList);
        System.out.println(stringBuilder);
}

    private static void addElement(int size, List<List<Integer>> listList) {
        for (int i = 0; i < size; i ++){
            if (!setSum.contains(i)) {
                listList.add(List.of(i));
            }
        }
    }

    private static StringBuilder getStringBuilder(List<List<Integer>> listList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (var list : listList) {
            for (var o : list) {
                stringBuilder.append(o).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder;
    }

    private static List<List<Integer>> getLists(Map<String, LinkedHashSet<Integer>> map) {
        List<List<Integer>> listList = new ArrayList<>();
        for (var val : map.entrySet()) {
             listList.add(new ArrayList<>(val.getValue()));
        }
        return listList;
    }

    private static Map<String, LinkedHashSet<Integer>> getStringSetMap(int size, String[] sortString) {
        Map<String, LinkedHashSet<Integer>> map = new HashMap<>();

        for (int i = 0; i < size; i++) {
            if (map.get(sortString[i]) == null) {
                LinkedHashSet<Integer> set = new LinkedHashSet<>();
                for (int j = i + 1; j < size; j++) {
                    if(sortString[i].equals(sortString[j])) {
                        set.add(i);
                        set.add(j);
                        setSum.add(i);
                        setSum.add(j);
                    }
                }
                if (!set.isEmpty()) {
                    map.put(sortString[i], set);
                }
            }
        }
        return map;
    }

    private static String[] getSortString(int size, String[] string) {
        String[] sortString = new String[size];

        for (int i = 0; i < size; i++) {
            char[] chars = string[i].toCharArray();
            Arrays.sort(chars);
            sortString[i] = String.valueOf(chars);
        }
        return sortString;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}

