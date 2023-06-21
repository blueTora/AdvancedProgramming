package ceit.utils;

import ceit.model.Note;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.PatternSyntaxException;

public class FileUtils {

    private static final String NOTES_PATH = "./notes/";

    //It's a static initializer. It's executed when the class is loaded.
    //It's similar to constructor
    static {
        boolean isSuccessful = new File(NOTES_PATH).mkdirs();
        System.out.println("Creating " + NOTES_PATH + " directory is successful: " + isSuccessful);
    }

    public static File[] getFilesInDirectory() {
        return new File(NOTES_PATH).listFiles();
    }

//    //....BufferedReader....
//    public static String fileReader(File file) {
//        StringBuilder content = new StringBuilder();
//        //TODO: Phase1: read content from file
//
//        try {
//            FileReader r = new FileReader(file);
//            BufferedReader in = new BufferedReader(r);
//
//            String temp;
//            while ((temp = in.readLine()) != null){
//                content.append(temp + "\n");
//            }
//
//            in.close();
//            r.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return "" + content;
//    }
//
//    //....BufferedWriter....
//    public static void fileWriter(String content) {
//        //TODO: phase1: write content on file
//        String fileName = getProperFileName(content);
//
//        try{
//            FileWriter w = new FileWriter("notes/" + fileName.trim() + ".txt");
//            BufferedWriter out = new BufferedWriter(w);
//
//            out.write(content);
//            out.close();
//            w.close();
//
//        } catch (PatternSyntaxException | FileNotFoundException e){
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



    //TODO: Phase1: define method here for writing file with OutputStream
    //....FileOutputStream....
//    public static void fileWriter(String content) {
//
//        String fileName = getProperFileName(content);
//
//        try{
//            FileOutputStream out = new FileOutputStream("notes/" + fileName.trim() + ".txt");
//
//            byte[] text = new byte[content.length()];
//            for (int i = 0; i < content.length(); i++) {
//                text[i] = (byte) content.charAt(i);
//            }
//
//            out.write(text);
//            out.close();
//
//        } catch (PatternSyntaxException | FileNotFoundException e){
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    //TODO: Phase1: define method here for reading file with InputStream
    //....FileInputStream....
//    public static String fileReader(File file) {
//        StringBuilder content = new StringBuilder();
//
//        try {
//            FileInputStream in = new FileInputStream(file);
//
//            int size = in.available();
//
//            for (int i = 0; i < size; i++) {
//                content.append( (char)in.read() );
//            }
//
//            in.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return "" + content;
//    }

    //TODO: Phase2: proper methods for handling serialization
    public static String objectReader(File file) {
        String content = "";

        try (FileInputStream r = new FileInputStream(file)){
            ObjectInputStream in = new ObjectInputStream(r);

            Note note = (Note)in.readObject();
            content = note.toString();

            in.close();

        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "" + content;
    }

    public static void objectWriter(String content) {
        String fileName = getProperFileName(content);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd _ HH-mm-ss");
        Note note = new Note(fileName, content, formatter.format(date));

        try (FileOutputStream w = new FileOutputStream("notes/" + fileName + " _ " +  formatter.format(date) + ".bin");){
            ObjectOutputStream out = new ObjectOutputStream(w);

            out.writeObject(note);
            out.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getProperFileName(String content) {
        int loc = content.indexOf("\n");
        if (loc != -1) {
            return content.substring(0, loc);
        }
        if (!content.isEmpty()) {
            return content;
        }
        return System.currentTimeMillis() + "_new file.txt";
    }
}
