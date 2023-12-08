import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test
{
    public static void main(String[] args)
    {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

        scheduledThreadPoolExecutor.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket(7001);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (true)
                    try {
                        Socket socket = serverSocket.accept();
                        DataInputStream in = new DataInputStream(socket.getInputStream());
                        try{
                            String message = in.readUTF();
                            System.out.println("收到客户端发送的数据：" + message);
                        }catch (IOException e){
                            System.out.println("数据接收完毕！！！");
                        }
                        in.close();
                        socket.close();
                        continue;
                    }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            }
            , 1L, 2L, TimeUnit.SECONDS);
    }
}