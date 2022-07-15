package com.cn.service.ipml;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cn.entity.Book;
import com.cn.entity.User;
import com.cn.service.BookService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class BookServiceImpl implements BookService {

    @Override
    public int insert(Book entity) {
        return 0;
    }

    @Override
    public int deleteById(Serializable id) {
        return 0;
    }

    @Override
    public int deleteByMap(Map<String, Object> columnMap) {
        return 0;
    }

    @Override
    public int delete(Wrapper<Book> queryWrapper) {
        return 0;
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return 0;
    }

    @Override
    public int updateById(Book entity) {
        return 0;
    }

    @Override
    public int update(Book entity, Wrapper<Book> updateWrapper) {
        return 0;
    }

    @Override
    public Book selectById(Serializable id) {
        return null;
    }

    @Override
    public List<Book> selectBatchIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public List<Book> selectByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public Book selectOne(Wrapper<Book> queryWrapper) {
        return null;
    }

    @Override
    public Integer selectCount(Wrapper<Book> queryWrapper) {
        return null;
    }

    @Override
    public List<Book> selectList(Wrapper<Book> queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<Book> queryWrapper) {
        return null;
    }

    @Override
    public List<Object> selectObjs(Wrapper<Book> queryWrapper) {
        return null;
    }

    @Override
    public <P extends IPage<Book>> P selectPage(P page, Wrapper<Book> queryWrapper) {
        return null;
    }

    @Override
    public <P extends IPage<Map<String, Object>>> P selectMapsPage(P page, Wrapper<Book> queryWrapper) {
        return null;
    }
}
