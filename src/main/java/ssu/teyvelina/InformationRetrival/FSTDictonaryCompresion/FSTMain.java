package ssu.teyvelina.InformationRetrival.FSTDictonaryCompresion;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

//TODO: 5. Сжатие словаря.
// Сжать словарь из задания 1. Для реализации выбрать один из двух способов - Lucene или FST-автомат.

public class FSTMain {
    public static void main(String[] args) throws IOException {
        MyFST myFST = new MyFST();
        String dictFile = "/home/teyvelina/Documents/IdeaProjects/information-retrival/dict.txt";
        List<String> words = myFST.readDictionary(dictFile);

        String fstSavePath = "/home/teyvelina/Documents/IdeaProjects/information-retrival/output/outFst.bin";
        myFST.saveFST(myFST.wordsToFST(words), Paths.get(fstSavePath));

        //--- проверка
        List<String> words1 = myFST.FSTToWords(myFST.readFSTFromFile(Paths.get(fstSavePath)));
        for (int i = 0; i < words1.size(); i++) {
            System.out.println(words1.get(i));
        }
    }
}
