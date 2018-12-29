package ssu.teyvelina.InformationRetrival;

import org.apache.lucene.queryparser.classic.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ssu.teyvelina.InformationRetrival.Lucene.Indexer;
import ssu.teyvelina.InformationRetrival.Lucene.LuceneConstants;
import ssu.teyvelina.InformationRetrival.Lucene.Searcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    private static int ErrorCount = 0;
    static int counter = 1;
    private static String URL = "https://www.regard.ru/catalog/group24000/page";

    private static String indexDirectoryPath = "/home/teyvelina/Documents/IdeaProjects/information-retrival/index";
    private static final ArrayList<Good> list = new ArrayList<Good>();
    private static final Set<String> brandsList = new HashSet<>();
    private static final Set<String> typesList = new HashSet<>();
    private static Set<String> setOfGoods = new HashSet<String>();

    //TODO:
    // 2. Сбор информации и ее обработка.
    //  1) Подобрать сайт. Спарсить не менее 10000 элементов.
    //  2) Выбрать часть данных и составить на их основе индекс с помощью Lucene.
    //  3) Сделать поиск по элементам.
    //  4) Протестить Lucene на этих данных (сделать запросы).
    // 3. Синонимы.
    //  1)Добавить словарь синонимов (поиск синонимов). Написать свой анализатор и synonymMap.
    //  2)Сделать запросы по синонимам.
    // 4. NDCG.
    //  Вычислить NDCG для поиска (оценка поиска). Написать запрос к системе, получить для него список релевантностей
    //  и реализовать их по формуле NDCG. Алгоритм вычисления релевантности придумать самому.
    // 6. Улучшение поиска.
    // Если в результате выполнения задания 4, получено значение NDCG меньше 1, то необходимо довести это значение до 1.


    public static void main(String[] args) throws IOException, ParseException {
        list.clear();
        brandsList.clear();
        typesList.clear();
        //TODO: 2.1 Bыгрузка с сайта
//        Load();
//        //---------- запись в файл
//        Save();
//        //---------- чтение данных из файла
//        dataFromFile("goods.txt");
//        System.out.println(list.size() + " элементов");
//        //TODO: 2.2 Индексация
//        Indexer indexer = new Indexer(indexDirectoryPath);
//        indexer.createIndex(list);

        //проверка
        /*System.out.println("LIST...");
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }*/

        //TODO: 2.4 Поиск
        Searcher searcher = new Searcher();
//        ArrayList<Good> searchres = searcher.search(LuceneConstants.DESCRIPTION, "Full HD", indexDirectoryPath, "");
//        ArrayList<Good> searchResult1 = searcher.search(LuceneConstants.BRAND, "Dell", indexDirectoryPath, "");
//        ArrayList<Good> searchResult2 = searcher.search(LuceneConstants.BRAND, "ASUS", indexDirectoryPath, "");
//        ArrayList<Good> searchResult3 = searcher.search(LuceneConstants.BRAND, "Acer", indexDirectoryPath, "");
//        ArrayList<Good> searchResult4 = searcher.search(LuceneConstants.BRAND, "Braun", indexDirectoryPath, "");
//
//        ArrayList<Good> searchResult5 = searcher.search(LuceneConstants.TYPE, "чехол", indexDirectoryPath, "");
//        ArrayList<Good> searchResult6 = searcher.search(LuceneConstants.TYPE, "клавиатура", indexDirectoryPath, "");
//        ArrayList<Good> searchResult7 = searcher.search(LuceneConstants.TYPE, "DVD-плеер", indexDirectoryPath, "");
//        ArrayList<Good> searchResult8 = searcher.search(LuceneConstants.TYPE, "ноутбук", indexDirectoryPath, "");
        //TODO: 3.2 Запросы с синонимами
//        //---------- Синонимы
//        //"минисистема", "мидисистема", "микросистема", "музыкальныйцентр"
//        ArrayList<Good> searchResult9 = searcher.search(LuceneConstants.TYPE, "мидисистема", indexDirectoryPath, "");
//        ArrayList<Good> searchResult10 = searcher.search(LuceneConstants.TYPE, "минисистема", indexDirectoryPath, "");
//        ArrayList<Good> searchResult11 = searcher.search(LuceneConstants.TYPE, "микросистема", indexDirectoryPath, "");
//        ArrayList<Good> searchResult12 = searcher.search(LuceneConstants.TYPE, "музыкальныйцентр", indexDirectoryPath, "");
//
//        System.out.println("Синонимы....");
//
//        ArrayList<Good> searchResult13 = searcher.search(LuceneConstants.TYPE, "мидисистема", indexDirectoryPath, "Synonym");
//        ArrayList<Good> searchResult14 = searcher.search(LuceneConstants.TYPE, "минисистема", indexDirectoryPath, "Synonym");
//        ArrayList<Good> searchResult15 = searcher.search(LuceneConstants.TYPE, "микросистема", indexDirectoryPath, "Synonym");
//        ArrayList<Good> searchResult16 = searcher.search(LuceneConstants.TYPE, "музыкальныйцентр", indexDirectoryPath, "Synonym");
//
//        System.out.println("-------------------------------");
//
//        //"SSD", "кэширующий накопитель", "HDD", "гибридный", "жесткийдиск"
//        ArrayList<Good> searchResult17 = searcher.search(LuceneConstants.TYPE, "SSD", indexDirectoryPath, "");
//        ArrayList<Good> searchResult18 = searcher.search(LuceneConstants.TYPE, "гибридный", indexDirectoryPath, "");
//        ArrayList<Good> searchResult19 = searcher.search(LuceneConstants.TYPE, "HDD", indexDirectoryPath, "");
//        ArrayList<Good> searchResult20 = searcher.search(LuceneConstants.TYPE, "жесткийдиск", indexDirectoryPath, "");
//
//        System.out.println("Синонимы...");
//
//        ArrayList<Good> searchResult21 = searcher.search(LuceneConstants.TYPE, "SSD", indexDirectoryPath, "Synonym");
//        ArrayList<Good> searchResult22 = searcher.search(LuceneConstants.TYPE, "гибридный", indexDirectoryPath, "Synonym");
//        ArrayList<Good> searchResult23 = searcher.search(LuceneConstants.TYPE, "HDD", indexDirectoryPath, "Synonym");
//        ArrayList<Good> searchResult24 = searcher.search(LuceneConstants.TYPE, "жесткийдиск", indexDirectoryPath, "Synonym");

        //TODO: 4 NDCG
        ArrayList<Good> result1 = searcher.search2(LuceneConstants.BRAND, "ASUS",
                LuceneConstants.TYPE, "ноутбук",
                LuceneConstants.DESCRIPTION, "Windows",
                LuceneConstants.DESCRIPTION, "Professional",
                indexDirectoryPath, "");
        FileWriter fileWriter = new FileWriter("result.txt");
        for(Good g : result1){
            fileWriter.write(g.toSimpleString());
        }
        fileWriter.close();

        ArrayList<Good> res1 = new ArrayList<>();
        for(int i = 0; i < 15; i++){
            res1.add(result1.get(i));
        }
        Map<Integer, Integer> respMap1 = setResponse(
                new Response("ASUS", "ноутбук", "Windows 10 Professional"), res1);
        //TODO 6
        Map<Integer, Integer> myRespMap1 = new HashMap<>();
        myRespMap1.put(0, 3);
        myRespMap1.put(1, 3);
        myRespMap1.put(2, 3);
        myRespMap1.put(3, 2);
        myRespMap1.put(4, 3);
        myRespMap1.put(5, 3);
        myRespMap1.put(6, 3);
        myRespMap1.put(7, 3);
        myRespMap1.put(8, 3);
        myRespMap1.put(9, 2);
        myRespMap1.put(10, 2);
        myRespMap1.put(11, 3);
        myRespMap1.put(12, 2);
        myRespMap1.put(13, 2);
        myRespMap1.put(14, 2);

        System.out.println("NDCG = " + Ranking.getNDCG(respMap1));
        System.out.println("NDCG_my = " + Ranking.getNDCG(myRespMap1));
    }

    private static void Save() throws IOException {
        dataToFile();
        brandsToFile();
        typesToFile();
    }

    private static void Load(){
        System.out.println("Data is loading, please wait."); //1
        long timestart = System.currentTimeMillis();
        LoadData(1, 63, URL);

        URL = "https://www.regard.ru/catalog/group52000/page"; //2
        LoadData(1, 12, URL);//2982

        URL = "https://www.regard.ru/catalog/group45000/page"; //моноблоки
        LoadData(1, 15, URL);//3573

        URL = "https://www.regard.ru/catalog/group36000/page"; //4
        LoadData(1, 13, URL);//4078

        URL = "https://www.regard.ru/catalog/group29000/page";
        LoadData(1, 20, URL);//5006

        //URL = "https://www.regard.ru/catalog/group53000/page";
        URL = "https://www.regard.ru/catalog/group53001/page";
        LoadData(1, 1, URL);//6252
        URL = "https://www.regard.ru/catalog/group57244/page"; //антенны
        LoadData(1, 2, URL);
        URL = "https://www.regard.ru/catalog/group56089/page"; // аудиомагнитола
        LoadData(1, 2, URL);
        URL = "https://www.regard.ru/catalog/group53028/page"; //домашний кинотеатр
        LoadData(1, 1, URL);
        URL = "https://www.regard.ru/catalog/group53049/page"; // кронштейн
        LoadData(1, 13, URL);
        URL = "https://www.regard.ru/catalog/group53040/page";
        LoadData(1, 1, URL);
        URL = "https://www.regard.ru/catalog/group53004/page";
        LoadData(1, 2, URL);
        URL ="https://www.regard.ru/catalog/group46000/page"; // портативный плеер
        LoadData(1, 2, URL);
        URL = "https://www.regard.ru/catalog/group16185/page";
        LoadData(1, 3, URL);
        URL = "https://www.regard.ru/catalog/group41000/page";
        LoadData(1, 5, URL);


        URL = "https://www.regard.ru/catalog/group43000/page"; // блок питания
        LoadData(1, 12, URL);

        URL = "https://www.regard.ru/catalog/group64000/page"; // бытовая техника   +-
        LoadData(1, 52, URL);//8326

        URL = "https://www.regard.ru/catalog/group5000/page";
        LoadData(1, 27, URL);//9391

        URL = "https://www.regard.ru/catalog/group13000/page";
        LoadData(1, 12, URL);//9841

        URL = "https://www.regard.ru/catalog/group14000/page";
        LoadData(1, 31, URL);//11058

        long timeend = System.currentTimeMillis();
        System.out.println("Времени на загрузку с сайта затрачено: "+ ((timeend - timestart) / 1000) + " секунд"); //2613
        System.out.println("Errors: " + ErrorCount);
    }

    private static void LoadData(int startPage, int numOfPages, String url) {
        //numOfPages = 1;
        for (int i = startPage; i < startPage + numOfPages; i++) {
            int page = i;
            Document doc = null;
            try {
                doc = Jsoup.connect(url + page + ".htm").userAgent("Yandex").get();
                Elements mainElements = doc.select("div.block");
                if (mainElements != null){
                    for (int j = 0; j < mainElements.size(); j++){
                        Element element = mainElements.get(j);

                        String good_url = Parser.findUrl(element.toString(), "<div class=\"block_img\"> \n" +
                                "   <a href=\"");
                        String good_id = Parser.findId(element.toString(), "<div class=\"code\">\n" +
                                "    ID: ");
                        String good_name = Parser.findValue(element.toString(), "class=\"header\">");
                        String good_price = Parser.findValue(element.toString(), "title=\"Добавить в корзину\">&nbsp;</a></span> \n" +
                                "   <span>");
                        String good_description = Parser.findDescription(element.toString(), "</a> \n" +
                                "  </div>");

                        String good_brand = "";
                        String good_type = "";
                        // переходим по ссылке на товар, чтобы считать характеристики (производитель, тип и т.д.)
                        Document doc1 = null;
                        good_brand = "-";
                        good_type = "-";
                        try {
                            doc1 = Jsoup.connect(good_url).userAgent("Yandex").get();
                            Elements goodElements = doc1.getElementsByTag("tr");
                            good_brand = Parser.findBrand(goodElements);
                            good_type = Parser.findType(goodElements);
                            if (url.contains("group13000")){  // клавиатуры (тип устройства вместо тип)
                                good_type = Parser.findTypeDev(goodElements);
                            }
                            //System.out.println(goodElements.size());
                        }catch (Exception e){
                            ErrorCount++;
                            e.printStackTrace();
                        }
                        if (good_brand == "")
                            good_brand = "-";
                        if (good_type == "")
                            good_type = "-";
                        /*моноблоки*/
                        if (url.contains("group45000")){
                            good_type = "моноблок";
                        }
                        /**/
                        /*антенны*/
                        if (url.contains("group57244")){
                            good_type = "антенна";
                        }
                        /**/
                        /*аудиомагнитола*/
                        if (url.contains("group56089")){
                            good_type = "аудиомагнитола";
                        }
                        /**/
                        /*домашний кинотеатр*/
                        if (url.contains("group53028")){
                            good_type = "домашний кинотеатр";
                        }
                        /**/
                        /*group53049*/
                        if (url.contains("group53049")){
                            good_type = "кронштейн";
                        }
                        /**/
                        /*блок питания*/
                        if (url.contains("group43000")){
                            good_type = "блок питания";
                        }
                        /**/
                        /*портативный плеер*/
                        if (url.contains("group46000")){
                            good_type = "портативный плеер";
                        }
                        /**/
                        /*мышь*/
                        if (url.contains("group14000")){
                            good_type = "мышь";
                        }
                        /**/
                        Good good = new Good(good_url, good_id, good_name, good_price, good_brand, good_type, good_description);
                        list.add(good);
                        brandsList.add(good_brand);
                        typesList.add(good_type);

                        System.out.println(counter);
                        counter++;
                    }
                }
            } catch (IOException e) {
                ErrorCount++;
                e.printStackTrace();
            }
        }
    }


    private static void dataToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("goods.txt");
        System.out.println("Элементов-------------------");
        System.out.println(list.size());
        for(Good g : list){
            fileWriter.write(g.toSimpleString());
        }
        fileWriter.close();
    }

    private static void brandsToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("brands.txt");
        for (String brand : brandsList){
            fileWriter.write(brand);
            fileWriter.write('\n');
        }
        fileWriter.close();
    }

    private static void typesToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("types.txt");
        for (String type : typesList){
            fileWriter.write(type);
            fileWriter.write('\n');
        }
        fileWriter.close();
    }

    private static String makeString(String input){
        String result = input;
        if (input.charAt(0) == ' '){
            result = input.substring(1, input.length());
        }
        if (input.charAt(input.length() - 1) == ' '){
            result = input.substring(0, input.length() - 1);
        }
        return result;
    }

    private static void dataFromFile(String fileName) throws IOException {
        list.clear();
        brandsList.clear();
        typesList.clear();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String urlStr = bufferedReader.readLine();
        while (urlStr != null){
            //Good good = new Good(good_url, good_id, good_name, good_price, good_brand, good_type);
            String idStr = bufferedReader.readLine(),
                    nameStr = bufferedReader.readLine(),
                    priceStr = bufferedReader.readLine(),
                    brandStr = makeString(bufferedReader.readLine()),
                    typeStr = bufferedReader.readLine(),
                    descriptionStr = bufferedReader.readLine();
            list.add(new Good(urlStr.toString(), idStr, nameStr, priceStr, brandStr, typeStr, descriptionStr));
            brandsList.add(brandStr);
            typesList.add(typeStr);
            urlStr = bufferedReader.readLine();
        }
    }

    //TODO: 4.2 Релевантность
    public static Map<Integer, Integer> setResponse(Response response, ArrayList<Good> searchResult){
        Map<Integer,Integer> responseMap = new HashMap<>();
        for (int i = 0; i < searchResult.size(); i++){
            if(searchResult.get(i).getBrand().toLowerCase().equals(response.brandName.toLowerCase())
                    && searchResult.get(i).getType().toLowerCase().equals(response.typeName.toLowerCase())
                    && searchResult.get(i).getDescription().contains(response.descriptionName) ){
                responseMap.put(i, 3);
            } else {                if (searchResult.get(i).getBrand().toLowerCase().equals(response.brandName.toLowerCase())
                    && searchResult.get(i).getType().toLowerCase().equals(response.typeName.toLowerCase())) {
                    responseMap.put(i, 2);
                } else {
                    if (searchResult.get(i).getBrand().toLowerCase().equals(response.brandName.toLowerCase())) {
                        responseMap.put(i, 1);
                    }
                }
            }
        }
        return responseMap;
  }
}