import java.io.*;
import java.util.*;

// Main class to run the library system
public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library("City Library");
        library.addBook(new Book("1984", "George Orwell", "123456789"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "987654321"));

        User user1 = new User("Alice", "U001");
        User user2 = new User("Bob", "U002");

        library.registerUser(user1);
        library.registerUser(user2);

        library.borrowBook("123456789", "U001");
        library.returnBook("123456789", "U001");

        library.saveLibraryData("library_data.txt");
        library.loadLibraryData("library_data.txt");

        library.displayLibraryInfo();
    }
}

// Book class
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrow() {
        if (!isBorrowed) {
            isBorrowed = true;
        } else {
            System.out.println("Book is already borrowed.");
        }
    }

    public void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
        } else {
            System.out.println("Book was not borrowed.");
        }
    }

    @Override
    public String toString() {
        return "Book [Title=" + title + ", Author=" + author + ", ISBN=" + isbn + ", Borrowed=" + isBorrowed + "]";
    }
}

// User class
class User {
    private String name;
    private String userId;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "User [Name=" + name + ", UserID=" + userId + "]";
    }
}

// Library class
class Library {
    private String name;
    private List<Book> books;
    private Map<String, User> users;
    private Map<String, String> borrowedBooks; // ISBN -> UserID

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.users = new HashMap<>();
        this.borrowedBooks = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Added: " + book);
    }

    public void registerUser(User user) {
        users.put(user.getUserId(), user);
        System.out.println("Registered: " + user);
    }

    public void borrowBook(String isbn, String userId) {
        Book book = findBookByIsbn(isbn);
        User user = users.get(userId);

        if (book != null && user != null && !book.isBorrowed()) {
            book.borrow();
            borrowedBooks.put(isbn, userId);
            System.out.println(user.getName() + " borrowed " + book.getTitle());
        } else {
            System.out.println("Borrowing failed. Either book or user does not exist, or book is already borrowed.");
        }
    }

    public void returnBook(String isbn, String userId) {
        Book book = findBookByIsbn(isbn);
        if (book != null && borrowedBooks.containsKey(isbn) && borrowedBooks.get(isbn).equals(userId)) {
            book.returnBook();
            borrowedBooks.remove(isbn);
            System.out.println("Book returned: " + book.getTitle());
        } else {
            System.out.println("Return failed. Either book was not borrowed or wrong user ID.");
        }
    }

    public void displayLibraryInfo() {
        System.out.println("Library: " + name);
        System.out.println("Books:");
        for (Book book : books) {
            System.out.println(book);
        }
        System.out.println("Users:");
        for (User user : users.values()) {
            System.out.println(user);
        }
    }

    public void saveLibraryData(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(books);
            oos.writeObject(users);
            oos.writeObject(borrowedBooks);
            System.out.println("Library data saved.");
        } catch (IOException e) {
            System.out.println("Error saving library data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadLibraryData(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            books = (List<Book>) ois.readObject();
            users = (Map<String, User>) ois.readObject();
            borrowedBooks = (Map<String, String>) ois.readObject();
            System.out.println("Library data loaded.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading library data: " + e.getMessage());
        }
    }

    private Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
}
import java.io.*;
import java.util.*;

// Main class
public class MultiFeatureProgram {
    public static void main(String[] args) {
        System.out.println("Welcome to the Multi-Feature Java Program!");
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Basic Arithmetic");
            System.out.println("2. File Operations");
            System.out.println("3. Multi-threading Example");
            System.out.println("4. Exit");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Arithmetic.run();
                    break;
                case 2:
                    FileOperations.run();
                    break;
                case 3:
                    MultiThreadingExample.run();
                    break;
                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

// Arithmetic operations class
class Arithmetic {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter two numbers:");
        double num1 = scanner.nextDouble();
        double num2 = scanner.nextDouble();

        System.out.println("Choose an operation: +, -, *, /");
        char operation = scanner.next().charAt(0);

        double result;
        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Division by zero is not allowed.");
                    return;
                }
                break;
            default:
                System.out.println("Invalid operation.");
                return;
        }
        System.out.println("Result: " + result);
    }
}

// File operations class
class FileOperations {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file name:");
        String fileName = scanner.nextLine();

        System.out.println("Choose an option:");
        System.out.println("1. Write to file");
        System.out.println("2. Read from file");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                writeFile(fileName, scanner);
                break;
            case 2:
                readFile(fileName);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void writeFile(String fileName, Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            System.out.println("Enter text to write to the file:");
            String content = scanner.nextLine();
            writer.write(content);
            System.out.println("Content written to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            System.out.println("File contents:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

// Multi-threading example class
class MultiThreadingExample {
    public static void run() {
        Thread thread1 = new Thread(new Task("Task 1"));
        Thread thread2 = new Thread(new Task("Task 2"));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }

        System.out.println("All tasks completed.");
    }

    static class Task implements Runnable {
        private final String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println(name + " - Step " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(name + " interrupted.");
                }
            }
        }
    }
}
import java.io.*;
import java.util.*;

// Main Class
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Multi-Feature Java Program!");
        
        // Example 1: Simple User Input and Output
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello, " + name + "!");

        // Example 2: File Handling
        FileHandler fileHandler = new FileHandler("example.txt");
        fileHandler.writeToFile("This is an example text written to the file.");
        String fileContent = fileHandler.readFromFile();
        System.out.println("File Content: " + fileContent);

        // Example 3: Multithreading
        System.out.println("Starting Threads...");
        Thread thread1 = new CustomThread("Thread 1");
        Thread thread2 = new CustomThread("Thread 2");
        thread1.start();
        thread2.start();

        // Example 4: Using a Custom Class
        Person person = new Person(name, 25);
        person.displayInfo();

        // Example 5: Collections Example
        List<String> fruits = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));
        System.out.println("Fruits List: " + fruits);

        // Example 6: Exception Handling
        try {
            System.out.println("Dividing 10 by 0...");
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }

        // Example 7: Lambda Expressions
        Runnable task = () -> System.out.println("This is a task running using a lambda expression.");
        new Thread(task).start();

        // Example 8: Stream API
        System.out.println("Using Stream API to filter fruits:");
        fruits.stream()
                .filter(fruit -> fruit.startsWith("A"))
                .forEach(System.out::println);

        // Example 9: Sorting with Comparator
        fruits.sort((a, b) -> a.compareTo(b));
        System.out.println("Sorted Fruits: " + fruits);

        // Example 10: Nested Classes
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();
        inner.display();

        scanner.close();
    }
}

// Custom Class
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void displayInfo() {
        System.out.println("Person Name: " + name);
        System.out.println("Person Age: " + age);
    }
}

// File Handling Class
class FileHandler {
    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public String readFromFile() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return content.toString();
    }
}

// Custom Thread Class
class CustomThread extends Thread {
    private String threadName;

    public CustomThread(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(threadName + " is running: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
    }
}

// Outer Class with Nested Class
class OuterClass {
    public class InnerClass {
        public void display() {
            System.out.println("This is a nested class example.");
        }
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        GamePanel panel = new GamePanel();

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class GamePanel extends JPanel implements ActionListener {
    private final int TILE_SIZE = 25;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int MAX_TILES = (WIDTH * HEIGHT) / (TILE_SIZE * TILE_SIZE);

    private final int[] x = new int[MAX_TILES];
    private final int[] y = new int[MAX_TILES];

    private int snakeLength;
    private int foodX;
    private int foodY;
    private boolean running;

    private char direction = 'R';
    private Timer timer;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (direction != 'D') direction = 'U';
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 'U') direction = 'D';
                        break;
                    case KeyEvent.VK_LEFT:
                        if (direction != 'R') direction = 'L';
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 'L') direction = 'R';
                        break;
                }
            }
        });

        startGame();
    }

    private void startGame() {
        snakeLength = 3;
        for (int i = 0; i < snakeLength; i++) {
            x[i] = 50 - i * TILE_SIZE;
            y[i] = 50;
        }

        spawnFood();
        running = true;
        timer = new Timer(100, this);
        timer.start();
    }

    private void spawnFood() {
        Random random = new Random();
        foodX = random.nextInt(WIDTH / TILE_SIZE) * TILE_SIZE;
        foodY = random.nextInt(HEIGHT / TILE_SIZE) * TILE_SIZE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (running) {
            // Draw food
            g.setColor(Color.RED);
            g.fillOval(foodX, foodY, TILE_SIZE, TILE_SIZE);

            // Draw snake
            for (int i = 0; i < snakeLength; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.YELLOW);
                }
                g.fillRect(x[i], y[i], TILE_SIZE, TILE_SIZE);
            }
        } else {
            gameOver(g);
        }
    }

    private void move() {
        for (int i = snakeLength; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] -= TILE_SIZE;
                break;
            case 'D':
                y[0] += TILE_SIZE;
                break;
            case 'L':
                x[0] -= TILE_SIZE;
                break;
            case 'R':
                x[0] += TILE_SIZE;
                break;
        }
    }

    private void checkCollision() {
        // Check if head collides with body
        for (int i = snakeLength; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }

        // Check if head collides with walls
        if (x[0] < 0 || x[0] >= WIDTH || y[0] < 0 || y[0] >= HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    private void checkFood() {
        if (x[0] == foodX && y[0] == foodY) {
            snakeLength++;
            spawnFood();
        }
    }

    private void gameOver(Graphics g) {
        String message = "Game Over";
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString(message, (WIDTH - metrics.stringWidth(message)) / 2, HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkCollision();
            checkFood();
        }
        repaint();
    }
}
