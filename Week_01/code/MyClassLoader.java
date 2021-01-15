import java.io.*;

public class MyClassLoader extends ClassLoader {


    private final String path;

    public MyClassLoader(String path) {
        this.path = path;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = new byte[0];
       try{
           data = readByte(name);
       }catch (IOException e){

       }
        return super.defineClass(name,data,0,data.length);
    }


    private byte[] readByte(String name) throws IOException {
        File file = new File(path+name+".xlass");
        InputStream fis = null;
        ByteArrayOutputStream bos = null;
        byte[] bytes = new byte[1];
        byte[] readBytes =null;
        try{
            bos = new  ByteArrayOutputStream();
            fis = new FileInputStream(file);
            int length;
            while ((length=fis.read(bytes))!=-1){
                byte[] tempBytes =new byte[1];
                tempBytes[0]=(byte)(255-(int)bytes[0]);
                bos.write(tempBytes,0,length);
            }
            readBytes = bos.toByteArray();
        }catch (Exception e){
               e.printStackTrace();
        }finally {
            if(fis!=null){
                fis.close();;
            }
            if(bos!=null){
                bos.close();
            }
        }
        return  readBytes;
    }

}