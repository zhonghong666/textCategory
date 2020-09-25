package com.zhonghong;

import com.mayabot.nlp.fasttext.FastText;
import com.mayabot.nlp.fasttext.Meter;
import com.mayabot.nlp.fasttext.args.InputArgs;
import com.mayabot.nlp.fasttext.loss.LossName;

import java.io.File;
import java.util.Arrays;

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

        String modelName = BASE_PATH + "model\\textCategory_%s_%s_%s_%s.model";

        // 构建训练参数
        InputArgs inputArgs = new InputArgs();
        // 分层softmax
        inputArgs.setLoss(LossName.hs);
        inputArgs.setLr(1.0);
        inputArgs.setDim(256);
        inputArgs.setEpoch(50);
//        inputArgs.setWordNgrams(2);
        double target = 0.95;
        double res = 0;
        while (res < target) {
            // 训练模型
            FastText model = FastText.trainSupervised(file, inputArgs);
            // 评估模型
            Meter meter = model.test(new File(BASE_PATH + "assess.txt"), 1, 0, true);
            res = meter.precision();
            // 保存模型
            model.quantize(8, false, false);
            model.saveModelToSingleFile(new File(String.format(modelName, inputArgs.getLr(), inputArgs.getDim(), inputArgs.getEpoch(), (int)(res * 100))));

            // 预测分类
            model.predict(Arrays.asList("王一博 的 私人 手机 震动 起来 戚年 微怔，瞧了 一眼 来电 显示 她 乖巧 的 窝在 王一博 怀里 不语".split(" ")), 5, 0).forEach(item -> {
                System.out.println("label ==> " + item.getLabel() + '\t' + "score ==> " + item.getScore());
            });
            inputArgs.setLr(inputArgs.getLr() - 0.5);
            if (inputArgs.getLr() <= 0) {
                return;
            }
        }
    }
}
