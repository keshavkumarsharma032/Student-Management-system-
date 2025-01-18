import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Student {
    private int id;
    private String name, phone, course, address;

    File myFile = new File("Student.txt");

    public int addStudent(Scanner scanner, int iD) {
        scanner.nextLine();
        id = iD;
        try {

            if (!myFile.exists()) {
                myFile.createNewFile();
            }

            FileWriter fileWriter = new FileWriter("Student.txt", true);

            id++;

            System.out.print("\tStudent name    :");
            name = scanner.nextLine();
            System.out.print("\tStudent course    :");
            course = scanner.nextLine();
            System.out.print("\tStudent phone    :");
            phone = scanner.nextLine();
            System.out.print("\tStudent address    :");
            address = scanner.nextLine();

            fileWriter.append("ID       : " + Integer.toString(id) + "\n");
            fileWriter.append("Name     : " + name + "\n");
            fileWriter.append("Course   : " + course + "\n");
            fileWriter.append("Phone    : " + phone + "\n");
            fileWriter.append("Address  : " + address + "\n");
            fileWriter.append("-----------------------------\n");

            fileWriter.close();

            FileWriter idFileWriter = new FileWriter("ID.txt", true);
            idFileWriter.write(Integer.toString(id) + "\n");
            idFileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void printAll() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Student.txt"));

            String line = bufferedReader.readLine();

            if (line != null) {
                while (line != null) {
                    System.out.println("\t" + line);
                    line = bufferedReader.readLine();
                }
            } else {
                System.out.println("\tFile is Empty!");
            }

            bufferedReader.close();

        } catch (IOException e) {
            System.out.println("\tFile doesn't exist add a student first " + e.getMessage());
        }
    }

    public void searchStudent(int iD) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Student.txt"));

            String line = bufferedReader.readLine();
            if (line != null) {

                do {
                    id = Integer.parseInt(line.split(":")[1].trim());
                    name = bufferedReader.readLine();
                    phone = bufferedReader.readLine();
                    course = bufferedReader.readLine();
                    address = bufferedReader.readLine();
                    bufferedReader.readLine();

                    if (iD == id) {
                        System.out.println("\n\tID       : " + id);
                        System.out.println("\t" + name);
                        System.out.println("\t" + phone);
                        System.out.println("\t" + course);
                        System.out.println("\t" + address);
                        break;
                    }

                } while ((line = bufferedReader.readLine()) != null);

            } else {
                System.out.println("\tFile is Empty!");
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int iD) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Student.txt"));

            File tempFile = new File("temp.txt");
            String line = bufferedReader.readLine();
            if (line != null) {

                tempFile.createNewFile();

                FileWriter fileWriter = new FileWriter("temp.txt", true);

                do {
                    id = Integer.parseInt(line.split(":")[1].trim());
                    name = bufferedReader.readLine();
                    phone = bufferedReader.readLine();
                    course = bufferedReader.readLine();
                    address = bufferedReader.readLine();
                    String desh = bufferedReader.readLine();

                    if (iD != id) {
                        fileWriter.append("ID       : " + id + "\n");
                        fileWriter.append(name + "\n");
                        fileWriter.append(phone + "\n");
                        fileWriter.append(course + "\n");
                        fileWriter.append(address + "\n");
                        fileWriter.append(desh + "\n");
                    }

                } while ((line = bufferedReader.readLine()) != null);

                fileWriter.close();

            } else {
                System.out.println("\tFile is Empty!");
            }

            bufferedReader.close();

            if (myFile.delete()) {
                tempFile.renameTo(myFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Scanner scanner, int iD) {
        deleteStudent(iD);
        
        scanner.nextLine();
        try {

            FileWriter fileWriter = new FileWriter("Student.txt", true);

            System.out.print("\tStudent name    :");
            name = scanner.nextLine();
            System.out.print("\tStudent course    :");
            course = scanner.nextLine();
            System.out.print("\tStudent phone    :");
            phone = scanner.nextLine();
            System.out.print("\tStudent address    :");
            address = scanner.nextLine();

            fileWriter.append("ID       : " + Integer.toString(iD) + "\n");
            fileWriter.append("Name     : " + name + "\n");
            fileWriter.append("Course   : " + course + "\n");
            fileWriter.append("Phone    : " + phone + "\n");
            fileWriter.append("Address  : " + address + "\n");
            fileWriter.append("-----------------------------\n");

            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll(File iDFile) {
        if (myFile.delete()&&iDFile.delete()) {
            System.out.print("\tAll students deleted...");
        }else if(!myFile.exists()){
            System.out.print("\tFile doesn't exist!");
        }else{
            System.out.print("\tcouldn't delete!");
        }
    }
}

public class StudentManagementSystem {

    public static int lastID() {

        String iD = null, id = null;

        try {
            FileReader idFileReader = new FileReader("ID.txt");
            BufferedReader bufferedReader = new BufferedReader(idFileReader);
            iD = bufferedReader.readLine();

            while (iD != null) {
                if (iD != null) {
                    id = iD;
                }
                iD = bufferedReader.readLine();
            }

            bufferedReader.close();
            idFileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (id != null) {
            return Integer.parseInt(id);
        } else {
            return 0;
        }
    }

    public static boolean isIdValid(int id) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ID.txt"));
            String iD = bufferedReader.readLine();
            if (iD != null) {
                do {
                    if (id == Integer.parseInt(iD)) {
                        bufferedReader.close();
                        return true;
                    }
                } while ((iD = bufferedReader.readLine()) != null);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("\tFile doesn't exist add a student first " + e.getMessage());
        }
        return false;
    }

    public static void clearScrean() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        Student s1 = new Student();
        int id;

        File idFile = new File("ID.txt");
        try {
            if (!idFile.exists()) {
                idFile.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        int iD = lastID();

        while (true) {

            clearScrean();

            System.out.println("\n\t________________________\n");
            System.out.println("\t   STUDENT MANAGEMENT");
            System.out.println("	________________________\n");
            System.out.println("\t----------------------------");
            System.out.println("\t1.Add Student");
            System.out.println("\t2.Delete Student");
            System.out.println("\t3.Update Student");
            System.out.println("\t4.Search Student");
            System.out.println("\t5.Print All Students");
            System.out.println("\t6.Delete All Students");
            System.out.println("\t7.Exit");
            System.out.println("\t----------------------------");
            System.out.println("\t____________________________");

            System.out.print("\tEnter your choice: ");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    iD = s1.addStudent(scanner, iD);
                    System.out.println("\tStudent Added!");
                    break;
                case 2:
                    System.out.print("\tEnter id... ");
                    id = scanner.nextInt();
                    if (isIdValid(id)) {
                        s1.deleteStudent(id);
                        System.out.print("\tDelete successfully...");
                    } else {
                        System.out.println("\tId doesn't exist!");
                    }
                    break;
                case 3:
                    System.out.print("\tEnter id... ");
                    id = scanner.nextInt();
                    if (isIdValid(id)) {
                        s1.updateStudent(scanner, id);
                        System.out.println("\tUpdated successfully...");
                    } else {
                        System.out.println("\tId doesn't exist!");
                    }
                    break;
                case 4:
                    System.out.print("\tEnter id... ");
                    id = scanner.nextInt();
                    if (isIdValid(id)) {
                        s1.searchStudent(id);
                    } else {
                        System.out.println("\tId doesn't exist!");
                    }
                    break;
                case 5:
                    s1.printAll();
                    break;
                case 6:
                    s1.deleteAll(idFile);
                    iD = 0;
                    break;
                case 7:
                    scanner.close();
                    System.out.println("\tyou're exited...\n");
                    System.exit(0);
                default:
                    System.out.println("\tInvalid input");
            }
            scanner.nextLine();
            System.out.println("\n\tPress Enter to continue...");
            scanner.nextLine();
        }
    }
}