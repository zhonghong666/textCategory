package com.zhonghong;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author: zhonghong
 * @description: 清洗数据
 * @date: 2020/9/25
 */
public class WashData {
    private static final String REGX = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}｛｝―《》·〔〕『』〖〗「」【】‘；：”“’。，、？\"\\-　]";
    private static final Random RANDOM = new Random();
    private static final Map<String, BufferedWriter> CATEGORY = new HashMap<>();
    private static final String WASH = "C:\\Users\\BennyTian\\Desktop\\wash\\%s.txt";
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\BennyTian\\Desktop\\wash";
        String trainPath = "C:\\Users\\BennyTian\\Desktop\\data\\train.txt";
        String valPath = "C:\\Users\\BennyTian\\Desktop\\data\\val.txt";
        String textPath = "C:\\Users\\BennyTian\\Desktop\\data\\text.txt";
        BufferedWriter train = new BufferedWriter(new FileWriter(trainPath));
        BufferedWriter val = new BufferedWriter(new FileWriter(valPath));
        BufferedWriter text = new BufferedWriter(new FileWriter(textPath));
        int num = 0;
        int valNum = 0;
        int textNum = 0;
        BufferedWriter temp;
        for (File item : new File(path).listFiles()) {
            if (item.isFile()) {
                BufferedReader br = new BufferedReader(new FileReader(item));
                String line;
                while((line = br.readLine()) != null) {
                    if (num < 500 * 0.6) {
                        train.write(line);
                        train.newLine();
                        num++;
                    }
                    if (valNum < 500 * 0.2) {
                        val.write(line);
                        val.newLine();
                        valNum++;
                    }
                    if (textNum < 500 * 0.2) {
                        text.write(line);
                        text.newLine();
                        textNum++;
                    }
                    if ((num + valNum + textNum) >= 500) {
                        System.out.println(4535435);
                        break;
                    }
                }
                br.close();
            }
            num = 0;
            valNum = 0;
            textNum = 0;
        }
        CATEGORY.values().forEach(item -> {
            try {
                item.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        train.close();
        val.close();
        text.close();
    }
}
