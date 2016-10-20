package Matrix;

import Exceptions.MatrixSizeError;

import java.io.*;
import java.util.Random;

public class Matrix {
    private double[][] matrix;
    //m-count of rows
    //n-count of column
    private int m,n;


    public Matrix() {
    }

    public Matrix(int m,int n) {
        this.m=m;
        this.n=n;
        this.matrix = new double[m][n];
    }


    public int getCountRows() {
        return m;
    }
    
    public int getCountColumns() {
        return n;
    }



    public double getElement(int m, int n) throws ArrayIndexOutOfBoundsException {
        try{
            return matrix[m][n];
        }
        catch (Exception e) {
            throw new ArrayIndexOutOfBoundsException("Element" + m + ' ' + n + "not found");
        }
    }
    public void setElement(int m,int n,double el) throws ArrayIndexOutOfBoundsException {
        try{
            matrix[m][n]=el;
        }
        catch (Exception e) {
            throw new ArrayIndexOutOfBoundsException("Element" + m + ' ' + n + "not found");
        }
    }

    public static Matrix addition(Matrix A,Matrix B) throws MatrixSizeError {
        int m_a=A.getCountRows();
        int n_a=A.getCountColumns();
        int m_b=B.getCountRows();
        int n_b=B.getCountColumns();

        if (m_a!=m_b && n_a!=n_b)
            throw new MatrixSizeError();
        double el_a,el_b;

        Matrix C=new Matrix(m_a,n_a);


        for(int i=0;i<m_a;i++){
            for(int j=0;j<n_a;j++){
                el_a=A.getElement(i,j);
                el_b=B.getElement(i,j);
                C.matrix[i][j]=el_a+el_b;
            }
        }
        return C;
    }

    public void generateMatrix() {
        System.out.println("Generating matrix. Size="+m+"x"+n);
        Random random = new Random();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextDouble();
            }
        }
        System.out.println("Complete");
    }


    public static Matrix inputElements(DataInputStream dis) throws IOException {
        int m=dis.readInt();
        int n=dis.readInt();
        Matrix a=new Matrix(m,n);
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                a.matrix[i][j]=dis.readDouble();
            }
        }
        return a;
    }

    public static void outputElements(Matrix a,DataOutputStream dos) throws IOException {
        int m=a.getCountRows();
        int n=a.getCountColumns();
        dos.writeInt(m);
        dos.writeInt(n);
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                dos.writeDouble(a.matrix[i][j]);
            }
        }
    }



    public static Matrix inputFromTxt(String path) {
            Matrix a=null;
            try(BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(path)))){
                int m=Integer.parseInt(br.readLine());//count of rows
                int n=Integer.parseInt(br.readLine());//count of column
                a=new Matrix(m,n);

                String line=null;
                String[] elem=null;
                for (int i = 0; i < m; i++) {
                    line=br.readLine();
                    elem=line.trim().split(" ",n);//remove space and split elements
                    for (int j = 0; j < n; j++) {
                        a.matrix[i][j] = Double.parseDouble(elem[j]);
                    }
                }

            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
            return a;


        }

    public static void outputToTxt(Matrix a,String path) {

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));) {
            bw.write(String.valueOf(a.getCountRows()) + '\n');
            bw.write(String.valueOf(a.getCountColumns()) + '\n');
            for (int i = 0; i < a.getCountRows(); i++) {
                for (int j = 0; j < a.getCountColumns(); j++) {
                    bw.write(String.valueOf(a.matrix[i][j])+' ');
                }
                bw.write('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
