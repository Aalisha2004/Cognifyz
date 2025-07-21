import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount{
    private double balance;
    private Lock lock=new ReentrantLock(); 
    
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount){
        if(amount>0){
            balance+=amount;
            System.out.println(Thread.currentThread().getName()+" deposited "+amount+", New Balance: "+balance);
        }
    }

    public void withdraw(double amount){
        lock.lock(); 
        try{
            if(amount>0 && amount<=balance){
                balance-=amount;
                System.out.println(Thread.currentThread().getName()+" withdrew "+amount+", New Balance: "+balance);
            }else{
                System.out.println(Thread.currentThread().getName()+" tried to withdraw "+amount+", Insufficient Balance!");
            }
        }finally{
            lock.unlock();
        }
    }

    public double getBalance(){
        return balance;
    }
}

class BankWorker implements Runnable{
    private BankAccount account;
    private boolean deposit;
    private double amount;

    public BankWorker(BankAccount account,boolean deposit,double amount){
        this.account=account;
        this.deposit=deposit;
        this.amount=amount;
    }

    public void run(){
        if(deposit){
            account.deposit(amount); 
        }else{
            account.withdraw(amount); 
        }
    }
}

public class MultiThreadedBankApp{
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        
        System.out.print("Enter the initial balance of the bank account: ");
        double initialBalance=scanner.nextDouble();
        BankAccount account=new BankAccount(initialBalance);

        System.out.print("Enter the number of transactions: ");
        int numTransactions=scanner.nextInt();
        Thread threads[]=new Thread[numTransactions];

        for(int i=0;i<numTransactions;i++){
            System.out.print("Transaction "+(i+1)+": Enter 1 for deposit, 2 for withdrawal: ");
            int choice=scanner.nextInt();
            boolean deposit=(choice==1);

            System.out.print("Enter the amount for the transaction: ");
            double amount=scanner.nextDouble();

            threads[i]=new Thread(new BankWorker(account,deposit,amount),"Thread "+(i+1));
        }

        for(Thread t:threads){
            t.start();
        }
        
        for(Thread t:threads){
            try{
                t.join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("Final Balance: "+account.getBalance());
        scanner.close();
    }
}