package com.aaa.service.impl;

import com.aaa.mapper.BookMapper;
import com.aaa.model.BookInfo;
import com.aaa.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description :
 * @Author      : cat
 * @exception   :
 * @CreateDate  : 2019/11/21
 * @Version     : 1.0
 */
@Service
public class BookServiceImpl implements BookService {
    private Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    private BookMapper bookMapper;
    @Override
    public List<BookInfo> getAllBook() {
        List<BookInfo> allBook = bookMapper.getAllBook();
        if (allBook ==null && allBook.size()<=0){
            logger.warn("book is null");
            return null;
        }else {
            return allBook;
        }
    }

    @Override
    public void addBook(BookInfo bookInfo) {
        if (bookInfo==null){
            logger.warn("bookInfo is null");
        }else {
            bookMapper.addBook(bookInfo);
        }
    }

    @Override
    public void updateBook(BookInfo bookInfo) {
        if (bookInfo==null){
            logger.warn("bookInfo is null");
        }else {
            bookMapper.updateBook(bookInfo);
        }
    }

    @Override
    public void deleteBook(Integer bookId) {
        if (bookId==null){
            logger.warn("bookId is null");
        }else {
            bookMapper.deleteBook(bookId);
        }
    }
}
