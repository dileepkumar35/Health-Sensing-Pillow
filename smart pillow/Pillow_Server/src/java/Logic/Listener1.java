/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sumit
 */
public class Listener1 {
     public static void main(String[] args) throws IOException {
    
        ServerSocket listener = new ServerSocket(9092);
        
        try{
            while(true){
                Socket socket = listener.accept();
                socket.setKeepAlive(true);
                System.out.println("Client Connected");
                try{
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String data=in.readLine();
                    System.out.println("Client response: " + data);
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    
                 
                    
                       
                        
                                            
                      if(!data.equals(""))
                    {
                        String a[]=data.split("#");
                        System.out.println("");
                        System.out.println(a[0]);// R
                        System.out.println(a[1]);// M
                        System.out.println(a[2]);// L
                      
                       
                        String  pattern = "dd-MM-yyyy";
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                            String tdate = simpleDateFormat.format(new Date());
                            System.out.println(tdate);
                        
                            String pattern1 = "hh-mm-ss";
                           SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);

                           String tdate1 = simpleDateFormat1.format(new Date());
                           tdate1=tdate1.replace(":", "-");
                           System.out.println(tdate1);
                        
                                            
                        
                        
                        File f2=new File(info.py_path1+"pillow_test/pillow_test.txt");
                        FileWriter fw2=new FileWriter(f2);
                        fw2.write(a[0]+"\n"+a[1]+"\n"+a[2]);
                        fw2.close();
                        
                        File f22=new File(info.py_path1+"pillow_test.txt");
                        FileWriter fw22=new FileWriter(f22);
                        fw22.write(a[0]+"#"+a[1]+"#"+a[2]);
                        fw22.close();
                       String csvFilePath = "D:/pillow.csv";
                            File f=new File(csvFilePath);
                            if(!f.exists())
                            {
                            f.createNewFile();
                            System.out.println("File Created");
                            String[] rowData = {"Right", "Middle","Left","Date","Time"};
                            appendToCSV(csvFilePath,rowData);
                            String[] rowData1 = {a[0], a[1],a[2], tdate,tdate1};
                            System.out.println(rowData1[0]+"----------"+rowData1[1]);
                            appendToCSV(csvFilePath,rowData1);

                            }
                            else{
                            String[] rowData = {a[0], a[1],a[2], tdate,tdate1};
                            System.out.println(rowData[0]+"----------"+rowData[1]);
                            appendToCSV(csvFilePath, rowData);
                            }
                            
                            
                            
                             String csvFilePath1 = "D:/pillow1.csv";
                            File f1=new File(csvFilePath1);
                            if(!f1.exists())
                            {
                            f1.createNewFile();
                            System.out.println("File Created");
                            String[] rowData = {"Right", "Middle","Left","Date","Time"};
                            appendToCSV(csvFilePath,rowData);
                            if(Integer.parseInt(a[0])>10)
                            {
                            a[0]="1";
                            }
                             if(Integer.parseInt(a[1])>10)
                            {
                            a[1]="1";
                            }
                              if(Integer.parseInt(a[2])>10)
                            {
                            a[2]="1";
                            }
                            String[] rowData1 = {a[0], a[1],a[2], tdate,tdate1};
                            System.out.println(rowData1[0]+"----------"+rowData1[1]);
                            appendToCSV(csvFilePath,rowData1);

                            }
                            else{
                                if(Integer.parseInt(a[0])>10)
                            {
                            a[0]="1";
                            }
                             if(Integer.parseInt(a[1])>10)
                            {
                            a[1]="1";
                            }
                              if(Integer.parseInt(a[2])>10)
                            {
                            a[2]="1";
                            }
                            String[] rowData = {a[0], a[1],a[2], tdate,tdate1};
                            System.out.println(rowData[0]+"----------"+rowData[1]);
                            appendToCSV(csvFilePath, rowData);
                            }
                            
                            
                            
                    }  
                   
                    out.flush();
                } finally {
                    socket.close();
                }
            }
           } finally {
            listener.close();
          }
     }
     
     
    public static void appendToCSV(String filePath, String[] data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

            // Append data to CSV
            for (int i = 0; i < data.length; i++) {
                writer.append(data[i]);
                if (i != data.length - 1) {
                    writer.append(",");
                }
            }
            writer.append("\n");

            writer.close();
            System.out.println("Data appended successfully to the CSV file.");

        } catch (IOException e) {
            System.out.println("An error occurred while appending data to the CSV file: " + e.getMessage());
        }
    }
     
     
}
