package lv.sda.books;

import java.util.Scanner;

public class Application {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Bookstore BS = new Bookstore();
        while (true) {
            Bookstore.printMenu();
            String input = scanner.nextLine();

            if ("q".equalsIgnoreCase(input)){
                System.out.println("Exit");
                break;
            }
            switch (input) {
                case "1" -> BS.printData("Search book by name:", BS.searchBook(BS.inputBookName(), 2));
                case "2" -> BS.addBook();
                case "3" -> { try {BS.removeBook(BS.searchBook(BS.inputBookIsbn(), 1).get(0));}
                              catch (Exception e){ System.out.println("Book not found");} }
                case "4" -> BS.printData("Search book by isbn nr.:", BS.searchBook(BS.inputBookIsbn(), 1));
                case "5" -> BS.printData("List of all books:", BS.searchBook("", 0));
                case "6" -> BS.saveData();
            }
        }
    }
}