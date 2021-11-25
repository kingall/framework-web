package com.lighting.component.lucene.service;

import com.sun.el.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface LuceneService {
    /**
     * 增加索引
     * @param list
     * @throws IOException
     */
    public void createProductIndex(List<?> list) throws IOException;

/*
    */
/**
     * 查询
     * @param pageQuery
     * @return
     * @throws Exception
     * @throws ParseException
     *//*

    public PageQuery searchProduct(PageQuery pageQuery) throws Exception, ParseException;
*/

    /**
     *删除
     * @param id
     * @throws IOException
     */
    public void deleteProductIndexById(String id) throws IOException;
}
