package hoxtonr.frame.Tools;

import java.io.*;

public class FileReader {
    public String readFileByByte(String filePath){
        long beginTime =System.currentTimeMillis();
        StringBuffer stringBuffer=new StringBuffer();
        byte[] buffer=new byte[2048];

        int count=0;
        File file=new File(filePath);
        try{
            InputStream inputStream=new FileInputStream(file);
            while(-1!=(count=inputStream.read(buffer))){
                stringBuffer.append(new String(buffer,0,count));
            }
            inputStream.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return stringBuffer.toString();
    }
    public static void main(String[] args){

    }
}
