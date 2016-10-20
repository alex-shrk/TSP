package Server;

import Exceptions.MatrixSizeError;
import Matrix.Matrix;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int port=12000;

    public static void main(String[] args) {
        while(true){
            try(ServerSocket serverSocket=new ServerSocket(port);
                Socket s=serverSocket.accept();
                DataInputStream dis=new DataInputStream(s.getInputStream());
                DataOutputStream dos=new DataOutputStream(s.getOutputStream());
                )
            {
                System.out.println("Client connected");

                Matrix matrixA = Matrix.inputElements(dis);
                Matrix matrixB = Matrix.inputElements(dis);

                Matrix.outputElements(Matrix.addition(matrixA,matrixB),dos);
                dos.flush();

            } catch (IOException | MatrixSizeError e) {
                e.printStackTrace();
            }
        }
    }

}
