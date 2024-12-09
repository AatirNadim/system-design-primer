// every entity should carry a single purpose

class TaxImplications {
    double calculateTax(Employee emp) {
        return 1.01 * emp.salary;
    }
}

class Employee {
    String name;
    int age;
    String level;
    double salary;

    TaxImplications taxImplications;

    public Employee(String name, int age, String level, double salary) {
        this.name = name;
        this.age = age;
        this.level = level;
        this.salary = salary;
    }

}