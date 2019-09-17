import java.util.Scanner;

public class matriks{
	public static void main (String[]args) {
        
        //input ukuran
        Scanner input = new  Scanner(System.in);
        
		int row = input.nextInt() ;
        int col  = input.nextInt();
        row +=1;
        col +=1;

        float [][] matrix = {   {0,0,0,0,0},
                                {0,1,2,3,5},
                                {0,1,4,6,5},
                                {0,7,8,9,5},
                                {0,10,11,12,13}                                  
                            };
		// float [][] matrix = new float [row][col];
        
        // //input elemen
		// for (int i=1;i<row;i++) {
		// 	for (int j=1;j<col;j++) {
		// 		matrix[i][j] =input.nextFloat();
		// 	}
        // }
        
        // //print matriks
		// for (int i=1;i<row;i++) {
		// 	for (int j=1;j<col;j++) {
		// 		System.out.println(matrix[i][j]);
        //     }
        // }

        //Algoritma Gauss
        //jika matrix[1][1]=0 tukar baris
        //jika nggak 0
        for (int i=1;i<row-1;i++){
            for(int j=1;j<col;j++){
                for(int k=i+1;k<row;k++){
                    matrix[k][j] = matrix[k][j] - (matrix[k][i]/matrix[i][i])*matrix[i][j];
                } 
            }
        }

        for (int i=1;i<row;i++) {
            	for (int j=1;j<col;j++) {
            		System.out.println(matrix[i][j]);
                }
            }

<<<<<<< Updated upstream
    }
=======
    //===========Swap row i with row j==============
    void swapRow(int i, int j){ 
        float [] temp = this.Mat[i];
        this.Mat[i]        = this.Mat[j];
        this.Mat[j]        = temp;
    }
    
    

    // float Determinan(Matriks M) {
        
    // }
>>>>>>> Stashed changes
}