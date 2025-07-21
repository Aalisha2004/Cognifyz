import java.util.*;

public class TicTacToe{
    public static void main(String args[]){
        char board[][]=new char[3][3];
        char currentPlayer='X';
        boolean gameFinished=false;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                board[i][j]=' ';
            }
        }
        while(!gameFinished){
            displayBoard(board);
            int row = getValidInput("Enter the row (0-2): ");
            int col = getValidInput("Enter the column (0-2): ");
            if(board[row][col]!=' '){
                System.out.println("Invalid move. Try again.");
                continue;
            }
            board[row][col] = currentPlayer;
            if(checkWin(board,currentPlayer)){
                System.out.println("Player "+currentPlayer+" wins!");
                gameFinished=true;
            }else if(checkDraw(board)){
                System.out.println("It's a draw!");
                gameFinished=true;
            }
            currentPlayer=(currentPlayer=='X')?'O':'X';
        }
        Scanner scanner=new Scanner(System.in);
        System.out.print("Do you want to play another round? (y/n): ");
        String answer=scanner.nextLine();
        if(answer.equalsIgnoreCase("y")){
            gameFinished=false;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    board[i][j]=' ';
                }
            }
        }
    }

    private static void displayBoard(char board[][]){
        System.out.println("-------------");
        for(int i=0;i<3;i++){
            System.out.print("| ");
            for(int j=0;j<3;j++){
                System.out.print(board[i][j]+" | ");
            }
            System.out.println("\n-------------");
        }
    }

    private static int getValidInput(String prompt){
        Scanner scanner=new Scanner(System.in);
        int input=-1;
        while(input<0||input>2){
            System.out.print(prompt);
            try{
                input=scanner.nextInt();
            }catch(Exception e){
                System.out.println("Invalid input. Please enter a number between 0 and 2.");
                scanner.nextLine();
            }
        }
        return input;
    }

    private static boolean checkWin(char board[][],char player){
        for(int i= 0;i<3;i++){
            if(board[i][0]==player && board[i][1]==player && board[i][2]==player){
                return true;
            }
        }
        for(int j=0;j<3;j++){
            if(board[0][j]==player && board[1][j]==player && board[2][j]==player){
                return true;
            }
        }
        if(board[0][0]==player && board[1][1]==player && board[2][2]==player){
            return true;
        }
        if(board[0][2]==player && board[1][1]==player && board[2][0]==player){
            return true;
        }
        return false;
    }

    private static boolean checkDraw(char board[][]){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]==' '){
                    return false;
                }
            }
        }
        return true;
    }
}