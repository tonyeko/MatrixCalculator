import java.util.*;

class DriverMatriks {
    public static void main(String[] args) {
        // Scanner scan = new Scanner(System.in);
        
        Matriks M = new Matriks();

        M.BacaMatriks();

        System.out.println("Isi matriks: ");
        M.TulisMatriks();
        System.out.println();

        Matriks M2 = new Matriks();
        M2.CopyMatriks(M, M2);
        M2.TulisMatriks();
        System.out.println();

        M.TransposeMatriks();
        M.TulisMatriks();


        // System.out.println("Isi matriks transpose: ");
        // int i, j;
        
        // for (i=0; i<4; i++) {
        //     for (j=0; j<3; j++) {
        //         System.out.print(M2[i][j] + " ");
        //     }
        //     System.out.println();
        // }
    }
}