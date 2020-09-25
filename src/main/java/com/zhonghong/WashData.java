package com.zhonghong;

import java.io.*;

/**
 * @author: zhonghong
 * @description: 清洗数据
 * @date: 2020/9/25
 */
public class WashData {
    private static final String REGX = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}｛｝―《》·〔〕『』〖〗「」【】‘；：”“’。，、？\"\\-　]";
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\BennyTian\\Desktop\\sample\\";
        String outPath = "C:\\Users\\BennyTian\\Desktop\\wash\\data.txt";
        BufferedWriter bw=new BufferedWriter(new FileWriter(outPath));
        for (File item : new File(path).listFiles()) {
            if (item.isFile()) {
                BufferedReader br = new BufferedReader(new FileReader(item));
                String line;
                while((line = br.readLine())!=null) {
                    line = line.replaceAll(REGX, "").replaceAll("  ", " ")
                            .replaceAll("   ", " ").replaceAll("   ", " ");
                    bw.write(line);
                    bw.newLine();
                }
                br.close();
            }
        }
        bw.close();
    }
}
