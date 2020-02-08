package src;
import java.util.*;
import java.io.*;

class SPL extends Matriks {

    Matriks M = new Matriks();
    Scanner baca = new Scanner(System.in);
    int ValUndef = 999999;

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

    
    // float[] SolusiBanyakMetodeGauss()
    // // Matriks harus sudah dalam keadaan Echelon Form
    // {
    //     int i,j,k;
    //     Matriks MI;
    //     MI = this.M;

    //     float[] floatArray = new float [MI.NRowEff-1];
    //     float[] arr = floatArray;
        
        
    //     for (i=MI.NRowEff;i>=1;i--)
    //     System.out.println(i);
    //     {
    //         for (j=MI.NColEff-1;j>=1;j--)
    //         {
    //             if (i == MI.NRowEff && (MI.Mat[i][j] == 1))
    //             // CARI PIVOT DI INDEX TERAKHIR
    //             {
    //                 arr[i] = MI.Mat[i][MI.NColEff];
    //             }
    //             else if (i != MI.NRowEff && (MI.Mat[i][j] == 1))
    //             {
    //                 k = j+1;
                
    //                 arr[i] = MI.Mat[i][MI.NColEff];
    //                 while (k < MI.NColEff && k > j)
    //                 {
    //                     arr[i] = arr[i] - (MI.Mat[i][k] * arr[k]);
    //                     k++;
                
    //                 }
    //             }
    //         }
    //     }
    //     return arr;
    // }

    
    // void metodeGauss() {
    //     float[] SolusiGauss;

    //     this.M.EchelonForm();
    //     if (this.M.isNoSolution()) {
    //         System.out.println("SPL tidak memiliki solusi.");
    //     } else {
    //         SolusiGauss = this.SolusiBanyakMetodeGauss();
    //         System.out.println("MASUKK");
    //         System.out.println(SolusiGauss.length);
    //         for (int i = 1; i <= SolusiGauss.length; i++) {
    //             System.out.println(i);
    //             System.out.println(SolusiGauss[i]);
    //         }
    //     }
        
    // }





    void metodeGaussJordan() {
        // ADA PREKONDISI GAK YA?
        this.M.ReducedEchelonForm();
        if (this.M.isNoSolution()) {
            System.out.println("SPL tidak memiliki solusi.");
        } else {
            if (this.M.isManySolution()) {
                float[] freevarColIdx = new float[this.M.NColEff-1];
                for (int i = this.M.RowMin; i <= this.M.NRowEff; i++) {
                    int PivotIdx = this.M.PivotColIdx(i);
                    // ITERASI DARI BARIS SETELAH PivotIdx SAMPAI NColEff-1
                    // untuk mencari free variable ada di indeks ke berapa
                    for (int j = PivotIdx+1; i <= this.M.NColEff-1; j++) {
                        if (this.M.Mat[i][j] != 0) {
                            freevarColIdx[j] = 1;
                        }
                    }
                    // Iterasi 


                }
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

    boolean crammerIsInconsistent(Matriks A) { 
        //Prekondisi : Matriks A matriks augmented dan Matriks koefisien A adalah bujur sangkar, Determinant Matriks A =0
        // Xi = Dxi/D
        //SPL tidak konsisten apabila D=0  dan salah satu atau lebih dari Dxi !=0
        for (int i=1;i<=A.GetCoef().NColEff;i++){
            if (A.GetCoef().Crammer(A,i) != 0){
                return true;
            }
        }
        return false;
    }

    void metodeCrammer() {
        //Prekondisi: Matriks A adalah matriks augmented dengan Matriks koefisiennya adalah matriks bujur sangkar
        Matriks A = this.M; 
        if (A.GetCoef().DeterminanMetodeOBE() != 0){
            for (int j=ColMin;j<=A.GetCoef().NColEff;j++){
                float solution = A.GetCoef().Crammer(A.GetConstant(),j);
                // System.out.println("x"+j+ " = " +solution);
                System.out.printf("x%d = %.4f\n", j, solution);
            }
        } else { //Determinan == 0
            if (crammerIsInconsistent(A)){
                System.out.println("SPL tidak konsisten");
            } else {
                System.out.println("SPL solusi banyak");
            }
        } 
    }

    

}