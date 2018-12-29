package ssu.teyvelina.InformationRetrival.Lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import ssu.teyvelina.InformationRetrival.Good;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Indexer {
    private IndexWriter indexWriter;
    private IndexWriterConfig indexWriterConfig;

    public Indexer(String indexDirectoryPath) throws IOException {
        //this directory will contain the indexes
        Directory index =
                FSDirectory.open(new File(indexDirectoryPath).toPath());

        //create the indexer
        indexWriterConfig = new IndexWriterConfig();
        indexWriter = new IndexWriter(index, indexWriterConfig);
    }

    public void close() throws IOException {
        indexWriter.close();
    }

    private static Document getDocument(Good good) {
        Document document = new Document();

        document.add(new StringField(LuceneConstants.ID, good.getId(), Field.Store.YES));
        document.add(new StringField(LuceneConstants.URL, good.getUrl(), Field.Store.YES));
        document.add(new StringField(LuceneConstants.GOOD_NAME, good.getName(), Field.Store.YES));
        document.add(new StringField(LuceneConstants.PRICE, good.getPrice(), Field.Store.YES));
        document.add(new StringField(LuceneConstants.BRAND, good.getBrand(), Field.Store.YES));
        document.add(new StringField(LuceneConstants.TYPE, good.getType(), Field.Store.YES));
        document.add(new TextField(LuceneConstants.DESCRIPTION, good.getDescription(), Field.Store.YES));
        //String[] split = good.getName().split(" ");

        return document;
    }

    public void createIndex (ArrayList<Good> list) throws IOException {
        System.out.println("Indexing...");
        for(Good good : list){
            indexWriter.addDocument(getDocument(good));
        }
        int numIndexed;
        long startTime = System.currentTimeMillis();
        numIndexed = indexWriter.numDocs();
        long endTime = System.currentTimeMillis();
        System.out.println(numIndexed + " objects indexed, time taken: " + (endTime - startTime) + " ms");
        close();
    }
}
