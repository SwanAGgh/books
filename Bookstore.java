package lv.sda.books;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Bookstore{
    static Scanner scanner = new Scanner(System.in);
    final String filePath = "src/main/resources/books.txt";
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
    //Action:
    //0 - Return all books
    //1 - Search by ISBN
    //2 - Search by Title
    public List<Book> searchBook(String query, int action) {
        switch (action) {
            case 0 -> { return books; }
            case 1 -> {
                if (query.equals(INV)) {
                    System.out.println(INV);
                    return Collections.emptyList();
                } else { return books.stream().filter(e->e.getIsbn().equals(query)).collect(Collectors.toList());} }
            case 2 -> {  return books.stream().filter(e->e.getTitle().equalsIgnoreCase(query)).collect(Collectors.toList()); }
        }
        return Collections.emptyList();
    }

    public String inputBookTitle(){
        System.out.println("Enter book Title:");
        return scanner.nextLine();
    }

    public String inputBookIsbn(){
        System.out.println("Enter book Isbn:");
        String myIsbn = scanner.nextLine();
        return isValidInput09(myIsbn) ? myIsbn : INV;
    }

    // Add new book
    public void addBook() {
        Book newBook = new Book();
        System.out.println("Add book");

        if (inputBookIsbn().equals(INV)) {System.out.println(INV); return;} else newBook.setIsbn(inputBookIsbn());

        newBook.setTitle(inputBookTitle());

        System.out.println("Enter Description:");
        newBook.setDescription(scanner.nextLine());

        System.out.println("Enter Author:");
        String myInput = scanner.nextLine();
        if (isValidInputAZ(myInput)) newBook.setAuthor(myInput);
        else System.out.println(INV);
        if (inputBookTitle().equals(INV)) {System.out.println(INV); return;} else newBook.setTitle(inputBookTitle());

        System.out.println("Enter Publisher:");
        newBook.setPublisher(scanner.nextLine());

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
        try(BufferedWriter out = new BufferedWriter(new FileWriter(filePath))){
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