package com.walixiwa.webhunter.model;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class BaseParseUrlModel implements Serializable {
    private String baseUrl;
    private String subUrl;
    private String ruleFull;
    private String ruleTitle;
    private String urlHeader;
    private String ruleUrl;

    //从传入WebParser的链接中取出所需参数
    private String ruleParam1;
    private String ruleParam2;
    private String ruleParam3;

    public BaseParseUrlModel() {

    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public BaseParseUrlModel setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public String getSubUrl() {
        return subUrl;
    }

    public BaseParseUrlModel setSubUrl(String subUrl) {
        this.subUrl = subUrl;
        return this;
    }

    public String getRuleFull() {
        return ruleFull;
    }

    public BaseParseUrlModel setRuleFull(String ruleFull) {
        this.ruleFull = ruleFull;
        return this;
    }

    public String getRuleTitle() {
        return ruleTitle;
    }

    public BaseParseUrlModel setRuleTitle(String ruleTitle) {
        this.ruleTitle = ruleTitle;
        return this;
    }

    public String getUrlHeader() {
        return urlHeader;
    }

    public BaseParseUrlModel setUrlHeader(String urlHeader) {
        this.urlHeader = urlHeader;
        return this;
    }

    public String getRuleUrl() {
        return ruleUrl;
    }

    public BaseParseUrlModel setRuleUrl(String ruleUrl) {
        this.ruleUrl = ruleUrl;
        return this;
    }

    public String getRuleParam1() {
        return ruleParam1;
    }

    public BaseParseUrlModel setRuleParam1(String ruleParam1) {
        this.ruleParam1 = ruleParam1;
        return this;
    }

    public String getRuleParam2() {
        return ruleParam2;
    }

    public BaseParseUrlModel setRuleParam2(String ruleParam2) {
        this.ruleParam2 = ruleParam2;
        return this;
    }

    public String getRuleParam3() {
        return ruleParam3;
    }

    public BaseParseUrlModel setRuleParam3(String ruleParam3) {
        this.ruleParam3 = ruleParam3;
        return this;
    }

    public BaseParseUrlModel(String Json) {
        try {
            JSONObject jsonObject = new JSONObject(Json);
            if (jsonObject.has("baseUrl")) {
                this.baseUrl = jsonObject.getString("baseUrl");
            }
            if (jsonObject.has("subUrl")) {
                this.subUrl = jsonObject.getString("subUrl");
            }
            this.ruleFull = jsonObject.getString("ruleFull");
            if (jsonObject.has("ruleTitle")) {
                this.ruleTitle = jsonObject.getString("ruleTitle");
            }
            if (jsonObject.has("urlHeader")) {
                this.urlHeader = jsonObject.getString("urlHeader");
            }
            if (jsonObject.has("ruleParam1")) {
                this.ruleParam1 = jsonObject.getString("ruleParam1");
            }
            if (jsonObject.has("ruleParam2")) {
                this.ruleParam2 = jsonObject.getString("ruleParam2");
            }
            if (jsonObject.has("ruleParam3")) {
                this.ruleParam3 = jsonObject.getString("ruleParam3");
            }
            this.ruleUrl = jsonObject.getString("ruleUrl");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("baseUrl", baseUrl);
            if (!TextUtils.isEmpty(subUrl)) {
                jsonObject.put("subUrl", subUrl);
            }
            jsonObject.put("ruleFull", ruleFull);
            if (!TextUtils.isEmpty(ruleTitle)) {
                jsonObject.put("ruleTitle", ruleTitle);
            }
            if (!TextUtils.isEmpty(urlHeader)) {
                jsonObject.put("urlHeader", urlHeader);
            }
            if (!TextUtils.isEmpty(ruleParam1)) {
                jsonObject.put("ruleParam1", ruleParam1);
            }
            if (!TextUtils.isEmpty(ruleParam2)) {
                jsonObject.put("ruleParam2", ruleParam2);
            }
            if (!TextUtils.isEmpty(ruleParam3)) {
                jsonObject.put("ruleParam3", ruleParam3);
            }
            jsonObject.put("ruleUrl", ruleUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public BaseParseUrlModel initWithBase64String(String base64) {
        String Json = new String(Base64.decode(base64.getBytes(), Base64.DEFAULT));
        Log.e("init", "initWithBase64String: "+Json);
        try {
            JSONObject jsonObject = new JSONObject(Json);
            if (jsonObject.has("baseUrl")) {
                this.baseUrl = jsonObject.getString("baseUrl");
            }
            if (jsonObject.has("subUrl")) {
                this.subUrl = jsonObject.getString("subUrl");
            }
            this.ruleFull = jsonObject.getString("ruleFull");
            if (jsonObject.has("ruleTitle")) {
                this.ruleTitle = jsonObject.getString("ruleTitle");
            }
            if (jsonObject.has("urlHeader")) {
                this.urlHeader = jsonObject.getString("urlHeader");
            }
            if (jsonObject.has("ruleParam1")) {
                this.ruleParam1 = jsonObject.getString("ruleParam1");
            }
            if (jsonObject.has("ruleParam2")) {
                this.ruleParam2 = jsonObject.getString("ruleParam2");
            }
            if (jsonObject.has("ruleParam3")) {
                this.ruleParam3 = jsonObject.getString("ruleParam3");
            }
            this.ruleUrl = jsonObject.getString("ruleUrl");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String toBase64String() {
        String base64 = toJsonObject().toString();
        base64 = Base64.encodeToString(base64.getBytes(), Base64.DEFAULT);
        return base64;
    }
}
