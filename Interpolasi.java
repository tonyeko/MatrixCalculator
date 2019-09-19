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
        int i,j;
        float x,y;
        Scanner input = new Scanner(System.in);
    
        for (i=1;i<=N;i++)
        {
            j = 2;
            while (j<=N) 
            {
                this.Mat[i][1] = 1; 
                x = input.nextInt();
                y = input.nextInt();
                this.Mat[i][j] =  XPangkat(x,(j-1));
                this.Mat[1][N+1] =  y;

                j = j + 1;
            }
        }

        return M;

    }

    
}