package com.walixiwa.webhunter;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.walixiwa.webhunter.model.BaseParseUrlModel;
import com.walixiwa.webhunter.model.ParseResultModel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebParser {
    private static final String TAG = "WebParser";
    private String paramUrl;
    private BaseParseUrlModel parseUrlModel;
    private List<ParseResultModel> resultModels = new ArrayList<>();

    public WebParser init(BaseParseUrlModel parseUrlModel) {
        this.parseUrlModel = parseUrlModel;
        return this;
    }

    public WebParser withParamUrl(String paramUrl) {
        this.paramUrl = paramUrl;
        return this;
    }

    public void start() {
        String url = WebTool.uriEncode(getNextUrl());
        Log.e(TAG, "start: " + url);
        new MutiRequest().setUrl(url).setCallBack(new MutiRequest.OnRequestFinishListener() {
            @Override
            public void onRequestFinish(boolean status, String response) {
                Log.e(TAG, "onRequestFinish: " + status);
                parseUrl(response);
                Log.e(TAG, "onRequestFinish: "+status);
            }
        }).start();
    }

    //baseUrl = http://www.baidu.com
    //subUrl = /test.php?user=%param1&name=%param2&year=%param3

    private String getNextUrl() {
        String url;
        //没有所需参数，说明只是从源链接解析
        if (TextUtils.isEmpty(parseUrlModel.getBaseUrl())) {
            url = paramUrl;
        } else {
            //有所需参数,自行组合
            url = parseUrlModel.getBaseUrl() + parseUrlModel.getSubUrl();
            if (!TextUtils.isEmpty(parseUrlModel.getRuleParam1())) {
                String param1 = matchString(paramUrl, parseUrlModel.getRuleParam1());
                url = url.replace("%param1", param1);
            }
            if (!TextUtils.isEmpty(parseUrlModel.getRuleParam2())) {
                String param2 = matchString(paramUrl, parseUrlModel.getRuleParam2());
                url = url.replace("%param2", param2);
            }
            if (!TextUtils.isEmpty(parseUrlModel.getRuleParam3())) {
                String param3 = matchString(paramUrl, parseUrlModel.getRuleParam3());
                url = url.replace("%param3", param3);
            }
        }
        return url;
    }

    private void parseUrl(String html) {
        Pattern pattern = Pattern.compile(parseUrlModel.getRuleFull(), Pattern.CASE_INSENSITIVE);//匹配整条链接
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            String full = matcher.group(); //匹配整条链接结果
            ParseResultModel resultModel = new ParseResultModel();
            if (!TextUtils.isEmpty(parseUrlModel.getRuleTitle())) {
                resultModel.setTitle(matchString(full, parseUrlModel.getRuleTitle()));
            }
            if (!TextUtils.isEmpty(parseUrlModel.getRuleUrl())) {
                resultModel.setUrl(matchString(full, parseUrlModel.getRuleUrl()));
            }
            resultModels.add(resultModel);
        }
        if (callBack != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    callBack.callBack(resultModels);
                }
            });
        }
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

    public WebParser setCallBack(CallBack callBack) {
        this.callBack = callBack;
        return this;
    }

    public interface CallBack {
        void callBack(List<ParseResultModel> resultModels);
    }
}
