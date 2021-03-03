package by.bookstore.entity;

public class Session {
    private int id;
    private Basket basket = new Basket();
    private User user;

    public Session() {
    }

    public Session(User user) {
        this.user = user;
    }

    public Session(int id, Basket basket, User user) {
        this.id = id;
        this.basket = basket;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", basket=" + basket +
                ", user=" + user +
                '}';
    }
}
