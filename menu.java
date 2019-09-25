import java.io.*;
import java.util.*;

class menu {

    menu () {}

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

    void HasilMenu(int PilihanMenu) { 
        if (PilihanMenu == 1)
        { this.SubMenuSPL(); }
        else if (PilihanMenu == 2)
        { this.SubMenuDeterminan(); }
        else if (PilihanMenu == 3)
        {  this.SubMenuInvers(); }
        else if (PilihanMenu == 4)
        { System.out.println("Kofaktor "); }
        else if (PilihanMenu == 5)
        { System.out.println("Adjoin");}
        else if (PilihanMenu == 6)
        {  
            Matriks M1;
            float hasil;
    
            Interpolasi I = new Interpolasi ();

            M1 = I.InputTitik ();
            hasil = I.HasilInterpolasi (M1);
            I.TulisPersamaanPolinom (M1);
            System.out.print(hasil);
            System.out.println("");
        }
    }

    void SubMenuSPL(){
        System.out.println("");
        System.out.println("=========================== SubMenuSPL ===========================");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
 
    }

    
    void SubMenuDeterminan(){
        System.out.println("");
        System.out.println("======================== SubMenuDeterminan ========================");
        System.out.println("1. Metode Operasi Baris Elementer");
        System.out.println("2. Metode Kofaktor");
       
}

    void SubMenuInvers(){
        System.out.println("");
        System.out.println("====================== SubMenuMatriksBalikan ======================");
        System.out.println("1. Metode Gauss-Jordan");
        System.out.println("2. Metode Kofaktor");
    
    }

    void HasilSubMenuSPL(int PilihanSubMenu) { 
        if (PilihanSubMenu == 1)
        {System.out.println("1. Metode eliminasi Gauss");}
        else if (PilihanSubMenu == 2)
        {System.out.println("1. Metode eliminasi Gauss");}
        else if (PilihanSubMenu == 3)
        {System.out.println("1. Metode eliminasi Gauss");}
        else if (PilihanSubMenu == 4)
        {System.out.println("1. Metode eliminasi Gauss");}
    }

    void HasilSubMenuDeterminan(int PilihanSubMenu) { 
        if (PilihanSubMenu == 1)
        {System.out.println("1. Metode Operasi Baris Elementer");}
        else if (PilihanSubMenu == 2)
        {System.out.println("1. Metode Operasi Baris Elementer");}

    }

    void HasilSubMenuInvers(int PilihanSubMenu) { 
        if (PilihanSubMenu == 1)
        {System.out.println("1. Metode Operasi Baris Elementer");}
        else if (PilihanSubMenu == 2)
        {System.out.println("1. Metode Operasi Baris Elementer");}
    }

    

    void MainMenu () 
    {
       // deklarasi variabel
       int PilihanMenu,PilihanSubMenu;

       // membuat scanner baru
       Scanner input = new Scanner(System.in);

        do {
            this.ListMenu();
            do {
                System.out.print("Pilihan Anda: ");
                PilihanMenu = input.nextInt();
                if (PilihanMenu < 1 || PilihanMenu > 7)
                {
                    System.out.println("Menu tidak terdefinisi. Silakan masukkan kembali.");
                    System.out.println("");
                } 
            } while ((PilihanMenu < 1 || PilihanMenu > 7));
            this.HasilMenu(PilihanMenu);

            if (PilihanMenu == 1 || PilihanMenu == 2 || PilihanMenu == 3)
                {      
                        System.out.println("");
                        if (PilihanMenu == 1)
                        {
                            
                            do 
                        {
                            System.out.print("Pilihan Anda: ");
                            PilihanSubMenu = input.nextInt();
                            if (PilihanSubMenu < 1 || PilihanSubMenu > 4)
                             {
                                 System.out.println("SubMenu tidak terdefinisi. Silakan masukkan kembali.");
                                 System.out.println("");
                             }
                        } while ((PilihanSubMenu < 1 || PilihanSubMenu > 4));   
                        this.HasilSubMenuSPL(PilihanSubMenu); 

                        }
                        else if ( PilihanMenu == 2)
                        {
                            do 
                        {
                            System.out.print("Pilihan Anda: ");
                            PilihanSubMenu = input.nextInt();
                            if (PilihanSubMenu < 1 || PilihanSubMenu > 2)
                             {
                                 System.out.println("SubMenu tidak terdefinisi. Silakan masukkan kembali.");
                                 System.out.println("");
                             }
                        } while ((PilihanSubMenu < 1 || PilihanSubMenu > 2));   
                        this.HasilSubMenuDeterminan(PilihanSubMenu);  

                        }
                        else if ( PilihanMenu == 3)
                        {
                            do 
                        {
                            System.out.print("Pilihan Anda: ");
                            PilihanSubMenu = input.nextInt();
                            if (PilihanSubMenu < 1 || PilihanSubMenu > 2)
                             {
                                 System.out.println("SubMenu tidak terdefinisi. Silakan masukkan kembali.");
                                 System.out.println("");
                             }
                        } while ((PilihanSubMenu < 1 || PilihanSubMenu > 2));    
                        this.HasilSubMenuInvers(PilihanSubMenu); 

                        }
                        
                }

        } while (PilihanMenu != 7);
    }
}

