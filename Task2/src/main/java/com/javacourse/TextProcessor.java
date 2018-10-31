package com.javacourse;

import com.javacourse.text.Symbol;
import com.javacourse.text.Word;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import java.io.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TextProcessor {

    private List<Word> words;
    private static Logger logger;
    private static final String FILE_PATH = "data/book.txt";
    private static final String LOG_PATH = "log/log4j.xml";

    static {
        logger = Logger.getLogger(TextProcessor.class);
    }

    public TextProcessor(){
        /*words = new LinkedList<>();*/
        DOMConfigurator.configure(LOG_PATH);
    }

    public void processRequest(){
        //lazy initialization
        if(words==null){
            words = new LinkedList<>();
            initWordList();
        }
    }

    boolean initWordList(){
        String text = null;
        try {
            text = readTextFromFile(FILE_PATH);
        } catch (FileNotFoundException e) {
            return false;
        }
        String[] splittedText = text.split("[\\s.,() ]");
        List<Symbol> symbolList;
        for(String s : splittedText){
            if(s.matches(""))
                continue;
            symbolList = new ArrayList<>();
            for(char c: s.toCharArray()){
                symbolList.add(new Symbol(c));
            }
            words.add(new Word(symbolList));
        }
        return true;
    }

    String readTextFromFile(String filepath) throws FileNotFoundException {
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try{
            reader = new BufferedReader(new FileReader(filepath));
            while((line=reader.readLine())!=null){
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        }catch (IOException e){
            logger.error(e.getMessage());
        }finally {
            try {
                if(reader!=null){
                    reader.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        throw new FileNotFoundException("File not found");
    }
}
