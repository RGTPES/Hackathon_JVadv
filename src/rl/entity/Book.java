package rl.entity;

import java.util.Scanner;

public class Book {
    private String bookId;
    private String bookName;
    private String author;
    private int year;
    private String description;
    private boolean isAvailable;

    public Book() {
    }

    public Book(String bookId, String bookName, String author, int year, String description, boolean isAvailable) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.year = year;
        this.description = description;
        this.isAvailable = isAvailable;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void inputData(Scanner scanner) {
        while (true) {
            System.out.print("Nhap ten sach: ");
            String bookName = scanner.nextLine().trim();
            if (bookName.length() >= 5) {
                this.bookName = bookName;
                break;
            }
            System.out.println("Ten sach phai tu 5 ky tu tro len");
        }

        while (true) {
            System.out.print("Nhap tac gia: ");
            String author = scanner.nextLine().trim();
            if (!author.isEmpty()) {
                this.author = author;
                break;
            }
            System.out.println("Tac gia khong duoc de trong");
        }

        while (true) {
            try {
                System.out.print("Nhap nam xuat ban: ");
                int year = Integer.parseInt(scanner.nextLine().trim());
                if (year > 1900 && year <= 2026) {
                    this.year = year;
                    break;
                }
                System.out.println("Nam xuat ban phai sau 1900 va khong lon hon 2026");
            } catch (Exception e) {
                System.out.println("Vui long nhap so nguyen");
            }
        }

        System.out.print("Nhap mo ta: ");
        this.description = scanner.nextLine().trim();

        while (true) {
            System.out.print("Nhap tinh trang sach (true/false): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                this.isAvailable = Boolean.parseBoolean(input);
                break;
            }
            System.out.println("Chi duoc nhap true hoac false");
        }
    }

    public void displayData() {
        System.out.printf("| %-10s | %-20s | %-20s | %-6d | %-20s | %-12s |\n",
                bookId, bookName, author, year, description, isAvailable ? "co san" : "dang muon");
    }
}