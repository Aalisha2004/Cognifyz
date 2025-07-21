import java.util.*;

public class RandomPasswordGenerator{
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        Random random=new Random();

        System.out.print("Enter the desired password length: ");
        int length=scanner.nextInt();

        System.out.print("Include numbers? (y/n): ");
        boolean includeNumbers=scanner.next().equalsIgnoreCase("y");

        System.out.print("Include lowercase letters? (y/n): ");
        boolean includeLowercase=scanner.next().equalsIgnoreCase("y");

        System.out.print("Include uppercase letters? (y/n): ");
        boolean includeUppercase=scanner.next().equalsIgnoreCase("y");

        System.out.print("Include special characters? (y/n): ");
        boolean includeSpecialChars=scanner.next().equalsIgnoreCase("y");

        StringBuilder password=new StringBuilder();
        String numbers="0123456789";
        String lowercaseLetters="abcdefghijklmnopqrstuvwxyz";
        String uppercaseLetters="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialChars="!@#$%^&*()_+-=[]{}|;:,.<>?";

        while(password.length()<length){
            String charSet="";
            if(includeNumbers){
                charSet+=numbers;
            }
            if(includeLowercase){
                charSet+=lowercaseLetters;
            }
            if(includeUppercase){
                charSet+=uppercaseLetters;
            }
            if(includeSpecialChars){
                charSet+=specialChars;
            }

            if(charSet.isEmpty()){
                System.out.println("Please select at least one character type.");
                return;
            }
            int randomIndex=random.nextInt(charSet.length());
            password.append(charSet.charAt(randomIndex));
        }
        System.out.println("Generated password: "+password);
    }
}