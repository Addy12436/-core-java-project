import java.util.ArrayList;
import java.util.Scanner;

 class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private boolean hasATMCard;
    private String atmPin;

    public BankAccount(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
        this.hasATMCard = false;
        this.atmPin = null;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public boolean hasATMCard() {
        return hasATMCard;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount, String pin) {
        if (!hasATMCard) {
            System.out.println("No ATM Card linked. Request an ATM Card first.");
            return;
        }
        if (!atmPin.equals(pin)) {
            System.out.println("Invalid ATM PIN.");
            return;
        }
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Money withdrawn successfully.");
        } else {
            System.out.println("Invalid withdraw amount or insufficient funds.");
        }
    }

    public void requestATMCard(String pin) {
        if (pin != null && !pin.isEmpty()) {
            hasATMCard = true;
            atmPin = pin;
            System.out.println("ATM Card requested and PIN set.");
        } else {
            System.out.println("Invalid PIN. ATM Card not issued.");
        }
    }

    public void discardATMCard() {
        hasATMCard = false;
        atmPin = null;
        System.out.println("ATM Card discarded.");
    }
}




public class BankingSystem {
    private ArrayList<BankAccount> accounts;
    private Scanner scanner;

    public BankingSystem() {
        accounts = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter account holder name: ");
        String accountHolderName = scanner.nextLine();
        BankAccount newAccount = new BankAccount(accountNumber, accountHolderName);
        accounts.add(newAccount);
        System.out.println("Account created successfully.");
    }

    public void deleteAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            accounts.remove(account);
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void viewBalance() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("Account Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void depositMoney() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character
            account.deposit(amount);
            System.out.println("Money deposited successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdrawMoney() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            if (!account.hasATMCard()) {
                System.out.println("No ATM Card linked. Request an ATM Card first.");
                return;
            }
            System.out.print("Enter withdraw amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character
            account.withdraw(amount, accountNumber);
            System.out.println("Money withdrawn successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void requestATMCard() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.requestATMCard(accountNumber);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void discardATMCard() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.discardATMCard();
        } else {
            System.out.println("Account not found.");
        }
    }

    private BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nBanking System Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Delete Account");
            System.out.println("3. View Balance");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Request ATM Card");
            System.out.println("7. Discard ATM Card");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deleteAccount();
                    break;
                case 3:
                    viewBalance();
                    break;
                case 4:
                    depositMoney();
                    break;
                case 5:
                    withdrawMoney();
                    break;
                case 6:
                    requestATMCard();
                    break;
                case 7:
                    discardATMCard();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();
        bankingSystem.showMenu();
    }
}