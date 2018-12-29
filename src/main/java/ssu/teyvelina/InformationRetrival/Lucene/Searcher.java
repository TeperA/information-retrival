package ssu.teyvelina.InformationRetrival.Lucene;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import ssu.teyvelina.InformationRetrival.AnalyzerSynonyms;
import ssu.teyvelina.InformationRetrival.Good;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//TODO: 2.3

public class Searcher {
    public ArrayList<Good> search(String constant, String text, String indexDirectoryPath, String analyzerName) throws IOException, ParseException {
        Directory indexDirectory = FSDirectory.open(new File(indexDirectoryPath).toPath());
        IndexReader indexReader = DirectoryReader.open(indexDirectory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        QueryParser queryParser = null;
        if (analyzerName == "Synonym")
            queryParser = new QueryParser(constant, new AnalyzerSynonyms());
        else
            queryParser = new QueryParser(constant, new StandardAnalyzer());
        Query query = queryParser.parse(text);
        System.out.println("Search... '" + query + "'");

        TopDocs docs = indexSearcher.search(query, 10000);

        long num = docs.totalHits;
        System.out.println("Number of results: " + num + '\n');

        ArrayList<Good> searchResult = new ArrayList<Good>();

        for(ScoreDoc scoreDoc : docs.scoreDocs){
            org.apache.lucene.document.Document doc
                    = indexSearcher.doc(scoreDoc.doc);
            Good good = new Good(doc.get(LuceneConstants.URL), doc.get(LuceneConstants.ID),
                    doc.get(LuceneConstants.GOOD_NAME), doc.get(LuceneConstants.PRICE), doc.get(LuceneConstants.BRAND), doc.get(LuceneConstants.TYPE), doc.get(LuceneConstants.DESCRIPTION));
            searchResult.add(good);
            // add String
            //System.out.println(good.toSimpleString());
        }
        return searchResult;
    }/**/

    public ArrayList<Good> search2(String constant1, String text1,
                                   String constant2, String text2,
                                   String constant3, String text3,
                                   String indexDirectoryPath, String analyzerName) throws IOException, ParseException {
        Directory indexDirectory = FSDirectory.open(new File(indexDirectoryPath).toPath());
        IndexReader indexReader = DirectoryReader.open(indexDirectory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        Term term1 = new Term(constant1, text1);
        Term term2 = new Term(constant2, text2);
        Term term3 = new Term(constant3, text3);
        QueryParser queryParser1 = null, queryParser2 = null, queryParser3 = null;
        if (analyzerName == "Synonym") {
            queryParser1 = new QueryParser(constant1, new AnalyzerSynonyms());
            queryParser2 = new QueryParser(constant2, new AnalyzerSynonyms());
            queryParser3 = new QueryParser(constant3, new AnalyzerSynonyms());
        }
        else {
            queryParser1 = new QueryParser(constant1, new StandardAnalyzer());
            queryParser2 = new QueryParser(constant2, new StandardAnalyzer());
            queryParser3 = new QueryParser(constant3, new StandardAnalyzer());
        }
        Query query1 = new TermQuery(term1);
        Query query2 = new TermQuery(term2);
        Query query3 = new TermQuery(term3);

        BooleanQuery booleanQuery = new BooleanQuery.Builder()
                .add(query1, BooleanClause.Occur.SHOULD)
                .add(query2, BooleanClause.Occur.SHOULD)
                .add(query3, BooleanClause.Occur.SHOULD).build();

        TopDocs docs = indexSearcher.search(booleanQuery, 11000);

        long num = docs.totalHits;
        System.out.println("Number of results: " + num + '\n');

        ArrayList<Good> searchResult = new ArrayList<Good>();

        for(ScoreDoc scoreDoc : docs.scoreDocs){
            org.apache.lucene.document.Document doc
                    = indexSearcher.doc(scoreDoc.doc);
            Good good = new Good(doc.get(LuceneConstants.URL), doc.get(LuceneConstants.ID),
                    doc.get(LuceneConstants.GOOD_NAME), doc.get(LuceneConstants.PRICE),
                    doc.get(LuceneConstants.BRAND), doc.get(LuceneConstants.TYPE),
                    doc.get(LuceneConstants.DESCRIPTION));
            searchResult.add(good);
        }
        return searchResult;
    }

    public ArrayList<Good> search2(String constant1, String text1,
                                   String constant2, String text2,
                                   String constant3, String text3,
                                   String constant4, String text4,
                                   String indexDirectoryPath, String analyzerName) throws IOException, ParseException {
        Directory indexDirectory = FSDirectory.open(new File(indexDirectoryPath).toPath());
        IndexReader indexReader = DirectoryReader.open(indexDirectory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        Term term1 = new Term(constant1, text1);
        Term term2 = new Term(constant2, text2);
        Term term3 = new Term(constant3, text3);
        Term term4 = new Term(constant4, text4);
        QueryParser queryParser1 = null, queryParser2 = null, queryParser3 = null, queryParser4 = null;
        if (analyzerName == "Synonym") {
            queryParser1 = new QueryParser(constant1, new AnalyzerSynonyms());
            queryParser2 = new QueryParser(constant2, new AnalyzerSynonyms());
            queryParser3 = new QueryParser(constant3, new AnalyzerSynonyms());
            queryParser4 = new QueryParser(constant4, new AnalyzerSynonyms());
        }
        else {
            queryParser1 = new QueryParser(constant1, new StandardAnalyzer());
            queryParser2 = new QueryParser(constant2, new StandardAnalyzer());
            queryParser3 = new QueryParser(constant3, new StandardAnalyzer());
            queryParser4 = new QueryParser(constant4, new StandardAnalyzer());
        }
        Query query1 = new TermQuery(term1);
        Query query2 = new TermQuery(term2);
        Query query3 = new TermQuery(term3);
        Query query4 = new TermQuery(term4);

        BooleanQuery booleanQuery = new BooleanQuery.Builder()
                .add(query1, BooleanClause.Occur.SHOULD)
                .add(query2, BooleanClause.Occur.SHOULD)
                .add(query4, BooleanClause.Occur.SHOULD)
                .add(query3, BooleanClause.Occur.SHOULD).build();

        TopDocs docs = indexSearcher.search(booleanQuery, 11000);

        long num = docs.totalHits;
        System.out.println("Number of results: " + num + '\n');

        ArrayList<Good> searchResult = new ArrayList<Good>();

        for(ScoreDoc scoreDoc : docs.scoreDocs){
            org.apache.lucene.document.Document doc = indexSearcher.doc(scoreDoc.doc);
            Good good = new Good(doc.get(LuceneConstants.URL), doc.get(LuceneConstants.ID),
                    doc.get(LuceneConstants.GOOD_NAME), doc.get(LuceneConstants.PRICE),
                    doc.get(LuceneConstants.BRAND), doc.get(LuceneConstants.TYPE),
                    doc.get(LuceneConstants.DESCRIPTION));
            searchResult.add(good);
        }
        return searchResult;
    }
    /*public ArrayList<Good> search(String constant, String text, int maxElementCount, String indexDirectoryPath, String analyzerName) throws IOException, ParseException {
        Directory indexDirectory = FSDirectory.open(new File(indexDirectoryPath).toPath());
        IndexReader indexReader = DirectoryReader.open(indexDirectory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        QueryParser queryParser = null;ew FileWriter("sorted1.txt");
//        for (Good g : sortedList1) {
//            fw1.write(g.toSimpleString());
//        }
//        fw1.close();
//        Map<Good, Integer> respMap1_new = setResponse(new Response("Acer", "ноутбук", "Intel Core i5"), sortedList1);
//        System.out.
        if (analyzerName == "Synonym")
            queryParser = new QueryParser(constant, new AnalyzerSynonyms());
        else
            queryParser = new QueryParser(constant, new StandardAnalyzer());
        Query query = queryParser.parse(text);
        System.out.println("Search... '" + query + "'");

        TopDocs docs = indexSearcher.search(query, maxElementCount);

        long num = docs.totalHits;
        System.out.println("Number of results: " + num + '\n');

        ArrayList<Good> searchResult = new ArrayList<Good>();

        for(ScoreDoc scoreDoc : docs.scoreDocs){
            org.apache.lucene.document.Document doc
                    = indexSearcher.doc(scoreDoc.doc);
            Good good = new Good(doc.get(LuceneConstants.URL), doc.get(LuceneConstants.ID),
                    doc.get(LuceneConstants.GOOD_NAME), doc.get(LuceneConstants.PRICE), doc.get(LuceneConstants.BRAND), doc.get(LuceneConstants.TYPE), doc.get(LuceneConstants.DESCRIPTION));
            searchResult.add(good);
            //System.out.println(good.toSimpleString());
        }
        return searchResult;
    }/**/
}
