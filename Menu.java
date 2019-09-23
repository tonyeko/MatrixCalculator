import java.io.*;
import java.util.*;

class Menu {

    Menu() {}

    void TulisMenu() {
        Scanner baca = new Scanner(System.in);
        System.out.println("=============== SPL, Determinan, dan Aplikasinya ===============");
        System.out.println("============================= Menu =============================");
        System.out.println("1. Sistem Persamaan Linear");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Matriks kofaktor");
        System.out.println("5. Adjoin");
        System.out.println("6. Interpolasi Polinom");
        System.out.println("7. Exit");
        System.out.print("Masukkan Pilihan: ");
        // this.HasilMenu(baca.nextInt());
    }

    void TulisSubMenuSPL(){
        Scanner baca = new Scanner(System.in);
        System.out.println("Submenu SPL: ");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.print("Masukkan Pilihan: ");
        // this.HasilSubMenu(baca.nextInt());
    } 

    void TulisSubMenuDeterminan(){
        Scanner baca = new Scanner(System.in);
        System.out.println("Submenu SPL: ");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.print("Masukkan Pilihan: ");
        // this.HasilSubMenu(baca.nextInt());
    }

    
    // void HasilMenu(int PilihanMenu) { 
    //     Scanner baca = new Scanner(System.in);
    //     if (PilihanMenu == 1) { 
    //         this.TulisSubMenuSPL();
    //     } else if (PilihanMenu == 2) { 
    //         SPL.TulisSubMenuDeterminan(); 
    //     } else if (PilihanMenu == 3) { 
    //         SPL.TulisSPL(3); 
    //     } else if (PilihanMenu == 4) { 
    //         SPL.TulisSPL(4); 
    //     } else if (PilihanMenu == 5) { 
    //         SPL.TulisSPL(5); 
    //     } else if (PilihanMenu == 6) { 
    //         SPL.TulisSPL(6); 
    //     }

    // }

//     void HasilSubMenuSPL(int PilihanSubMenuSPL) { 
//         if (PilihanSubMenu == 1)
//         { SPL.TulisSPL(1); }
//         else if (PilihanSubMenu == 2)
//         { SPL.TulisSPL(2); }
//         else if (PilihanSubMenu == 3)
//         { SPL.TulisSPL(3); }
//         else if (PilihanSubMenu == 4)
//         { SPL.TulisSPL(4); }

//     }
}