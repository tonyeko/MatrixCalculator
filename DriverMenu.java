import java.util.Scanner;

class DriverMenu {
    public static void main(String[] args) {

        // deklarasi variabel
        int PilihanMenu,PilihanSubMenu;

        // membuat scanner baru
        Scanner input = new Scanner(System.in);

        menu M = new menu ();

        do {
            
            M.ListMenu();
            do {
                System.out.print("Pilihan Anda: ");
                PilihanMenu = input.nextInt();
                if (PilihanMenu < 1 || PilihanMenu > 7)
                {
                    System.out.println("Menu tidak terdefinisi. Silakan masukkan kembali.");
                    System.out.println("");
                } 
            } while ((PilihanMenu < 1 || PilihanMenu > 7));
            M.HasilMenu(PilihanMenu);

            if (PilihanMenu == 1 || PilihanMenu == 2 || PilihanMenu == 3)
                {      
                        System.out.println("");
                        M.SubMenu();
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
                        M.HasilSubMenu(PilihanSubMenu);
                }

        } while (PilihanMenu != 7);
    }
}