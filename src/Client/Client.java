package Client;

import Matrix.Matrix;

import java.io.*;
import java.net.Socket;

public class Client {

    public static final String address = "127.0.0.1";
    public static final int port = 12000;


    public static void main(String[] args) {
        try (Socket s = new Socket(address, port);
             DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        ) {

            Matrix matrixA = Matrix.inputFromTxt(args[0]);
            Matrix matrixB = Matrix.inputFromTxt(args[1]);
            String pathResultingMatrix = args[2];


            Matrix.outputElements(matrixA, dos);
            Matrix.outputElements(matrixB, dos);

            try (DataInputStream dis = new DataInputStream(s.getInputStream())) {

                Matrix.outputToTxt(Matrix.inputElements(dis), pathResultingMatrix);
                System.out.println("Matrix operation finished successfully)");

            }
            catch (IOException exc){
                System.out.println("Error executing operation");
                PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(pathResultingMatrix)));
                pw.println("Error executing operation");
                pw.flush();
                pw.close();
            }


        }

        catch (IOException e) {
            System.out.println("Error i/o");
            e.printStackTrace();
        }
    }
}
