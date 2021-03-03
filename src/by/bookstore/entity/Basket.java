package by.bookstore.entity;

public class Basket {
    private int id;
    private Book[] books = new Book[10];
    private int size = 0;

    public boolean addBook(Book book) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = book;
                size++;
                return true;
            }
        }
        return false;
    }

    public double getTotalPrice(){
        double total = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) break;
            total = total + books[i].getPrice();
        }
        return total;
    }

    public void removeBook(Book book) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].equals(book)) {
                for (int j = i; j < books.length - 1; j++) {
                    books[j] = books[j + 1];
                }
                size--;
                break;
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean isFull() {
        return size == books.length;
    }

    public Book[] getBooks() {
       int count = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null){
                count++;
            }
        }

        Book[] book2 = new Book[count];
        for (int i = 0; i < book2.length; i++) {
            book2[i] = books[i];

        }
        return book2;
    }
}
