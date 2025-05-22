import java.io.*;
import java.util.*;

abstract class User {
    private String id;
    private String username;
    private String password;

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }

    public abstract void displayRole();

    public void updateInfo(Scanner scanner) {
        System.out.print("Enter New Username: ");
        this.username = scanner.nextLine();
        System.out.print("Enter New Password: ");
        this.password = scanner.nextLine();
        System.out.println("Information updated successfully!");
    }
}
class Admin extends User {
    public Admin(String id, String username, String password) {
        super(id, username, password);
    }
    public void manageEmployees() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Add Employee");
        System.out.println("2. List All Employees");
        System.out.println("3. Search Employee by ID");
        System.out.println("4. Delete Employee");
        System.out.println("5. Update Employee");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> addEmployee(scanner);
            case 2 -> listEmployees();
            case 3 -> searchEmployee(scanner);
            case 4 -> deleteEmployee(scanner);
            case 5 -> updateEmployee(scanner);
            default -> System.out.println("Invalid choice!");
        }
    }

    private void addEmployee(Scanner scanner) throws IOException {
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Employee Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Employee Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Employee Type: ");
        String type = scanner.nextLine();

        FileWriter fw = new FileWriter("employees.txt", true);
        fw.write(id + "," + username + "," + password + "," + type + "\n");
        fw.close();
        System.out.println("Employee added successfully!");
    }

    private void listEmployees() throws IOException {
        File file = new File("employees.txt");
        if (!file.exists()) {
            System.out.println("No employees found!");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        System.out.println("Employees:");
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    private void searchEmployee(Scanner scanner) throws IOException {
        System.out.print("Enter Employee ID to search: ");
        String searchId = scanner.nextLine();

        File file = new File("employees.txt");
        if (!file.exists()) {
            System.out.println("No employees found!");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        boolean found = false;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(searchId + ",")) {
                System.out.println("Employee Found: " + line);
                found = true;
                break;
            }
        }
        br.close();

        if (!found) {
            System.out.println("Employee not found!");
        }
    }

    private void deleteEmployee(Scanner scanner) throws IOException {
        System.out.print("Enter Employee ID to delete: ");
        String deleteId = scanner.nextLine();

        File file = new File("employees.txt");
        if (!file.exists()) {
            System.out.println("No employees found!");
            return;
        }

        File tempFile = new File("temp.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

        String line;
        boolean found = false;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(deleteId + ",")) {
                found = true;
                continue;
            }
            bw.write(line + "\n");
        }
        br.close();
        bw.close();

        if (found) {
            file.delete();
            tempFile.renameTo(file);
            System.out.println("Employee deleted successfully!");
        } else {
            tempFile.delete();
            System.out.println("Employee not found!");
        }
    }

    private void updateEmployee(Scanner scanner) throws IOException {
        System.out.print("Enter Employee ID to update: ");
        String updateId = scanner.nextLine();

        File file = new File("employees.txt");
        if (!file.exists()) {
            System.out.println("No employees found!");
            return;
        }

        File tempFile = new File("temp.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

        String line;
        boolean found = false;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(updateId + ",")) {
                System.out.print("Enter New Username: ");
                String username = scanner.nextLine();
                System.out.print("Enter New Password: ");
                String password = scanner.nextLine();
                System.out.print("Enter New Type: ");
                String type = scanner.nextLine();

                bw.write(updateId + "," + username + "," + password + "," + type + "\n");
                found = true;
            } else {
                bw.write(line + "\n");
            }
        }
        br.close();
        bw.close();

        if (found) {
            file.delete();
            tempFile.renameTo(file);
            System.out.println("Employee updated successfully!");
        } else {
            tempFile.delete();
            System.out.println("Employee not found!");
        }
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Admin");
    }
}

class MarketingEmployee extends User {
    public MarketingEmployee(String id, String username, String password) {
        super(id, username, password);
    }

    public void manageMarketingTasks() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Add Promotion");
        System.out.println("2. List All Promotions");
        System.out.println("3. Search Promotion");
        System.out.println("4. Delete Promotion");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> addPromotion(scanner);
            case 2 -> listPromotions();
            case 3 -> searchPromotion(scanner);
            case 4 -> deletePromotion(scanner);
            default -> System.out.println("Invalid choice!");
        }
    }

    private void addPromotion(Scanner scanner) throws IOException {
        System.out.print("Enter Promotion ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Promotion Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Discount Percentage: ");
        String discount = scanner.nextLine();

        FileWriter fw = new FileWriter("promotions.txt", true);
        fw.write(id + "," + name + "," + discount + "\n");
        fw.close();
        System.out.println("Promotion added successfully!");
    }

    private void listPromotions() throws IOException {
        File file = new File("promotions.txt");
        if (!file.exists()) {
            System.out.println("No promotions found!");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        System.out.println("Promotions:");
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    private void searchPromotion(Scanner scanner) throws IOException {
        System.out.print("Enter Promotion ID to search: ");
        String searchId = scanner.nextLine();

        File file = new File("promotions.txt");
        if (!file.exists()) {
            System.out.println("No promotions found!");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        boolean found = false;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(searchId + ",")) {
                System.out.println("Promotion Found: " + line);
                found = true;
                break;
            }
        }
        br.close();

        if (!found) {
            System.out.println("Promotion not found!");
        }
    }

    private void deletePromotion(Scanner scanner) throws IOException {
        System.out.print("Enter Promotion ID to delete: ");
        String deleteId = scanner.nextLine();

        File file = new File("promotions.txt");
        if (!file.exists()) {
            System.out.println("No promotions found!");
            return;
        }

        File tempFile = new File("temp.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

        String line;
        boolean found = false;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(deleteId + ",")) {
                found = true;
                continue;
            }
            bw.write(line + "\n");
        }
        br.close();
        bw.close();

        if (found) {
            file.delete();
            tempFile.renameTo(file);
            System.out.println("Promotion deleted successfully!");
        } else {
            tempFile.delete();
            System.out.println("Promotion not found!");
        }
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Marketing Employee");
    }
}

class InventoryEmployee extends User {
    public InventoryEmployee(String id, String username, String password) {
        super(id, username, password);
    }

    public void manageInventory() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Add Product");
        System.out.println("2. List All Products");
        System.out.println("3. Search Product");
        System.out.println("4. Delete Product");
        System.out.println("5. Update Product");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> addProduct(scanner);
            case 2 -> listProducts();
            case 3 -> searchProduct(scanner);
            case 4 -> deleteProduct(scanner);
            case 5 -> updateProduct(scanner);
            default -> System.out.println("Invalid choice!");
        }
    }

    private void addProduct(Scanner scanner) throws IOException {
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Product Price: ");
        String price = scanner.nextLine();
        System.out.print("Enter Product Quantity: ");
        String quantity = scanner.nextLine();

        FileWriter fw = new FileWriter("products.txt", true);
        fw.write(id + "," + name + "," + price + "," + quantity + "\n");
        fw.close();
        System.out.println("Product added successfully!");
    }

    private void listProducts() throws IOException {
        File file = new File("products.txt");
        if (!file.exists()) {
            System.out.println("No products found!");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        System.out.println("Products:");
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    private void searchProduct(Scanner scanner) throws IOException {
        System.out.print("Enter Product ID to search: ");
        String searchId = scanner.nextLine();

        File file = new File("products.txt");
        if (!file.exists()) {
            System.out.println("No products found!");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        boolean found = false;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(searchId + ",")) {
                System.out.println("Product Found: " + line);
                found = true;
                break;
            }
        }
        br.close();

        if (!found) {
            System.out.println("Product not found!");
        }
    }

    private void deleteProduct(Scanner scanner) throws IOException {
        System.out.print("Enter Product ID to delete: ");
        String deleteId = scanner.nextLine();

        File file = new File("products.txt");
        if (!file.exists()) {
            System.out.println("No products found!");
            return;
        }

        File tempFile = new File("temp.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

        String line;
        boolean found = false;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(deleteId + ",")) {
                found = true;
                continue;
            }
            bw.write(line + "\n");
        }
        br.close();
        bw.close();

        if (found) {
            file.delete();
            tempFile.renameTo(file);
            System.out.println("Product deleted successfully!");
        } else {
            tempFile.delete();
            System.out.println("Product not found!");
        }
    }

    private void updateProduct(Scanner scanner) throws IOException {
        System.out.print("Enter Product ID to update: ");
        String updateId = scanner.nextLine();

        File file = new File("products.txt");
        if (!file.exists()) {
            System.out.println("No products found!");
            return;
        }

        File tempFile = new File("temp.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

        String line;
        boolean found = false;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(updateId + ",")) {
                System.out.print("Enter New Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter New Price: ");
                String price = scanner.nextLine();
                System.out.print("Enter New Quantity: ");
                String quantity = scanner.nextLine();

                bw.write(updateId + "," + name + "," + price + "," + quantity + "\n");
                found = true;
            } else {
                bw.write(line + "\n");
            }
        }
        br.close();
        bw.close();

        if (found) {
            file.delete();
            tempFile.renameTo(file);
            System.out.println("Product updated successfully!");
        } else {
            tempFile.delete();
            System.out.println("Product not found!");
        }
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Inventory Employee");
    }
}

class Seller extends User {
    public Seller(String id, String username, String password) {
        super(id, username, password);
    }

    public void manageSales() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("1. Search for Product");
        System.out.println("2. List All Products");
        System.out.println("3. Make an Order");
        System.out.println("4. Cancel an Order");
        System.out.print("Choose an option: ");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1 -> searchProduct(input);
            case 2 -> listProducts();
            case 3 -> makeOrder(input);
            case 4 -> cancelOrder(input);
            default -> System.out.println("Invalid choice!");
        }
    }

    private void searchProduct(Scanner input) throws IOException {
        System.out.print("Enter Product ID to search: ");
        String searchId = input.nextLine();

        File file = new File("products.txt");
        if (!file.exists()) {
            System.out.println("No products found!");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        boolean found = false;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(searchId + ",")) {
                System.out.println("Product Found: " + line);
                found = true;
                break;
            }
        }
        br.close();

        if (!found) {
            System.out.println("Product not found!");
        }
    }

    private void listProducts() throws IOException {
        File file = new File("products.txt");
        if (!file.exists()) {
            System.out.println("No products found!");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        System.out.println("Products:");
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    private void makeOrder(Scanner input) throws IOException {
        System.out.print("Enter Order ID: ");
        String orderId = input.nextLine();
        System.out.print("Enter Product ID: ");
        String productId = input.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = input.nextInt();
        input.nextLine();

        FileWriter fw = new FileWriter("orders.txt", true);
        fw.write(orderId + "," + productId + "," + quantity + "\n");
        fw.close();
        System.out.println("Order created successfully!");
    }

    private void cancelOrder(Scanner input) throws IOException {
        System.out.print("Enter Order ID to cancel: ");
        String cancelId = input.nextLine();

        File file = new File("orders.txt");
        if (!file.exists()) {
            System.out.println("No orders found!");
            return;
        }

        File tempFile = new File("temp.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

        String line;
        boolean found = false;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(cancelId + ",")) {
                found = true;
                continue;
            }
            bw.write(line + "\n");
        }
        br.close();
        bw.close();

        if (found) {
            file.delete();
            tempFile.renameTo(file);
            System.out.println("Order canceled successfully!");
        } else {
            tempFile.delete();
            System.out.println("Order not found!");
        }
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Seller");
    }
}

public class HyperMarketManagementSystem_VERSION_2 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
       
        Admin admin = new Admin("A001", "admin", "admin123");
        MarketingEmployee marketingEmployee = new MarketingEmployee("M001", "marketer", "mark123");
        InventoryEmployee inventoryEmployee = new InventoryEmployee("I001", "inventory", "inv123");
        Seller seller = new Seller("S001", "seller", "sell123");
    
        List<User> users = new ArrayList<>();
        users.add(admin);
        users.add(marketingEmployee);
        users.add(inventoryEmployee);
        users.add(seller);
    
        // Login System
        System.out.println("=== Welcome to Hyper Market Management System ===");
        System.out.print("Enter Username: ");
        String inputUsername = input.nextLine();
        System.out.print("Enter Password: ");
        String inputPassword = input.nextLine();
    
        User loggedInUser = null;
    
        for (User user : users) {
            if (user.getUsername().equals(inputUsername) && user.getPassword().equals(inputPassword)) {
                loggedInUser = user;
                break;
            }
        }
    
        if (loggedInUser == null) {
            System.out.println("Login failed! Invalid username or password.");
            return;
        }
    
        System.out.println("Login successful!");
        loggedInUser.displayRole();

        while (true) {
            System.out.println("\n=== Hyper Market Management System ===");
            System.out.println("1. Admin: Manage Employees");
            System.out.println("2. Marketing: Manage Marketing Tasks");
            System.out.println("3. Inventory: Manage Products");
            System.out.println("4. Sales: Manage Sales");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = input.nextInt();
            input.nextLine();

            try {
                if (choice == 1 && loggedInUser instanceof Admin) {
                    ((Admin)loggedInUser).manageEmployees();
                } else if (choice == 2 && loggedInUser instanceof MarketingEmployee) {
                    ((MarketingEmployee)loggedInUser).manageMarketingTasks();
                } else if (choice == 3 && loggedInUser instanceof InventoryEmployee) {
                    ((InventoryEmployee)loggedInUser).manageInventory();
                } else if (choice == 4 && loggedInUser instanceof Seller) {
                    ((Seller)loggedInUser).manageSales();
                } else if (choice == 5) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("Invalid choice or unauthorized access! Try again.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        input.close();
    }
}