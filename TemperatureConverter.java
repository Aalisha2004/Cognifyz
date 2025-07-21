import java.util.*;

public class TemperatureConverter{
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);

        System.out.print("Enter the temperature value: ");
        double temperature=scanner.nextDouble();

        System.out.print("Enter the unit of measurement (C for Celsius, F for Fahrenheit): ");
        char unit=scanner.next().charAt(0);

        double convertedTemperature;
        char convertedUnit;
        
        if(unit=='C'||unit=='c'){
            convertedTemperature=(temperature*9/5)+32;
            convertedUnit='F';
        }else if(unit=='F'||unit=='f'){
            convertedTemperature=(temperature-32)*5/9;
            convertedUnit='C';
        }else{
            System.out.println("Invalid unit of measurement. Please enter C for Celsius or F for Fahrenheit.");
            scanner.close();
            return;
        }

        System.out.printf("The converted temperature is %.2f %c\n",convertedTemperature,convertedUnit);
        scanner.close();
    }
}