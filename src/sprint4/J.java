package sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class J {

    public static void main(String[] args) throws IOException {
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int numbersQuantity = Integer.parseInt(bufferedReader.readLine());
        int sum = Integer.parseInt(bufferedReader.readLine());
        var stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] numbers = new int[numbersQuantity];
        for (int i = 0; i < numbersQuantity; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        var preparedSums = new HashMap<Integer, ArrayList<Pair>>();
        var result = new TreeSet<>(new ComparatorArray());

        for (int i = 0; i < numbersQuantity; ++i) {
            for (int j = i + 1; j < numbersQuantity; ++j) {
                int current = numbers[i] + numbers[j];
                int rest;
                try {
                    rest = Math.subtractExact(sum, current);
                } catch (ArithmeticException e) {
                    continue;
                }

                if (preparedSums.containsKey(rest)) {
                    for (Pair pair : preparedSums.get(rest)) {
                        ArrayList<Integer> four = new ArrayList<>();
                        four.add(numbers[i]);
                        four.add(numbers[j]);
                        four.add(numbers[pair.getI()]);
                        four.add(numbers[pair.getJ()]);
                        Collections.sort(four);
                        result.add(four);
                    }
                }
            }

            for (int k = 0; k < i; ++k) {
                if (preparedSums.containsKey(numbers[i] + numbers[k])) {
                    preparedSums.get(numbers[i] + numbers[k]).add(new Pair(i, k));
                } else {
                    ArrayList<Pair> newPairArray = new ArrayList<>();
                    newPairArray.add(new Pair(i, k));
                    preparedSums.put(numbers[i] + numbers[k], newPairArray);
                }
            }
        }

        var stringBuffer = new StringBuilder();
        for (ArrayList<Integer> four : result) {
            for (int i = 0; i < 4; i++) {
                stringBuffer.append(four.get(i)).append(" ");
            }
            stringBuffer.append("\n");
        }
        System.out.println(result.size());
        System.out.println(stringBuffer);

    }

}

class Pair {
    int i;
    int j;

    public Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}

class ComparatorArray implements Comparator<ArrayList<Integer>> {

    @Override
    public int compare(ArrayList<Integer> array1, ArrayList<Integer> array2) {

        for (int i = 0; i < 4; i++) {
            if (array1.get(i) > array2.get(i)) {
                return 1;
            }
            if (array1.get(i) < array2.get(i)) {
                return -1;
            }
        }
        return 0;
    }
}