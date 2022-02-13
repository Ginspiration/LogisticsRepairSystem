package com.ginspiration.serverbackground.util;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileUtil {
    public static String readJsonFile(String resourcePath){
        try {
            //spring,springBoot环境下可以使用此方式，也可以直接new File(path)
            File keyWordFile = ResourceUtils.getFile(resourcePath);
            BufferedReader reader = new BufferedReader(new FileReader(keyWordFile));
            //使用StringBuilder更快，但不安全，因为此处只有读，所以不影响
            StringBuilder buffer = new StringBuilder();
            String keyWord = null;
            while ((keyWord = reader.readLine()) != null) {
                buffer.append(keyWord);
            }
            //去除字符串中的空格
            return buffer.toString().replaceAll("\\s*", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
