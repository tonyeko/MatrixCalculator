import java.util.Scanner;
class DriverMatriks {
    public static void main(String[] args) {
        // Scanner scan = new Scanner(System.in);
        
        Matriks M = new Matriks();

        M.BacaMatriks(3,3);

        System.out.println("Isi matriks: ");
        M.TulisMatriks(3, 3);

        // int[][] M2;
        // M2 = new int[4][3];
        // M.transpose(3, 4, M2);

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