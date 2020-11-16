import Bank.Bank;
import Bank.Tasks;
import java.util.Scanner;
import java.util.logging.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;

public class Start {

    private final static Logger LOGGER = Logger.getLogger(Start.class.getName());

    
    private static FileHandler fh = null;
    
    public static void main(String[] args) throws InterruptedException {
        
        
        // Logger Level: Set to severe when the project is done.
        LOGGER.setLevel(Level.SEVERE);

        SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
        try {
            fh = new FileHandler("./logs/StartLogs-"
                + format.format(Calendar.getInstance().getTime()) + ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            SimpleDateFormat format2 = new SimpleDateFormat("M-d");
            File file = new File("./Transactions/Transaction_" + format2.format(Calendar.getInstance().getTime()) + ".txt");
            if (file.createNewFile()) {
                System.out.println("Transaction created: " + file.getName());
            } else {
                System.out.println("Transaction file already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        fh.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fh);

        final Bank bank = new Bank();
        // Scanner userInput = new Scanner(System.in);

        String cmdPrompt = new String();
        cmdPrompt += "1. Employee Management.\n";
        cmdPrompt += "2. Customer Management.\n";
        cmdPrompt += "3. Customer Account Management.\n";
        cmdPrompt += "4. Account Trasactions.\n";
        cmdPrompt += "5. Exit.\n";
        cmdPrompt += "-------------------------------\n";

        boolean invalidInput = false;
        
        do {
            System.out.println(cmdPrompt);
            LOGGER.log(Level.INFO, "Printing options for initial management.");

            Scanner userInput = new Scanner(System.in);
            int choice = 6;

            try {
                choice = userInput.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Please input a valid integer.\n");
            }

            switch(choice) {
                case 1 :
                    employeeManagement(bank);
                    break;
                
                case 2 :
                    customerManagement(bank);
                    break;
                
                case 3:
                    customerAccountManagement(bank);
                    break;
                
                case 4:
                    transactionManagement(bank);
                    break;
                
                case 5:
                    String exiting = new String("Exiting application");
                    for (int i = 0; i < 3; i++) {
                        exiting += ".";
                        Thread.sleep(500);
                        System.out.print("\r" + exiting);
                    }
                    userInput.close();
                    System.exit(0);
                    break;

                default:
                    break;
            }
        } while (!invalidInput);

    }

    public static void employeeManagement(Bank bank) {

        // Scanner userInput = new Scanner(System.in);

        boolean validInput = false;

        String cmdPrompt = new String();
        cmdPrompt += "1. Insert New Employee.\n";
        cmdPrompt += "2. Remove Existing Employee.\n";
        cmdPrompt += "3. Show All Employees.\n";
        cmdPrompt += "4. Go Back.\n";

        do {
            Scanner userInput = new Scanner(System.in);

            System.out.println(cmdPrompt);
            int choice = 5;

            validInput = true;

            try {
                choice = userInput.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Please input a valid integer.\n");
                validInput = false;
            }

            switch(choice) {
                case 1 :
                    if (!Tasks.insertNewEmployee(bank))
                        LOGGER.log(Level.SEVERE, "Failed to insert new employee.");
                break;
                
                case 2 :
                    if (!Tasks.removeExistingEmployee(bank))
                        LOGGER.log(Level.SEVERE, "Failed to remove employee.");
                break;

                case 3:
                    Tasks.showAllEmployees(bank);
                    break;
                case 4:
                    return;

                default:
                    // Do nothing.
                    break;
            }
        } while(!validInput);

        // userInput.close();
    }

    public static void customerManagement(Bank bank) {
        // Scanner userInput = new Scanner(System.in);

        String cmdPrompt = new String();
        cmdPrompt += "1. Insert New Customer.\n";
        cmdPrompt += "2. Remove Existing Customer.\n";
        cmdPrompt += "3. Show All Customers.\n";
        cmdPrompt += "4. Go Back.\n";

        System.out.println(cmdPrompt);
        // int choice = userInput.nextInt();
        boolean validInput;

        do {
            Scanner userInput = new Scanner(System.in);

            System.out.println(cmdPrompt);
            int choice = 5;

            validInput = true;

            try {
                choice = userInput.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Please input a valid integer.\n");
                validInput = false;
            }
            switch(choice) {
                case 1 :
                    if (!Tasks.insertNewCustomer(bank))
                        LOGGER.log(Level.SEVERE, "Failed to insert new employee.");
                break;
                
                case 2 :
                    if (!Tasks.removeExistingCustomer(bank))
                        LOGGER.log(Level.SEVERE, "Failed to insert new employee.");
                break;

                case 3:
                    Tasks.showAllCustomers(bank);
                    break;
                
                    case 4:
                    return;

                default:
                    // Do nothing.
                    break;
            }

        } while(!validInput);

        // userInput.close();
    }

    public static void customerAccountManagement(Bank bank) {
        // Scanner userInput = new Scanner(System.in);

        String cmdPrompt = new String();
        cmdPrompt += "1. Insert New Account.\n";
        cmdPrompt += "2. Remove Existing Account.\n";
        cmdPrompt += "3. Show All Accounts.\n";

        System.out.println(cmdPrompt);
        // int choice = userInput.nextInt();
        boolean validInput;

        do {
            Scanner userInput = new Scanner(System.in);

            System.out.println(cmdPrompt);
            int choice = 5;

            validInput = true;

            try {
                choice = userInput.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Please input a valid integer.\n");
                validInput = false;
            }
            switch(choice) {
                case 1 :
                Tasks.insertNewAccount(bank);
                break;
                
                case 2 :
                Tasks.removeExistingAccount(bank);
                break;

                case 3:
                    Tasks.showAllAccounts(bank);
                    break;

                case 4:
                    return;

                default:
                    // Do nothing.
                    break;
            }
        } while(!validInput);

        // userInput.close();
    }

    public static void transactionManagement(Bank bank) {
        // Scanner userInput = new Scanner(System.in);

        String cmdPrompt = new String();
        cmdPrompt += "1. Deposit Money.\n";
        cmdPrompt += "2. Withdraw Money.\n";
        cmdPrompt += "3. Transfer Money.\n";
        cmdPrompt += "4. Go Back.\n";

        System.out.println(cmdPrompt);
        // int choice = userInput.nextInt();
        boolean validInput;

        do {
            Scanner userInput = new Scanner(System.in);

            System.out.println(cmdPrompt);
            int choice = 5;

            validInput = true;

            try {
                choice = userInput.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Please input a valid integer.\n");
                validInput = false;
            }
            switch(choice) {
                case 1 :
                Tasks.depositMoney(bank);
                break;
                
                case 2 :
                Tasks.withdrawMoney(bank);
                break;

                case 3:
                    Tasks.transferMoney(bank);
                    break;

                case 4:
                    return;

                default:
                    // Do nothing.
                    break;
            }

        } while(!validInput);

        // userInput.close();
    }
}