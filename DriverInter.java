import java.util.*;

class DriverInter {
    public static void main(String[] args) {

    float hasil;
    Matriks M1;
    int N;

    Scanner input = new Scanner(System.in);

    Interpolasi I = new Interpolasi ();

    
    // System.out.print(hasil);
    N = input.nextInt();
    M1 = I.InputTitik(N);
    M1.TulisMatriks();
    hasil = I.HasilInterpolasi ((float)9.2, M1);
    System.out.print(hasil);

    }

}
