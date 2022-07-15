package com.cn.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.common.Result;
import com.cn.entity.News;
import com.cn.service.NewsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Resource
    NewsService newsService;



    @PostMapping
    public Result<?> save(@RequestBody News news){
        news.setTime(new Date());
        newsService.insert(news);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody News news) {
        newsService.updateById(news);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable long id){
        newsService.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam Integer pageNum,
                              @RequestParam Integer pageSize,
                              @RequestParam String search){
        LambdaQueryWrapper<News> wrapper = Wrappers.<News>lambdaQuery();
        //避免search为null时查出数据为null
        wrapper.like(StrUtil.isNotBlank(search),News::getTitle,search);

        Page<News> newsPage = newsService.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(newsPage);
    }

}
