import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;


public class main {

	public static void main(String[] args) {
		
		 // open a socket to 8081 in order to catch the redirect from gitHub to localhost:8081
        ServerSocket myServer = null;
   		try {
   			myServer = new ServerSocket(8082);
   			
   		} catch (IOException e) {
   			System.err.println("Couldn't open a socket..."+e.getMessage());
	    	System.exit(1);
   		}
   		
   	// get the code from the gitHub redirect's answer
   	 String code = "";
   	 try {
			Socket mySocket = myServer.accept();
			BufferedReader  in=new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
			String ans = in.readLine();
			
			while (false == ans.equals(null)){
				System.out.println(ans);
				ans = in.readLine();
			}
			
			
			// return ok and close the socket 
			/*BufferedWriter out=new BufferedWriter(new OutputStreamWriter(mySocket.getOutputStream()));
			out.write(HttpURLConnection.HTTP_OK);
			out.close();*/
			myServer.close();

		} catch (IOException e) {
			System.err.println("Couldn't write/read from the socket..."+e.getMessage());
	    	System.exit(1);
		}
		
	}
}
