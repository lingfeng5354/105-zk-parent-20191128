package com.aaa.mapper;

import com.aaa.model.BookInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description :
 * @Author      : cat
 * @exception   :
 * @CreateDate  : 2019/11/21 15:21
 * @Version     : 1.0
 */
@Mapper
@CacheNamespace(implementation =com.aaa.redis.RedisCache.class)
public interface BookMapper {
    /**
    * 查询所有书籍信息
    * @param
    * @return java.util.List<com.aaa.qy105.model.BookInfo>
    * @Author: cat
    * @Date: 2019/11/21
    */
    @Select("select * from book_info")
    List<BookInfo> getAllBook();
    /**
    * 添加图书
    * @param bookInfo
    * @return void
    * @Author: cat
    * @Date: 2019/11/21
    */
    @Insert("insert into book_info(book_id,book_name,book_store,book_price)values(#{bookId},#{bookName},#{bookStore},#{bookPrice})")
    void addBook(BookInfo bookInfo);
    /**
    * 修改图书
    * @param bookInfo
    * @return void
    * @Author: cat
    * @Date: 2019/11/21
    */
    @Update("update book_info set book_name=#{bookName},book_store=#{bookStore},book_price=#{bookPrice} where book_id=#{bookId}")
    void updateBook(BookInfo bookInfo);
    /**
    * 删除图书
    * @param bookId
    * @return void
    * @Author: cat
    * @Date: 2019/11/21
    */
    @Delete("delete from book_info where book_id=#{bookId}")
    void deleteBook(Integer bookId);
}
