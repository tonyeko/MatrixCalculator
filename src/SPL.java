package src;
import java.util.*;
import java.io.*;

class SPL extends Matriks {

    Matriks M = new Matriks();
    Scanner baca = new Scanner(System.in);

    SPL() { super(); }

    // =======================SPL=========================
    void TulisSPL (int cara){
        switch (cara) {
            case 1 :  
                System.out.println("Solusi SPL Menggunakan Metode Eliminasi Gauss");
                // this.metodeGauss();
                break;
            case 2:
                System.out.println("Solusi SPL Menggunakan Metode eliminasi Gauss-Jordan");
                // this.metodeGaussJordan();
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

    


    
    // void metodeGauss() {

    // }



    void metodeGaussJordan() {
        // ADA PREKONDISI GAK YA?
        this.M.ReducedEchelonForm();
        if (this.M.isNoSolution()) {
            System.out.println("SPL tidak memiliki solusi.");
        } else {
            if (this.M.isManySolution()) {

            } else {
                for (int i = this.M.RowMin; i <= this.M.NRowEff; i++) {
                    for (int j = this.M.ColMin; j <= this.M.NColEff-1; j++) {
                        if (this.M.Mat[i][j] != 0) {
                            System.out.println("x"+i+" = "+this.M.Mat[i][this.M.NColEff]);
                        }
                    }
                }
            }
        }
    }


    void metodeMatriksBalikan() {
        //x = Ainv B
        Matriks A = this.M.GetCoef();
        Matriks B = this.M.GetConstant();
        if (A.isSquare()) {
            float det = A.Determinan();
            if (det != 0) {
                Matriks Ainv = A.InversMetodeKofaktor();
                Matriks X = Ainv.KaliMatriks(B);
                for (int i = this.RowMin; i <= X.NRowEff; i++) {
                    System.out.println("x"+i+" = "+ X.Mat[i][this.ColMin]);
                }
            } else {
                System.out.println("Determinan bernilai 0. Gunakan metode lain untuk mencari solusi SPL");
            }
        } else {
            System.out.println("Bukan matriks persegi, matriks tidak memiliki invers. Gunakan metode lain.");
        }
    }

    void metodeCrammer(){
        if (this.M.GetCoef().isSquare()){
            if (this.M.GetCoef().Determinan()!=0){
                for (int j=this.ColMin;j<=this.GetCoef().NColEff;j++){
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