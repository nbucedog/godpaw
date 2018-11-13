package com.nbucedog.godpaw;/*
* MiniHttpServer.java
*/

import android.content.Context;
import android.content.res.AssetManager;
import android.provider.Settings;
import android.util.Log;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.StringTokenizer;

public class MiniHttpServer implements Runnable{

	String root;
	ServerSocket serverSocket;
	//int RolePool[];
	int maxnum;
	CardStation Card[];
	boolean stop = false;

	public MiniHttpServer(String root,int port,int[] Ranpool,int maxnum) {
		this.root = root;
		//this.RolePool = Ranpool;
		this.maxnum = maxnum;
		try {
			CardStation[] card = new CardStation[maxnum+1];
			for(int i=0;i<maxnum+1;i++){
				card[i] = new CardStation();
                card[i].Role = Ranpool[i];
            }
			Card = card;
		} catch (Exception e) {
			Log.d("DEMOLOG", "CardStaion error!");
			e.printStackTrace();
		}
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("cannot start the Server"+e.getLocalizedMessage());
		}
		if(serverSocket==null) System.exit(1);
		new Thread(this).start();
		Log.d("DEMOLOG", "start success!");
	}

	public void run(){
		while (!stop){
			try {
				Socket client = null;
				client = serverSocket.accept();
				if(client != null){
					System.out.println("Connect client:"+client);
					BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
					String line = in.readLine();
					System.out.println(line);
					String resource = line.substring(line.indexOf('/'),line.lastIndexOf('/')-5);
					resource = URLDecoder.decode(resource,"UTF-8");
					if(resource.equals("/")){
						resource = "/index.html";
					}
					String method = new StringTokenizer(line).nextElement().toString();
					while ((line = in.readLine()) != null){
						if(line.equals("")) break;
					}
					if("GET".equalsIgnoreCase(method)){
						boolean havetext = false;
						if(resource.indexOf('?') != -1){
							havetext = true;
						}
						else {
							havetext = false;
						}
						if(!havetext) {
							String type = ContentType(resource);
							if(type.equals("image/jpeg")||type.equals("image/gif")){
								imgService(root+resource,client);
							}
							else if(type.equals("application/x-ico")){
								icoService(root+resource,client);
							}
							else {
								fileService(root+resource, client);
							}
							Log.d("DEMOLOG", root+resource);
						} else{
							String text = resource.substring(resource.indexOf('=')+1);
							int arr_i;
							try {
								arr_i = Integer.parseInt(text);
							} catch (NumberFormatException e) {
								arr_i = 0;
							}
							if (arr_i<=0 || arr_i>maxnum) {
								Log.d("DEMOLOG", "MAXNUM");
								fileService(root+"/role/role0.html",client);
							} else {
								InetAddress inetAddress;
								String ipv4 = null;
								try {
									inetAddress = client.getInetAddress();
									ipv4 = inetAddress.toString();
								} catch (Exception e) {
									fileService(root+"/role/busy.html",client);
									e.printStackTrace();
								}
								Log.d("DEMOLOG", "IPv4:"+ipv4);
								if(Card[arr_i].readble(ipv4)){
									String roleid = Integer.toString(Card[arr_i].Role);
									Log.d("DEMOLOG", root+"/role/role"+roleid+".html");
									fileService(root+"/role/role"+roleid+".html",client);
								}
								else {
									fileService(root+"/role/peep.html",client);
								}
							}
						}
					}
					else if("POST".equalsIgnoreCase(method)){
						String postmsg = in.readLine();
						System.out.println(postmsg);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Log.d("DEMOLOG", "ServerSocket closed successed!");
	}
	void fileService(String file,Socket socket){
		String FileType = ContentType(file);
		try {
			PrintStream out = new PrintStream(socket.getOutputStream(),true);
			InputStream is = getClass().getResourceAsStream(file);
			out.println("HTTP/1.0 200 OK\r");
			Date now=new Date();
			out.println("Data: "+now+"\r");
			out.println("Server: MiniServer 1.0\r");
			out.println("Content-Length: "+is.available()+"\r");
			out.println("Content-Type: "+FileType+"\r");
			out.println("\r");

			byte data[] = new byte[is.available()];
			String a = Integer.toString(is.available());
			Log.d("DEMOLOG", a);
			is.read(data);
			out.write(data);
			out.close();
			is.close();
		} catch (IOException e) {
			System.out.println("File send file error!");
		}
	}
	void imgService(String file,Socket socket){
		String FileType = ContentType(file);
		try {
			PrintStream out = new PrintStream(socket.getOutputStream(),true);
			InputStream is = getClass().getResourceAsStream(file);
			out.println("HTTP/1.0 200 OK\r");
			Date now=new Date();
			out.println("Data: "+now+"\r");
			out.println("Server: MiniServer 1.0\r");
			out.println("Content-Length: 40000\r");
			out.println("Content-Type: "+FileType+"\r");
			out.println("\r");

			byte data[] = new byte[40000];
			String a = Integer.toString(is.available());
			Log.d("DEMOLOG", a);
			is.read(data);
			out.write(data);
			out.close();
			is.close();
		} catch (IOException e) {
			System.out.println("Img send file error!");
		}
	}
	void icoService(String file,Socket socket){
		String FileType = ContentType(file);
		try {
			PrintStream out = new PrintStream(socket.getOutputStream(),true);
			InputStream is = getClass().getResourceAsStream(file);
			out.println("HTTP/1.0 200 OK\r");
			Date now=new Date();
			out.println("Data: "+now+"\r");
			out.println("Server: MiniServer 1.0\r");
			out.println("Content-Length: 1150\r");
			out.println("Content-Type: "+FileType+"\r");
			out.println("\r");

			byte data[] = new byte[1150];
			String a = Integer.toString(is.available());
			Log.d("DEMOLOG", a);
			is.read(data);
			out.write(data);
			out.close();
			is.close();
		} catch (IOException e) {
			System.out.println("Ico send file error!");
		}
	}
	public static String ContentType(String name) {
		if (name.endsWith(".html")||name.endsWith(".htm")) {
			return "text/html";
		}else if (name.endsWith(".css")) {
			return "text/css";
		}else if (name.endsWith(".js")) {
			return "application/x-javascript";
		}else if (name.endsWith(".txt")||name.endsWith(".java")) {
			return "text/plain";
		}else if (name.endsWith(".gif")) {
			return "image/gif";
		}else if (name.endsWith(".class")) {
			return "application/octet-stream";
		}else if (name.endsWith(".jpg")||name.endsWith(".jpeg")) {
			return "image/jpeg";
		}else if (name.endsWith(".ico")){
			return "application/x-ico";
		}else {
			return "text/plain";
		}
	}
	public void stopService(){
		this.stop = true;
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}