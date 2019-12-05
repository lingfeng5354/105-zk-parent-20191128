package com.aaa.model;

/**
 * @Description :
 * @Author      : cat
 * @exception   :
 * @CreateDate  : 2019/11/21
 * @Version     : 1.0
 */

public class BookInfo {
    private Integer bookId;
    private String bookName;
    private Integer bookStore;
    private double bookPrice;

    @Override
    public String toString() {
        return "BookInfo{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookStore=" + bookStore +
                ", bookPrice=" + bookPrice +
                '}';
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookStore() {
        return bookStore;
    }

    public void setBookStore(Integer bookStore) {
        this.bookStore = bookStore;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }
}
