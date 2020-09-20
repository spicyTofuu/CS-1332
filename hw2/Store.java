/*I worked on the homework assignment alone, using only course materials,
but I Googled some usages regarding break and continue statement*/
import java.util.Scanner;
public class Store {

    public static void main(String[] args) {
        String[][] inventory = {{"Milk", "2", "$1.99"},
                                {"Apples", "12", "$1.25"},
                                {"Cereal", "1", "$3.99"},
                                {"Watermelon", "20", "$2.49"},
                                {"Broccoli", "0", "$0.99"},
                                {"Celery", "5", "$0.49"},
                                {"Oranges", "6", "$1.29"},
                                {"Pasta", "2", "$5.99"},
                                {"Olive oil", "1", "$10.99"}};
        Scanner sc = new Scanner(System.in);
        boolean mainloop = true;
        my_loop:
        while (mainloop) {
            System.out.println("Are you a [M]anager, a [C]ustomer, or would you like to [E]xit?");
            String answer = sc.nextLine();
            //Exit part
            if (answer.equals("E")) {
                System.out.println("Goodbye!");
                mainloop = false;
                break;
            }
            //Manager part
            myfirst_loop:
            while (answer.equals("M")) {
                System.out.println("\nWhich item would you like to restock?");
                String item = sc.nextLine();
                boolean stock = true;
                for (int row = 0; row < inventory.length; row++) {
                    for (int col = 0; col < inventory[row].length; col++) {
                        if (item.equalsIgnoreCase(inventory[row][col])) {
                            String caseitem = inventory[row][col];
                            stock = false;
                            System.out.println("\nBy how much are you restocking " + caseitem + "?");
                            int ext = sc.nextInt();
                            sc.nextLine();
                            int inv = sum(inventory, row, col);
                            int sum = ext + inv;
                            System.out.println("Stock of " + caseitem + " is now increased to " + sum + ".");
                            inventory[row][col + 1] = Integer.toString(sum);
                            System.out.println("\nAre you finished with restocking? [Y]es or [N]o?");
                            String continue1 = sc.nextLine();
                                if (continue1.equals("Y")) {
                                    System.out.println();
                                    continue my_loop;
                                } else if (continue1.equals("N")) {
                                    continue myfirst_loop;
                                }
                        }
                    }
                }
                if (stock) {
                    System.out.println("You don't have " + item + "!");
                    System.out.println("\nAre you finished with restocking? [Y]es or [N]o?");
                    String continue2 = sc.nextLine();
                    if (continue2.equals("Y")) {
                        break;
                    }
                }
            }
            //Customer part
            int i = 0;
            int j = 0;
            int stext = 0;
            //when a customer buy something, make stext greater than 0
            double alltotal = 0; //total price
            double total; //indiv price
            boolean finalresutl = false;
            String[][] rec = new String[inventory.length][inventory[0].length - 1];
            customer_loop:
            while (answer.equals("C")) {
                System.out.println("\nWhat would you like to buy?");
                String item = sc.nextLine();
                boolean stock = false;
                boolean repp = true;
                mysecond_loop:
                for (int row = 0; row < inventory.length; row++) {
                    for (int col = 0; col < inventory[row].length; col++) {
                        if (item.equalsIgnoreCase(inventory[row][0])) {
                            String uppercaseitem = inventory[row][col];
                            stock = true;
                            System.out.println("\nHow much " + uppercaseitem + " would you like to buy?");
                            int quan = sc.nextInt();
                            sc.nextLine();
                            int inv = sum(inventory, row, col);
                            if (quan > inv) {
                                System.out.println("Sorry, we only have " + inv + " " + uppercaseitem + " in stock.");
                                System.out.println("\nWould you like to continue shopping? [Y]es or [N]o?");
                                String answer1 = sc.nextLine();
                                if (answer1.equals("Y")) {
                                    break mysecond_loop;
                                } else if (answer1.equals("N")) {
                                    finalresutl = true;
                                    break mysecond_loop;
                                }
                            } else if (quan <= inv) {
                                stext++;
                                System.out.println("You have added " + quan + " "
                                    + uppercaseitem + " to your shopping cart.");
                                String st = inventory[row][col + 2].substring(1);
                                double price = Double.parseDouble(st);
                                total = price * quan;
                                alltotal = alltotal + total; // add all the cost together
                                inventory[row][col + 1] = Integer.toString(inv - quan);
                                repeatation(rec, i, j, uppercaseitem, quan, repp);
                                //making a receipt
                                i++;
                                System.out.println("\nWould you like to continue shopping? [Y]es or [N]o?");
                                String answeragain = sc.nextLine();
                                if (answeragain.equals("Y")) {
                                    break mysecond_loop;
                                } else if (answeragain.equals("N")) {
                                    finalresutl = true;
                                    break mysecond_loop;
                                }
                            }
                        }
                    }
                }
                if (!stock) {
                    System.out.println("Sorry, we don't have " + item + ".");
                    System.out.println("\nWould you like to continue shopping? [Y]es or [N]o?");
                    String answerPlayagain = sc.nextLine();
                    if (answerPlayagain.equals("N")) {
                        if (stext >= 1) {
                            finalresutl = true;
                            //When a customer doesnt buy anything, there shouldn't be a receipt for him
                        } else {
                            break customer_loop;
                        }
                    }
                }

                if (finalresutl) {
                    System.out.println("\nHere are the items you have purchased:");
                    printt(rec);
                    System.out.printf("The total cost of your purchase is $%.2f. Thank you for shopping!\n\n",
                        alltotal);
                    break customer_loop;
                }
            }
        }
    }





    public static int sum(String[][] idk, int row, int col) {
        int inv = Integer.parseInt(idk[row][col + 1]);
        return inv;
    }



    public static void printt(String[][] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; (arr[i] != null && j < arr[i].length); j++) {
                if (arr[i][j] != null) {
                    System.out.print(arr[i][j] + " ");
                }
            }
            if (arr[i][0] != null) {
                System.out.println();
            }
        }
    }

    public static String[][] repeatation(String[][] arr2, int i, int j, String item, int quan, boolean repp) {
        repeat_loop:
            for (int row = 0; row < arr2.length; row++) {
                for (int col = 0; col < arr2[row].length; col++) {
                    if (item.equals(arr2[row][col])) {
                        int num = Integer.parseInt(arr2[row][col - 1]) + quan;
                        int total = num + quan;
                        arr2[row][col - 1] = Integer.toString(num);
                        if (i < 9) { //Limit the number of rows to 9
                            if (arr2[i + 1][j] != null) {
                                i++;
                            }
                        }
                        repp = false;
                        break repeat_loop;
                    }
                }

            }
        if (repp) {
            arr2[i][j] = Integer.toString(quan);
            arr2[i][j + 1] = item;
        }
        return arr2;
    }
}
