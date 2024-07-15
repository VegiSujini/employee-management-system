import java.util.concurrent.TimeUnit;

public class EmployeeMain {
public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();

        employeeService.addEmployee(new Employee(1, "Sujini", 23, "Engineering"));
        employeeService.addEmployee(new Employee(2, "Bharath", 18, "HR"));
        employeeService.addEmployee(new Employee(3, "Sankar", 26, "Marketing"));

        try{
            TimeUnit.SECONDS.sleep(1);
            Employee emp1 = employeeService.getEmployeeById(2);
            System.out.println("Employee found: "+emp1.getName());

            TimeUnit.SECONDS.sleep(1);
            System.out.println("All employees");
            employeeService.getAllEmployees().forEach(emp -> System.out.println(emp.getName()));

            TimeUnit.SECONDS.sleep(1);
            System.out.println("Engineering Employees: ");
            employeeService.getEmployeesByDepartment("Engineering").forEach(emp -> System.out.println(emp.getName()));


            TimeUnit.SECONDS.sleep(1);
            System.out.println("Employees Sorted by name: ");
            employeeService.getEmployeesSortedByName().forEach(emp -> System.out.println(emp.getName()));
        }catch(InterruptedException interruptedException){
            System.out.println("Interupted Exception : "+interruptedException.getMessage());
        }
        catch(EmployeeNotFoundException employeeNotFoundException)
        {
            System.out.println(employeeNotFoundException.getMessage().toString());
        }
        finally{
            employeeService.shutdownExecutor();
        }
    }
}
