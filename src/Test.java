import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Пользователь on 16.10.2016.
 */
public class Test {

    public static boolean Can(int k, int l, int h){
        int last = 1<<h;
        boolean b = false;
        for(int pos = 1; pos<last; pos = pos<<1){
            if((k&pos)==0){
                if((l&pos)==0){
                    b=!b;
                } else {
                    if(b) return false;
                }
            } else if(((l&pos)!=0)||b) return false;
        }
        if(b) return false;
        return !b;
    }

    public static void Solve(int w, int h){
    int hh = (1<<h) - 1;
    int[][] arr = new int[256][21];
    for(int i = 0; i< hh; i++){
        arr[i][0] = 0;
    }

    for(int j = 1; j<=w;j++){
        for(int k = 0; k<= hh; k++){
            arr[k][j] = 1;
            for(int l = 0; l<= hh; l++){
                if(Can(l,k,h)) {
                    arr[k][j] =arr[k][j]+1;
                }
            }
            System.out.println(arr[k][j]);
        }
    }

        /**
        proc Solve {w h} {
            set hh [expr (1 << $h) - 1]
            set arr(0,0) 1; for {set i 1} {$i <= $hh} {incr i} {set arr($i,0) 0}
            for {set j 1} {$j <= $w} {incr j} {
                for {set k 0} {$k <= $hh} {incr k} {
                    set arr($k,$j) 0
                    set descr {}
                    for {set l 0} {$l <= $hh} {incr l} {
                        if {[Can $l $k $h]} {
                            append descr "+$arr($l,[expr $j-1]) ($l,[expr $j-1])"
                            incr arr($k,$j) $arr($l,[expr $j-1])
                        }
                    }
                    puts "$k,$j: $arr($k,$j) = $descr"
                }
            }
        }
         */
    }


    public static void main(String[] args) {
        Solve(20,2);
    }
}
