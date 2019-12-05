package com.aaa.service;

import com.aaa.model.BookInfo;

import java.util.List;

/**
 * @Description :
 * @Author      : cat
 * @exception   :
 * @CreateDate  : 2019/11/21 15:42
 * @Version     : 1.0
 */
public interface BookService {
    /**
     * 查询所有书籍信息
     * @param
     * @return java.util.List<com.aaa.qy105.model.BookInfo>
     * @Author: cat
     * @Date: 2019/11/21
     */
    List<BookInfo> getAllBook();

    /**
     * 添加图书
     * @param bookInfo
     * @return void
     * @Author: cat
     * @Date: 2019/11/21
     */
    void addBook(BookInfo bookInfo);

    /**
     * 修改图书
     * @param bookInfo
     * @return void
     * @Author: cat
     * @Date: 2019/11/21
     */
    void updateBook(BookInfo bookInfo);
    /**
     * 删除图书
     * @param bookId
     * @return void
     * @Author: cat
     * @Date: 2019/11/21
     */
    void deleteBook(Integer bookId);
}
