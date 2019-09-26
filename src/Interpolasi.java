package src;
import java.util.Scanner;

class Interpolasi {
    Matriks MInterpolasi;

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

    Matriks InputTitik()
    {
        Matriks M = new Matriks();

        int i,j,N;
        float x,y;
        N = this.MInterpolasi.NRowEff;

        M.NRowEff = N;
        M.NColEff = N+1;
    
        for (i=1;i<=N;i++)
        {   
            M.Mat[i][1] = 1;
            int k = this.MInterpolasi.ColMin;
            x = this.MInterpolasi.Mat[i][k];
            y = this.MInterpolasi.Mat[i][k+1];
            M.Mat[i][N+1] =  y;
            for(j = 2;j <= N; j++)
            {
                M.Mat[i][j] =  XPangkat(x,(j-1));
            }
        }
        return M;

    }

    float HasilInterpolasi (Matriks MI)
    {
        Scanner input = new Scanner(System.in);
        float[] arrHasil = MI.SolusiSatuMetodeGauss();
        int i;
        float x, hasil;
        
        System.out.println("Masukkan x yang ingin dinterpolasi: "); 
        x = input.nextFloat();
        hasil = 0;
        for (i = MI.RowMin; i <= MI.NRowEff; i++) {
          // System.out.println(MI.GetCoef().Crammer(MI.GetConstant(),i));
           hasil = hasil + (arrHasil[i] * XPangkat (x, i-1)) ; 
        }

        return hasil;

    }

    void TulisPersamaanPolinom (Matriks MI)
    {
        float[] arrHasil = MI.SolusiSatuMetodeGauss();
        float a;
        int i;

        System.out.print("p");
        System.out.print(MI.NRowEff-1);
        System.out.print("(x) = ");

        for (i = MI.RowMin; i <= MI.NRowEff; i++) 
        {
            a = arrHasil[i];
            if ((i-1) == 0)
            {
                System.out.printf("%.4f", a);
              
            }
            else if ((i-1) == 1 && (a>0))
            {
                // System.out.print(" + ");
                // System.out.print(a);
                // System.out.print("x");
                System.out.printf(" + %.4fx", a);
            }
            else if ((i-1) == 1 && (a<0))
            {
                a = Math.abs(a);
                // System.out.print(" - ");
                // System.out.print(a);
                // System.out.print("x");
                System.out.printf(" - %.4fx", a);
            }
            else if ((i < MI.NRowEff) && ((i-1) > 1) && (a>0))
            {
                // System.out.print(" + ");
                // System.out.print(a);
                // System.out.print("x^");
                // System.out.print((i-1));
                System.out.printf(" + %.4fx^%d", a, i-1);
                
            }
            else if ((i < MI.NRowEff) && ((i-1) > 1) && (a<0))
            {
                a = Math.abs(a);
                // System.out.print(" - ");
                // System.out.print(a);
                // System.out.print("x^");
                // System.out.print((i-1));
                System.out.printf(" - %.4fx^%d", a, i-1);
                
            }
            else if ((i == MI.NRowEff) && ((i-1) > 1) && (a>0))
            {
                // System.out.print(" + ");
                // System.out.print(a);
                // System.out.print("x^");
                // System.out.print((i-1));
                System.out.printf(" + %.4fx^%d", a, i-1);
            }
            else 
            {
                a = Math.abs(a);
                // System.out.print(" - ");
                // System.out.print(a);
                // System.out.print("x^");
                // System.out.print((i-1));
                System.out.printf(" - %.4fx^%d", a, i-1);
            }
            
        }

    }

    
}