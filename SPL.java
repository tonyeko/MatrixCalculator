import java.util.*;
import java.io.*;

class SPL {
    
    SPL() {}

    void TulisSPL (int cara){
        switch (cara) {
            case 1 :  
                System.out.println("Solusi SPL Menggunakan Metode Eliminasi Gauss");
            
                break;
            case 2:
                System.out.println("Solusi SPL Menggunakan Metode eliminasi Gauss-Jordan");
                break;   
            case 3:
                System.out.println("Solusi SPL Menggunakan Metode Matriks Balikan");
                this.metodeMatriksBalikan();
                break;   
            case 4:
                System.out.println("Solusi SPL Menggunakan Kaidah Crammer");
                this.metodeCrammer();
                break;   
        }
    }

    void metodeGauss() {

    }

    void metodeGaussJordan() {
        // ADA PREKONDISI GAK YA?
        Matriks MHasil = this.ReducedEchelonForm();

        for (i = RowMin; i <= MHasil.NRowEff; i++) {
            for (j = ColMin; j <= MHasil.NColEff-1; j++) {
                if (MHasil.Mat[i][j] != 0) {
                    System.out.println("x"+i+" = "+MHasil.[i][MHasil.NColEff]);
                }
            }
        } 


    }


    void metodeMatriksBalikan() {
        //x = Ainv B
        Matriks A = this.GetCoef();
        Matriks B = this.GetConstant();
        if (A.isSquare()) {
            float det = A.Determinan();
            if (det != 0) {
                Matriks Ainv = A.MatriksInvers();
                Matriks X = Ainv.KaliMatriks(B);
                for (int i = RowMin; i <= X.NRowEff; i++) {
                    System.out.println("x"+i+" = "+ X.Mat[i][ColMin]);
                }
            } else {
                System.out.println("Determinan bernilai 0. Gunakan metode lain untuk mencari solusi SPL");
            }
        } else {
            System.out.println("Bukan matriks persegi, matriks tidak memiliki invers. Gunakan metode lain.");
        }
    }

    void metodeCrammer(){
        if (this.GetCoef().isSquare()){
            if (this.GetCoef().Determinan()!=0){
                for (int j=ColMin;j<=this.GetCoef().NColEff;j++){
                    float solution = this.GetCoef().Crammer(this.GetConstant(),j);
                    System.out.println("x"+j+ " = " +solution);
                }
            } else{ //determinant ==0
                System.out.println("Determinan bernilai 0. Gunakan metode lain untuk mencari solusi SPL");
            } 
        } else {
            //Force to Square by concatenating 0 in elemen
        }
        
    }

    

}