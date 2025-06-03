import java.io.*;
import javax.swing.*;

public class Repository {
    private String filename;

    public Repository(String filename) {this.filename = filename;}

    public void insert(String line) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
            bw.write(line + "\n");
            bw.close();
        } catch (IOException ioe) {
            System.out.println("Error writing to file: " + ioe.getMessage());
        }
    }

    public void update(String newContent) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false));
            bw.write(newContent);
            bw.close();
        } catch (IOException ioe) {
            System.out.println("Error updating file: " + ioe.getMessage());
        }
    }

    public String getLast() {
        String lastLine = null;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            
            while ((line = br.readLine()) != null) lastLine = line;
            br.close();
        } catch (IOException ioe) {
            System.out.println("Error reading file: " + ioe.getMessage());
        }
        return lastLine;
    }

    public String getAll() {
        String result = "";
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            
            boolean first = true;
            
            while ((line = br.readLine()) != null) {
                if (!first) result += "\n";
                result += line;
                first = false;
            }
            br.close();
        } catch (IOException ioe) {
            System.out.println("Error reading file: " + ioe.getMessage());
        }
        return result;
    }

    public static Customer getCustomerFromLogin(String username, String password) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("customerDetails.txt"));
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String pass = parts[1];
                String name = parts[2];
                String address = parts[3];
                
                if (id.equals(username) && pass.equals(password)) {
                    br.close();
                    return new Customer(id, name, address);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error reading customer details: " + e.getMessage());
        }
        return null;
    }

    public static boolean delete(Customer customer, String productToRemove, JFrame parent) {
        String shoppingFile = getShoppingListFile(customer.getId());

        StringBuilder newShoppingContent = new StringBuilder();
        boolean found = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader(shoppingFile));
            String line;
            boolean first = true;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length > 0 && parts[0].equalsIgnoreCase(productToRemove)) {
                    found = true;
                    continue;
                }

                if (!first) newShoppingContent.append("\n");
                newShoppingContent.append(line);
                first = false;
            }
            br.close();
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(parent, "Error processing file.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Repository shoppingRepo = new Repository(shoppingFile);
        shoppingRepo.update(newShoppingContent.toString());

        return found;
    }

    public static int calculateOwed(Customer customer) {
        try {
            Repository repo = new Repository(getTransactionsFile(customer.getId()));
            String lastLine = repo.getLast();
            
            if (lastLine != null) {
                String[] parts = lastLine.split(",");
                String moneyOwed = parts[3].replace("Money Owed:", "").trim();
                
                return Integer.parseInt(moneyOwed);
            }
        } catch (Exception e) {
            System.out.println("Error calculating owed: " + e.getMessage());
        }
        return sumShoppingListTotal(getShoppingListFile(customer.getId()));
    }

    public static void clear(Customer customer) {
        try {
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(getShoppingListFile(customer.getId()), false));
            bw1.close();
            
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(getTransactionsFile(customer.getId()), false));
            bw2.close();
        } catch (IOException ioe) {
            System.out.println("Error clearing customer files: " + ioe.getMessage());
        }
    }

    private static int sumShoppingListTotal(String shoppingListFile) {
        int total = 0;
        
        try {
            Repository repo = new Repository(shoppingListFile);
            String allLines = repo.getAll();
            String[] lines = allLines.split("\n");
            
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                String[] parts = line.split(",");

                try {
                    int quantity = Integer.parseInt(parts[1]);
                    int unitPrice = Integer.parseInt(parts[2]);
                    
                    total += quantity * unitPrice;
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid number format in line: " + line + " - " + nfe.getMessage());
                }   
            }
        } catch (Exception e) {
            System.out.println("Error summing shopping list: " + e.getMessage());
        }
        return total;
    }

    public static String getShoppingListFile(String customerId) {return customerId + "_shoppingList.txt";}
    public static String getTransactionsFile(String customerId) {return customerId + "_transactions.txt";}
    public static int calculateShoppingListTotal(String customerId) {return sumShoppingListTotal(getShoppingListFile(customerId));}

    public static String[][] getShoppingListData(Customer customer) {
        try {
            Repository repo = new Repository(getShoppingListFile(customer.getId()));
            String allLines = repo.getAll();
            if (allLines.trim().isEmpty()) return new String[0][];
            String[] lines = allLines.split("\n");
            String[][] data = new String[lines.length][3];
            for (int i = 0; i < lines.length; i = i + 1) {
                String[] parts = lines[i].split(",");
                data[i][0] = parts[0];
                data[i][1] = parts[1];
                data[i][2] = parts[2];
            }
            return data;
        } catch (Exception e) {
            System.out.println("Error getting shopping list data: " + e.getMessage());
        }
        return new String[0][0];
    }

    public static String[] getLastTransactionData(Customer customer) {
        try {
            Repository repo = new Repository(getTransactionsFile(customer.getId()));
            String lastLine = repo.getLast();
            if (lastLine != null) {
                String[] parts = lastLine.split(",");
                String moneyGiven = parts[0].replace("Money Given:", "").trim();
                String totalPrice = parts[2].replace("Total Price:", "").trim();
                String moneyOwed = parts[3].replace("Money Owed:", "").trim();
                return new String[] {moneyGiven, totalPrice, moneyOwed};
            }
        } catch (Exception e) {
            System.out.println("Error getting last transaction: " + e.getMessage());
        }
        int totalOwed = sumShoppingListTotal(getShoppingListFile(customer.getId()));
        return new String[] {"-", String.valueOf(totalOwed), String.valueOf(totalOwed)};
    }
}