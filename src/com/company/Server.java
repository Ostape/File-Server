package com.company;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    private static final int PORT = 34522;
    private static String path = "E:\\File Server\\fileServer\\";
    public static void main(String[] args)throws IOException{
        System.out.println("Server started!");
        serverSide();

    }
    private static void serverSide()throws IOException{
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                try (
                        Socket socket = server.accept(); // accepting a new client
                        DataInputStream input = new DataInputStream(socket.getInputStream());
                        DataOutputStream output = new DataOutputStream(socket.getOutputStream())
                ) {
                    if (readMessageClient(input, output)){
                        socket.close();
                        server.close();
                        break;
                    }
                }
                catch (EOFException e){}
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Unknown Exception");
        }
    }
    private static boolean readMessageClient(DataInputStream input, DataOutputStream output) throws IOException {
        int choose = input.readInt();

        switch (choose){
            case 1:
                String nameFile = input.readUTF();
                String text ="";
                if (existFile(nameFile)){
                    File file = new File(path+nameFile);
                    try (Scanner scanner = new Scanner(file)) {
                        text = scanner.nextLine();
                    } catch (FileNotFoundException e) {
                        System.out.println("No file found: " + path);
                    }
                    System.out.println("200 File is read");
                    output.writeUTF("200 File is read");
                    output.writeUTF(text);
                }else {
                    System.out.println("404 File not found");
                    output.writeUTF("404 File not found");
                }
                break;

            case 2:
                nameFile = input.readUTF();
                String temp = input.readUTF();
                if (existFile(nameFile)){
                    System.out.println("403 The file with this name already exists");
                    output.writeUTF("403 The file with this name already exists");
                }
                else{
                    File file = new File(path + nameFile);

                    try (FileWriter writer = new FileWriter(file)) {
                        writer.write(temp);
                    } catch (IOException e) {
                    }
                    System.out.println("200 File created");
                    output.writeUTF("200 File created");
                }
                break;

            case 3:
                nameFile = input.readUTF();
                if (existFile(nameFile)){
                    File file = new File(path + nameFile);
                    try {
                        file.delete();
                        System.out.println("200 File deleted");
                        output.writeUTF("200 File deleted");
                    }catch (Exception e){
                        System.out.println("Exception in deleting file!");
                    }
                }
                else {
                    System.out.println("404 File not found");
                    output.writeUTF("404 File not found");
                }
                break;

            case 4:
                System.out.println("Server close!");
               return true;
        }
        return false;
    }

    private static boolean existFile(String fileName){
        return  new File(path+fileName).exists();
    }

}