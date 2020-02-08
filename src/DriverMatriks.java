package src;
import java.io.*;
import java.util.*;

class DriverMatriks {
    public static void main(String[] args) throws FileNotFoundException {
        // deklarasi variabel
       int PilihanMenu, PilihanSubMenu,i;
       Scanner input = new Scanner(System.in);
       Menu Menu = new Menu();
       Matriks M = new Matriks();
       SPL S = new SPL ();
       float[] floatArray = new float [100];
       float[] arr = floatArray;

        // solusi satu
        // M.BacaMatriks(); System.out.println();
        // M.EchelonForm();
        // M.TulisMatriks(); System.out.println();
        // arr = M.SolusiSatuMetodeGauss();
        // M.TulisSolusiSatu(arr);


        // do {
        //     // Menampilkan List Menu
        //     Menu.ListMenu();
        //     // Input pilihan menu
        //     do {
        //         System.out.print("Pilihan Anda: ");
        //         PilihanMenu = input.nextInt();
        //         if (PilihanMenu < 1 || PilihanMenu > 7)
        //         {
        //             System.out.println("Menu tidak terdefinisi. Silakan masukkan kembali.");
        //             System.out.println("");
        //         } 
        //     } while ((PilihanMenu < 1 || PilihanMenu > 7));
        //     Menu.HasilMenu(PilihanMenu);

        //     if (PilihanMenu == 1 || PilihanMenu == 2 || PilihanMenu == 3)
        //         {      
        //                 System.out.println("");
        //                 if (PilihanMenu == 1)
        //                 {
        //                     do 
        //                     {
        //                         System.out.print("Pilihan Anda: ");
        //                         PilihanSubMenu = input.nextInt();
        //                         if (PilihanSubMenu < 1 || PilihanSubMenu > 4)
        //                         {
        //                             System.out.println("SubMenu tidak terdefinisi. Silakan masukkan kembali.");
        //                             System.out.println("");
        //                         }
        //                     } while ((PilihanSubMenu < 1 || PilihanSubMenu > 4));   
        //                     Menu.HasilSubMenuSPL(PilihanSubMenu); 

        //                 }
        //                 else if ( PilihanMenu == 2)
        //                 {
        //                     do 
        //                     {
        //                         System.out.print("Pilihan Anda: ");
        //                         PilihanSubMenu = input.nextInt();
        //                         if (PilihanSubMenu < 1 || PilihanSubMenu > 2)
        //                         {
        //                             System.out.println("SubMenu tidak terdefinisi. Silakan masukkan kembali.");
        //                             System.out.println("");
        //                         }
        //                     } while ((PilihanSubMenu < 1 || PilihanSubMenu > 2));   
        //                     Menu.HasilSubMenuDeterminan(PilihanSubMenu);  

        //                 }
        //                 else if ( PilihanMenu == 3)
        //                 {
        //                     do 
        //                     {
        //                         System.out.print("Pilihan Anda: ");
        //                         PilihanSubMenu = input.nextInt();
        //                         if (PilihanSubMenu < 1 || PilihanSubMenu > 2)
        //                         {
        //                             System.out.println("SubMenu tidak terdefinisi. Silakan masukkan kembali.");
        //                             System.out.println("");
        //                         }
        //                     } while ((PilihanSubMenu < 1 || PilihanSubMenu > 2));    
        //                     Menu.HasilSubMenuInvers(PilihanSubMenu); 

        //                 }
                        
        //         }

        // } while (PilihanMenu != 7);

        // if (PilihanMenu == 7) {
        //     System.out.println("Terima kasih telah menggunakan program kami.");
        // }

        // // deklarasi variabel
        // int PilihanMenu,PilihanSubMenu;

        // // membuat scanner baru
        // Scanner baca = new Scanner(System.in);
        
        // Matriks M = new Matriks();
        // // Matriks MI = new Matriks();
        
        // // // Matriks M2 = new Matriks();
        // Menu.MainMenu();

        // M.BacaMatriks(); System.out.println();
        // M.EchelonForm();
        // M.TulisMatriks(); System.out.println();
        // M.BacaFileMatriks("simpan66.txt");
        // M.MatriksAdjoint().TulisMatriks(); System.out.println();
        // M = M.InversMetodeOBE();
        // M.TulisMatriks(); System.out.println();
        // // M = M.MatriksInvers();
        // // M.TulisMatriks(); System.out.println();
        // M.Simpan(4);

        // M.TulisMatriks(); System.out.println();
        // M.EchelonForm(); 
        // M.TulisMatriks(); System.out.println();
        // M.ReducedEchelonForm(); 
        // M.TulisMatriks(); System.out.println();
        // MI = M.MakeIdentity();
        // M = M.MakeAugmented(MI);
        // M.TulisMatriks(); System.out.println();
        // M.ReducedEchelonForm();
        // M.TulisMatriks(); System.out.println();

        // M.metodeGaussJordan();
        // M.metodeCrammer();
        // M.Simpan();

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

        // TES INTERPOLASI 
        // Interpolasi I = new Interpolasi();

        // MI = I.InputTitik();
        // I.TulisPersamaanPolinom(MI);
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