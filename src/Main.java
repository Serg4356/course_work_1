import java.math.BigInteger;

public class Main {

    //Временная, для вычислений
    static long[][] B = new long[256][21];
    //Результирующая таблица
    static long[][] A = new long[9][21];

    //Вычисляет k - ю степень 2
    public static long St2(int k) {

        if (k <= 0) {
            return 1;
        } else {
            return (long) Math.pow(2, k);
        }
    }

    //{k,l -номера сечений, pi - количество анализируемых разрядов сечений}
    public static boolean Can(int k, int l, int pi) {
        int i;
        long d;
        boolean b, res;
        res = false;
        b = false;
        for (i = 1; i <= pi; i++) {
            d = St2(i);
            if ((k & d) == 0){ //{определяется значение разряда с номером d для сечения k}
                if ((l & d) == 0) {//если d-тый разряд 1-го сечения - 0, сравнивается d-тый разряд 2-го сечения
                    b = !b; /**если d-тый разряд первого и второго сечения = 0, значение b меняется на противоположное, затем
                            *чтобы при нечетном количестве нулей выбивало из метода с результатом false
                            */
                } else { // kd = 0, ld = 1
                    if (b) { // если b = true
                        return false; // завершение метода - false, срабатывает в случаях когда количество 0 перед 1 нечетно
                        /**
                         * Метод завершается с результатом false в случаях
                         *  d = 00 || l = 10 - корректно
                         *  d = 0000 || l = 1000 - корректно
                         *
                         */
                    }
                }
            } else if (((l & d) != 0) || b) {
                return false;
            }
        }
        ;
        res = !b;
        return res;
    }

    //Основная логика
    public static void Solve() {
        int i, j, k, l;
        long max;
        //Цикл по значению длины комнаты
        for (i = 1; i <= 8; i++) {
            max = St2(i) - 1;
            B[0][0] = 1;
            //Цикл по значению ширины комнаты
            for (j = 1; j <= 20; j++) { //цикл для обсепечения всей ширины комнаты сечениями
                //Сечение с номером k
                for (k = 0; k <= max; k++) {
                    //Сечение с номером l
                    for (l = 0; l <= max; l++) //if (Can(k,l,i))
                    {
                        //Проверка совместимости сечений
                        boolean r = Test.Can(k, l, i);
                        if (r) {
                            B[k][j] = B[k][j] + B[l][j - 1];
                        }
                    }
                }
                A[i][j] = B[0][j];
            }
        }
    }

    public static void main(String[] args) {

        //забиваем нулями таблицы
        for (int m = 0; m <= 20; m++) {
            for (int n = 0; n <= 8; n++) {
                A[n][m] = 0;
            }
        }
        for (int m = 0; m <= 20; m++) {
            for (int n = 0; n <= 255; n++) {
                B[n][m] = 0;
            }
        }
        //производим расчет
        Solve();

        //выводим результаты
        for (int m = 1; m <= 20; m++) {
            for (int n = 1; n <= 8; n++) {

                if (n * m % 2 != 0) {
                    String val="*";
                    for(int p=0;p<25;p++)
                        val+=" ";
                    System.out.print(val);
                } else
                if (m == 1 && n % 2 == 0) {
                    String val="1";
                    for(int p=0;p<25;p++)
                        val+=" ";
                    System.out.print(val);
                } else {
                    String val=Long.toString(A[n][m]);
                    int v=val.length();
                    for(int p=0;p<26-v;p++)
                        val+=" ";
                    System.out.print(val);
                }
            }
            System.out.println();
        }

        //System.out.println(Can(33,18,6));
        //System.out.println(Can(0,18,6));
    }
}
