package socket.io;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class server {
	public static void main(String[] args) throws IOException {
			//服务器开始代码（可以写到服务器main函数里作为测试使用）
			ServerSocket server = new ServerSocket(9000);
			while(true){
			  Socket client = server.accept();
			  SSocket ss = new SSocket(client);
			  new Thread(ss).start();
			}
		}
	}
	class SSocket implements Runnable{
	    protected Socket client;
	    public SSocket (Socket client){
	        this.client = client;
	    }
	    public void run() {
	        DataInputStream input = null;
	        DataOutputStream output = null;
	        try{
	            System.out.println("Thread:strat()");
	            input = new DataInputStream(client.getInputStream());
	            output = new DataOutputStream(client.getOutputStream());
	            while(true){
	            	try {
	            		int read = input.read();
	            		System.out.println("server socket read:"+read);
		            	String inputs = input.readUTF();
			            String value =  inputs + "123";
			            output.writeUTF(value);
			            output.flush();
					} catch (Exception e) {
						 output.writeUTF("client is close");
				         output.flush();
					}
	            }
	        }catch(IOException e){
	            e.printStackTrace();
	        }
	    }
	}
