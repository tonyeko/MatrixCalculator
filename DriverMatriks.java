import java.util.*;

class DriverMatriks {
    public static void main(String[] args) {
        // Scanner scan = new Scanner(System.in);
        
        Matriks M = new Matriks();
        Matriks M2 = new Matriks();

        M.BacaMatriks();

        System.out.println("Isi matriks: ");
        M.TulisMatriks();
        System.out.println();

        
        M2.CopyMatriks(M);
        M2.TulisMatriks();
        System.out.println();

        // M.TransposeMatriks();
        // M.TulisMatriks();
        // System.out.println();
        
        M2.swapRow(1, 2);
        
        M2.TulisMatriks();
        System.out.println();
        System.out.println(M.Determinan());

        Matriks C11 = new Matriks();
        C11 = M.Kofaktor(2, 2);
        C11.TulisMatriks(); 
        System.out.println();

        Matriks Hasil = new Matriks();
        Hasil.CopyMatriks(M);
        Hasil = Hasil.MatriksCofactor();
        Hasil.TulisMatriks();
        System.out.println();

        Hasil = M.MatriksInvers();
        Hasil.TulisMatriks();
        System.out.println();
        // System.out.println(M.Cramer(Hasil, 1));

        Hasil = M.MakeAugmented(M2);
        Hasil.TulisMatriks();
        System.out.println();

        M = M.MakeIdentity();
        M.TulisMatriks(); 
        System.out.println();
    }
}