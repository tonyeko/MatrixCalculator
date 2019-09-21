import java.util.*;
import java.io.*;

class Matriks {
    /* ATRIBUT */
    int RowMin = 1; int ColMin = 1;
    int RowMax = 100; int ColMax = 100;
    int NRowEff, NColEff;
    float[][] Mat = new float[RowMax+1][ColMax+1];

    /* METHOD */
    /** Konstruktor **/
    Matriks() {
        this.NColEff = 0;
        this.NRowEff = 0;
        for (int i = 0; i <= this.RowMax; i++) {
            for(int j = 0; j<= this.ColMax; j++) {
                this.Mat[i][j] = 0;
            }
        }
    }

    int NbElmt(Matriks M) {
        return this.NColEff * this.NRowEff;
    }

    void BacaMatriks() {
        Scanner baca = new Scanner(System.in);
        System.out.print("Baris: "); this.NRowEff = baca.nextInt();
        System.out.print("Kolom: "); this.NColEff = baca.nextInt();
        for (int i = 1; i <= this.NRowEff; i++) {
            for (int j = 1; j <= this.NColEff; j++) {
                this.Mat[i][j] = baca.nextFloat();
            }
        }  
    }

    void TulisMatriks() {
        for (int i = 1; i <= this.NRowEff; i++) {
            for (int j = 1; j <= this.NColEff; j++) {
                System.out.printf("%.3f\t", this.Mat[i][j]);
            }
            System.out.println();
        }
    }

    Matriks KaliMatriks(Matriks M2) {
        Matriks MRes = new Matriks();
        int i, j, k;

        MRes.NRowEff = this.NRowEff;
        MRes.NColEff = M2.NColEff;
        for (i = RowMin; i <= this.NRowEff; i++) {
            for (j = ColMin; j <= M2.NColEff; j++) {
                for (k = ColMin; k <= this.NColEff; k++) {
                    MRes.Mat[i][j] += this.Mat[i][k] * M2.Mat[k][j];
                }
            }
        }
        return MRes;
    }

    void BacaFileMatriks(String filematriks) throws FileNotFoundException {
        int NRow = 0; int NCol = 0;
        File bacafile = new File (filematriks);
        Scanner scanBaris = new Scanner(bacafile);

        while (scanBaris.hasNextLine()) {
            NRow++;
            NCol = 0;
            Scanner scanNumber = new Scanner(scanBaris.nextLine());
            while (scanNumber.hasNextFloat()) {
                NCol++;
                if (scanNumber.hasNextFloat()) {
                    this.Mat[NRow][NCol] = scanNumber.nextFloat();
                }
             
            }
        }
        this.NRowEff = NRow;
        this.NColEff = NCol;
    }
    
    

    

    

    // float Elmt(Matriks M, int i, int j) {
    //     return M.Mat[i][j];
    // }
    /*
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
    }
    */
    float[][] Transpose(float[][] Mat, int NRowEff, int NColEff) {
        int i, j;
        float[][] M_transpose = new float[RowMax+1][ColMax+1];        ;
        for (i = 1; i <= this.NRowEff; i++) {
            for (j = 1; j <= this.NColEff; j++) {
                M_transpose[j][i] = Mat[i][j];
            }
        }

        return M_transpose;
    }

    void TransposeMatriks() {
        int temp;

        this.Mat = this.Transpose(this.Mat, this.NRowEff, this.NColEff);
        temp = this.NColEff;
        this.NColEff = this.NRowEff;
        this.NRowEff = temp;
    }

    void CopyMatriks(Matriks Min) {
        int i, j;

        this.NRowEff = Min.NRowEff;
        this.NColEff = Min.NColEff;
        for (i = RowMin; i <= this.NRowEff; i++) {
            for (j = ColMin; j <= this.NColEff; j++) {
                this.Mat[i][j] = Min.Mat[i][j];
            }
        }
    }

    void swapRow(int i, int j) { 
        float[] temp = this.Mat[i];
        this.Mat[i] = this.Mat[j];
        this.Mat[j] = temp;
    }

    float Determinan() {
        Matriks Kofaktor = new Matriks();
        int i;
        float det = 0;
        int Sign = 1;
        
        if (NbElmt(this) == 1) {
            return this.Mat[RowMin][ColMin];
        } else if (NbElmt(this) == 2) {
            return this.Mat[RowMin][ColMin]*this.Mat[RowMin+1][ColMin+1] - this.Mat[RowMin+1][ColMin]*this.Mat[RowMin][ColMin+1];
        } else {
            for (i = ColMin; i <= this.NColEff; i++) {
                Kofaktor = Kofaktor(RowMin, i);
                det += Sign * this.Mat[RowMin][i] * Kofaktor.Determinan();
                Sign *= -1;
            }
        }
        return det;   
    }

    Matriks Kofaktor(int a, int b) {
        Matriks Cof = new Matriks();
        int i, j, c1, c2;

        Cof.NRowEff = this.NRowEff-1;
        Cof.NColEff = this.NRowEff-1;
        c1 = this.RowMin;
        /* i ITERASI UNTUK MENGULANG ROW */
        for (i = this.RowMin; i <= this.NRowEff; i++) {
            c2 = this.ColMin;
            if (i != a) {
                /* j ITERASI UNTUK MENGULANG COL */
                for (j = this.ColMin; j <= this.NColEff; j++) {
                    if (j != b) {
                        Cof.Mat[c1][c2] = this.Mat[i][j];
                        c2++;
                    }
                }
                c1++;
            }
        }
        return Cof;
    }

    float Cramer(Matriks MHasil, int ColC) {
        Matriks Pembilang = new Matriks();
        int i;

        Pembilang.NRowEff = this.NRowEff;
        Pembilang.NColEff = this.NColEff;

        Pembilang.CopyMatriks(this);

        for (i = RowMin; i <= this.NRowEff; i++) {
            Pembilang.Mat[i][ColC] = MHasil.Mat[i][ColMin];
        }

        return Pembilang.Determinan()/this.Determinan();
    }

    Matriks MatriksCofactor() {
        Matriks MC = new Matriks();
        int i, j;
        int Sign = 1;

        MC.NRowEff = this.NRowEff;
        MC.NColEff = this.NColEff;
        for (i = RowMin; i <= this.NRowEff; i++) {
            for (j = ColMin; j <= this.NColEff ; j++) {
                MC.Mat[i][j] = Sign * this.Kofaktor(i, j).Determinan();
                if (MC.Mat[i][j] == -0) {
                    MC.Mat[i][j] *= -1;
                }
                Sign *= -1;
            }
        }
        return MC;
    }

    Matriks MatriksAdjoint() {
        Matriks adj = new Matriks();
        adj.NRowEff = this.NRowEff;
        adj.NColEff = this.NColEff;
        adj = this.MatriksCofactor();
        adj.TransposeMatriks();
        
        return adj;
    }

    Matriks MatriksInvers() {
        Matriks MInvers = new Matriks();
        Matriks Madj = new Matriks();
        int i, j;
        float det;
        
        MInvers.NRowEff = this.NRowEff; 
        MInvers.NColEff = this.NColEff; 
        det = this.Determinan();
        Madj = this.MatriksAdjoint();

        for (i = RowMin; i <= this.NRowEff; i++) {
            for (j = ColMin; j <= this.NColEff ; j++) {
                MInvers.Mat[i][j] = (1/det) * (Madj.Mat[i][j]); 
                if (MInvers.Mat[i][j] == -0) {
                    MInvers.Mat[i][j] *= -1;
                }
            }
        }
        return MInvers;
    }

    Matriks MakeAugmented(Matriks M2) {
        int i, j;
        Matriks Aug = new Matriks();
        Aug.NRowEff = this.NRowEff;
        Aug.NColEff = this.NColEff + M2.NColEff;

        for (i = RowMin; i <= Aug.NRowEff; i++) {
            for (j = ColMin; j <= Aug.NColEff; j++) {
                if (j <= this.NColEff) {
                    Aug.Mat[i][j] = this.Mat[i][j];
                } else {
                    Aug.Mat[i][j] = M2.Mat[i][j-this.NColEff];
                }
            }
        }

        return Aug;
    }

    Matriks GetCoef() {
        Matriks MCoef = new Matriks();
        int i, j;

        MCoef.NRowEff = this.NRowEff;
        MCoef.NColEff = this.NColEff-1;

        for (i = RowMin; i <= MCoef.NRowEff; i++) {
            for (j = ColMin; j <= MCoef.NColEff; j++) {
                MCoef.Mat[i][j] = this.Mat[i][j];
            }
        }

        return MCoef;

    }

    Matriks GetConstant() {
        Matriks MConst = new Matriks();
        int i;

        MConst.NRowEff = this.NRowEff;
        MConst.NColEff = ColMin;

        for (i = RowMin; i <= MConst.NRowEff; i++) {
            MConst.Mat[i][ColMin] = this.Mat[i][this.NColEff];
        }

        return MConst;
    }

    Matriks MakeIdentity() {
        Matriks Identity = new Matriks();
        int i, j;

        Identity.NRowEff = this.NRowEff;
        Identity.NColEff = this.NColEff;
        for (i = this.RowMin; i <= this.NRowEff; i++) {
            for (j = this.ColMin; j <= this.NColEff; j++) {
                if (i == j) {
                    Identity.Mat[i][j] = 1;
                } else {
                    Identity.Mat[i][j] = 0;
                }
            }
        }
        return Identity;
    }

    // Matriks EchelonMatriks() {
    //     Matriks Echelon = new Matriks();
    //     int i, j, k;
    //     float pivot, scale;

    //     Echelon.CopyMatriks(this);
    //     pivot = Echelon.Mat[RowMin][ColMin];
    //     for (j = ColMin; j <= this.NColEff; j++) {
    //         Echelon.Mat[RowMin][j] /= pivot;       
    //     }

    //     for (i = RowMin; i <= this.NRowEff; i++) {
    //         for (j = RowMin+1; j <= this.NRowEff; j++) {
    //             scale = Echelon.Mat[j][j];
    //             for (k = ColMin; k <= this.NColEff; k++) {
    //                 Echelon.Mat[j][k] = Echelon.Mat[j][k] - scale*Echelon.Mat[i][k];
    //             }
    //         }
    //     }
    //     return Echelon;
    // }
    

     //==========check if matrix is a square matrix================
    boolean isSquare(){
        return (this.NRowEff == this.NColEff);
    }

    boolean isPivotExist(int col) {
        boolean found = false;
        int i = col; int j = col;
        while (i <= this.NRowEff && !found) {
            if (this.Mat[i][j] != 0) {
                found = true;
            }
            else {
                i++;
            }
        }
        return found;
    }

    //===========Jika element diagonal baris ada yang nol, swap baris
    void SwapPivot(int row) {
        // Untuk 
        //Kamus
        int targetRow;
        int temp_size;
        // int countSwap =0;

        //Algoritma
        if(this.NRowEff>=this.NColEff){
            temp_size = this.NColEff;
        } else { //NRowEff < NColEff
            temp_size = this.NColEff;
        }

        for (int i=row;i<=temp_size;i++){
            if (this.Mat[i][i] == 0){
                targetRow = i+1;
                if (targetRow <= temp_size){
                    do{
                         if (this.Mat[targetRow][targetRow-(i-row)] != 0){ //prevent kasus index out of range untuk 2 baris terakhir
                            swapRow(i,targetRow);
                         }
                        // countSwap +=1;
                        targetRow +=1;
                    } while (this.Mat[i][i]==0 && targetRow<=this.NRowEff);
                }
            }
        }
    }   

    float SearchLeading(int row) {
        int j = ColMin;
        while (j <= this.NColEff) {
            if (this.Mat[row][j] != 0) {
                return this.Mat[row][j];
            } else {
                j++
            }
        }
        return 0;
    }

    //============= membagi baris i dengan scale==================
    void scaleRow(int i,float scale){
        for(int j = ColMin; j <= this.NColEff; j++) {
            this.Mat[i][j] = this.Mat[i][j]/scale;
            if (this.Mat[i][j] == -0){
                this.Mat[i][j] = 0;
            }
            if (this.Mat[i][j] < 0 && this.Mat[i][j] > -0.00001) { 
                // PERLU TANYA SAMPAI 0.0000 BERAPA ANGKA DIANGGAP 0
                this.Mat[i][j] = 0;
            }   
        }
    }

    void interchangeRow(int row, float scale, int Pivotrow) {
        // row = row - scale*Pivotrow
        for (int j = ColMin; j <= this.NColEff; j++) {
            this.Mat[row][j] -= (scale * this.Mat[Pivotrow][j]);
        }
    }

    void SortMatrix() {
        
    }

    //==============Gauss to convert to Echelon Form ============
    void EchelonForm(){
        //Kamus
        // int det = 1;
        boolean foundlead=false;
        float pivot;
        int i,j;

        //Algorithm
        //Pengecekkan sebelum OBE
        for (i = RowMin; i <= this.NRowEff; i++) {
            pivot = SearchLeading(i);
            if (pivot == 0) {
                if (isPivotExist(i)) {
                    SwapPivot(i);

                    pivot = this.Mat[i][i];
                    for (j = i+1; j <= this.NRowEff; j++) {
                        float scale = this.Mat[j][i] / pivot;
                        this.interchangeRow(j, scale, i);
                        TulisMatriks();
                        System.out.println("-----------");                 
                    }
                }
            } else {
                pivot = this.Mat[i][i];
                for (j = i+1; j <= this.NRowEff; j++) {
                    float scale = this.Mat[j][i] / pivot;
                    this.interchangeRow(j, scale, i);
                    TulisMatriks();
                    System.out.println("-----------");                 
                }
                System.out.println("-----------");
            }
        }
            // 
            pivot = this.Mat[i][i];
            for (j = i+1; j <= this.NRowEff; j++) {
                if (this.Mat[i][i] != 0) {
                    float scale = this.Mat[j][i] / pivot;
                    this.interchangeRow(j, scale, i);
                    TulisMatriks();
                    System.out.println("-----------");                 
                }
            }
            System.out.println("-----------");
        }
        
        //bagi setiap baris dengan leading element pada setiap baris sehingga jadi leading 1
        i = RowMin;
        j = ColMin;
        while (i<=this.NRowEff) {
            foundlead = false;
            // Mencari Leading Coeficient
            while ((j<=this.NColEff) && (!foundlead)) {
                if (this.Mat[i][j] != 0){
                    foundlead = true;
                } else {
                    j++;
                }
            }
            // Ditemukan Leading Coeficient atau j > NColEff,
            // Jika j > NColEff, maka Leading Coef tidak ditemukan, tidak dilakukan pembagian terhadap 0
            if (j <= this.NColEff) {
                scaleRow(i, this.Mat[i][j]);
            }
            i++;
        }

        j=1;
        // boolean cek=true;//anggapan semua elemen baris NRowEff-1 bernilai 0 
        // while ((j<=this.NColEff-1)&&(cek)){
        //     if (this.Mat[this.NRowEff-1][j]!=0){
        //         cek = false;
        //     }
        //     j++;
        // }
        // if (cek){
        //     this.Mat[NRowEff][NColEff] = 0;
        // }

        // untuk mengatasi -0 karena digit desimal yang sangat kecil misal 1.73396E-6
        // for (i=RowMin; i<=this.NColEff; i++) {
        //     for (j=ColMin; j<=this.NRowEff; j++) {
        //         if (this.Mat[i][j] < 0 && this.Mat[i][j] > -0.00000000001) {
        //             this.Mat[i][j] = 0;
        //         }   
        //     }
        // }
    }

    // void ReducedEchelonForm() {
    //     this.EchelonForm();

    //     /* KAMUS LOKAL */
    //     boolean foundlead=false;
    //     float valUndef= -99999;
    //     int i,j;

    //     SwapZeroDiag(this.NRowEff);  //Pengecekkan sebelum OBE
    //     for (i = this.NRowEff; i >= RowMin; i--){
    //         SwapZeroDiag(i);
    //         for (j=i-1;j>=ColMin;j--){
    //             if (this.Mat[i][i] != 0){
    //                 float scale = this.Mat[j][i] / this.Mat[i][i];
    //                 for (int k=this.NColEff;k>=ColMin;k--){
    //                     this.Mat[j][k]=this.Mat[j][k] -(scale*this.Mat[i][k]);   
    //                 }
    //             } 
    //         }
    //     }

    //     //bagi setiap baris dengan leading element pada setiap baris sehingga jadi leading 1
    //     j =1;
    //     i =1;
    //     while (i<=this.NRowEff){
    //         foundlead = false;
    //         // Mencari Leading Coeficient
    //         while ((j <= this.NColEff) && (!foundlead)) {
    //             if (this.Mat[i][j] != 0){
    //                 foundlead = true;
    //             } else {
    //                 j +=1;
    //             }
    //             scaleRow(i, this.Mat[i][j]);
    //         }
    //         // scaleRow(i, this.Mat[i][j]);
    //         i +=1;
    //     }

    // }


}