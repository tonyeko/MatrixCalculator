import java.io.*;
import java.util.*;

class DriverMatriks {
    public static void main(String[] args) throws FileNotFoundException {
        // // deklarasi variabel
        // int PilihanMenu,PilihanSubMenu;

        // // membuat scanner baru
        // Scanner input = new Scanner(System.in);

        // menu M = new menu ();

        // do {
            
        //     M.ListMenu();
        //     do {
        //         System.out.print("Pilihan Anda: ");
        //         PilihanMenu = input.nextInt();
        //         if (PilihanMenu < 1 || PilihanMenu > 7)
        //         {
        //             System.out.println("Menu tidak terdefinisi. Silakan masukkan kembali.");
        //             System.out.println("");
        //         } 
        //     } while ((PilihanMenu < 1 || PilihanMenu > 7));
        //     M.HasilMenu(PilihanMenu);

        //     if (PilihanMenu == 1 || PilihanMenu == 2 || PilihanMenu == 3)
        //         {      
        //                 System.out.println("");
        //                 M.SubMenu();
        //                 do 
        //                 {
        //                     System.out.print("Pilihan Anda: ");
        //                     PilihanSubMenu = input.nextInt();
        //                     if (PilihanSubMenu < 1 || PilihanSubMenu > 4)
        //                      {
        //                          System.out.println("SubMenu tidak terdefinisi. Silakan masukkan kembali.");
        //                          System.out.println("");
        //                      }
        //                 } while ((PilihanSubMenu < 1 || PilihanSubMenu > 4));    
        //                 M.HasilSubMenu(PilihanSubMenu);
        //         }

        // } while (PilihanMenu != 7);
        // Scanner scan = new Scanner(System.in);
        
        Matriks M = new Matriks();
        Matriks M2 = new Matriks();

        // M.BacaMatriks();
        // M.BacaFileMatriks("test.txt");
        // System.out.println("Isi matriks: ");
        // M.TulisMatriks();
        // System.out.println();
        // M.TulisMatriks();
        // System.out.println();
        // M2 = M.GetCoef();
        // M2.TulisMatriks();
        // Matriks Hasil = new Matriks();
        // Hasil = M.GetConstant();
        
        // System.out.println();

        Interpolasi I = new Interpolasi();

        M2 = I.InputTitik(3);
        M2.TulisMatriks();

        System.out.println(M2.GetCoef().Cramer(M2.GetConstant(), 1));



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
    }
}