package lv.sda.books;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {

    @Test
    public void sampleTest_0(){
        Bookstore BS = new Bookstore();
        assertEquals(12,BS.searchBook("", 0).size());
    }

    @Test
    public void sampleTest_1(){
        Bookstore BS = new Bookstore();
        Book newBook = new Book("00001", "Title", "Description", "Author", "Publisher", 100, LocalDate.of(2000,1,1));
        BS.addBook(newBook);
        assertEquals(13,BS.searchBook("", 0).size());
    }

    @Test
    public void sampleTest_2(){
        Bookstore BS = new Bookstore();
        assertEquals(3,BS.searchBook("Book 222", 2).size());
    }

    @Test
    public void sampleTest_3(){
        Bookstore BS = new Bookstore();
        BS.removeBook(BS.searchBook("123", 1).get(0));
        assertEquals(11,BS.searchBook("", 0).size());
    }

}
