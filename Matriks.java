import java.util.*;
import java.io.*;

class Matriks {
    /* ATRIBUT */
    int RowMin = 1; int ColMin = 1;
    int RowMax = 100; int ColMax = 100;
    int IdxUndef = 9999;
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

    // void Simpan() {
    //     Scanner baca = new Scanner(System.in);
    //     System.out.print("Masukkan nama file: ");
    //     String namafile = baca.next(); System.out.println();
    //     Matriks hasil = this;

    //     File simpanfile = new File (namafile);

    //     while (simpanfile.exists()) {
    //         System.out.print("File sudah ada. Masukkan nama file yang lain: ");
    //         namafile = baca.next(); System.out.println();
    //         simpanfile = new File (namafile);
    //     }

    //     simpanfile.createNewFile();

    //     FileWriter fw = new FileWriter(simpanfile.getAbsoluteFile());
    //     BufferedWriter bw = new BufferedWriter(fw);
    //     bw.write(hasil);
    //     bw.close();
    // }
    
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

    void DeterminanMetodeOBE() {
        float pivot;
        int i, j, pivotidx;

        // Algorithm
        //Pengecekkan sebelum OBE
        for (i = RowMin; i <= this.NRowEff; i++) {
            if (isPivotExist(i)) {
                pivotidx = this.PivotColIdx(i);
                pivot = this.SearchLeading(i);
                for (j = i+1; j <= this.NRowEff; j++) {
                    if (this.Mat[j][pivotidx] != 0) {
                        float scale = this.Mat[j][pivotidx] / pivot;
                        this.interchangeRow(j, scale, i);
                    }
                    this.Mat[j][pivotidx] = 0;      
                }
            }
        }
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

    float Crammer(Matriks MHasil, int ColC) {
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

     //==========check if matrix is a square matrix================
    boolean isSquare(){
        return (this.NRowEff == this.NColEff);
    }

    boolean isPivotExist(int row) {
        // RETURN TRUE JIKA ADA PIVOT DI BARIS row
        boolean found = false;
        int j = ColMin; 
        while (j <= this.NColEff && !found) {
            if (this.Mat[row][j] != 0) {
                found = true;
            }
            else {
                j++;
            }
        }
        return found;
    }

    int PivotColIdx(int row) {
        // RETURN INDEX PIVOT 
        int j = ColMin; 
        while (j <= this.NColEff) {
            if (this.Mat[row][j] != 0) {
                return j;
            }
            else {
                j++;
            }
        }
        return IdxUndef;
    }

    float SearchLeading(int row) {
        int j = ColMin;
        while (j <= this.NColEff) {
            if (this.Mat[row][j] != 0) {
                return this.Mat[row][j];
            } else {
                j++;
            }
        }
        return 0;
    }

    //============= membagi baris i dengan scale==================
    void scaleRow(int i,float scale){
        for(int j = ColMin; j <= this.NColEff; j++) {
            this.Mat[i][j] = this.Mat[i][j]/scale;
            RoundToZero(i, j);
        }
    }

    float RoundToZero(int i, int j) {
        if (this.Mat[i][j] == -0){
            this.Mat[i][j] = 0;
        }
        if (this.Mat[i][j] < 0 && this.Mat[i][j] > -0.00001) { 
            // PERLU TANYA SAMPAI 0.0000 BERAPA ANGKA DIANGGAP 0
            this.Mat[i][j] = 0;
        } 

        return this.Mat[i][j];
    }

    void interchangeRow(int row, float scale, int PivotRow) {
        // row = row - scale*PivotRow
        for (int j = ColMin; j <= this.NColEff; j++) {
            this.Mat[row][j] -= (scale * this.Mat[PivotRow][j]);
        }
    }

    void sortLeading() {
        for (int i = RowMin; i <= this.NRowEff; i++) {
            for (int j = i+1; j <= this.NRowEff; j++) {
                if (this.PivotColIdx(i) > this.PivotColIdx(j)) {
                    swapRow(i, j);
                } 
            }

        }
    }

    //==============Gauss to convert to Echelon Form ============
    void EchelonForm() {
        //Kamus
        float pivot;
        int i, j, pivotidx;

        // Algorithm
        //Pengecekkan sebelum OBE
        for (i = RowMin; i <= this.NRowEff; i++) {
            if (isPivotExist(i)) {
                pivotidx = this.PivotColIdx(i);
                pivot = this.SearchLeading(i);
                for (j = i+1; j <= this.NRowEff; j++) {
                    if (this.Mat[j][pivotidx] != 0) {
                        float scale = this.Mat[j][pivotidx] / pivot;
                        this.interchangeRow(j, scale, i);
                    }
                    this.Mat[j][pivotidx] = 0;      
                }
            }
        }

        //bagi setiap baris dengan leading element pada setiap baris sehingga jadi leading 1
        i = RowMin;
        while (i <= this.NRowEff) {
            if (this.PivotColIdx(i) != IdxUndef) {
                scaleRow(i, this.SearchLeading(i));
            }
            i++;
        }
        this.sortLeading();
    }

    void ReducedEchelonForm() {
        this.EchelonForm();

        //Kamus
        float pivot;
        int i, j, pivotidx;

        // Algorithm
        //Pengecekkan sebelum OBE
        for (i = this.NRowEff; i >= RowMin; i--) {
            if (isPivotExist(i)) {
                pivotidx = this.PivotColIdx(i);
                pivot = this.SearchLeading(i);
                for (j = i-1; j >= ColMin; j--) {
                    if (this.Mat[j][pivotidx] != 0) {
                        float scale = this.Mat[j][pivotidx] / pivot;
                        this.interchangeRow(j, scale, i);
                    }   
                    RoundToZero(j, pivotidx);
                }
            }
        }
    }

    // int[] FreeVarIdx() {
    //     // RETURN IDX UNDEF JIKA TIDAK DITEMUKAN

    // }

    boolean isNoSolution() {
        boolean pass = false;
        boolean found = false;
        for (int i = RowMin; i <= this.NRowEff; i++) {
            int j = ColMin;
            while (j <= this.NColEff-1 && !pass) {
                if (this.Mat[i][j] != 0) {
                    pass = true;
                }
            }
            if (!pass) {
                if (this.Mat[i][this.NColEff] != 0) {
                    found = true;
                }
            }
        }

        return found;
    }

    boolean isManySolution() {
        boolean pass = false;
        boolean found = false;
        for (int i = RowMin; i <= this.NRowEff; i++) {
            int j = ColMin;
            while (j <= this.NColEff-1 && !pass) {
                if (this.Mat[i][j] != 0) {
                    pass = true;
                }
            }
            if (!pass) {
                if (this.Mat[i][this.NColEff] == 0) {
                    found = true;
                }
            }
        }

        return found;
    }

    // =======================SPL=========================
    void TulisSPL (int cara){
        switch (cara) {
            case 1 :  
                System.out.println("Solusi SPL Menggunakan Metode Eliminasi Gauss");
                // this.metodeGauss();
                break;
            case 2:
                System.out.println("Solusi SPL Menggunakan Metode eliminasi Gauss-Jordan");
                this.metodeGaussJordan();
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
        this.ReducedEchelonForm();
        if (this.isNoSolution()) {
            System.out.println("SPL tidak memiliki solusi.");
        } else {
            if (this.isManySolution()) {

            } else {
                for (int i = RowMin; i <= this.NRowEff; i++) {
                    for (int j = ColMin; j <= this.NColEff-1; j++) {
                        if (this.Mat[i][j] != 0) {
                            System.out.println("x"+i+" = "+this.Mat[i][this.NColEff]);
                        }
                    }
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