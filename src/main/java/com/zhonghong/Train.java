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

    public static void main(String[] args) {
        String path = "C:\\Users\\BennyTian\\Desktop\\sample\\";
        String assessPath = "C:\\Users\\BennyTian\\Desktop\\assess\\";
        // 读取样本数据
        File file = new File("C:\\Users\\BennyTian\\Desktop\\wash\\data.txt");
        /*File[] tempList = file.listFiles();
        List<FileSampleLineIterable> sampleLineList = Lists.newArrayList();
        for (File item : tempList) {
            if (item.isFile()) {
                sampleLineList.add(new FileSampleLineIterable(new File(path + item.getName())));
            }
        }*/
        // 构建训练参数
        InputArgs inputArgs = new InputArgs();
        inputArgs.setLoss(LossName.softmax);
        inputArgs.setLr(0.3);
        inputArgs.setDim(1000);
        inputArgs.setEpoch(20);
        // 训练模型
        FastText model = FastText.trainSupervised(file, inputArgs);
        // 评估模型
        model.test(new File(assessPath + "assess.txt"), 1, 0, true);
        // 保存模型
        model.quantize(2, false, false);
        model.saveModelToSingleFile(new File("C:\\Users\\BennyTian\\Desktop\\model\\textCategory.model"));
    }
}
