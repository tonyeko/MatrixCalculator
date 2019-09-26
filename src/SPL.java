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
                System.out.println("Solusi SPL Menggunakan Metode Eliminasi Gauss:");
                // this.metodeGauss();
                break;
            case 2:
                System.out.println("Solusi SPL Menggunakan Metode eliminasi Gauss-Jordan:");
                this.metodeGaussJordan();
                break;   
            case 3:
                System.out.println("Solusi SPL Menggunakan Metode Matriks Balikan:");
                this.metodeMatriksBalikan();
                break;   
            case 4:
                System.out.println("Solusi SPL Menggunakan Kaidah Crammer:");
                this.metodeCrammer();
                break;   
        }
    }

    
    float[] SolusiMetodeGauss()
    // Matriks harus sudah dalam keadaan Echelon Form
    {
        int i,j,k;
        Matriks MI;
        MI = this.M;

        float[] floatArray = new float [MI.NRowEff+1];
        float[] arr = floatArray;
        
        
        for (i=MI.NRowEff;i>=1;i--)
        // System.out.println(i);
        {
            for (j=MI.NColEff-1;j>=1;j--)
            {
                if (i == MI.NRowEff && (MI.Mat[i][j] == 1))
                // CARI PIVOT DI INDEX TERAKHIR
                {
                    arr[i] = MI.Mat[i][MI.NColEff];
                }
                else if (i != MI.NRowEff && (MI.Mat[i][j] == 1))
                {
                    k = j+1;
                
                    arr[i] = MI.Mat[i][MI.NColEff];
                    while (k < MI.NColEff)
                    {
                        arr[i] = arr[i] - (MI.Mat[i][k] * arr[k]);
                        k++;
                
                    }
                }
            }
        }
        return arr;
    }

    
//     void metodeGauss() {
//         float[] SolusiGauss;

//         this.M.EchelonForm();
//         if (this.M.isNoSolution()) {
//             System.out.println("SPL tidak memiliki solusi.");
//         } else {
// `           if (this.M.isManySolution()) {
//                 SolusiGauss = this.SolusiMetodeGauss();
//                 System.out.println(SolusiGauss.length);
//                 for (int i = 1; i <= SolusiGauss.length; i++) {
//                     System.out.println(i);
//                     System.out.println("x"+i+" = "+SolusiGauss[i]);
//             } else {
                
//             }
// }
//         }
        
//     }

    void metodeGaussJordan() {
        // ADA PREKONDISI GAK YA?
        Matriks Mref = this.M;
        Mref.ReducedEchelonForm();
        // System.out.println(Mref.isNoSolution());
        if (Mref.isNoSolution()) {
            System.out.println("SPL tidak memiliki solusi.");
        } else {
            if (Mref.isManySolution()) {
                int[] freevarColIdx = new int[Mref.NColEff];
                freevarColIdx[0] = 0;
                for (int i = 1; i <= (Mref.NColEff-1); i++) {
                    int count = 0; // jika count == 0 atau count > 1, maka kolom tersebut free variable
                    boolean Ada1Utama = false;
                    for (int j = 1; j  <= Mref.NRowEff; j++) {
                        if (Mref.Mat[j][i] != 0 && Mref.Mat[j][i] != 1) {
                            count++;
                        }
                        if (Mref.Mat[j][i] == 1 && (!Ada1Utama)) {
                            Ada1Utama = true;
                        }
                    }
                    System.out.println(Ada1Utama);
                    if ((count > 0) || (count == 0 && !Ada1Utama)) {
                        freevarColIdx[i] = 1; // Kolom tersebut merupakan free variable
                    } else {
                        freevarColIdx[i] = 0;
                    }
                }

                // for (int i = 1; i <= freevarColIdx.length-1; i++) {
                //     System.out.print(freevarColIdx[i]+" ");
                // }
                System.out.println();
                Mref = Mref.addNewRow();
                Mref = Mref.sortByVariable();
                Mref.TulisMatriks();
                // Iterasi untuk print
                String hasil = "";
                for (int i = 1; i <= Mref.NRowEff; i++) {
                    boolean AdaKonstanta = false;
                    // hasil.concat("x"+i+" = "+" ");
                    hasil += "x"+i+" = "+" ";
                    // System.out.println(hasil);
                    if (Mref.Mat[i][Mref.NColEff] != 0) {
                        // hasil.concat(Mref.NColEff + " ");
                        hasil += Mref.Mat[i][Mref.NColEff] + " ";
                        AdaKonstanta = true;
                    }
                    // Iterasi untuk print 
                    for (int j = 1; j <= (Mref.NColEff-1); j++) {
                        if (freevarColIdx[j] == 1 && Mref.Mat[i][j] != 0 && AdaKonstanta) {
                            if (Mref.Mat[i][j] < 0) {
                                // hasil.concat("+ " + (-1)*Mref.Mat[i][j] + "x"+j + " ");
                                if (Mref.Mat[i][j] != -1) {
                                    hasil += " + " + (-1)*Mref.Mat[i][j] + "x"+j + " ";
                                } else {
                                    hasil += " + x"+j + " ";
                                }
                                
                            } else {
                                // hasil.concat("- " + Mref.Mat[i][j] + "x"+j + " ");
                                if (Mref.Mat[i][j] != 1) {
                                    hasil += " - " + Mref.Mat[i][j] + "x"+j + " ";
                                } else {
                                    hasil += " - x"+j + " ";
                                }
                                // hasil += " - " + Mref.Mat[i][j] + "x"+j + " ";
                            }
                        } else if (freevarColIdx[j] == 1 && Mref.Mat[i][j] != 0 && !AdaKonstanta) {
                            if (Mref.Mat[i][j] < 0) {
                                // hasil.concat("+ " + (-1)*Mref.Mat[i][j] + "x"+j + " ");
                                if (Mref.Mat[i][j] != -1) {
                                    hasil += (-1)*Mref.Mat[i][j] + "x"+j + " ";
                                } else {
                                    hasil += " x"+j + " ";
                                }
                            } else {
                                // hasil.concat("- " + Mref.Mat[i][j] + "x"+j + " ");
                                // hasil += " - " + Mref.Mat[i][j] + "x"+j + " ";
                                if (Mref.Mat[i][j] != 1) {
                                    hasil += " - " + Mref.Mat[i][j] + "x"+j + " ";
                                } else {
                                    hasil += " - x"+j + " ";
                                }
                            }
                        }
                        if (freevarColIdx[j] == 1 && i == j) {
                            // hasil.concat("x"+j);
                            
                            hasil += "x"+j;
                        }
                    }
                    System.out.println(hasil);
                    hasil = "";
                }
            } else {
                for (int i = Mref.RowMin; i <= Mref.NRowEff; i++) {
                    for (int j = Mref.ColMin; j <= Mref.NColEff-1; j++) {
                        if (Mref.Mat[i][j] != 0) {
                            System.out.println("x"+i+" = "+Mref.Mat[i][Mref.NColEff]);
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
                    System.out.printf("x%d = %.4f\n", i, X.Mat[i][this.ColMin]);
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