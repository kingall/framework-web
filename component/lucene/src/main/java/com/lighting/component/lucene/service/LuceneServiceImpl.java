package com.lighting.component.lucene.service;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LuceneServiceImpl {

    private IndexWriter indexWriter;
    private SearcherManager searcherManager;

    public static final String LUCENEINDEXPATH = "D:/temp/lucene/indexDir/";
    List<Document> docs = new ArrayList<Document>();

    public LuceneServiceImpl() throws IOException {
        Analyzer analyzer = new SimpleAnalyzer();
        Path path = Paths.get(LUCENEINDEXPATH);
        File file = path.toFile();
        if (!file.exists()) {
            //如果文件夹不存在,则创建
            file.mkdirs();
        }
        FSDirectory directory = FSDirectory.open(path);
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriter = new IndexWriter(directory, indexWriterConfig);
        /**
         * 清空索引,下面两个方法，打开时，启动应用时，会自动清空所有索引，
         * 如果项目的数据比较多，建索引时间长或索引不能清空，这个一定要装着
         * 所以大家要根据实际情况慎用此开关
         */
     /*   indexWriter.deleteAll();
        indexWriter.commit();*/
        searcherManager = new SearcherManager(indexWriter, false, false, new SearcherFactory());
        ControlledRealTimeReopenThread cRTReopenThead = new ControlledRealTimeReopenThread(indexWriter, searcherManager,
                5.0, 0.025);
        cRTReopenThead.setDaemon(true);
        //线程名称
        cRTReopenThead.setName("更新IndexReader线程");
        // 开启线程
        cRTReopenThead.start();
    }

    public void createTagsIndex(String id, String tags) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("tags", tags, Field.Store.NO));
        doc.add(new StringField("id", id, Field.Store.YES));
        docs.add(doc);

    }

    public void commit() throws IOException {
        indexWriter.addDocuments(docs);
        indexWriter.commit();
        docs = new ArrayList<Document>();
    }


/*
    @Override
    public PageQuery searchProduct(PageQuery pageQuery) throws Exception, ParseException {
        return null;
    }*/

    public void deleteProductIndexById(String id) throws IOException {

    }

    public void sreachByTags(List<String> tags) throws IOException {
        searcherManager.maybeRefresh();
        IndexSearcher indexSearcher = searcherManager.acquire();
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        for (String tag : tags) {
            System.out.println(tag);
            builder.add(new TermQuery(new Term("" +
                    "" +
                    "tags", tag)), BooleanClause.Occur.SHOULD);
        }
        BooleanQuery booleanQuery = builder.build();
        long startTime = System.currentTimeMillis();
        TopDocs hits = indexSearcher.search(booleanQuery, 100);
        System.out.println("耗时："+(System.currentTimeMillis() - startTime));
        System.out.println("总共查询到" + hits.totalHits + "个文档");
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = indexSearcher.doc(scoreDoc.doc);
            System.out.println(doc.get("id"));
            System.out.println(doc.get("tags"));
        }

    }
}
