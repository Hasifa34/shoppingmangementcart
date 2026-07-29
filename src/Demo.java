import java.io.File;

import static java.nio.file.Files.exists;

public class Demo{
    public static void main(String[] args){
        File file = new File("sample.txt");
        if(file. exists()){
            System.out.println("File name:"+file.getName());
            System.out.println("File path:"+file.getAbsolutePath());
            System.out.println("File size:"+file.length()+"byte");
            System.out.println("canRead:"+file.canRead());
            System.out.println("can write"+file.canWrite());
        }
    }
}