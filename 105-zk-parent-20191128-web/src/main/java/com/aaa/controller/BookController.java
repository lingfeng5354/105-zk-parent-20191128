package com.aaa.controller;


import com.aaa.model.BookInfo;
import com.aaa.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description :
 * @Author      : cat
 * @exception   :
 * @CreateDate  : 2019/11/21
 * @Version     : 1.0
 */
@Controller
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookService bookService;
    @RequestMapping("getAllBook")
    public String getAllBook(ModelMap modelMap){
        List<BookInfo> bookList = bookService.getAllBook();
        if (bookList==null){
            modelMap.addAttribute("msg","没有获取到书籍");
        }else {
            modelMap.addAttribute("bookList", bookList);
        }
        return "index";
    }
    @RequestMapping("updateBook")
    @ResponseBody
    public String updateBook(BookInfo bookInfo){
        bookService.updateBook(bookInfo);
        return "success";
    }
    @RequestMapping("deleteBook")
    @ResponseBody
    public String deleteBook(Integer bookId){
        bookService.deleteBook(bookId);
        return "success";
    }

    @RequestMapping("addBook")
    @ResponseBody
    public String addBook(BookInfo bookInfo){
        bookService.addBook(bookInfo);
        return "success";
    }
}
