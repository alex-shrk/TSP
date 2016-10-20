import Matrix.Matrix;

public class GenerateMatrix {

    public static void main(String[] args) {
        Matrix m_a=new Matrix(10,10);
        m_a.generateMatrix();
        Matrix.outputToTxt(m_a,"matrix_a");
        Matrix m_b=new Matrix(10,10);
        m_b.generateMatrix();
        Matrix.outputToTxt(m_b,"matrix_b");
    }
}
