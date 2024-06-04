import java.util.Scanner;
import java.util.Random;
import java.util.stream.*;

public class Main {
    public static boolean CheckTaskDesc(String taskDescriptions) {
        return taskDescriptions.length() < 50;
    }

    public static boolean CheckUserName(String UserName) {
        return UserName.contains("_") && UserName.length() <= 5;
    }

    public static boolean CheckPasswordComplexity(String userPassword) {
        return userPassword.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&]).{8,}$");
    }

    public static void registerUser(String userPassword, String UserName) {
        if (CheckUserName(UserName)) {
            System.out.println("Username successfully Captured");
        } else {
            System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
        }

        if (CheckPasswordComplexity(userPassword)) {
            System.out.println("Password successfully Captured");
        } else {
            System.out.println("Password is not correctly formatted. Please ensure that your password contains an underscore and is at least 8 characters in length, includes a capital letter, a number, and a special character.");
        }
    }

    public static void returnLoginStatus(String userPassword, String UserName, String NewUserName, String NewPassword, String Name, String LastName) {
        if (UserLogin(userPassword, UserName, NewUserName, NewPassword)) {
            System.out.println("Login successful");
            System.out.println("Welcome " + Name + " " + LastName + ", It's great to see you again!");
        } else {
            System.out.println("Login Failed");
        }
    }

    public static boolean UserLogin(String userPassword, String UserName, String NewUserName, String NewPassword) {
        return UserName.equals(NewUserName) && userPassword.equals(NewPassword);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Create Account");
        System.out.println("______\n");

        System.out.print("Name: ");
        String Name = scanner.nextLine();
        System.out.print("LastName: ");
        String LastName = scanner.nextLine();
        System.out.println("Create UserName");
        String UserName = scanner.nextLine();
        System.out.println("Create Password");
        String userPassword = scanner.nextLine();
        registerUser(userPassword, UserName);

        while (!CheckUserName(UserName)) {
            System.out.println("Please re-enter UserName:");
            UserName = scanner.nextLine();
        }

        while (!CheckPasswordComplexity(userPassword)) {
            System.out.println("Please re-enter Password:");
            userPassword = scanner.nextLine();
            registerUser(userPassword, UserName);
        }

        System.out.println("\nLogin Account");
        System.out.println("______\n");
        System.out.println("Please enter Username:");
        String NewUserName = scanner.nextLine();
        System.out.println("Please enter Password:");
        String NewPassword = scanner.nextLine();

        while (!UserLogin(userPassword, UserName, NewUserName, NewPassword)) {
            returnLoginStatus(userPassword, UserName, NewUserName, NewPassword, Name, LastName);
            System.out.println("Incorrect Username or Password");
            System.out.println("Please re-enter Username:");
            NewUserName = scanner.nextLine();
            System.out.println("Please re-enter Password:");
            NewPassword = scanner.nextLine();
        }

        returnLoginStatus(userPassword, UserName, NewUserName, NewPassword, Name, LastName);

        System.out.println("Welcome to EasyKanban:");
        System.out.println("______________________");
        System.out.println("(1).Add Task");
        System.out.println("(2).Show Report");
        System.out.println("(3).Quit");

        int numberOption = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (numberOption == 1) {
            secondMain();
        } else if (numberOption == 2) {
            System.out.println("Coming Soon!");
        } else if (numberOption == 3) {
            scanner.close();
        }
    }

    public static void secondMain() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of tasks: ");
        int taskNum = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left-over

        String[] taskNames = new String[taskNum];
        String[] taskDescriptions = new String[taskNum];
        String[] DeveloperDetails = new String[taskNum];
        int[] TaskStat = new int[taskNum];
        int[] TaskDuration = new int[taskNum];
        String[] newStatus = new String[taskNum];

        for (int i = 0; i < taskNum; i++) {
            System.out.print("Enter Developer Name: ");
            DeveloperDetails[i] = scanner.nextLine();

            System.out.print("Enter task name: ");
            taskNames[i] = scanner.nextLine();

            System.out.print("Enter task description: ");
            taskDescriptions[i] = scanner.nextLine();

            while (!CheckTaskDesc(taskDescriptions[i])) {
                System.out.println("Description must be less than 50 characters!");
                taskDescriptions[i] = scanner.nextLine();
            }

            boolean validStatus = false;
            while (!validStatus) {
                System.out.println("Task Status: ");
                System.out.println("(1). Todo ");
                System.out.println("(2). Doing ");
                System.out.println("(3). Done ");

                TaskStat[i] = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (TaskStat[i]) {
                    case 1:
                        newStatus[i] = "Todo";
                        validStatus = true;
                        break;
                    case 2:
                        newStatus[i] = "Doing";
                        validStatus = true;
                        break;
                    case 3:
                        newStatus[i] = "Done";
                        validStatus = true;
                        break;
                    default:
                        System.out.println("Invalid Input, Please choose options: 1, 2 or 3:");
                        break;
                }
            }

            System.out.print("Task Duration (hours): ");
            TaskDuration[i] = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        }

        printTaskDetails(taskNum, taskNames, DeveloperDetails, taskDescriptions, newStatus, TaskDuration);
        returnTotalHours(taskNum, TaskDuration);
    }

    public static void printTaskDetails(int taskNum, String[] taskNames, String[] DeveloperDetails, String[] taskDescriptions, String[] newStatus, int[] TaskDuration) {
        Random random = new Random();
        int min = 100;
        int max = 12500;

        for (int j = 0; j < taskNum; j++) {
            int TaskNumber = random.nextInt(max - min + 1) + min;

            System.out.println("Task " + (j + 1) + ":");
            System.out.println("Name: " + taskNames[j]);
            System.out.println("Task Number: " + TaskNumber);
            System.out.println("Description: " + taskDescriptions[j]);
            System.out.println("Developer Name: " + DeveloperDetails[j]);
            System.out.println("Task Duration: " + TaskDuration[j] + " hrs");
            System.out.println("Task Status: " + newStatus[j]);

            String taskID = generateTaskID(taskNames[j], DeveloperDetails[j], j);
            System.out.println("Task ID: " + taskID);

            System.out.println();
        }
    }

    public static String generateTaskID(String taskName, String developerDetails, int taskIndex) {
        String firstTwoLetters = taskName.substring(0, Math.min(2, taskName.length())).toUpperCase();
        String lastTwoChars = developerDetails.substring(Math.max(0, developerDetails.length() - 2)).toUpperCase();
        return firstTwoLetters + ":" + (taskIndex + 1) + ":" + lastTwoChars;
    }

    public static void returnTotalHours(int taskNum, int[] TaskDuration) {
        int sum = IntStream.of(TaskDuration).sum();
        System.out.println("Total hours worked: " + sum);
    }
}
