//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.*;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void textNormalize() throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            String line, normalizedLine = "";
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    //Normalize Line
                    normalizedLine = lineNormalize(line);
                    System.out.println(normalizedLine);
                    //write
                    writer.write(normalizedLine);
                }
            }
            System.out.println("Done");
            reader.close();
            writer.close();
        } catch (IOException e) {
            System.err.println("Loi doc file");
        }
    }

    public static String lineNormalize(String line) {
        //add space after ,.;
        line = line.replaceAll(" *([,;])","$1 ");
        line = line.replaceAll("\"", " \" ");
        //remove unused space
        line = line.replaceAll("  +", " ");
        line = line.trim();
        //Quotation mark
        String[] tokken = line.split("\"");
        line = "";
        for (int i = 0; i < tokken.length; i++) {
            line += (i % 2 == 0) ? tokken[i] : "\"" + tokken[i].trim() + "\"";
        }
        String[] sentences = line.split("\\.");
        line = "";
        for (int i = 0; i < sentences.length; i++) {
            line +=  capitalize(sentences[i])+". ";
        }
        line = line.trim();
        //tab
//        line = "\t" + line;
        return "\t"+line+"\n";
    }

    public static String capitalize(String sentence) {
        sentence = sentence.trim();
        return (sentence.length()<2) ? sentence.toUpperCase() : (sentence.substring(0, 1).toUpperCase() + sentence.substring(1).toLowerCase());
    }

    public static void main(String[] args) throws IOException {
        textNormalize();
    }
}