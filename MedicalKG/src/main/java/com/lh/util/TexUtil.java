package com.lh.util;

import java.io.*;
import java.nio.charset.Charset;

public class TexUtil {

    /**
     * Writer 字符
     * @param text
     * @throws IOException
     */
    public static void write(String text,String path) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(new File(path),true),Charset.forName("UTF-8")));
        bufferedWriter.write(text);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static BufferedReader read(String path) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
        return bufferedReader;
    }

}
