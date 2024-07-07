package threads;

import constants.MyServerConstant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ServerThread extends Thread{
    private ServerSocket serverSocket;
    private List<HandleClientThread> clients;

    public ServerThread() throws IOException {
        int port = pokupiPort();
        serverSocket = new ServerSocket(port);
        clients = new ArrayList<>();
    }

    @Override
    public void run() {
        while (!serverSocket.isClosed()){
            System.out.println("Cekam klijenta");
            try {
                Socket socket = serverSocket.accept();
                HandleClientThread clientThread = new HandleClientThread(socket);
                clientThread.start();
                clients.add(clientThread);
                System.out.println("Klijent se uspesno povezao");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stopAllClientThreads();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    private void stopAllClientThreads(){
        for(HandleClientThread clientThread : clients){
            try {
                clientThread.getSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int pokupiPort() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(MyServerConstant.FILE_PATH_SERVER));
        return Integer.valueOf(properties.getProperty(MyServerConstant.PORT));
    }
}
