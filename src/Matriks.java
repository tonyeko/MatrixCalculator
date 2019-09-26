package src;
import java.util.*;
import java.io.*;

public class Matriks {
    /* ATRIBUT */
    int RowMin = 1; int ColMin = 1;
    int RowMax = 100; int ColMax = 100;
    int IdxUndef = 9999;
    int NRowEff, NColEff;
    float[][] Mat = new float[RowMax+1][ColMax+1];

    /* METHOD */
    /** Konstruktor **/
    public Matriks() {
        this.NColEff = 0;
        this.NRowEff = 0;
        for (int i = 0; i <= this.RowMax; i++) {
            for(int j = 0; j<= this.ColMax; j++) {
                this.Mat[i][j] = 0;
            }
        }
    }

    public int NbElmt(Matriks M) {
        return this.NColEff * this.NRowEff;
    }

    public float RoundingValue(float N, int scale) {
        return (float)(Math.round(N * (Math.pow(10, scale))) / (Math.pow(10, scale)));
    }

    public void BacaMatriks() {
        Scanner baca = new Scanner(System.in);
        System.out.print("Baris: "); this.NRowEff = baca.nextInt();
        System.out.print("Kolom: "); this.NColEff = baca.nextInt();
        for (int i = 1; i <= this.NRowEff; i++) {
            for (int j = 1; j <= this.NColEff; j++) {
                this.Mat[i][j] = baca.nextFloat();
            }
        }  
    }

    public void TulisMatriks() {
        for (int i = 1; i <= this.NRowEff; i++) {
            for (int j = 1; j <= this.NColEff; j++) {
                System.out.printf("%.4f\t", this.Mat[i][j]);
            }
            System.out.println();
        }
    }

    public Matriks KaliMatriks(Matriks M2) {
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

    Matriks addNewRow() {
        Matriks A = this;
        while (! A.GetCoef().isSquare()){
            A.NRowEff += 1;
            for (int j=1;j<=A.NColEff;j++){
                A.Mat[A.NRowEff][j] = 0;
            }
            
        }
        return A;
    }

    Matriks sortByVariable() {
        Matriks Msorted = this;
        int[] PivotColIdxArr = new int[Msorted.NRowEff+1];
        for (int i = 1; i <= Msorted.NRowEff; i++) {
            PivotColIdxArr[i] = Msorted.PivotColIdx(i);
        }
        
        for (int i = RowMin; i <= PivotColIdxArr.length-1; i++) {
            if (PivotColIdxArr[i] != i && PivotColIdxArr[i] != IdxUndef) {
                int temp = PivotColIdxArr[i];
                PivotColIdxArr[i] = PivotColIdxArr[i+1];
                PivotColIdxArr[i+1] = temp;
                Msorted.swapRow(i, i+1);
            }
        }

        return Msorted;
    }

    public void BacaFileMatriks(String filematriks) throws FileNotFoundException {
        int NRow = 0; int NCol = 0;
        File bacafile = new File ("../test/"+filematriks+".txt");
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

    public void Simpan(int dType, String simpan) { 
        //harus menconvert semua datatype ke byte
        //dType blh 1/2/3/4 sesuai dengan data type yang diingikan
        try{
            //Variable Lokal sementara
            Matriks MHsl = this;  //Matriks
            int intTemp;    //int
            double dblTemp; //double
            String strTemp; //String
            String spasi ="\t";  // NOTE : INI PAKE TAB APA SPASI? KALO SPASI JADI GAK RAPI

            //Baca nama file
            Scanner baca = new Scanner(System.in);
            System.out.print("Masukkan nama file: ");
            String namafile = baca.nextLine(); System.out.println();
            namafile = namafile+".txt";  
            FileOutputStream hasil = new FileOutputStream("../test/"+namafile);    
            
            switch(dType){
                case 1: strTemp=simpan;    
                        byte b[]=strTemp.getBytes();//convert ke byte
                        hasil.write(b);
                        break;
                case 2: intTemp = 123; 
                        b =String.valueOf(intTemp).getBytes();
                        hasil.write(b);
                        break;
                case 3: dblTemp = 12.52; 
                        b =String.valueOf(dblTemp).getBytes();
                        hasil.write(b);
                        break;
                case 4:  for (int i=1;i<=MHsl.NRowEff;i++){
                            for (int j=1;j<=MHsl.NColEff;j++){
                                b = String.valueOf(RoundingValue(MHsl.Mat[i][j], 4)).getBytes();
                                hasil.write(b);
                                b = spasi.getBytes();
                                hasil.write(b);
                            }
                            hasil.write(10);
                        }
            }
            hasil.close();    
            System.out.println("File "+namafile+" berhasil disimpan");
        }catch(Exception e){System.out.println(e);}    
    }

    public float[][] Transpose(float[][] Mat, int NRowEff, int NColEff) {
        int i, j;
        float[][] M_transpose = new float[RowMax+1][ColMax+1];        ;
        for (i = 1; i <= this.NRowEff; i++) {
            for (j = 1; j <= this.NColEff; j++) {
                M_transpose[j][i] = Mat[i][j];
            }
        }

        return M_transpose;
    }

    public void TransposeMatriks() {
        int temp;

        this.Mat = this.Transpose(this.Mat, this.NRowEff, this.NColEff);
        temp = this.NColEff;
        this.NColEff = this.NRowEff;
        this.NRowEff = temp;
    }

    public void CopyMatriks(Matriks Min) {
        int i, j;

        this.NRowEff = Min.NRowEff;
        this.NColEff = Min.NColEff;
        for (i = RowMin; i <= this.NRowEff; i++) {
            for (j = ColMin; j <= this.NColEff; j++) {
                this.Mat[i][j] = Min.Mat[i][j];
            }
        }
    }

    public void swapRow(int i, int j) { 
        float[] temp = this.Mat[i];
        this.Mat[i] = this.Mat[j];
        this.Mat[j] = temp;
    }

    // ==================================== DETERMINAN ===========================================
    public float Determinan() {
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
                Kofaktor = Minor(RowMin, i);
                det += this.Mat[RowMin][i] * (Sign * Kofaktor.Determinan());
                Sign *= -1;
            }
        }
        return det;   
    }

    public float DeterminanMetodeOBE() {
        // MATRIKS HARUS PERSEGI
        float pivot, result;
        int i, j, pivotidx, countSwap;
        Matriks Mtemp = new Matriks();

        result = 1; Mtemp.CopyMatriks(this);
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

        countSwap = 0;
        for (i = RowMin; i <= this.NRowEff; i++) {
            for (j = i+1; j <= this.NRowEff; j++) {
                if (this.PivotColIdx(i) > this.PivotColIdx(j)) {
                    swapRow(i, j);
                    countSwap++;
                } 
            }
        }

        for (i = RowMin; i <= this.NRowEff; i++) {
            result *= this.Mat[i][i];
        }

        if (countSwap%2 == 1) {
            result *= -1;
        }

        this.CopyMatriks(Mtemp);
        return result;
    }

    // =======================================================================================================


    // ===================================== INVERS ==============================================
    public Matriks InversMetodeKofaktor() {
        // METODE KOFAKTOR
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
                this.RoundToZero(i, j);
                // if (MInvers.Mat[i][j] == -0) {
                //     MInvers.Mat[i][j] *= -1;
                // }
            }
        }
        return MInvers;
    }
    
    public Matriks InversMetodeOBE() {
        /* 
        Prekondisi: Matriks dijamin memiliki invers
        */
        Matriks MI = new Matriks();
        Matriks M = new Matriks();

        M.CopyMatriks(this);
        MI = this.MakeIdentity();
        M = M.MakeAugmented(MI);
        M.ReducedEchelonForm();

        for (int i = RowMin; i <= M.NRowEff; i++) {
            for (int j = ColMin; j <= M.NColEff/2; j++) {
                M.Mat[i][j] = M.Mat[i][M.NColEff/2+j];
            }
        }
        M.NColEff /= 2;

        return M;
    }

    // =======================================================================================================

    public Matriks Minor(int a, int b) {
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

    public float Crammer(Matriks MHasil, int ColC) {
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

    public Matriks MatriksCofactor() {
        Matriks MC = new Matriks();
        int i, j, Sign;

        MC.NRowEff = this.NRowEff;
        MC.NColEff = this.NColEff;
        for (i = RowMin; i <= this.NRowEff; i++) {
            for (j = ColMin; j <= this.NColEff ; j++) {
                if ((i+j)%2 == 0) {
                    Sign = 1;
                } else {
                    Sign = -1;
                }
                MC.Mat[i][j] = Sign * this.Minor(i, j).Determinan();
                if (MC.Mat[i][j] == -0) {
                    MC.Mat[i][j] *= -1;
                }
            }
        }
        return MC;
    }

    public Matriks MatriksAdjoint() {
        Matriks adj = new Matriks();
        adj.NRowEff = this.NRowEff;
        adj.NColEff = this.NColEff;
        adj = this.MatriksCofactor();
        adj.TransposeMatriks();
        
        return adj;
    }

    public Matriks MakeAugmented(Matriks M2) {
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

    public Matriks GetCoef() {
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

    public Matriks GetConstant() {
        Matriks MConst = new Matriks();
        int i;

        MConst.NRowEff = this.NRowEff;
        MConst.NColEff = ColMin;

        for (i = RowMin; i <= MConst.NRowEff; i++) {
            MConst.Mat[i][ColMin] = this.Mat[i][this.NColEff];
        }

        return MConst;
    }

    public Matriks MakeIdentity() {
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
     public boolean isSquare(){
        return (this.NRowEff == this.NColEff);
    }

    public boolean isPivotExist(int row) {
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

    public int PivotColIdx(int row) {
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

    public float SearchLeading(int row) {
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
    public void scaleRow(int i,float scale){
        for(int j = ColMin; j <= this.NColEff; j++) {
            this.Mat[i][j] = this.Mat[i][j]/scale;
            this.RoundToZero(i, j);
        }
    }

    public float RoundToZero(int i, int j) {
        if (this.Mat[i][j] == -0){
            this.Mat[i][j] = 0;
        }
        if (this.Mat[i][j] < 0 && this.Mat[i][j] > -0.0001) { 
            // PERLU TANYA SAMPAI 0.0000 BERAPA ANGKA DIANGGAP 0
            this.Mat[i][j] = 0;
        } 

        return this.Mat[i][j];
    }

    public void interchangeRow(int row, float scale, int PivotRow) {
        // row = row - scale*PivotRow
        for (int j = ColMin; j <= this.NColEff; j++) {
            this.Mat[row][j] -= (scale * this.Mat[PivotRow][j]);
        }
    }

    // public void sortLeading() {
        // for (int i = RowMin; i <= this.NRowEff; i++) {
        //     for (int j = i+1; j <= this.NRowEff; j++) {
        //         if (this.PivotColIdx(i) > this.PivotColIdx(j)) {
        //             swapRow(i, j);
        //         } 
        //     }

        // }
    // }

    //==============Gauss to convert to Echelon Form ============
    public void EchelonForm() {
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
        // this.sortLeading();
        // SORTING LEADING 1
        for (i = RowMin; i <= this.NRowEff; i++) {
            for (j = i+1; j <= this.NRowEff; j++) {
                if (this.PivotColIdx(i) > this.PivotColIdx(j)) {
                    swapRow(i, j);
                } 
            }
        }
    }

    float[] SolusiSatuMetodeGauss()
    // Matriks harus sudah dalam keadaan Echelon Form
    {
        int i,j,k;
        Matriks MI;
        MI = this;
        MI.EchelonForm();

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
                else if (i != MI.NRowEff && (MI.Mat[i][j] == 1) && arr[j] == 0)
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

    public void ReducedEchelonForm() {
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
                    this.RoundToZero(j, pivotidx);
                }
            }
        }
    }

    // int[] FreeVarIdx() {
    //     // RETURN IDX UNDEF JIKA TIDAK DITEMUKAN

    // }

    public boolean isNoSolution() {
        boolean pass = false;
        boolean found = false;
        for (int i = RowMin; i <= this.NRowEff; i++) {
            int j = ColMin; pass = false;
            while (j <= this.NColEff-1 && !pass) {
                if (this.Mat[i][j] != 0) {
                    pass = true;
                } else {
                    j++;
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

    public boolean isManySolution() {
        // boolean pass = false;
        boolean found = false;
        // for (int i = RowMin; i <= this.NRowEff; i++) {
        //     int j = ColMin;
        //     while (j <= this.NColEff-1 && !pass) {
        //         if (this.Mat[i][j] != 0) {
        //             pass = true;
        //         } else {
        //             j++;
        //         }
        //     }
        //     if (!pass) {
        //         if (this.Mat[i][this.NColEff] == 0) {
        //             found = true;
        //         }
        //     }
        // }
        if (this.NRowEff > 2) {
            for (int i = ColMin; i <= (this.NColEff-1); i++) {
                int count = 0; // jika count == 0 atau count > 1, maka kolom tersebut free variable
                for (int j = RowMin; j  <= this.NRowEff; j++) {
                    if (this.Mat[j][i] != 0) {
                        count++;
                    }
                }
                if (count != 1) {
                    found = true; // Kolom tersebut merupakan free variable
                    break;
                }
            }
        } else { // this.NRowEff <= 2
            for (int i = ColMin; i <= (this.NColEff-1); i++) {
                int count = 0;
                for (int j = RowMin; j <= this.NRowEff; j++) {
                    if (this.Mat[j][i] != 1) {
                        count++;
                    } 
                }
                // untuk NRowEff = 2, kemungkinan count = 1 atau count = 2
                if (count > 1) {
                    found = true;
                    break;
                }
            }
        }
        return found;        
    }

}