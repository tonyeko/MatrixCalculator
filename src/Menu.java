package src;
import java.io.*;
import java.util.*;

class Menu {
    Scanner baca = new Scanner(System.in);
    Matriks M = new Matriks();
    ConsoleCapturer capturer = new ConsoleCapturer();
    String consoleOutput;

    void ListMenu() {
     
        System.out.println("");
        System.out.println("=============== SPL, Determinan, dan Aplikasinya ===============");
        System.out.println("============================= Menu =============================");
        System.out.println("1. Sistem Persamaan Linear");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Matriks kofaktor");
        System.out.println("5. Adjoin");
        System.out.println("6. Interpolasi Polinom");
        System.out.println("7. Exit");
        System.out.println("");
    
    }

    void HasilMenu(int PilihanMenu) throws FileNotFoundException {
        if (PilihanMenu == 1)
        { this.SubMenuSPL(); }
        else if (PilihanMenu == 2)
        { this.SubMenuDeterminan(); }
        else if (PilihanMenu == 3)
        {  this.SubMenuInvers(); }
        else if (PilihanMenu == 4)
        { 
            System.out.println("Kofaktor ");
            this.bacaMatriks(2);
            // BELUM DI CEK APA PUNYA KOFAKTOR
            if (this.M.isSquare()) {
                capturer.start();
                System.out.println("Matriks Input: ");
                this.M.TulisMatriks();
                System.out.println("Matriks Kofaktor: ");
                this.M.MatriksCofactor().TulisMatriks();
                consoleOutput = capturer.stop();
                this.SimpanKeFile(consoleOutput);
                // System.out.println(consoleOutput);
            } else {
                System.out.println("Bukan matriks persegi. Tidak memiliki Matriks Kofaktor");
            }
            
            System.out.println();
        }
        else if (PilihanMenu == 5)
        { 
            System.out.println("Adjoin");
            this.bacaMatriks(2);
            // BELUM DI CEK APA PUNYA ADJOIN
            this.M.MatriksAdjoint().TulisMatriks();
            System.out.println();
        }
        else if (PilihanMenu == 6)
        {  
            Matriks M1;
            float hasil;
    
            Interpolasi I = new Interpolasi();
            
            this.bacaMatriks(3);
            I.MInterpolasi = this.M;
            M1 = I.InputTitik();
            hasil = I.HasilInterpolasi(M1);
            I.TulisPersamaanPolinom(M1);
            System.out.println();

            System.out.printf("Hasil interpolasi: %.4f\n", hasil);
        }
    }

    void SubMenuSPL(){
        System.out.println("");
        System.out.println("=========================== SubMenu SPL ===========================");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
 
    }

    
    void SubMenuDeterminan(){
        System.out.println("");
        System.out.println("======================== SubMenu Determinan ========================");
        System.out.println("1. Metode Operasi Baris Elementer");
        System.out.println("2. Metode Kofaktor");
       
}

    void SubMenuInvers(){
        System.out.println("");
        System.out.println("====================== SubMenu MatriksBalikan ======================");
        System.out.println("1. Metode Gauss-Jordan");
        System.out.println("2. Metode Kofaktor");
    }

    void HasilSubMenuSPL(int PilihanSubMenu) throws FileNotFoundException {
        this.bacaMatriks(1);
        SPL SPL = new SPL();
        SPL.M.CopyMatriks(this.M);
        
        // MATRIKS SUDAH DIBACA
        if (PilihanSubMenu == 1)
        {  
            SPL.TulisSPL(1); 
        }
        else if (PilihanSubMenu == 2)
        { 
            SPL.TulisSPL(2); 
        }
        else if (PilihanSubMenu == 3)
        { 
            SPL.TulisSPL(3); 
        }
        else if (PilihanSubMenu == 4)
        { 
            capturer.start();
            System.out.println("Matriks Input: ");
            this.M.TulisMatriks();
            SPL.TulisSPL(4);
            consoleOutput = capturer.stop();
            this.SimpanKeFile(consoleOutput); 
            
        }
    }

    void HasilSubMenuDeterminan(int PilihanSubMenu) throws FileNotFoundException {
        this.bacaMatriks(2);
        if (PilihanSubMenu == 1)
        {
            System.out.println("1. Metode Operasi Baris Elementer");
            // BELUM DICEK APA PUNYA INVERS
            this.M.InversMetodeOBE().TulisMatriks();
        }
        else if (PilihanSubMenu == 2)
        {
            System.out.println("1. Metode Kofaktor");
            // BELUM DI CEK APA PUNYA INVERS
            this.M.InversMetodeKofaktor().TulisMatriks();
        }

    }

    void HasilSubMenuInvers(int PilihanSubMenu) throws FileNotFoundException {
        this.bacaMatriks(2);
        if (PilihanSubMenu == 1)
        {System.out.println("1. Metode Gauss-Jordan");}
        else if (PilihanSubMenu == 2)
        { System.out.println("2. Metode Kofaktor"); }
    }

    // ================== INTERAKSI FILE EKSTERNAL ====================
    void bacaMatriks(int option) throws FileNotFoundException {
        // OPTION == 1 AUGMENTED
        // OPTION == 2 COEFF
        // OPTION == 3 TITIK X,Y INTERPOLASI
        if (option == 1) {
            System.out.println();
            System.out.println("Baca Matriks Augmented");
            System.out.print("Apakah Anda ingin membaca file Matriks Augmented eksternal (Y/N)? ");
            String ext = baca.nextLine();
            if (ext.equals("Y") || ext.equals("y")) {
                System.out.print("Masukkan nama file: ");
                String filematriks = baca.nextLine();
                this.M.BacaFileMatriks(filematriks);
            } else {
                System.out.println("Masukkan Matriks Augmented: ");
                this.M.BacaMatriks();
                // JIKA MATRIKS TIDAK AUGMENTED, BACA ULANG
            }
        } else if (option == 2) {
            System.out.println();
            System.out.println("Baca Matriks Persegi");
            System.out.print("Apakah Anda ingin membaca file Matriks Persegi eksternal (Y/N)? ");
            String ext = baca.nextLine();
            if (ext.equals("Y") || ext.equals("y")) {
                System.out.print("Masukkan nama file: ");
                String filematriks = baca.nextLine();
                this.M.BacaFileMatriks(filematriks);
                while (!this.M.isSquare()) {
                    System.out.println("Matriks tidak persegi. Silahkan masukkan kembali.");
                    System.out.print("Masukkan nama file: ");
                    filematriks = baca.nextLine();
                    this.M.BacaFileMatriks(filematriks);
                }
            } else {
                System.out.println("Masukkan Matriks Persegi: ");
                this.M.BacaMatriks();
                while (!this.M.isSquare()) {
                    System.out.println("Matriks tidak persegi. Silahkan masukkan kembali.");
                    this.M.BacaMatriks();
                }
            }
        } else { // INTERPOLASI
            System.out.println();
            System.out.println("Baca Data Titik");
            System.out.print("Apakah Anda ingin membaca file Data Titik eksternal (Y/N)? ");
            String ext = baca.nextLine();
            if (ext.equals("Y") || ext.equals("y")) {
                System.out.print("Masukkan nama file: ");
                String filematriks = baca.nextLine();
                this.M.BacaFileMatriks(filematriks);
                while (this.M.NColEff > 2 || this.M.NColEff <= 0) {
                    System.out.println("Data titik tidak sesuai. Silahkan masukkan kembali.");
                    System.out.print("Masukkan nama file (): ");
                    filematriks = baca.nextLine();
                    this.M.BacaFileMatriks(filematriks);
                }
            } else {
                System.out.print("Masukkan banyak titik: ");
                int N = baca.nextInt();
                while (N < 1) {
                    System.out.print("Masukan salah. Masukkan banyak titik: ");
                    N = baca.nextInt();                
                    // System.out.println("Matriks tidak persegi. Silahkan masukkan kembali.");
                    // this.M.BacaMatriks();
                }
                this.M.NColEff = 2; // x dan y
                this.M.NRowEff = N;
                for (int i = this.M.RowMin; i <= this.M.NRowEff; i++) {
                    System.out.print("Masukkan titik ke-"+i+": ");
                    this.M.Mat[i][1] = baca.nextFloat(); 
                    this.M.Mat[i][2] = baca.nextFloat();
                }
            }
        }
    }

    void SimpanKeFile(String console) {
        System.out.print("Apakah Anda ingin menyimpan hasil perhitungan (Y/N)? ");
        String pilihan = baca.nextLine();
        if (pilihan.equals("Y") || pilihan.equals("y")) {
            this.M.Simpan(1, console);
        }
    }

}