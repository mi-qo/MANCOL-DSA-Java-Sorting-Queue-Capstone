import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Parallel ArrayLists for Data Management
        ArrayList<String> orderIds = new ArrayList<>();
        ArrayList<String> customerNames = new ArrayList<>();
        ArrayList<String> apparelItems = new ArrayList<>();
        ArrayList<Double> totalAmounts = new ArrayList<>();
        
        // FIFO Queue for Order Processing
        Queue<String> processingQueue = new LinkedList<>();
        
        boolean isRunning = true;
        
        while (isRunning) {
            System.out.println("\n=== APPAREL BRAND ORDER PROCESSING SYSTEM ===");
            System.out.println("1. Add New Order (Enqueue)");
            System.out.println("2. Process Next Order (Dequeue)");
            System.out.println("3. View Pending Orders in Queue (Peek)");
            System.out.println("4. Display All Order Records");
            System.out.println("5. Sort Orders by Total Amount (Bubble Sort)");
            System.out.println("6. Sort Orders by Customer Name (Insertion Sort)");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    System.out.println("\n[ Feature: Add Order ]");
                    addOrder(scanner, orderIds, customerNames, apparelItems, totalAmounts, processingQueue);
                    break;
                case "2":
                    System.out.println("\n[ Feature: Process Order ]");
                    processNextOrder(processingQueue);
                    break;
                case "3":
                    System.out.println("\n[ Feature: View Queue ]");
                    viewQueue(processingQueue);
                    break;
                case "4":
                    System.out.println("\n[ Feature: Display All Records ]");
                    displayAllRecords(orderIds, customerNames, apparelItems, totalAmounts);
                    break;
                case "5":
                    System.out.println("\n[ Feature: Sort by Amount ]");
                    bubbleSortByTotalAmount(orderIds, customerNames, apparelItems, totalAmounts);
                    break;
                case "6":
                    System.out.println("\n[ Feature: Sort by Name ]");
                    insertionSortByCustomerName(orderIds, customerNames, apparelItems, totalAmounts);
                    break;
                case "7":
                    System.out.println("Exiting system. Have a great day!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 7.");
            }
        }
        
        scanner.close();
    }

    // --------------------------------------------------------
    // QUEUE OPERATION: ENQUEUE (Add New Order)
    // --------------------------------------------------------
    public static void addOrder(
            Scanner scanner,
            ArrayList<String> orderIds, 
            ArrayList<String> customerNames, 
            ArrayList<String> apparelItems, 
            ArrayList<Double> totalAmounts,
            Queue<String> processingQueue) {
        
        System.out.print("Enter Order ID (e.g., ORD-001): ");
        String id = scanner.nextLine();
        
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Apparel Item: ");
        String item = scanner.nextLine();
        
        double amount = 0;
        boolean validAmount = false;
        while (!validAmount) {
            System.out.print("Enter Total Amount ($): ");
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount < 0) {
                    System.out.println("Error: Amount cannot be negative.");
                } else {
                    validAmount = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a valid numeric amount.");
            }
        }
        
        // Add to parallel ArrayLists
        orderIds.add(id);
        customerNames.add(name);
        apparelItems.add(item);
        totalAmounts.add(amount);
        
        // Add to the processing queue
        processingQueue.add(id);
        
        System.out.println("\nSuccess: Order " + id + " has been added and queued for processing.");
    }

    // --------------------------------------------------------
    // QUEUE OPERATION: DEQUEUE (Process Next Order)
    // --------------------------------------------------------
    public static void processNextOrder(Queue<String> processingQueue) {
        if (processingQueue.isEmpty()) {
            System.out.println("The processing queue is empty. No orders to process.");
            return;
        }
        
        // Remove and return the first element in the queue (FIFO)
        String nextOrder = processingQueue.poll();
        System.out.println("\nProcessing Order ID: " + nextOrder);
        System.out.println("Status: Order packed and shipped!");
    }

    // --------------------------------------------------------
    // QUEUE OPERATION: PEEK (View Next in Line)
    // --------------------------------------------------------
    public static void viewQueue(Queue<String> processingQueue) {
        if (processingQueue.isEmpty()) {
            System.out.println("The queue is currently empty.");
        } else {
            System.out.println("Next order to be processed: " + processingQueue.peek());
            System.out.println("Total orders waiting in queue: " + processingQueue.size());
            System.out.println("Current Queue (Order IDs): " + processingQueue);
        }
    }

    // --------------------------------------------------------
    // HELPER: DISPLAY ALL RECORDS
    // --------------------------------------------------------
    public static void displayAllRecords(
            ArrayList<String> orderIds, 
            ArrayList<String> customerNames, 
            ArrayList<String> apparelItems, 
            ArrayList<Double> totalAmounts) {
        
        if (orderIds.isEmpty()) {
            System.out.println("The order database is currently empty.");
            return;
        }

        System.out.printf("%-10s | %-20s | %-20s | %-10s\n", "Order ID", "Customer Name", "Apparel Item", "Total ($)");
        System.out.println("-----------------------------------------------------------------------");
        for (int i = 0; i < orderIds.size(); i++) {
            System.out.printf("%-10s | %-20s | %-20s | $%-9.2f\n", 
                orderIds.get(i), customerNames.get(i), apparelItems.get(i), totalAmounts.get(i));
        }
    }

    // --------------------------------------------------------
    // SORTING ALGORITHM 1: BUBBLE SORT (By Total Amount - Descending)
    // --------------------------------------------------------
    public static void bubbleSortByTotalAmount(
            ArrayList<String> orderIds, 
            ArrayList<String> customerNames, 
            ArrayList<String> apparelItems, 
            ArrayList<Double> totalAmounts) {
        
        if (totalAmounts.isEmpty()) {
            System.out.println("No records to sort.");
            return;
        }

        System.out.println("\n--- Records Before Bubble Sort ---");
        displayAllRecords(orderIds, customerNames, apparelItems, totalAmounts);

        int n = totalAmounts.size();
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (totalAmounts.get(j) < totalAmounts.get(j + 1)) {
                    // Swap Amounts
                    double tempAmount = totalAmounts.get(j);
                    totalAmounts.set(j, totalAmounts.get(j + 1));
                    totalAmounts.set(j + 1, tempAmount);
                    
                    // Swap Order IDs
                    String tempId = orderIds.get(j);
                    orderIds.set(j, orderIds.get(j + 1));
                    orderIds.set(j + 1, tempId);
                    
                    // Swap Customer Names
                    String tempName = customerNames.get(j);
                    customerNames.set(j, customerNames.get(j + 1));
                    customerNames.set(j + 1, tempName);
                    
                    // Swap Apparel Items
                    String tempItem = apparelItems.get(j);
                    apparelItems.set(j, apparelItems.get(j + 1));
                    apparelItems.set(j + 1, tempItem);
                    
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        
        System.out.println("\n--- Records After Bubble Sort (Highest Amount First) ---");
        displayAllRecords(orderIds, customerNames, apparelItems, totalAmounts);
    }

    // --------------------------------------------------------
    // SORTING ALGORITHM 2: INSERTION SORT (By Customer Name)
    // --------------------------------------------------------
    public static void insertionSortByCustomerName(
            ArrayList<String> orderIds, 
            ArrayList<String> customerNames, 
            ArrayList<String> apparelItems, 
            ArrayList<Double> totalAmounts) {
        
        if (customerNames.isEmpty()) {
            System.out.println("No records to sort.");
            return;
        }

        System.out.println("\n--- Records Before Insertion Sort ---");
        displayAllRecords(orderIds, customerNames, apparelItems, totalAmounts);

        for (int i = 1; i < customerNames.size(); i++) {
            String keyName = customerNames.get(i);
            String keyOrderId = orderIds.get(i);
            String keyApparel = apparelItems.get(i);
            double keyAmount = totalAmounts.get(i);
            
            int j = i - 1;
            
            while (j >= 0 && customerNames.get(j).compareToIgnoreCase(keyName) > 0) {
                customerNames.set(j + 1, customerNames.get(j));
                orderIds.set(j + 1, orderIds.get(j));
                apparelItems.set(j + 1, apparelItems.get(j));
                totalAmounts.set(j + 1, totalAmounts.get(j));
                j = j - 1;
            }
            
            customerNames.set(j + 1, keyName);
            orderIds.set(j + 1, keyOrderId);
            apparelItems.set(j + 1, keyApparel);
            totalAmounts.set(j + 1, keyAmount);
        }
        
        System.out.println("\n--- Records After Insertion Sort (Alphabetical) ---");
        displayAllRecords(orderIds, customerNames, apparelItems, totalAmounts);
    }
}
