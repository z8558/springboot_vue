package com.cn.service.ipml;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cn.entity.News;
import com.cn.service.NewsService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class NewsServiceImpl implements NewsService {
    @Override
    public int insert(News entity) {
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
    public int delete(Wrapper<News> queryWrapper) {
        return 0;
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return 0;
    }

    @Override
    public int updateById(News entity) {
        return 0;
    }

    @Override
    public int update(News entity, Wrapper<News> updateWrapper) {
        return 0;
    }

    @Override
    public News selectById(Serializable id) {
        return null;
    }

    @Override
    public List<News> selectBatchIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public List<News> selectByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public News selectOne(Wrapper<News> queryWrapper) {
        return null;
    }

    @Override
    public Integer selectCount(Wrapper<News> queryWrapper) {
        return null;
    }

    @Override
    public List<News> selectList(Wrapper<News> queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<News> queryWrapper) {
        return null;
    }

    @Override
    public List<Object> selectObjs(Wrapper<News> queryWrapper) {
        return null;
    }

    @Override
    public <P extends IPage<News>> P selectPage(P page, Wrapper<News> queryWrapper) {
        return null;
    }

    @Override
    public <P extends IPage<Map<String, Object>>> P selectMapsPage(P page, Wrapper<News> queryWrapper) {
        return null;
    }
}
