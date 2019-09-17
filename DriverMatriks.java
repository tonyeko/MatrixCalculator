import java.security.Principal;

class DriverMatriks {
    public static void main(String[] args) {
        // Scanner scan = new Scanner(System.in);
        
        Matriks M = new Matriks();

        M.BacaMatriks();

        System.out.println("Isi matriks: ");
        M.TulisMatriks();
        System.out.println();

        // int[][] M2;
        // M2 = new int[4][3];
        M = M.Transpose();
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