import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class EmployeeService{
    private Map<Integer,Employee> employeeMap = new ConcurrentHashMap<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    public synchronized void addEmployee(Employee employee){
        executorService.submit(()->{
            employeeMap.put(employee.getId(), employee);
            System.out.println("Employee added : "+employee.getName());
        });
    }

    public Employee getEmployeeById(int id) throws EmployeeNotFoundException{
        if(!employeeMap.containsKey(id)){
            throw new EmployeeNotFoundException("Employee with ID "+id+" not found.");
        }
       return employeeMap.get(id);
    }

    public List<Employee> getAllEmployees(){
        return new ArrayList<>(employeeMap.values());
    }

    public List<Employee> getEmployeesByDepartment(String department){
        return employeeMap.values().stream().filter(emp -> emp.getDepartment().equals(department)).collect(Collectors.toList());
    }

    public List<Employee> getEmployeesSortedByName(){
        return employeeMap.values().stream().sorted(Comparator.comparing(Employee::getName)).collect(Collectors.toList());
    }

    public void shutdownExecutor(){
        executorService.shutdown();
    }

}