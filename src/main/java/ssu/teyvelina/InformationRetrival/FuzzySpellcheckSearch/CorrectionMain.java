package ssu.teyvelina.InformationRetrival.FuzzySpellcheckSearch;

import java.io.File;
import java.io.IOException;
import java.util.*;


//TODO: 1 Исправление опечаток.
// Составить (найти) словарь (50000 слов).
// Построить k-граммы на основе словаря. Используя их найти список слов-коррекций для заданного слова.

public class CorrectionMain {
    public static void main(String[] args) throws IOException {

        //------ Словарь
        String dictFile = "/home/teyvelina/Documents/IdeaProjects/information-retrival/dict.txt";
        Set<String> dictionary = Dictionary.loadDictionary(dictFile);
        long dictFileSize = new File(dictFile).length();
        System.out.println("Dictionary file size: " + (dictFileSize / (1024 * 1024)) + " MB");

        //------- Расстояние Левенштейна
        Levenshtein lev = new Levenshtein(100);
        //проверка
        /*System.out.println(lev.getDistace("BCDE","ABCDEF", 10));
        System.out.println(lev.getDistace("ABC","ABCDEF", 10));
        System.out.println(lev.getDistace("ABC","BCDE", 10));
        System.out.println(lev.getDistace("BCE","BCE", 10));
        System.out.println(lev.getDistace("BCE","CBE", 10));*/

        //------ Ввод данных
        Scanner in = new Scanner(System.in);
        System.out.print("Enter word to correct: ");
        String word = in.nextLine();
        System.out.print("Enter k: ");
        int k = in.nextInt();
        in.close();

        //------- Словарь k-грамм
        Map<String, Set<String>> dictKGram = new HashMap<String, Set<String>>();
        KGramm kgr = new KGramm();

        for(String s : dictionary){
            Set<String> sKGr = kgr.makeKGramm(s,k);
            for(String skgr : sKGr){
                if(dictKGram.containsKey(skgr))
                    dictKGram.get(skgr).add(s);
                else {
                    Set<String> tmp = new HashSet<String>();
                    tmp.add(s);
                    dictKGram.put(skgr, tmp);
                }
            }
        }

        long timestart1 = System.currentTimeMillis();

        //------- Исправление опечаток
        Set<String> wordKGram = kgr.makeKGramm(word, k);
        Set<String> correct = new HashSet<String>();
        for(String s : wordKGram){
            if (dictKGram.containsKey(s)){
                correct.addAll(dictKGram.get(s));
            }
        }

        //------- Расстояние Левенштейна для найденных слов
        int min = 100000;
        Set<String> result = new HashSet<String>();
        for (String s : correct) {
            int dist = lev.getDistace(s, word, word.length());
            if (dist < min){
                min = dist;
                result.clear();
            }
            if (dist == min)
                result.add(s);
        }
        System.out.print("Correct: ");
        System.out.println(result);

        Map<Integer, Set<String>> distances = new HashMap<Integer, Set<String>>();
        for (String s : result){
            int d = lev.getDistace(word, s, s.length());
            if (distances.containsKey(d)){
                distances.get(d).add(s);
            } else {
                Set<String> tmp = new HashSet<String>();
                tmp.add(s);
                distances.put(d,tmp);
            }
        }
        //System.out.println(distances);
        long timeend1 = System.currentTimeMillis() ;
        System.out.println("Time with k-gramms: " + (timeend1-timestart1) + "ms");


        //------------------- без k-грамм
        long timestart2 = System.currentTimeMillis();
        //------- Расстояние Левенштейна
        int minim = 100000;
        Set<String> result1 = new HashSet<String>();
        for (String s : dictionary) {
            int dist = lev.getDistace(s, word, word.length());
            if (dist < min){
                min = dist;
                result1.clear();
            }
            if (dist == min)
                result1.add(s);
        }
        System.out.print("Correct 2: ");
        System.out.println(result);
        long timeend2 = System.currentTimeMillis() ;
        System.out.println("Time without k-gramms: " + (timeend2-timestart2) + "ms");
        //-------------------
    }
}

