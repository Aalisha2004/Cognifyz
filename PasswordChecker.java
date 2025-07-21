import java.util.*;

public class PasswordChecker{
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password=scanner.nextLine();
        int strength=checkPasswordStrength(password);
        System.out.println("Password Strength: "+strength);
    }

    private static int checkPasswordStrength(String password){
        int strength=0;
        if(password.length()>=8){
            strength++;
        }
        for(char c:password.toCharArray()){
            if(Character.isUpperCase(c)){
                strength++;
                break;
            }
        }
        for(char c:password.toCharArray()){
            if(Character.isLowerCase(c)){
                strength++;
                break;
            }
        }
        for(char c:password.toCharArray()){
            if(Character.isDigit(c)){
                strength++;
                break;
            }
        }
        for(char c:password.toCharArray()){
            if(!Character.isLetterOrDigit(c)){
                strength++;
                break;
            }
        }
        return strength;
    }
}