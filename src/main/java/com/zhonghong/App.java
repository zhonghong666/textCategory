package com.zhonghong;


import com.mayabot.nlp.common.Lists;
import com.mayabot.nlp.fasttext.FastText;
import com.mayabot.nlp.fasttext.args.InputArgs;
import com.mayabot.nlp.fasttext.args.ModelName;
import com.mayabot.nlp.fasttext.loss.LossName;
import com.mayabot.nlp.fasttext.train.FileSampleLineIterable;
import com.mayabot.nlp.segment.Lexer;
import com.mayabot.nlp.segment.Lexers;
import org.ansj.splitWord.analysis.NlpAnalysis;

import java.io.File;
import java.util.List;

/**
 * Hello world!
 *
 * @author zhonghong
 */
public class App {
    private static final String regx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\"\n  ]";
    public static void main( String[] args ) {
        String text = "⏩观看指南-水门/止水/鼬\n" +
                "⏩当他们得知你要出远门\n" +
                "⏩他们三个是你同母异父的哥哥 目前和你同居 无厘头的甜宠文 不喜慎入 \n" +
                "⏩ooc预警 撞梗致歉\n" +
                "\n" +
                "\n" +
                "“小懒猫～起床啦！”止水爬在你床边捏你的脸。“闹钟都不定，不怕睡过头？”\n" +
                "“有你在怕什么。”你揉了揉眼睛，等着止水给你一个晚安吻，然后下床洗漱。\n" +
                "看着客厅被水门收拾好的三个行李箱，你嘴角抽搐了一下。这样确定我能抬得动吗？？\n" +
                "正想着一会怎么能把这几个行李箱抬到车站去，你闻到了煎蛋的味道，于是迅速扫了一眼房间，发现鼬不在。\n" +
                "“止水！快去看看厨房！鼬在做饭！”\n" +
                "你慌张的指着厨房的方向冲止水喊着。\n" +
                "你看着止水瞬身闪进厨房，过了五分钟没闻到糊了的味道，总算放下心。等着早饭的期间你坐在客厅的沙发上查看那几个行李箱。\n" +
                "水门坐在了你旁边。\n" +
                "“放心，锅碗瓢盆、纸笔本、衣服、化妆品还有你的玩具我都帮你装好了！”水门指着行李箱冲你说到。\n" +
                "“………………”\n" +
                "“我还给你拿了零食，顺便放了个热水袋。里面还有雨伞、遮阳帽、手持风扇，还有……啧，什么来着？”\n" +
                "水门仰头看着天花板仔细回想，自己还把什么连他叫不上名的东西都一起放在了里面。\n" +
                "“额……哥哥，谢谢。我只是出去参加个同学聚会，顺便玩两天，不用带这么多东西的……而且，大夏天的我带什么热水袋啊……”你扶额。\n" +
                "“还不是怕你照顾不好自己，要不是这行李箱不够大，我都想把自己装进去了！”\n" +
                "“……哥，谢谢你哈。”\n" +
                "\n" +
                "五分钟以后准时开饭。\n" +
                "你坐在桌子上，边吃边听三个哥哥在旁边嘱咐你。\n" +
                "“不许到外面吃冷饮！小吃也不行！都不干净！你要是想吃我给你做……”\n" +
                "“行李要是拿不动一会让止水送你过去吧”\n" +
                "“你可别被骗子骗了啊，现在传销的很多的……”\n" +
                "“我的傻妹妹你有没有在听啊？慢点吃别噎到了”看着你把煎蛋一口吃掉，鼬拿过一旁的牛奶放在你手边。\n" +
                "“在听在听。”你看了下时间，头疼怎么能把那三个大行李箱搬到车站去。出了厨房你才发现，止水已经在那里等着了。\n" +
                "“我帮你搬，你搬不动。”\n" +
                "“哥，你是速度快不是力气大……”你担忧的看着止水拎着三个行李箱，“我拿一个最轻的就行。”\n" +
                "“你认不认识路？你要去的地方我熟，要不我陪你去吧？”鼬说着就要换衣服。\n" +
                "“不用！哥！”正在挑行李箱的你立马跳到鼬那边拦住他，“我认识，认识。”\n" +
                "“我这不是担心你吗，长这么大都没离开过哥哥的视线，万一不小心丢了怎么办…”鼬一边说还一边假装抹泪。\n" +
                "“哥啊，我都二十多了……”\n" +
                "“二十多也是小孩！”听到你说年龄，水门立刻凑了过来。\n" +
                "“再说你都没离开过哥哥这么长时间！”止水也跟着鼬一起假装抹泪，“好好吃饭，可别瘦了。”\n" +
                "“不知道旅店能不能住习惯？你可别喝酒！万一有男同学对你图谋不轨可就不好了…”\n" +
                "“嗐，要等妹妹这么久……”\n" +
                "他们三个在你旁边你一句我一句的，你完全插不上嘴。终于忍不住，你抓了抓头发，怒吼道：“停！！！————”\n" +
                "他们确实被你吓到了。都瞪着眼睛一句话不敢说。\n" +
                "你把行李箱往旁边一推，拿起包就走。\n" +
                "“哥！我就走一天而已，明天就回来了！”\n" +
                "说完趁他们还没反应过来，你赶紧推开门就跑。屋子里留下他们愣愣的看着你没关上的门，半天才回过神来。\n" +
                "水门委屈巴巴的把行李箱推了回去，鼬还在想着你会不会走丢，止水要出门去追你，被那两位拦住了。\n" +
                "嗐，又是为妹妹操碎心的一天。\n" +
                "\n" +
                "End\n" +
                "感觉不像甜宠像骨科（？？）http://www.baidu.com";
//        for(int i = 0; i < 10; i++) {
//            text += text;
//        }
//        System.out.println(text.length());
        // 感知机分词
        Lexer lexer = Lexers.perceptronBuilder()
                .withPos().withPersonName().withNer().build();
        String filter = lexer.scan(text).toPlainString();
//        System.out.println(lexer.scan(text).toPlainString());
//        System.out.println(filter.replaceAll(regx, ""));
//        FastText fastText = FastText.loadModelFromSingleFile(new File("data/fasttext/wiki.zh.bin"));
//        List<ScoreLabelPair> predict = fastText.predict(Arrays.asList("fastText在预测标签时使用了非线性激活函数".split(" ")), 5, 0);
        // CORE分词器
//        lexer = Lexers.coreBuilder().withPos().withPersonName().build();
//        System.out.println(lexer.scan(text).toPlainString());

        System.out.println();
        StringBuffer sb = new StringBuffer();
        NlpAnalysis.parse(text.replaceAll("\\#{1,2}[0-9]+|\\[img:(.*?)\\]|http(.*?)|[【】()＼／/﹏っ~*￣°⏩～\n]|  |[^\\u0020-\\u007E\\u00A0-\\u00BE\\u2E80-\\uA4CF\\uF900-\\uFAFF\\uFE30-\\uFE4F\\uFF00-\\uFFEF\\u0080-\\u009F\\u2000-\\u201f\\r\\n]", "")).getTerms().forEach(item -> {
            sb.append(item.getName());
            sb.append(" ");
        });
//        System.out.println(sb.toString());

        // 训练模型
        File trainFile = new File("");
        InputArgs inputArgs = new InputArgs();
        inputArgs.setLoss(LossName.softmax);
        inputArgs.setLr(0.1);
        inputArgs.setDim(1000);
        inputArgs.setEpoch(20);
        List<FileSampleLineIterable> sampleLineList = Lists.newArrayList();
        FileSampleLineIterable fileSampleLineIterable = new FileSampleLineIterable(new File("C:\\Users\\BennyTian\\Desktop\\sample_158.txt"));
        System.out.println(fileSampleLineIterable.lines());

        sampleLineList.add(fileSampleLineIterable);
        FastText model = FastText.train(sampleLineList, ModelName.sup, inputArgs);
//        FastText model = FastText.trainSupervised(trainFile, inputArgs);
//        // 模型评估
        model.test(new File("C:\\Users\\BennyTian\\Desktop\\sample_30.txt"), 1, 0, true);
//        // 模型压缩
//        model.quantize(2, false, false);
//        // 模型保存
//        model.saveModelToSingleFile(new File("C:\\textCategory.model"));
    }
}
