package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Class responsible for managing a collection of to-do items and displaying the interactive menu
public class to_do_item {

    // Map to store to-do items using their ID as the key
    public Map<String,Items>item;

    // Constructor initializes the map for storing items
    public to_do_item(){
        item = new HashMap<>();
    }

    //----------------------------------------------------------------------------------------------------------------------
// Method to add a to-do item to the list
    public boolean Add_Item(Items additem) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(additem);
            session.getTransaction().commit();
            System.out.println("Item saved to database.");
            return true;
        } catch (Exception e) {
            System.out.println("Error saving item: " + e.getMessage());
            return false;
        }
    }


    //----------------------------------------------------------------------------------------------------------------------
//* Delete a to-do item
    public boolean Delete_item(String ID) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Items itemToDelete = session.get(Items.class, ID);
            if (itemToDelete != null) {
                session.delete(itemToDelete);
                session.getTransaction().commit();
                System.out.println("Item deleted from database.");
                return true;
            } else {
                System.out.println("Item not found in database.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error deleting item: " + e.getMessage());
            return false;
        }
    }

    //----------------------------------------------------------------------------------------------------------------------
//* View the to-do items
    public void display_items_list() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var itemList = session.createQuery("FROM Items", Items.class).list();
            if (itemList.isEmpty()) {
                System.out.println("No items found in the database.");
            } else {
                for (Items item : itemList) {
                    System.out.println(
                            "\n=================Item Info=================" +
                            "\nID: " + item.getId() +
                            "\nName: " + item.getName() +
                            "\nDescription: " + item.getShortDescription() +
                            "\nStatus: " + item.getTaskStatus() +
                            "\n==========================================");
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching items: " + e.getMessage());
        }
    }

    //----------------------------------------------------------------------------------------------------------------------
// Method to display and manage the to-do list menu
    public void To_Do_List_Menu(){
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            // Display menu options
            System.out.println("\nTo Do List Menu");
            System.out.println("1. Add an item to list");
            System.out.println("2. Delete an item from list");
            System.out.println("3. View the to-do list");
            System.out.println("4. Exit Menu");
            System.out.print("Enter your option by the number associated: ");

            while (true) {
                // Check if the input is an integer
                if (!sc.hasNextInt()) {
                    System.out.println("\nInvalid input. Please enter a number between 1 and 4.");
                    sc.next(); // Clear the invalid input
                } else {
                    option = sc.nextInt(); // Read the input

                    // Check if the input is within the valid range (1 or 2)
                    if (option >= 1 && option <= 4) {
                        break;  // Exit the loop if valid input is entered
                    } else {
                        System.out.println("Invalid option. Please enter a number between 1 and 4.");
                    }
                }
            }

// Handle menu options
            switch (option) {

                //Add
                case 1:
                    //Add ID
                    System.out.print("\nEnter the Item ID(up to 5 numbers): ");
                    sc.nextLine();
                    String id = sc.nextLine();

                    // Validate ID format
                    while (!id.matches("\\d{1,5}")) {
                        System.out.println("Error: ID must be a number with 1 to 5 digits only.");
                        System.out.print("Enter the Item ID (1 to 5 digits): ");
                        id = sc.nextLine();
                    }

                    // Prompt and validate name
                    System.out.print("Enter the name of the item: ");
                    //sc.nextLine();
                    String name = sc.nextLine();

                    while (name.length() < 1 || name.length() > 15) {
                        System.out.println("Error: name entered had no characters entered, please re-enter proper amount(up to 15 characters.)");
                        System.out.print("Enter Item name: ");
                        name = sc.nextLine();
                    }

                    // Prompt and validate description
                    System.out.print("Enter a short description of the item: ");
                    //sc.nextLine();
                    String description = sc.nextLine();

                    while (description.length() < 1 || description.length() > 50) {
                        System.out.println("Error: short description entered had no characters entered, Please enter below 50 characters.");
                        System.out.print("Enter short description: ");
                        description = sc.nextLine();
                    }

                    // Prompt and validate task status (boolean)
                    int task_status;
                    while (true) {
                        System.out.print("Enter the completion status of the task (1 for completed, 0 for not completed): ");
                        String input = sc.next().trim();

                        if (input.equals("1")) {
                            task_status = 1;
                            break;
                        } else if (input.equals("0")) {
                            task_status = 0;
                            break;
                        } else {
                            System.out.println("Error: Please enter '1' for completed or '0' for not completed.");
                        }
                    }

                    // Add the validated item
                    System.out.println("\nItem has been added.");
                    Add_Item(new Items(id, name, description, task_status));
                    break;

                //Delete
                case 2:
                    System.out.print("Enter ID to delete task: ");
                    sc.nextLine(); // Clear input
                    String remove_ID = sc.nextLine();
                    Delete_item(remove_ID);
                    break;

                //view
                case 3:
                    display_items_list();
                    break;

                //close menu
                case 4:
                    System.out.println("Exiting Menu..."); // Continue menu until option 4 is selected (exit)
                    break;

            }//switch
        } while (option != 4);
    }//menu
}//class