package Exceptions;

public class MatrixSizeError extends Exception {
    public MatrixSizeError() {
        super("Несовместные размеры матриц");
    }
}
