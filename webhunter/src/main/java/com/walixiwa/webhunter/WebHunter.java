package com.walixiwa.webhunter;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.walixiwa.webhunter.model.BaseWebHunterModel;
import com.walixiwa.webhunter.model.BaseWebResultModel;
import com.walixiwa.webhunter.model.TabModel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebHunter {
    private static final String TAG = "WebHunter";
    private BaseWebHunterModel hunterModel;
    private int page = 1;
    private int tabIndex = 0;

    private List<BaseWebResultModel> resultModels = new ArrayList<>();

    private Handler handler = new Handler(Looper.getMainLooper());

    public WebHunter init(BaseWebHunterModel model) {
        this.hunterModel = model;
        return this;
    }

    public WebHunter with(int page, int tabIndex) {
        this.page = page;
        this.tabIndex = tabIndex;
        return this;
    }

    public void start() {
        TabModel tabModel = hunterModel.getTabModels().get(tabIndex);
        String url;
        if (page == 1) {
            if (!TextUtils.isEmpty(tabModel.getFirst())) {
                url = hunterModel.getModelUrl() + tabModel.getTabUrl().replace("%first/", "").replace("%page/", "").replace("%page", "");
            } else {
                url = hunterModel.getModelUrl() + tabModel.getTabUrl().replace("%page", String.valueOf(page));
            }
        } else {
            if (!TextUtils.isEmpty(tabModel.getFirst())) {
                url = hunterModel.getModelUrl() + tabModel.getTabUrl().replace("%first/", tabModel.getFirst()).replace("%page", String.valueOf(page));
            } else {
                url = hunterModel.getModelUrl() + tabModel.getTabUrl().replace("%page", String.valueOf(page));
            }
        }

        Log.e(TAG, "start: " + url);
        new MutiRequest().setUrl(url).setCharset(hunterModel.getResultCharset()).setCallBack(new MutiRequest.OnRequestFinishListener() {
            @Override
            public void onRequestFinish(boolean status, String response) {
                if (status) {
                    parse(response);
                }
            }
        }).start();
    }

    private void parse(String html) {
        Pattern pattern = Pattern.compile(hunterModel.getRuleResult(), Pattern.CASE_INSENSITIVE);//匹配整条链接
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            String result = matcher.group(); //匹配整条链接结果
            BaseWebResultModel resultModel = new BaseWebResultModel();
            if (!TextUtils.isEmpty(hunterModel.getRuleResultTitle())) {
                resultModel.setResultTitle(matchString(result, hunterModel.getRuleResultTitle()));
            }
            if (!TextUtils.isEmpty(hunterModel.getRuleResultCover())) {
                String cover = matchString(result, hunterModel.getRuleResultCover());
                resultModel.setResultCover(TextUtils.isEmpty(hunterModel.getRuleResultCoverHeader()) ? cover : hunterModel.getRuleResultCoverHeader() + cover);
            }
            if (!TextUtils.isEmpty(hunterModel.getRuleResultLink())) {
                String header = hunterModel.getRuleResultLinkHeader();
                String url;
                if (TextUtils.isEmpty(header)) {
                    url = matchString(result, hunterModel.getRuleResultLink());
                } else {
                    if (header.equals("parent")) {
                        url = hunterModel.getModelUrl() + matchString(result, hunterModel.getRuleResultLink());
                    } else {
                        url = header + matchString(result, hunterModel.getRuleResultLink());
                    }
                }
                resultModel.setResultLink(url);
            }
            if (!TextUtils.isEmpty(hunterModel.getRuleResultDate())) {
                resultModel.setResultDate(matchString(result, hunterModel.getRuleResultDate()));
            }
            if (!TextUtils.isEmpty(hunterModel.getRuleResultExtra1())) {
                resultModel.setResultExtra1(matchString(result, hunterModel.getRuleResultExtra1()));
            }
            if (!TextUtils.isEmpty(hunterModel.getRuleResultExtra2())) {
                resultModel.setResultExtra2(matchString(result, hunterModel.getRuleResultExtra2()));
            }
            resultModel.setWebHunterModel(hunterModel);
            resultModels.add(resultModel);
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                callBack.callBack(hunterModel,resultModels);
            }
        });
    }


    /**
     * 根据单个正则规则，默认返回第一个匹配项
     *
     * @param value
     * @param regex
     * @return
     */
    private String matchString(String value, String regex) {
        String result = "";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            result = matcher.group(1);
        }
        return result == null ? "" : result.replaceAll("<.*?>", "").replaceAll("\\s", "").trim();
    }

    private CallBack callBack;

    public WebHunter setCallBack(CallBack callBack) {
        this.callBack = callBack;
        return this;
    }

    public interface CallBack {
        void callBack(BaseWebHunterModel hunterModel,List<BaseWebResultModel> resultModels);
    }


}
