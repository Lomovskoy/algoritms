package sprint3.Final;

public class Solution {

    public static int brokenSearch(int[] array, int searchValue) {
        // Обозначаем первый индекс, начала массива.
        int firstIndex = 0;
        // Обозначаем конечный индекс, конец массив конца массива
        int lastIndex = array.length - 1;

        // Пока первый индекс меньше последнего
        while (firstIndex <= lastIndex) {
            // Находим индекс середины массива
            int averageIndex  = (firstIndex + lastIndex) / 2;
            // Если значение среднего индекса массива равен искомому элемента
            if (array[averageIndex] == searchValue) {
                // Возвращаем индекс найденного элемента
                return averageIndex;
            }
            // Если элемент под первым индексом <= элемента под индексом середины массива
            if (array[firstIndex] <= array[averageIndex]) {
                // Если искомое значение < элемента под индексом середины и больше элемента под первым индексом
                if (searchValue < array[averageIndex] && searchValue >= array[firstIndex]) {
                    // Приравниваем первый индекс среднему индексу - 1 (его мы уже проверили)
                    lastIndex = averageIndex - 1;
                } else {
                    // Приравниваем последний индекс среднему индексу + 1 (его мы уже проверили)
                    firstIndex = averageIndex + 1;
                }
            }
            // Если элемент под первым индексом > элемента под индексом середины массива
            else {
                // Если искомый элемент > элемент под индексом середины массива,
                // и <= элементу массива под первым индексом
                if (searchValue > array[averageIndex] && searchValue <= array[lastIndex]) {
                    // Приравниваем первый элемент среднему + 1 (его мы уже проверили)
                    firstIndex = averageIndex + 1;
                } else {
                    // Приравниваем первый элемент среднему - 1 (его мы уже проверили)
                    lastIndex = averageIndex - 1;
                }
            }
        }
        // Если ничего не найдено, возвращаем -1
        return -1;
    }

    public static void test() {
        int[] arr = {19, 21, 100, 101, 1, 4, 5, 7, 12};
        System.out.println(brokenSearch(arr, 5));
    }
}