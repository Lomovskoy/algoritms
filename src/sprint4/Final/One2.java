package sprint4.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class One2 {

    private static final int LIMIT = 5;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();

        int storageSize = Integer.parseInt(reader.readLine());
        Map<String, Map<Integer, Integer>> dataMap = getDataMap(storageSize, reader);

        int requestsCount = Integer.parseInt(reader.readLine());
        responsePrint(requestsCount, reader, dataMap);

    }

    private static void responsePrint(int requestsCount, BufferedReader reader, Map<String, Map<Integer, Integer>> dataMap) throws IOException {
        Set<Document> responseMap = new LinkedHashSet<>();
        for (int i = 0; i < requestsCount; i++) {
            for (String string : new LinkedHashSet<>(List.of(reader.readLine().split(" ")))) {
                var subMap = dataMap.get(string);
                if (subMap != null) {
                    for (var val : subMap.entrySet()) {
                        if(responseMap.isEmpty()) {
                            responseMap.add(new Document(val.getKey(), val.getValue()));
                        } else {
                            boolean flag = true;
                            for (var response : responseMap) {
                                if (response.getDocId() == val.getKey()) {
                                    response.setRelevancy(response.getRelevancy() + 1);
                                    flag = false;
                                }
                            }
                            if (flag) {
                                responseMap.add(new Document(val.getKey(), val.getValue()));
                            }
                        }
                    }
                }
            }
            print(responseMap);
        }
    }

    private static void print(Set<Document> responseMap) {
        responseMap.stream()
                .sorted(Comparator.comparingInt(Document::getRelevancy).reversed()
                        .thenComparing(Document::getDocId))
                .limit(LIMIT)
                .forEach(in -> System.out.print((in.getDocId() + 1) + " "));
        responseMap.clear();
        System.out.println();
    }

    static class Document  {
        public int docId;
        public int relevancy;

        public Document(int docId, int relevancy) {
            this.docId = docId;
            this.relevancy = relevancy;
        }

        public int getDocId() {
            return docId;
        }

        public void setDocId(int docId) {
            this.docId = docId;
        }

        public int getRelevancy() {
            return relevancy;
        }

        public void setRelevancy(int relevancy) {
            this.relevancy = relevancy;
        }

    }

    private static Map<String, Map<Integer, Integer>> getDataMap(int storageSize, BufferedReader reader) throws IOException {
        Map<String, Map<Integer, Integer>> dataMap = new HashMap<>();
        for (int i = 0; i < storageSize; i++) {
            for (String str : reader.readLine().split(" ")) {
                if (dataMap.get(str) == null) {
                    Map<Integer, Integer> map = new HashMap<>();
                    map.put(i, 1);
                    dataMap.put(str, map);
                } else {
                    Map<Integer, Integer> map = dataMap.get(str);
                    if (map.get(i) == null) {
                        map.put(i, 1);
                    } else {
                        map.put(i, map.get(i) + 1);
                    }
                }
            }
        }
        return dataMap;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}