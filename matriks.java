import java.util.Scanner;

class Matriks {
    int MaxRow = 10;
    int MaxCol = 10;
    float[][] Mat = new float[MaxRow+1][MaxCol+1];

    Matriks() {
        for (int i = 0; i <= MaxRow; i++) {
            for(int j = 0; j<= MaxCol; j++) {
                this.Mat[i][j] = 0;
            }
        }
    }

    void BacaMatriks(int row, int col) {
        Scanner input = new  Scanner(System.in);
        
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                this.Mat[i][j] = input.nextFloat();
            }
        }  
    }

    void TulisMatriks(int row, int col) {
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                System.out.print(this.Mat[i][j]+"\t");
            }
            System.out.println();
        }
    }

    void Gauss(int row, int col) {
        //jika matrix[1][1]=0 tukar baris
        //jika nggak 0
        for (int i=1;i<row-1;i++){
            for(int j=1;j<col;j++){
                for(int k=i+1;k<row;k++){
                    matrix[k][j] = matrix[k][j] - (matrix[k][i]/matrix[i][i])*matrix[i][j];
                } 
            }
        }

    /* 
    void transpose(int N, int M, int Matt[][]) {
        int i, j;

        for (i=0; i<N; i++) {
            for (j=0; j<M; j++) {
                Matt[j][i] = this.Mat[i][j];
            }
        }
    }
    */
}