package com.walixiwa.hunter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.walixiwa.webhunter.WebHunter;
import com.walixiwa.webhunter.WebParser;
import com.walixiwa.webhunter.WebTool;
import com.walixiwa.webhunter.model.BaseParseUrlModel;
import com.walixiwa.webhunter.model.BaseWebHunterModel;
import com.walixiwa.webhunter.model.BaseWebResultModel;
import com.walixiwa.webhunter.model.ParseResultModel;
import com.walixiwa.webhunter.model.TabModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    private TextView mTextViewJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewJson = findViewById(R.id.json);
        mTextViewJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                if (cm != null) {
                    cm.setText(mTextViewJson.getText());
                }
                Toast.makeText(MainActivity.this, "复制成功!", Toast.LENGTH_LONG).show();
            }
        });
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextViewJson.setText(getModel7().toJson());
                Log.e(TAG, "onClick: " + getModel7().toJson());

                BaseWebHunterModel hunterModel = new BaseWebHunterModel(getModel7().toJson());
                new WebHunter().init(hunterModel).setCallBack(new WebHunter.CallBack() {
                    @Override
                    public void callBack(BaseWebHunterModel h, List<BaseWebResultModel> resultModels) {
                        Log.e(TAG, "size: " + resultModels.size());
                        for (int i = 0; i < resultModels.size(); i++) {
                            Log.e(TAG, "callBack: " + resultModels.get(i).toString());
                        }
                    }
                }).with(2, 2).start();
            }
        });
    }

     /*new WebParser().init(resultModels.get(i).getWebHunterModel().getBaseParseUrlModel()).withParamUrl(testUrl).setCallBack(new WebParser.CallBack() {
        @Override
        public void callBack(List<ParseResultModel> resultModels) {
            for (int i = 0; i < resultModels.size(); i++) {
                Log.e(TAG, "callBack: " + resultModels.get(i).getUrl());
            }
        }
    }).start();*/

    private BaseWebHunterModel getModel() {
        BaseWebHunterModel model = new BaseWebHunterModel();
        model.setModelName("AV淘宝");
        model.setModelUrl("http://www.avtb0055.com");
        model.setModelVersion("19.10.15");
        model.setRequestCharset("utf-8");
        model.setResultCharset("utf-8");
        model.setRuleResult("<li id=\"video-.*?\">[\\s\\S]*?</div>\n\t\t\t\t\t</li>");
        model.setRuleResultCover("<img src=\"(.*?)\"");
        model.setRuleResultLinkHeader("parent");
        model.setRuleResultLink("<a href=\"(.*?)\"");
        model.setRuleResultTitle("title=\"(.*?)\"");
        model.setRuleResultDate("<span class=\"pull-left\">(.*?)</span>");
        model.setHasDetailPage(true);

        BaseParseUrlModel baseParseUrlModel = new BaseParseUrlModel();
        baseParseUrlModel.setRuleFull("<source src=\".*?\" type=\"video/mp4\" label=\".*?\" res=\".*?\" />");
        baseParseUrlModel.setRuleUrl("<source src=\"(.*?)\"");
        baseParseUrlModel.setRuleTitle("label=\"(.*?)\"");
        model.setBaseParseUrlModel(baseParseUrlModel);

        model.setTabModels(Arrays.asList(
                new TabModel("亚洲", "/asian/%first/%page/", "recent/"),
                new TabModel("重口", "/awesome/%first/%page/", "recent/"),
                new TabModel("少女", "/babe/%first/%page/", "recent/"),
                new TabModel("潮吹", "/chaochui/%first/%page/", "recent/"),
                new TabModel("大屌", "/dadiao/%first/%page/", "recent/"),
                new TabModel("长视频", "/duration/%first/%page/", "recent/"),
                new TabModel("口爆颜射", "/facial/%first/%page/", "recent/"),
                new TabModel("肛交", "/gangjiao/%first/%page/", "recent/"),
                new TabModel("国产", "/guochan/%first/%page/", "recent/"),
                new TabModel("HD高清", "/hd/%first/%page/", "recent/"),
                new TabModel("户外", "/huwai/%first/%page/", "recent/"),
                new TabModel("巨乳", "/juru/%first/%page/", "recent/"),
                new TabModel("口交  ", "/koujiao/%first/%page/", "recent/"),
                new TabModel("乱伦", "/luanlun/%first/%page/", "recent/"),
                new TabModel("美女", "/meinv/%first/%page/", "recent/"),
                new TabModel("女同", "/nvtong/%first/%page/", "recent/"),
                new TabModel("欧美", "/oumei/%first/%page/", "recent/"),
                new TabModel("性派对", "/party/%first/%page/", "recent/"),
                new TabModel("器具及自慰", "/qiju/%first/%page/", "recent/"),
                new TabModel("群交", "/qunjiao/%first/%page/", "recent/"),
                new TabModel("强奸", "/rapping/%first/%page/", "recent/"),
                new TabModel("人妻熟女", "/renqi/%first/%page/", "recent/"),
                new TabModel("日本无码", "/ribenwuma/%first/%page/", "recent/"),
                new TabModel("日本有码", "/ribenyouma/%first/%page/", "recent/"),
                new TabModel("学生妹 校园", "/schoolgirl/%first/%page/", "recent/"),
                new TabModel("性爱", "/sex/%first/%page/", "recent/"),
                new TabModel("丝袜", "/siwa/%first/%page/", "recent/"),
                new TabModel("SM调教", "/sm/%first/%page/", "recent/"),
                new TabModel("中文字幕", "/subtitle/%first/%page/", "recent/"),
                new TabModel("素人", "/suren/%first/%page/", "recent/"),
                new TabModel("3P", "/threesome/%first/%page/", "recent/"),
                new TabModel("动漫无码", "/wuma/%first/%page/", "recent/"),
                new TabModel("动漫有码", "/youma/%first/%page/", "recent/"),
                new TabModel("制服诱惑", "/zhifu/%first/%page/", "recent/"),
                new TabModel("主播", "/zhubo/%first/%page/", "recent/"),
                new TabModel("足交", "/zujiao/%first/%page/", "recent/"))
        );
        return model;
    }


    private BaseWebHunterModel getModel2() {
        BaseWebHunterModel model = new BaseWebHunterModel();
        model.setModelName("天噜啦");
        model.setModelUrl("http://www.tlula209.com");
        model.setModelVersion("19.10.18");
        model.setRequestCharset("utf-8");
        model.setResultCharset("utf-8");
        model.setRuleResult("<li>[\\s\\S]*?</li>");
        model.setRuleResultCover("<img src=\"(.*?)\" />");
        model.setRuleResultLinkHeader("parent");
        model.setRuleResultLink("href=\"(.*?)\"");
        model.setRuleResultTitle("<h3>(.*?)</h3>");
        model.setRuleResultDate("(\\d{4}-\\d{2}-\\d{2})");
        model.setHasDetailPage(true);

        BaseParseUrlModel baseParseUrlModel = new BaseParseUrlModel();
        baseParseUrlModel.setRuleFull("var url = '.*?'");
        baseParseUrlModel.setRuleUrl("var url = '(.*?)'");
        model.setBaseParseUrlModel(baseParseUrlModel);

        model.setTabModels(Arrays.asList(
                new TabModel("日韩女优", "/vodlist/5%first/%page.html", "-"),
                new TabModel("欧美激情", "/vodlist/6%first/%page.html", "-"),
                new TabModel("偷拍自拍", "/vodlist/4%first/%page.html", "-"),
                new TabModel("成人动漫", "/vodlist/8%first/%page.html", "-"),
                new TabModel("经典三级", "/vodlist/7%first/%page.html", "-"),
                new TabModel("强奸乱伦", "/vodlist/9%first/%page.html", "-"),
                new TabModel("变态另类", "/vodlist/10%first/%page.html", "-"),
                new TabModel("制服丝袜", "/vodlist/11%first/%page.html", "-"),
                new TabModel("激情3P", "/vodlist/12%first/%page.html", "-"),
                new TabModel("中文字幕", "/vodlist/31%first/%page.html", "-")
                )
        );
        return model;
    }

    private BaseWebHunterModel getModel3() {
        BaseWebHunterModel model = new BaseWebHunterModel();
        model.setModelName("环亚无码");
        model.setModelUrl("http://wmcj8.com");
        model.setModelVersion("2019.10.25");
        model.setRequestCharset("utf-8");
        model.setResultCharset("utf-8");
        model.setRuleResult("\\{\"vod_id\":[\\s\\S]*?content\":\".*?\"\\}");
        model.setRuleResultCover("vod_pic\":\"(.*?)\"");
        model.setRuleResultLink("vpath\":\".*?\\$(.*?)\\$ckplayer\"");
        model.setRuleResultTitle("vod_title\":\"(.*?)\"");
        model.setRuleResultDate("vod_addtime\":\"(.*?)\"");
        model.setHasDetailPage(false);

        model.setTabModels(Arrays.asList(
                new TabModel("乱伦无码", "/inc/jsonsapi.php?ac=videolist&t=2&pg=%page"),
                new TabModel("强奸无码", "/inc/jsonsapi.php?ac=videolist&t=3&pg=%page"),
                new TabModel("人妻无码", "/inc/jsonsapi.php?ac=videolist&t=4&pg=%page"),
                new TabModel("制服无码", "/inc/jsonsapi.php?ac=videolist&t=5&pg=%page"),
                new TabModel("巨乳无码", "/inc/jsonsapi.php?ac=videolist&t=12&pg=%page")
        ));
        return model;
    }

    private BaseWebHunterModel getModel4() {
        BaseWebHunterModel model = new BaseWebHunterModel();
        model.setModelName("蝌蚪窝");
        model.setModelUrl("http://www.caca033.com");
        model.setModelVersion("19.10.18");
        model.setRequestCharset("utf-8");
        model.setResultCharset("utf-8");
        model.setRuleResult("<div class=\"item[\\s\\S]*?<div class=\"views\">");
        model.setRuleResultCover("data-original=\"(.*?)\"");
        model.setRuleResultLink("<a href=\"(.*?)\"");
        model.setRuleResultTitle("title=\"(.*?)\" >");
        model.setRuleResultDate("<div class=\"added\">(.*?)</div>");
        model.setHasDetailPage(true);

        BaseParseUrlModel baseParseUrlModel = new BaseParseUrlModel();
        baseParseUrlModel.setRuleFull("video_url: '.*?'");
        baseParseUrlModel.setRuleUrl("video_url: '(.*?)'");
        model.setBaseParseUrlModel(baseParseUrlModel);

        model.setTabModels(Arrays.asList(
                new TabModel("欧美", "/categories/265bf8ab0e0507b1f769720fbd07987e/?mode=async&function=get_block&block_id=list_videos_common_videos_list&sort_by=post_date&from=%page"),
                new TabModel("乱伦", "/categories/68d1e8dcdaf8ebd5c4fe96ee5b6a9a6c/?mode=async&function=get_block&block_id=list_videos_common_videos_list&sort_by=post_date&from=%page"),
                new TabModel("口交肛交", "/categories/6733a683955cdfbbf25939e6bafaf821/?mode=async&function=get_block&block_id=list_videos_common_videos_list&sort_by=post_date&from=%page"),
                new TabModel("中文字幕", "/categories/390e6a5d67423d91d7f7bd1642db5e7b/?mode=async&function=get_block&block_id=list_videos_common_videos_list&sort_by=post_date&from=%page"),
                new TabModel("高清", "/categories/49ccc25ccd43331cc4b3d841ed3d4022/?mode=async&function=get_block&block_id=list_videos_common_videos_list&sort_by=post_date&from=%page"),
                new TabModel("素人妻", "/categories/cdd610c35ff9e81ddd7af9f086fecc0e/?mode=async&function=get_block&block_id=list_videos_common_videos_list&sort_by=post_date&from=%page"),
                new TabModel("日韩", "/categories/2ecc21991dfdbe500adc4747d58ca8bd/?mode=async&function=get_block&block_id=list_videos_common_videos_list&sort_by=post_date&from=%page"),
                new TabModel("无码", "/categories/73d4bfed3fcf5ea19aea2be948163b46/?mode=async&function=get_block&block_id=list_videos_common_videos_list&sort_by=post_date&from=%page")
        ));
        return model;
    }

    private BaseWebHunterModel getModel5() {
        BaseWebHunterModel model = new BaseWebHunterModel();
        model.setModelName("8X在线");
        model.setModelUrl("https://8xxa9.com");
        model.setModelVersion("19.10.18");
        model.setRequestCharset("utf-8");
        model.setResultCharset("utf-8");
        model.setRuleResult("<div class=\"t_p\">[\\s\\S]*?<div class=\"clear\">");
        model.setRuleResultCover("data-original=\"(.*?)\"");
        model.setRuleResultLinkHeader("parent");
        model.setRuleResultLink("<a href=\"(.*?)\"");
        model.setRuleResultTitle("noreferrer\">(.*?)</a>");
        model.setRuleResultDate("<span>(\\d{4}年\\d{2}月\\d{1,2}日)</span>");
        model.setHasDetailPage(true);

        BaseParseUrlModel baseParseUrlModel = new BaseParseUrlModel();
        baseParseUrlModel.setRuleFull("<div class=\"x_z\"><a href=\".*?\"");
        baseParseUrlModel.setRuleUrl("<div class=\"x_z\"><a href=\"(.*?)\"");
        model.setBaseParseUrlModel(baseParseUrlModel);

        model.setTabModels(Arrays.asList(
                new TabModel("大陆", "/html/category/video/video1/page_%page.html"),
                new TabModel("日韩", "/html/category/video/video2/page_%page.html"),
                new TabModel("欧美", "/html/category/video/video3/page_%page.html"),
                new TabModel("动画", "/html/category/video/video4/page_%page.html"),
                new TabModel("三级", "/html/category/video/video5/page_%page.html"),
                new TabModel("8X红人", "/html/8xhongren_20419_%page.html")
        ));
        return model;
    }


    private BaseWebHunterModel getModel6() {
        BaseWebHunterModel model = new BaseWebHunterModel();
        model.setModelName("四虎在线");
        model.setModelUrl("https://www.8754hu.com");
        model.setModelVersion("2019.10.25");
        model.setRequestCharset("utf-8");
        model.setResultCharset("utf-8");
        model.setRuleResult("<li class=\"col-md-2 col-sm-3 col-xs-4\">[\\s\\S]*?</li>");
        model.setRuleResultCover("data-original=\"(.*?)\"");
        model.setRuleResultLinkHeader("parent");
        model.setRuleResultLink("href=\"(.*?)\"");
        model.setRuleResultTitle("title=\"(.*?)\"");
        model.setRuleResultDate("(\\d{4}-\\d{2}-\\d{2})");
        model.setHasDetailPage(true);

        BaseParseUrlModel baseParseUrlModel = new BaseParseUrlModel();
        baseParseUrlModel.setBaseUrl("https://www.8754hu.com");
        baseParseUrlModel.setSubUrl("/vod/html%param2/%param1_down_0.html");
        baseParseUrlModel.setRuleParam1("/vod/html.*?/(.*?).html");
        baseParseUrlModel.setRuleParam2("/vod/html(.*?)/");
        baseParseUrlModel.setRuleFull("var httpurl = \".*?\"");
        baseParseUrlModel.setRuleUrl("var httpurl = \"(.*?)\"");
        model.setBaseParseUrlModel(baseParseUrlModel);

        model.setTabModels(Arrays.asList(
                new TabModel("欧美性爱", "/vod/html27/index%first/%page.html", "_"),
                new TabModel("自拍偷拍", "/vod/html2/index%first/%page.html", "_"),
                new TabModel("夫妻同房", "/vod/html3/index%first/%page.html", "_"),
                new TabModel("开放90后", "/vod/html4/index%first/%page.html", "_"),
                new TabModel("换妻游戏", "/vod/html5/index%first/%page.html", "_"),
                new TabModel("网红主播", "/vod/html6/index%first/%page.html", "_"),
                new TabModel("手机小视频", "/vod/html7/index%first/%page.html", "_"),
                new TabModel("经典三级", "/vod/html8/index%first/%page.html", "_"),

                new TabModel("无码中字", "/vod/html10/index%first/%page.html", "_"),

                new TabModel("熟女人妻", "/vod/html11/index%first/%page.html", "_"),
                new TabModel("美颜巨乳", "/vod/html12/index%first/%page.html", "_"),
                new TabModel("颜射吃精", "/vod/html13/index%first/%page.html", "_"),
                new TabModel("丝袜制服", "/vod/html14/index%first/%page.html", "_"),
                new TabModel("高清无码", "/vod/html15/index%first/%page.html", "_"),

                new TabModel("中文有码", "/vod/html16/index%first/%page.html", "_"),

                new TabModel("宇都宮紫苑", "/vod/html18/index%first/%page.html", "_"),
                new TabModel("天海翼", "/vod/html19/index%first/%page.html", "_"),
                new TabModel("水菜麗", "/vod/html20/index%first/%page.html", "_"),
                new TabModel("泷泽萝拉", "/vod/html21/index%first/%page.html", "_"),
                new TabModel("S级女优", "/vod/html22/index%first/%page.html", "_"),

                new TabModel("波多野结衣", "/vod/html23/index%first/%page.html", "_"),
                new TabModel("吉泽明步", "/vod/html24/index%first/%page.html", "_"),
                new TabModel("苍井空", "/vod/html25/index%first/%page.html", "_")
        ));
        return model;
    }

    private BaseWebHunterModel getModel7() {
        BaseWebHunterModel model = new BaseWebHunterModel();
        model.setModelName("国外直播");
        model.setModelUrl("https://chaturbate.global");
        model.setModelVersion("2019.11.07");
        model.setRequestCharset("utf-8");
        model.setResultCharset("utf-8");
        model.setRuleResult("<li class=\"room_list_room\">[\\s\\S]*?viewers</li>");
        model.setRuleResultCover("<img src=\"(.*?)\"");
        model.setRuleResultLinkHeader("parent");
        model.setRuleResultLink("<a href=\"(.*?)\">[\\s\\S]*?<img src");
        model.setRuleResultTitle("data-slug=\"(.*?)\"");
        model.setRuleResultDate("<li class=\"cams\">(.*?)</li>");
        model.setHasDetailPage(true);

        BaseParseUrlModel baseParseUrlModel = new BaseParseUrlModel();
        baseParseUrlModel.setRuleFull("hls_source.*?.m3u8");
        baseParseUrlModel.setRuleUrl("(http.*?m3u8)");
        model.setBaseParseUrlModel(baseParseUrlModel);

        model.setTabModels(Arrays.asList(
                new TabModel("FEMALE", "/female-cams/?page=%page"),
                new TabModel("MALE", "/male-cams/?page=%page"),
                new TabModel("COUPLE", "/couple-cams/?page=%page"),
                new TabModel("TRANS", "/trans-cams/?page=%page")
        ));
        return model;
    }

    private BaseWebHunterModel getModel8() {
        BaseWebHunterModel model = new BaseWebHunterModel();
        model.setModelName("飞机馆");
        model.setModelUrl("http://cj.fjgzycj.com");
        model.setModelVersion("2019.10.25");
        model.setRequestCharset("utf-8");
        model.setResultCharset("utf-8");
        model.setRuleResult("\\{\"vod_id\":[\\s\\S]*?type_name.*?\\}");
        model.setRuleResultCover("vod_pic\":\"(.*?)\"");
        model.setRuleResultLink("vod_play_url\":\".*?\\$(.*?)\"");
        model.setRuleResultTitle("vod_name\":\"(.*?)\"");
        model.setRuleResultDate("vod_time\":\"(.*?)\"");
        model.setHasDetailPage(false);
        model.setTabModels(Arrays.asList(
                new TabModel("韩国直播", "/api.php/provide/vod/at/json/?ac=videolist&t=3&pg=%page"),
                new TabModel("动漫卡通", "/api.php/provide/vod/at/json/?ac=videolist&t=4&pg=%page"),
                new TabModel("网曝热门", "/api.php/provide/vod/at/json/?ac=videolist&t=6&pg=%page"),
                new TabModel("制服诱惑", "/api.php/provide/vod/at/json/?ac=videolist&t=7&pg=%page"),
                new TabModel("网红直播", "/api.php/provide/vod/at/json/?ac=videolist&t=8&pg=%page"),
                new TabModel("空姐模特", "/api.php/provide/vod/at/json/?ac=videolist&t=9&pg=%page"),
                new TabModel("自拍偷拍", "/api.php/provide/vod/at/json/?ac=videolist&t=10&pg=%page"),
                new TabModel("学生萝莉", "/api.php/provide/vod/at/json/?ac=videolist&t=11&pg=%page"),
                new TabModel("美熟少妇", "/api.php/provide/vod/at/json/?ac=videolist&t=12&pg=%page"),
                new TabModel("中文字幕", "/api.php/provide/vod/at/json/?ac=videolist&t=13&pg=%page"),
                new TabModel("日本有码", "/api.php/provide/vod/at/json/?ac=videolist&t=14&pg=%page"),
                new TabModel("日本无码", "/api.php/provide/vod/at/json/?ac=videolist&t=15&pg=%page"),
                new TabModel("欧美情色", "/api.php/provide/vod/at/json/?ac=videolist&t=16&pg=%page"),
                new TabModel("香港三级", "/api.php/provide/vod/at/json/?ac=videolist&t=33&pg=%page"),
                new TabModel("国产精品", "/api.php/provide/vod/at/json/?ac=videolist&t=34&pg=%page"),
                new TabModel("抖阴视频", "/api.php/provide/vod/at/json/?ac=videolist&t=35&pg=%page"),
                new TabModel("国产乱伦", "/api.php/provide/vod/at/json/?ac=videolist&t=36&pg=%page"),
                new TabModel("野合车震", "/api.php/provide/vod/at/json/?ac=videolist&t=37&pg=%page"),
                new TabModel("职场同事", "/api.php/provide/vod/at/json/?ac=videolist&t=38&pg=%page"),
                new TabModel("自慰群交", "/api.php/provide/vod/at/json/?ac=videolist&t=39&pg=%page")
        ));
        return model;
    }

    private BaseWebHunterModel getModel9() {
        BaseWebHunterModel model = new BaseWebHunterModel();
        model.setModelName("IX在线");
        model.setModelUrl("http://api.iixxzyapi.com");
        model.setModelVersion("2019.10.25");
        model.setRequestCharset("utf-8");
        model.setResultCharset("utf-8");
        model.setRuleResult("<video>[\\s\\S]*?</video>");
        model.setRuleResultCover("<pic>(.*?)</pic>");
        model.setRuleResultLink("vod_play_url\":\".*?\\$(.*?)\"");
        model.setRuleResultTitle("vod_name\":\"(.*?)\"");
        model.setRuleResultDate("vod_time\":\"(.*?)\"");
        model.setHasDetailPage(false);
        model.setTabModels(Arrays.asList(
                new TabModel("韩国直播", "/api.php/provide/vod/at/json/?ac=videolist&t=3&pg=%page"),
                new TabModel("动漫卡通", "/api.php/provide/vod/at/json/?ac=videolist&t=4&pg=%page"),
                new TabModel("网曝热门", "/api.php/provide/vod/at/json/?ac=videolist&t=6&pg=%page"),
                new TabModel("制服诱惑", "/api.php/provide/vod/at/json/?ac=videolist&t=7&pg=%page"),
                new TabModel("网红直播", "/api.php/provide/vod/at/json/?ac=videolist&t=8&pg=%page"),
                new TabModel("空姐模特", "/api.php/provide/vod/at/json/?ac=videolist&t=9&pg=%page"),
                new TabModel("自拍偷拍", "/api.php/provide/vod/at/json/?ac=videolist&t=10&pg=%page"),
                new TabModel("学生萝莉", "/api.php/provide/vod/at/json/?ac=videolist&t=11&pg=%page"),
                new TabModel("美熟少妇", "/api.php/provide/vod/at/json/?ac=videolist&t=12&pg=%page"),
                new TabModel("中文字幕", "/api.php/provide/vod/at/json/?ac=videolist&t=13&pg=%page"),
                new TabModel("日本有码", "/api.php/provide/vod/at/json/?ac=videolist&t=14&pg=%page"),
                new TabModel("日本无码", "/api.php/provide/vod/at/json/?ac=videolist&t=15&pg=%page"),
                new TabModel("欧美情色", "/api.php/provide/vod/at/json/?ac=videolist&t=16&pg=%page"),
                new TabModel("香港三级", "/api.php/provide/vod/at/json/?ac=videolist&t=33&pg=%page"),
                new TabModel("国产精品", "/api.php/provide/vod/at/json/?ac=videolist&t=34&pg=%page"),
                new TabModel("抖阴视频", "/api.php/provide/vod/at/json/?ac=videolist&t=35&pg=%page"),
                new TabModel("国产乱伦", "/api.php/provide/vod/at/json/?ac=videolist&t=36&pg=%page"),
                new TabModel("野合车震", "/api.php/provide/vod/at/json/?ac=videolist&t=37&pg=%page"),
                new TabModel("职场同事", "/api.php/provide/vod/at/json/?ac=videolist&t=38&pg=%page"),
                new TabModel("自慰群交", "/api.php/provide/vod/at/json/?ac=videolist&t=39&pg=%page")
        ));
        return model;
    }
}
