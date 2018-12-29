package ssu.teyvelina.InformationRetrival.FuzzySpellcheckSearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {
    private Set<String> dict;

    public static Set<String> loadDictionary(String filename) throws IOException {
        Set<String> lines = new HashSet<String>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null)
            lines.add(line);

        reader.close();
        return lines;
    }

    public Set<String> getDict(){
        return this.dict;
    }
}
