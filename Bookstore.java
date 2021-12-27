package lv.sda.books;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Bookstore{
    static Scanner scanner = new Scanner(System.in);
    final String filePath = "src/main/resources/books.txt";
    final String filePath2 = "src/main/resources/books2.txt";
    final String INV = "Invalid input";
    private List<Book> books;
    public Bookstore(){
        try {
            Path path = Paths.get(filePath);
            books = Files.lines(path)
                    .map(line -> {
                        List<String> fields = Arrays.stream(line.split(";")).collect(Collectors.toList());
                        return new Book(
                                fields.get(0),
                                fields.get(1),
                                fields.get(2),
                                fields.get(3),
                                fields.get(4),
                                Integer.parseInt(fields.get(5)),
                                LocalDate.of(Integer.parseInt(fields.get(6)), 1, 1)
                        );
                    })
                    .collect(Collectors.toList());
            //books.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Search book
    //TODO - ignore special characters
    public List<Book> searchBook(String query, int action) {
        List<Book> booksByValue = new ArrayList<>();
        switch (action) {
            case 1 -> {
                if (query.equals(INV)) {
                    System.out.println(INV);
                    return Collections.emptyList();
                } else {
                    for (Book book : books) if (book.getIsbn().equals(query)) booksByValue.add(book);
                    return booksByValue;}
                }
            case 2 -> {
                for (Book book : books) if (book.getTitle().equalsIgnoreCase(query)) booksByValue.add(book);
                return booksByValue;
            }
        }
        return books;
    }

    public String inputBookName(){
        System.out.println("Enter book name:");
        return scanner.nextLine();
    }

    public String inputBookIsbn(){
        System.out.println("Enter book Isbn:");
        String myIsbn = scanner.nextLine();
        return isValidInput09(myIsbn) ? myIsbn : INV;
    }

    // Add new book
    public void addBook() {
        //Book newBook = new Book("100", "Title", "Description", "Author", "publisher", 1000, LocalDate.of(2020, 1, 1));
        Book newBook = new Book();
        System.out.println("Add book");

        System.out.println("Enter isbn:");
        String myInput = scanner.nextLine();
        if (isValidInput09(myInput)) newBook.setIsbn(myInput);
        else System.out.println(INV);

        System.out.println("Enter Title:");
        myInput = scanner.nextLine();
        newBook.setTitle(myInput);

        System.out.println("Enter Description:");
        myInput = scanner.nextLine();
        newBook.setDescription(myInput);

        System.out.println("Enter Author:");
        myInput = scanner.nextLine();
        if (isValidInputAZ(myInput)) newBook.setAuthor(myInput);
        else System.out.println(INV);

        System.out.println("Enter Publisher:");
        myInput = scanner.nextLine();
        newBook.setPublisher(myInput);

        System.out.println("Enter number of pages:");
        myInput = scanner.nextLine();
        if (isValidInput09(myInput)) newBook.setPages(Integer.parseInt(myInput));
        else System.out.println(INV);

        System.out.println("Enter publishing year:");
        myInput = scanner.nextLine();
        if (isValidInput09(myInput) && (Calendar.getInstance().get(Calendar.YEAR))-Integer.parseInt(myInput)>=0) newBook.setPublishingYear(LocalDate.of(Integer.parseInt(myInput), 1, 1));
        else System.out.println(INV);

        books.add(newBook);
    }

    // Remove book from list
    public void removeBook(Book book) {
        System.out.println("Remove book");
        books.remove(book);
        System.out.println("Book removed");
    }

    public void printData(String msg, List<Book> books){
        System.out.println(msg);
        if ((long) books.size() !=0) books.forEach(book -> System.out.println(book.toString()));
        else System.out.println("Book(s) not found");
    }

    //Save data
    public void saveData(){
        try(BufferedWriter out = new BufferedWriter(new FileWriter(filePath2))){
            for (Book book: books) out.write(book.toCSV());
            out.close();
            System.out.println("Data saved successfully");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printMenu() {
        System.out.println("""
                Menu
                1. Search book
                2. Add Book
                3. Remove Book
                4. Get Book info
                5. List Available Books
                6. Save data to local file
                Press Q to quit\s""");
    }

    public boolean isValidInput09(String input){
        return input.matches("[0-9]+");
    }

    public boolean isValidInputAZ(String input){
        return input.matches("^[A-Za-z ]+$");
    }

}