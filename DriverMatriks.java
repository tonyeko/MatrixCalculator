import java.io.*;
import java.util.*;

class DriverMatriks {
    public static void main(String[] args) throws FileNotFoundException {
        // // deklarasi variabel
        // int PilihanMenu,PilihanSubMenu;

        // // membuat scanner baru
        Scanner baca = new Scanner(System.in);
        
        Matriks M = new Matriks();
        Matriks MI = new Matriks();
        // Menu Menu = new Menu();
        // // Matriks M2 = new Matriks();
        // Menu.TulisMenu();

        M.BacaMatriks();
        // M.TulisMatriks(); System.out.println();
        // M.EchelonForm(); 
        // M.TulisMatriks(); System.out.println();
        // M.ReducedEchelonForm(); 
        // M.TulisMatriks(); System.out.println();
        MI = M.MakeIdentity();
        M = M.MakeAugmented(MI);
        M.TulisMatriks(); System.out.println();
        M.ReducedEchelonForm();
        M.TulisMatriks(); System.out.println();

        M.Simpan();

        // // M.BacaFileMatriks("test.txt");
        // // System.out.println("Isi matriks: ");
        // // M.TulisMatriks();
        // // System.out.println();
        // M.TulisMatriks();
        // System.out.println();
        // // M2 = M.GetCoef();
        // // M2.TulisMatriks();
        // // Matriks Hasil = new Matriks();
        // // Hasil = M.GetConstant();
        
        // System.out.println();

        // Interpolasi I = new Interpolasi();

        // M2 = I.InputTitik(3);
        // M2.TulisMatriks();

        // System.out.println(M2.GetCoef().Cramer(M2.GetConstant(), 1));



        // M2.BacaMatriks();
        // M2.TulisMatriks();
        // System.out.println();

        // M2 = M.KaliMatriks(M2);
        // M2.TulisMatriks();
        // System.out.println();

        // // M.TransposeMatriks();
        // // M.TulisMatriks();
        // // System.out.println();
        
        // M2.swapRow(1, 2);
        
        // M2.TulisMatriks();
        // System.out.println();
        // System.out.println(M.Determinan());

        // Matriks C11 = new Matriks();
        // C11 = M.Kofaktor(2, 2);
        // C11.TulisMatriks(); 
        // System.out.println();

        // Matriks Hasil = new Matriks();
        // Hasil.CopyMatriks(M);
        // Hasil = Hasil.EchelonMatriks();
        // Hasil.TulisMatriks();
        // Hasil = Hasil.MatriksCofactor();
        // Hasil.TulisMatriks();
        // System.out.println();

        // Hasil = M.MatriksInvers();
        // Hasil.TulisMatriks();
        // System.out.println();
        // // System.out.println(M.Cramer(Hasil, 1));

        // Hasil = M.MakeAugmented(M2);
        // Hasil.TulisMatriks();
        // System.out.println();

        // M = M.MakeIdentity();
        // M.TulisMatriks(); 
        // System.out.println();

        // float hasil;
        // Matriks M1;
        // int N;

        // Scanner input = new Scanner(System.in);

        // Interpolasi I = new Interpolasi ();

        
        // System.out.print(hasil);
        // N = input.nextInt();
        // M1 = I.InputTitik(N);
        // M1.TulisMatriks();
        // hasil = I.HasilInterpolasi((float)9.2, M1);
        // System.out.println(hasil);
    }
}