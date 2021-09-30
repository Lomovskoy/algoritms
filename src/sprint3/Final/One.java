package sprint3.Final;

/*
ID 53695340

-- ПРИНЦИП РАБОТЫ --
Сначала определяем 2 индекса начальный и конечный.
В цикле пока первый индекс меньше или равен конечному -
находим индекс середины массива. Сразу проверяем,
если элемент в середине массива равен искомому, то элемент найден, возвращаем индекс.
Если элемент под первым индексом <= элементу под индексом середины массива, то
проверяем следующее условие, если искомое значение < элемента под индексом середины
и больше элемента под первым индексом значит нам нужно искать в это диапазоне.
Тогда делим массив пополам минус средний элемент, мы его проверили, и берём первую половину.
Если же нет, то берём 2ю половину массива, и приравниваем первый индекс среднему индексу + 1 (его мы уже проверили).
Если же элемент под первым индексом > элемента под индексом середины массива,
проверяем следующие условия. Если искомый элемент > элемента под индексом середины массива,
и <= элементу массива под последним индексом, значит нам нужно искать в это диапазоне.
Приравниваем первый индекс среднему + 1 (его мы уже проверили), таким образом берём вторую половину массива.
Если же не там, то нам нужно брать первую половину массива и мы
приравниваем последний индекс среднему - 1 (его мы уже проверили).
Далее переходим на следующую итерацию цикла, где мы будем проверять уже половину изначального массива тем же образом,
и так далее пока не вернём искомое значение, если оно не найдено, цикл завершится и будет возвращено -1

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Из описания алгоритма следует, что это немного преобразованный бинарный поиск, который может искать значения в
массиве отдельные части которого отсортированы. Мы сначала проверяем направление сортировки первой половины массива,
если он отсортирован по возрастанию, то проверяем, входит ли искомое значение в его диапазон,
если да ищем в этой части если нет в другой.
Если же нет, то есть текущая половина массива отсортирована в обратном порядке, то делаем наоборот. Если искомое значение
больше среднего, но меньше последнего нужно икать с 2й половине, так как при обратной сортировке
меньшие элементы будут там и наоборот.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Амортизированная сложность алгоритма составляет O(log n).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Амортизированная пространственная сложность алгоритма составляет O(n).

*/
public class One {

    public static int brokenSearch(int[] array, int searchValue) {
        int firstIndex = 0;
        int lastIndex = array.length - 1;

        while (firstIndex <= lastIndex) {
            int averageIndex  = (firstIndex + lastIndex) / 2;
            if (array[averageIndex] == searchValue) {
                return averageIndex;
            }
            if (array[firstIndex] <= array[averageIndex]) {
                if (searchValue < array[averageIndex] && searchValue >= array[firstIndex]) {
                    lastIndex = averageIndex - 1;
                } else {
                    firstIndex = averageIndex + 1;
                }
            } else {
                if (searchValue > array[averageIndex] && searchValue <= array[lastIndex]) {
                    firstIndex = averageIndex + 1;
                } else {
                    lastIndex = averageIndex - 1;
                }
            }
        }
        return -1;
    }

    public static void test() {
        int[] arr = {19, 21, 100, 101, 1, 4, 5, 7, 12};
        System.out.println(brokenSearch(arr, 5));
    }
}