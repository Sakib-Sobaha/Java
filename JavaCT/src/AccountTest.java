interface Account {
    // the following method returns the balance
    double balance();

    // the following method subtracts the amount from the balance,
    // the amount in the method can’t be negative, the balance can’t be negative
    void debit(double amount) throws InvalidAmountException, InvalidBalanceException;

    // the following method adds the amount directly to the balance,
    // the amount in the method can’t be negative
    void credit(double amount) throws InvalidAmountException;


}

class InvalidAmountException extends Exception {
    private double invalidamount;

    InvalidAmountException(double amount) {
        this.invalidamount = amount;
    }

    @Override
    public String toString() {
        return "The given amount can't be negative:" +this.invalidamount;
    }
}
class InvalidBalanceException extends Exception {
    private double invalidbalance;

    InvalidBalanceException(double amount) {
        this.invalidbalance  = amount;
    }
    public void  getinvalidbalance(Account a){
        this.invalidbalance =  a.balance() - this.invalidbalance;
    }

    @Override
    public String toString() {
        return "The account balance can't be less than zero: " + this.invalidbalance;
    }
}
class SavingsAccount implements Account{
    private double balance;

    public double balance(){
        return this.balance;
    }

    SavingsAccount(){
        this.balance = balance();
    }

    public void credit(double amount) throws InvalidAmountException{
        System.out.println("Credit Request: "+amount);
        if(amount < 0){
            throw new InvalidAmountException(amount);
        }
        else{
            this.balance+=amount;
        }
    }

    public void debit(double amount) throws InvalidAmountException,InvalidBalanceException{
        Account a = new SavingsAccount();
        System.out.println("Debit Request: "+amount);
        if(amount < 0){
            throw new InvalidAmountException(amount);
        }
        else if(amount > this.balance) {
            throw new InvalidBalanceException(amount);
        }
        else {
            this.balance-=amount;
        }
    }
}



public class AccountTest {
    public static void main(String[] args)  {
        Account a = new SavingsAccount();
        try {
            a.credit(1000);
        } catch (InvalidAmountException e) {
            System.out.println(e);
        }
        System.out.println("Balance: " + a.balance());
        try {
            a.credit(-100);
        } catch (InvalidAmountException e) {
            System.out.println(e);
        }
        System.out.println("Balance: " + a.balance());
        try {
            a.debit(2000);
        } catch (InvalidAmountException e) {
            System.out.println(e);
        } catch (InvalidBalanceException e) {
            e.getinvalidbalance(a);
            System.out.println(e);
        }
        System.out.println("Balance: " + a.balance());
        try {
            a.debit(200);
        } catch (InvalidAmountException e) {
            System.out.println(e);
        } catch (InvalidBalanceException e) {
            e.getinvalidbalance(a);
            System.out.println(e);
        }
        System.out.println("Balance: " + a.balance());
        try {
            a.debit(-200);
        } catch (InvalidAmountException e) {
            System.out.println(e);
        } catch (InvalidBalanceException e) {
            e.getinvalidbalance(a);
            System.out.println(e);
        }
        System.out.println("Balance: " + a.balance());
    }
}

/* Output of the main method:
Credit Request: 1000.0
Balance: 1000.0
Credit Request: -100.0
The given amount can’t be negative: -100.0
Balance: 1000.0
Debit Request: 2000.0
The account balance can’t be less than zero: -1000.0
Balance: 1000.0
Debit Request: 200.0
Balance: 800.0
Debit Request: -200.0
The given amount can’t be negative: -200.0
Balance: 800.0
*/