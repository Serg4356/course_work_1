
public class Parquet{

    // метод для сопоставления сечений
    public static boolean acceptCut(int firstCut, int secondCut, int digitsCount){
        int countSecond = 0;// переменная для хранения кол-ва 0 во втором сечении

        for(int i=0;i<digitsCount;i++){//цикл по каждому разряду сечения
            int digit = (1<<i);// маска
            if((firstCut&digit)==0) {//если i-ый разряд 1-го сечения =0
                    if ((secondCut & digit) == 0) {//если i-ый разряд 2-го сечения =0
                        countSecond++;
                        } else {//если i-ый разряд 2-го сечения !=0
                            if(countSecond%2!=0) return false;// и кол-во 0 во втором сечении - нечетно
                        }
                } else {//если i-ый разряд 1-го сечения !=0
                    if ((countSecond%2!=0)||(secondCut & digit)!=0) return false; //  первое и второе сечение = 1 и кол-во 0 во втором сечении - нечетно
                }
            }
            if((countSecond%2!=0)) return false;
        return true;
    }

    //метод для нахождения решений для заданных m n
    public static void countValues(int digitsCount, int roomWidth){
    long resNumber = 0;
    int maxi = 1<<digitsCount;
    long [][] arr = new long[maxi][roomWidth+1];

    arr[0][0] = 1;

        for (int i = 1; i <= roomWidth; i++) {
            for (int x = 0; x < maxi; x++) {
                for (int z = 0; z < maxi; z++) {
                    if (acceptCut(x, z, digitsCount)) {
                        arr[x][i] = arr[x][i] + arr[z][i-1];
                    }
                }
            }
            String r ="";
            if(i*digitsCount%2!=0){
                r = "*";
            } else {
                r = Long.toString(arr[0][i]);
            }
            int strlength = r.length();
            for(int l = 0; l<(20-strlength);l++){
                r +=" ";
            }
            r += "| ";
            System.out.print(r);
        }
    }

    public static void main(String[] args) {

        for(int i=1; i<=8;i++) {
            countValues(i, 20);
            System.out.println();
            for(int y = 0; y<20*22; y++){
                System.out.print("-");
            }
            System.out.println();
        }
    }
}
