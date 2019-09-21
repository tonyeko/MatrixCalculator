import java.util.Scanner;
import java.util.*;

class Interpolasi {

    Interpolasi () {}

    float XPangkat (float x, int n)
    {
       float hasil;
       int i;

       hasil = 1;
        for (i=1;i<=n;i++)
        {
            hasil = hasil * x; 
        }
        return hasil;
    }

    Matriks InputTitik ()
    {
        Matriks M = new Matriks ();

        int i,j,N;
        float x,y;
        Scanner input = new Scanner(System.in);

        N = input.nextInt();

        M.NRowEff = N;
        M.NColEff = N+1;
    
        for (i=1;i<=N;i++)
        {   
            M.Mat[i][1] = 1;
            x = input.nextFloat(); 
            y = input.nextFloat();
            M.Mat[i][N+1] =  y;
            for(j=2;j<=N;j++)
            {
                M.Mat[i][j] =  XPangkat(x,(j-1));
            }
        }

        return M;

    }

    float HasilInterpolasi (Matriks MI)
    {
        Matriks M = new Matriks();
        int i;
        float x,hasil;
        Scanner input = new Scanner(System.in);

        x = input.nextFloat();
        hasil = 0;
        for (i = MI.RowMin; i <= MI.NRowEff; i++) {
           System.out.println(MI.GetCoef().Cramer(MI.GetConstant(),i));
           hasil = hasil + (MI.GetCoef().Cramer(MI.GetConstant(),i) * XPangkat (x, i-1)) ; 
        }

        return hasil;

    }

    
}