package com.cn.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.common.Result;
import com.cn.entity.Book;
import com.cn.entity.User;
import com.cn.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    BookService bookService;



    @PostMapping
    public Result<?> save(@RequestBody Book book){
        bookService.insert(book);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Book book) {
        bookService.updateById(book);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable long id){
        bookService.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam Integer pageNum,
                              @RequestParam Integer pageSize,
                              @RequestParam String search){
        LambdaQueryWrapper<Book> wrapper = Wrappers.<Book>lambdaQuery();
        //避免search为null时查出数据为null
        wrapper.like(StrUtil.isNotBlank(search),Book::getName,search);

        Page<Book> bookPage = bookService.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(bookPage);
    }

}
