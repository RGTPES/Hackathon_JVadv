package rl.presentation;

import rl.business.LibaryBusiness;
import rl.entity.Book;

import java.util.Scanner;

public class LibararyManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibaryBusiness libararyBusiness = LibaryBusiness.getInstance();

        do {
            System.out.println("********** QUAN LY KHO HANG **********");
            System.out.println("1. Hien thi toan bo danh sach");
            System.out.println("2. Them moi sach");
            System.out.println("3. Cap nhat thong tin sach theo ma");
            System.out.println("4. Xoa sach theo ma");
            System.out.println("5. Tim sach theo ten tac gia");
            System.out.println("6. Thong ke tinh trang sach");
            System.out.println("7. Sap xep sach theo nam xuat ban giam dan");
            System.out.println("8. Thoat");
            System.out.print("Lua chon cua ban: ");

            String answer = sc.nextLine().trim();

            switch (answer) {
                case "1":
                    libararyBusiness.displayAllBooks();
                    break;

                case "2":
                    boolean continueAdd = true;
                    while (continueAdd) {
                        Book book = new Book();

                        while (true) {
                            System.out.print("Moi ban nhap id sach: ");
                            String newId = sc.nextLine().trim();
                            if (!newId.matches("B\\d{3}")) {
                                System.out.println("Ma sach phai co dang Bxxx");
                                continue;
                            }
                            if (!libararyBusiness.checkBookIdExists(newId)) {
                                book.setBookId(newId);
                                break;
                            }
                            System.out.println("Ma sach da ton tai");
                        }

                        book.inputData(sc);
                        libararyBusiness.addBook(book);

                        System.out.print("Ban co muon tiep tuc them khong? (Y/N): ");
                        String choice = sc.nextLine().trim();
                        if (!choice.equalsIgnoreCase("Y")) {
                            continueAdd = false;
                        }
                    }
                    break;

                case "3":
                    System.out.print("Nhap ma sach can cap nhat: ");
                    String updateId = sc.nextLine().trim();
                    libararyBusiness.updateBookById(sc, updateId);
                    break;

                case "4":
                    System.out.print("Nhap ma sach can xoa: ");
                    String deleteId = sc.nextLine().trim();
                    libararyBusiness.deleteBookById(deleteId);
                    break;

                case "5":
                    System.out.print("Nhap ten tac gia can tim: ");
                    String author = sc.nextLine().trim();
                    libararyBusiness.searchBookByAuthor(author);
                    break;

                case "6":
                    libararyBusiness.statisticsBookStatus();
                    break;

                case "7":
                    libararyBusiness.sortBooksByYearDesc();
                    break;

                case "8":
                    System.out.println("Thoat chuong trinh");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Vui long chon tu 1 den 8");
            }
        } while (true);
    }
}