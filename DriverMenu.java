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
            System.out.print("Pilihan Anda: ");
            PilihanMenu = input.nextInt();
            if (PilihanMenu == 1 || PilihanMenu == 2 || PilihanMenu == 3)
            {
                System.out.println("");
                M.SubMenu();
                System.out.print("Pilihan Anda: ");
                PilihanSubMenu = input.nextInt();
            }     

        } while (PilihanMenu != 7);
       
    }
}