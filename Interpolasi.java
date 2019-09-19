import java.util.Scanner;
import java.lang.Math;

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

    Matriks InputTitik (int N)
    {
        Matriks M = new Matriks ();
        M.NRowEff = N;
        M.NColEff = N+1;
        int i,j;
        float x,y;
        Scanner input = new Scanner(System.in);
    
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

    
}