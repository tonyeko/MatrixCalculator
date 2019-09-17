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
        C11 = M.MatriksKofaktor(2, 2);
        C11.TulisMatriks();
    }
}