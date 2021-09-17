package sprint4;

public class OrderedHashSet {

    private static final int INITIAL_SIZE = 16;
    private static final int BASKET_SIZE = 1;
    private static final int COEFFICIENT = 3;
    private String[][] data;
    private int[] number;
    private int index = 0;

    public OrderedHashSet() {
        data = new String[INITIAL_SIZE][BASKET_SIZE];
        number = new int[INITIAL_SIZE];
    }

    public OrderedHashSet(int size) {
        if (size == 0) size = INITIAL_SIZE;
        data = new String[size][BASKET_SIZE];
        number = new int[size];
    }

    public void addElement(String string) {
        int hashElement = getHash(string);
        if (hashElement >= data.length) {
            updateDataArray(hashElement);
        }
        if (data[hashElement][0] == null) {
            data[hashElement][0] = string;
            updateNumber(hashElement);
        } else {
            boolean flag = true;
            for (int i = 0; i < data[hashElement].length; i++) {
                if (data[hashElement][i] != null && data[hashElement][i].equals(string)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                updateBasket();
                data[hashElement][data[0].length - 1] = string;
                updateNumber(hashElement);
            }
        }
    }

    public String getElement(int element) {
        int hashElement = number[element];
        if (data[hashElement].length > 1) {
            for (int i = 0; i < data[hashElement].length; i++) {
                if (data[hashElement][i] != null) {
                    String str = data[hashElement][i];
                    data[hashElement][i] = null;
                    return str;
                }
            }
        } else {
            String str = data[hashElement][0];
            data[hashElement][0] = null;
            return str;
        }
        return null;
    }

    public int getSize() {
        return index;
    }

    private void updateNumber(int hashElement) {
        if (index >= number.length) {
            updateNumberArray();
        }
        number[index++] = hashElement;
    }

    private int getHash(String string) {
        char[] chars = string.toCharArray();
        int hash = 0;
        int j = 0;
        if (chars.length > 1) {
            for (; j < chars.length; j++) {
                int x = j == 0 ? 1 : j;
                hash += ((chars[j] + j) * j / x) - j;
            }
            hash = (hash / j) / chars.length;
        } else {
            hash = chars[0];
        }
        return hash;
    }

    private void updateDataArray(int newSize) {
        String[][] newData = new String[newSize + (newSize / COEFFICIENT)][data[0].length];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    private void updateNumberArray() {
        int[] newNumber = new int[number.length + (number.length / COEFFICIENT)];
        System.arraycopy(number, 0, newNumber, 0, number.length);
        number = newNumber;
    }

    private void updateBasket() {
        String[][] newData = new String[data.length][data[0].length + 1];
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, newData[i], 0, data[i].length);
        }
        data = newData;
    }
}