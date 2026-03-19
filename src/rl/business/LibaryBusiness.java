package rl.business;

import rl.entity.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LibaryBusiness {
    private static LibaryBusiness instance;
    private List<Book> listBook;

    private LibaryBusiness() {
        listBook = new ArrayList<>();
    }

    public static LibaryBusiness getInstance() {
        if (instance == null) {
            instance = new LibaryBusiness();
        }
        return instance;
    }

    public void displayAllBooks() {
        if (listBook.isEmpty()) {
            System.out.println("Danh sach dang trong");
            return;
        }
        System.out.println("+------------+----------------------+----------------------+--------+----------------------+--------------+");
        System.out.println("| Book ID    | Book Name            | Author               | Year   | Description          | Is Available |");
        System.out.println("+------------+----------------------+----------------------+--------+----------------------+--------------+");
        listBook.forEach(Book::displayData);
        System.out.println("+------------+----------------------+----------------------+--------+----------------------+--------------+");
    }

    public boolean addBook(Book book) {
        boolean exists = listBook.stream()
                .anyMatch(b -> b.getBookId().equalsIgnoreCase(book.getBookId()));
        if (exists) {
            System.out.println("Ma sach da ton tai");
            return false;
        }
        listBook.add(book);
        System.out.println("Them thanh cong");
        return true;
    }

    public Optional<Book> findBookById(String bookId) {
        return listBook.stream()
                .filter(b -> b.getBookId().equalsIgnoreCase(bookId))
                .findFirst();
    }

    public boolean checkBookIdExists(String bookId) {
        return listBook.stream().anyMatch(b -> b.getBookId().equalsIgnoreCase(bookId));
    }

    public void updateBookById(Scanner sc, String bookId) {
        Optional<Book> optionalBook = findBookById(bookId);
        if (!optionalBook.isPresent()) {
            System.out.println("Ma sach khong ton tai");
            return;
        }

        Book book = optionalBook.get();
        boolean isExit = true;

        do {
            System.out.println("1. Cap nhat ten sach");
            System.out.println("2. Cap nhat tac gia");
            System.out.println("3. Cap nhat nam xuat ban");
            System.out.println("4. Cap nhat mo ta");
            System.out.println("5. Cap nhat tinh trang");
            System.out.println("6. Thoat");
            System.out.print("Lua chon cua ban: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Nhap ten sach moi: ");
                    String newBookName = sc.nextLine().trim();
                    if (!newBookName.isEmpty()) {
                        if (newBookName.length() >= 5) {
                            book.setBookName(newBookName);
                            System.out.println("Cap nhat thanh cong");
                        } else {
                            System.out.println("Ten sach phai tu 5 ky tu tro len");
                        }
                    }
                    break;
                case "2":
                    System.out.print("Nhap tac gia moi: ");
                    String newAuthor = sc.nextLine().trim();
                    if (!newAuthor.isEmpty()) {
                        book.setAuthor(newAuthor);
                        System.out.println("Cap nhat thanh cong");
                    }
                    break;
                case "3":
                    System.out.print("Nhap nam xuat ban moi: ");
                    String inputYear = sc.nextLine().trim();
                    if (!inputYear.isEmpty()) {
                        try {
                            int newYear = Integer.parseInt(inputYear);
                            if (newYear > 1900 && newYear <= 2026) {
                                book.setYear(newYear);
                                System.out.println("Cap nhat thanh cong");
                            } else {
                                System.out.println("Nam xuat ban phai sau 1900 va khong lon hon 2026");
                            }
                        } catch (Exception e) {
                            System.out.println("Vui long nhap so nguyen");
                        }
                    }
                    break;
                case "4":
                    System.out.print("Nhap mo ta moi: ");
                    String newDescription = sc.nextLine().trim();
                    if (!newDescription.isEmpty()) {
                        book.setDescription(newDescription);
                        System.out.println("Cap nhat thanh cong");
                    }
                    break;
                case "5":
                    System.out.print("Nhap tinh trang moi (true/false): ");
                    String inputStatus = sc.nextLine().trim().toLowerCase();
                    if (!inputStatus.isEmpty()) {
                        if (inputStatus.equals("true") || inputStatus.equals("false")) {
                            book.setAvailable(Boolean.parseBoolean(inputStatus));
                            System.out.println("Cap nhat thanh cong");
                        } else {
                            System.out.println("Chi duoc nhap true hoac false");
                        }
                    }
                    break;
                case "6":
                    isExit = false;
                    break;
                default:
                    System.out.println("Vui long chon tu 1 den 6");
            }
        } while (isExit);
    }

    public boolean deleteBookById(String bookId) {
        Optional<Book> optionalBook = findBookById(bookId);
        if (!optionalBook.isPresent()) {
            System.out.println("Ma sach khong ton tai");
            return false;
        }

        if (!optionalBook.get().isAvailable()) {
            System.out.println("Chi xoa duoc sach dang o kho");
            return false;
        }

        listBook.remove(optionalBook.get());
        System.out.println("Xoa thanh cong");
        return true;
    }

    public void searchBookByAuthor(String author) {
        List<Book> result = listBook.stream()
                .filter(b -> b.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("Khong tim thay sach");
            return;
        }

        System.out.println("+------------+----------------------+----------------------+--------+----------------------+--------------+");
        System.out.println("| Book ID    | Book Name            | Author               | Year   | Description          | Is Available |");
        System.out.println("+------------+----------------------+----------------------+--------+----------------------+--------------+");
        result.forEach(Book::displayData);
        System.out.println("+------------+----------------------+----------------------+--------+----------------------+--------------+");
        System.out.println("Tong so ket qua tim thay: " + result.size());
    }

    public void statisticsBookStatus() {
        long availableCount = listBook.stream().filter(Book::isAvailable).count();
        long borrowedCount = listBook.stream().filter(b -> !b.isAvailable()).count();

        System.out.println("So sach co san: " + availableCount);
        System.out.println("So sach dang muon: " + borrowedCount);
    }

    public void sortBooksByYearDesc() {
        if (listBook.isEmpty()) {
            System.out.println("Danh sach dang trong");
            return;
        }

        listBook = listBook.stream()
                .sorted(Comparator.comparing(Book::getYear).reversed())
                .collect(Collectors.toList());

        System.out.println("Da sap xep theo nam xuat ban giam dan");
        displayAllBooks();
    }
}