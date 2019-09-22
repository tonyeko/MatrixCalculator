import java.util.*;

class DriverInter {
    public static void main(String[] args) {

    float hasil;
    Matriks M1;
    int N;

    Scanner input = new Scanner(System.in);

    Interpolasi I = new Interpolasi ();

    
    // System.out.print(hasil);
   
   
    M1 = I.InputTitik();
   // M1.TulisMatriks();
  //   hasil = I.HasilInterpolasi (M1);
   // System.out.print(hasil);
    I.TulisPersamaanPolinom(M1);

    }

}
