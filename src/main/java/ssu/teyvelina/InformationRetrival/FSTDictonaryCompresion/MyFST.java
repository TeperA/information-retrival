package ssu.teyvelina.InformationRetrival.FSTDictonaryCompresion;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.IndexOutput;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.BytesRefBuilder;
import org.apache.lucene.util.IntsRefBuilder;
import org.apache.lucene.util.fst.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MyFST {
    protected static List<String> readDictionary(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
        List<String> words = new ArrayList<>();
        String word = reader.readLine();
        while(word != null){
            words.add(word.toLowerCase());
            word = reader.readLine();
        }
        return words;
    }

    protected static FST<Long> wordsToFST(List<String> words) throws IOException {
        PositiveIntOutputs outputs = PositiveIntOutputs.getSingleton();
        Builder<Long> builder = new Builder<Long>(FST.INPUT_TYPE.BYTE1, outputs);
        IntsRefBuilder intsRefBuilder = new IntsRefBuilder();
        BytesRefBuilder bytesRefBuilder = new BytesRefBuilder();
        for (String word : words) {
            bytesRefBuilder.copyChars(word);
            builder.add(Util.toIntsRef(bytesRefBuilder.get(), intsRefBuilder), 1L);
        }
        return builder.finish();
    }

    protected static void saveFST(FST<Long> fst, Path path) throws IOException {
        Path fstFolderPath = path.getParent();
        Path fstFileName = path.getFileName();
        Directory directory = new SimpleFSDirectory(fstFolderPath);
        if (fst != null) {
            IOContext context = IOContext.DEFAULT;
            IndexOutput output = directory.createOutput(String.valueOf(fstFileName), context);
            fst.save(output);
            output.close();
        } else {
            System.out.println("Error! FST is null.");
        }
    }

    protected static FST<Long> readFSTFromFile(Path path) throws IOException {
        PositiveIntOutputs positiveIntOutputs = PositiveIntOutputs.getSingleton();
        return FST.read(path, positiveIntOutputs);
    }

    protected static List<String> FSTToWords(FST<Long> fst) throws IOException {
        List<String> words = new ArrayList<>();
        BytesRefFSTEnum<Long> iterator = new BytesRefFSTEnum<>(fst);
        while (iterator.next() != null){
            words.add(iterator.current().input.utf8ToString());
        }
        return words;
    }
}
