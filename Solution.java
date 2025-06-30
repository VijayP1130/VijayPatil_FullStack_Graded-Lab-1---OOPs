import java.util.Random;
import java.util.Scanner;

public class EmployeeCredentialApp {

    // Employee class
    static class Employee {
        private String firstName;
        private String lastName;

        public Employee(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }

    // CredentialService class
    static class CredentialService {

        public String generateEmailAddress(String firstName, String lastName, String department) {
            return firstName.toLowerCase() + lastName.toLowerCase() + "@" + department.toLowerCase() + ".abc.com";
        }

        public String generatePassword() {
            String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String smallLetters = "abcdefghijklmnopqrstuvwxyz";
            String numbers = "0123456789";
            String specialCharacters = "!@#$%^&*_=+-/.?<>)";

            String allChars = capitalLetters + smallLetters + numbers + specialCharacters;
            Random random = new Random();
            StringBuilder password = new StringBuilder();

            // At least one character of each type
            password.append(capitalLetters.charAt(random.nextInt(capitalLetters.length())));
            password.append(smallLetters.charAt(random.nextInt(smallLetters.length())));
            password.append(numbers.charAt(random.nextInt(numbers.length())));
            password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

            // Remaining characters (to total 8)
            for (int i = 4; i < 8; i++) {
                password.append(allChars.charAt(random.nextInt(allChars.length())));
            }

            return password.toString();
        }

        public void showCredentials(Employee emp, String email, String password) {
            System.out.println("Dear " + emp.getFirstName() + ", your generated credentials are as follows");
            System.out.println("Email     ---> " + email);
            System.out.println("Password  ---> " + password);
        }
    }

    // Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter First Name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = sc.nextLine();

        System.out.println("\nPlease enter the department from the following:");
        System.out.println("1. Technical");
        System.out.println("2. Admin");
        System.out.println("3. Human Resource");
        System.out.println("4. Legal");

        int choice = sc.nextInt();
        String department = "";

        switch (choice) {
            case 1:
                department = "tech";
                break;
            case 2:
                department = "admin";
                break;
            case 3:
                department = "hr";
                break;
            case 4:
                department = "legal";
                break;
            default:
                System.out.println("Invalid department selection. Exiting program.");
                return;
        }

        Employee emp = new Employee(firstName, lastName);
        CredentialService cs = new CredentialService();

        String email = cs.generateEmailAddress(firstName, lastName, department);
        String password = cs.generatePassword();

        cs.showCredentials(emp, email, password);
    }
}
