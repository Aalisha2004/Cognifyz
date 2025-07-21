import java.io.*;
import java.util.Scanner;

public class FileEncryptionDecryption{

    public static String caesarCipher(String text,int shift){
        StringBuilder result=new StringBuilder();
        for(int i=0;i<text.length();i++){
            char c=text.charAt(i);
            if(Character.isLetter(c)){
                char base=Character.isUpperCase(c)?'A':'a';
                result.append((char)((c-base+shift)%26+base));
            }else{
                result.append(c);  
            }
        }
        return result.toString();
    }

    public static void encryptFile(String inputFile,String outputFile,int shift)throws IOException{
        try(BufferedReader reader=new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer=new BufferedWriter(new FileWriter(outputFile))){

            String line;
            while((line=reader.readLine())!=null){
                String encryptedLine=caesarCipher(line,shift);
                writer.write(encryptedLine);
                writer.newLine();
            }
        }
    }

    public static void decryptFile(String inputFile,String outputFile,int shift)throws IOException{
        encryptFile(inputFile,outputFile,-shift); 
    }

    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);

        System.out.println("Choose an option: ");
        System.out.println("1. Encrypt a file");
        System.out.println("2. Decrypt a file");
        int choice=scanner.nextInt();
        scanner.nextLine();  

        System.out.print("Enter the file path: ");
        String inputFile=scanner.nextLine();

        System.out.print("Enter the output file path: ");
        String outputFile=scanner.nextLine();

        System.out.print("Enter the shift value (for encryption/decryption): ");
        int shift=scanner.nextInt();

        try{
            if(choice==1){
                encryptFile(inputFile,outputFile,shift);
                System.out.println("File encrypted successfully!");
            }else if(choice==2){
                decryptFile(inputFile,outputFile,shift);
                System.out.println("File decrypted successfully!");
            }else{
                System.out.println("Invalid choice!");
            }
        }catch(IOException e){
            System.out.println("An error occurred: "+e.getMessage());
        }
    }
}