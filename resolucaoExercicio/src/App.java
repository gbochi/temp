import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class App {
    public static void main(String[] args) throws Exception {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        String path = "C:\\Users\\guilh\\Documents\\Guilherme\\Udemy\\JAVA_2\\Secao_18\\in.txt";
        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();


        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            List<Employee> list = new ArrayList<>();

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }
        
            System.out.println("Email of people whose salary is more than 2000.00:");
            List<String> emails = list.stream()
                    .filter(x -> x.getSalary() > salary)
                    .map(x -> x.getEmail())
                    .sorted()
                    .collect(Collectors.toList());

            emails.forEach(System.out::println);

            double sumSalary = list.stream()
                    .filter(x -> x.getName().charAt(0) == 'M')
                    .map(x -> x.getSalary())
                    .reduce(0.0, (x,y) -> x + y);

            System.out.printf("Sum of salary of people whose name starts with 'M': %.2f", sumSalary);
            

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            sc.close();
        }

        sc.close();
    }
}
