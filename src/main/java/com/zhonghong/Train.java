package com.zhonghong;

import com.mayabot.nlp.fasttext.FastText;
import com.mayabot.nlp.fasttext.args.InputArgs;
import com.mayabot.nlp.fasttext.loss.LossName;

import java.io.File;

/**
 * @author: zhonghong
 * @description: 训练模型
 * @date: 2020/9/25
 */
public class Train {

    private static final String BASE_PATH = "C:\\Users\\BennyTian\\Desktop\\textCategory\\";

    public static void main(String[] args) {
        // 读取训练数据
        File file = new File(BASE_PATH + "train.txt");

        // 构建训练参数
        InputArgs inputArgs = new InputArgs();
        // 分层softmax
        inputArgs.setLoss(LossName.hs);
        inputArgs.setLr(0.3);
        inputArgs.setDim(1000);
        inputArgs.setEpoch(20);
        // 训练模型
        FastText model = FastText.trainSupervised(file, inputArgs);
        // 评估模型
        model.test(new File(BASE_PATH + "assess.txt"), 1, 0, true);
        // 保存模型
        model.quantize(2, false, false);
        model.saveModelToSingleFile(new File(BASE_PATH + "model\\textCategory.model"));
    }
}
