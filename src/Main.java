import java.util.Scanner;
import java.util.Random;
import java.util.stream.*;
public class Main {
    public static boolean CheckTaskDesc(String taskDescriptions){
        return   taskDescriptions.length() < 50;
    }


    //This boolean method check weither the String username meets all the conditions listed and returns boolean value

    public static boolean CheckUserName(String UserName) {
        if (!UserName.contains("_")) {
            return false;
        }
        return UserName.length() <= 5;
    }

    //This boolean method check weither the String userPassword meets all the conditions listed and returns a boolean value
    public static boolean CheckPasswordComplexity(String userPassword) {
        return userPassword.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&]).{8,}$");
    }

    //This method check weither the String username meets all the conditions listed and returns a string response
    public static void registerUser(String userPassword, String UserName) {

        if (UserName.length() <= 5 && UserName.contains("_")) {

            System.out.println("Username successfully Captured");
        } else {
            System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length. ");
        }

        if (userPassword.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&]).{8,}$")) {
            System.out.println("Password successfully Captured");
        } else {
            System.out.println("Password is not correctly formatted. Please ensure that your password contains an underscore and is at least 8 characters in length, includes a capital letter, a number, and a special character.");
        }


    }

    //This method checks to see if stored username and password matches input username and password and returns a String response
    public static void returnLoginStatus(String userPassword, String UserName, String NewUserName, String NewPassword, String Name, String LastName) {
        {
            if (userPassword.equals(NewPassword) && UserName.equals(NewUserName)) {
                System.out.println("Login successful");
                System.out.println("Welcome " + Name + " " + LastName + ", It's great to see you again!");
            } else {
                System.out.println("Login Failed");
            }
        }
    }

    //This method checks to see if stored username and password matches input username and password and returns a boolean value
    public static boolean UserLogin(String userPassword, String UserName, String NewUserName, String NewPassword) {
        return UserName.equals(NewUserName) && userPassword.equals(NewPassword);
    }

    public static void main(String[] args) {

        //creating scanner to use in code
        Scanner scanner = new Scanner(System.in);

        //Text heading
        System.out.println("Create Account");
        System.out.println("______\n");

        //user input field for registration
        System.out.print("Name: ");
        String Name = scanner.nextLine();
        System.out.print("LastName: ");
        String LastName = scanner.nextLine();
        System.out.println("Create UserName");
        String UserName = scanner.nextLine();
        System.out.println("Create Password");
        String userPassword = scanner.nextLine();
        registerUser(userPassword, UserName);

        //Variables for second code


        //This while loop checks if username meets password complexity
        while (!CheckUserName(UserName)) {
            System.out.println("Please re-enter UserName:");
            UserName = scanner.nextLine();
        }


        //While loop for checking user Password Complexity
        while (!CheckPasswordComplexity(userPassword)) {

            System.out.println("Please re-enter Password:");
            userPassword = scanner.nextLine();
            registerUser(userPassword, UserName);
        }

        //User input for Login
        System.out.println("\nLogin Account");
        System.out.println("______\n");
        System.out.println("Please enter Username:");
        String NewUserName = scanner.nextLine();
        System.out.println("Please enter Password:");
        String NewPassword = scanner.nextLine();


        //While loop for checking and compairing stored username and making the appropriate decisions
        while (!UserLogin(userPassword, UserName, NewUserName, NewPassword)) {

            returnLoginStatus(userPassword, UserName, NewUserName, NewPassword, Name, LastName);
            System.out.println("Incorrect Username or Password");
            System.out.println("Please re-enter Username:");
            NewUserName = scanner.nextLine();
            System.out.println("Please re-enter Password:");
            NewPassword = scanner.nextLine();

        }


        //This method returns final login status
        returnLoginStatus(userPassword, UserName, NewUserName, NewPassword, Name, LastName);

        System.out.println("Welcome to EasyKanban:");
        System.out.println("______________________");
        System.out.println("(1).Add Task");
        System.out.println("(2).Show Report");
        System.out.println("(3).Quit");

        int numberOption = scanner.nextInt();

        if(numberOption == 1){
            //run firstMet
            secondMain();
        } else if(numberOption == 2){
            System.out.println("Coming Soon!");
        }else if(numberOption == 3){
            scanner.close();
        }
        //close scanner to prevent data leak
        scanner.close();
    }

    //second part of the code that will allow user to perform basic functions
    public static void secondMain() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of tasks: ");
        int taskNum = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left-over

        //Arrays that will be used to store data foreach individual task
        String[] taskNames = new String[taskNum];
        String[] taskDescriptions = new String[taskNum];
        String[] DeveloperDetails = new String[taskNum];
        int[] TaskStat = new int[taskNum];
        int[] TaskDuration = new int[taskNum];
        String[] newStatus =  new String[taskNum];

            //for loop that accumuliates user data  and stores them in an asigned array
        for (int i = 0; i < taskNum; i++) {
            System.out.print("Enter Developer Name: ");
            DeveloperDetails[i] = scanner.nextLine();

            System.out.print("Enter task name: ");
            taskNames[i] = scanner.nextLine();

            System.out.print("Enter task description: ");
            taskDescriptions[i] = scanner.nextLine();

            //this while loop checks that the user description letter count is not more than 50 characters
            while (!CheckTaskDesc(taskDescriptions[i])) {
                System.out.println("Description must be less than 50 characters!");
                taskDescriptions[i] = scanner.nextLine();
            }
            ;


            //Displayed Input options
            System.out.println("Task Status: ");
            System.out.println("(1). Todo ");
            System.out.println("(2). Doing ");
            System.out.println("(3). Done ");


            TaskStat[i] = scanner.nextInt();
                //conditions that check for input options and assigns choice to proper var
                if (TaskStat[i] == 1) {
                    newStatus[i] = "Todo";
                    break;
                } else if (TaskStat[i] == 2) {
                    newStatus[i] = "Doing";
                   break;
                } else if (TaskStat[i] == 3) {
                    newStatus[i] = "Done";
                    break;
                } else {
                    System.out.println("Invalid Input,Please choose options: 1,2 or 3:");
                    TaskStat[i] = scanner.nextInt();
                    if (TaskStat[i] == 1) {
                        newStatus[i] = "Todo";
                    } else if (TaskStat[i] == 2) {
                        newStatus[i] = "Doing";
                    } else if (TaskStat[i] == 3) {
                        newStatus[i] = "Done";
                    }else{
                        newStatus[i] = "Invalid Input";
                    }
                }
            {

            }
            System.out.print("Task Duration: ");
            TaskDuration[i] = scanner.nextInt();

            //CreateTaskID(taskNames,DeveloperDetails);

            System.out.println();
            scanner.nextLine();

        }

        // Calling method to display the tasks

        printTaskDetails(taskNum, taskNames, DeveloperDetails,taskDescriptions, newStatus ,TaskDuration);

        // returnTotalHours(taskNum,TaskDuration);

    }

    public static void printTaskDetails(int taskNum, String[] taskNames, String[] DeveloperDetails, String[] taskDescriptions,String[] newStatus ,int[] TaskDuration) {

        //Creating and using a random number generator method
        Random random=new Random();
        int min=100;
        int max=12500;

        //for loop that loops through arrays and display user data
        for (int j = 0; j < taskNum; j++) {
            int TaskNumber = random.nextInt(max - min + 1) + min;

            System.out.println("Task " + (j + 1) + ":"); // Adjust task index (1-based)
            System.out.println("Name: " + taskNames[j]);
            System.out.println("Task Number: " + TaskNumber);
            System.out.println("Description: " + taskDescriptions[j]);
            System.out.println("Developer Name: " + DeveloperDetails[j]);
            System.out.println("Task Duration: " + TaskDuration[j] + " hrs");
            System.out.println("Task Status: " +newStatus[j]);

            // Generate a unique task ID using the task index (j)
            String taskID = generateTaskID(taskNames[j], DeveloperDetails[j], j);
            System.out.println("Task ID: " + taskID);

            System.out.println(); // Add a blank line for readability
        }
    }

    //Method that generates random Task Id
    public static String generateTaskID(String taskName, String developerDetails, int taskIndex) {
        String firstTwoLetters = taskName.substring(0, Math.min(2, taskName.length())).toUpperCase();
        String lastTwoChars = developerDetails.substring(Math.max(0, developerDetails.length() - 2)).toUpperCase();

        // Use the provided task index (j) directly as part of the task ID
        return firstTwoLetters + ":" + taskIndex + ":" + lastTwoChars;
    }


    //Method that will add up and return amount of hours worked
    public static void returnTotalHours(int taskNum, int[] TaskDuration){

        TaskDuration = new int[taskNum];
        int sum = IntStream.of(TaskDuration).sum();

        System.out.println("Totals hours worked is: "+sum);
    }
}
