package com.walixiwa.webhunter.model;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseWebHunterModel implements Serializable {
    private String modelName;
    private String modelUrl;
    private String modelVersion;
    //请求编码
    private String requestCharset = "utf-8";
    //返回编码
    private String resultCharset = "utf-8";
    //总匹配
    private String ruleResult;
    private String ruleResultTitle;
    //链接前缀
    private String ruleResultLinkHeader;
    private String ruleResultLink;
    //图片前缀
    private String ruleResultCoverHeader;
    private String ruleResultCover;
    private String ruleResultDate;
    private String ruleResultExtra1;
    private String ruleResultExtra2;
    //是否有详情页
    private boolean hasDetailPage = false;
    //如果有详情页则选填
    private BaseParseUrlModel baseParseUrlModel;

    private List<TabModel> tabModels = new ArrayList<>();

    public String getModelName() {
        return modelName;
    }

    public BaseWebHunterModel setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getModelUrl() {
        return modelUrl;
    }

    public BaseWebHunterModel setModelUrl(String modelUrl) {
        this.modelUrl = modelUrl;
        return this;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public BaseWebHunterModel setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
        return this;
    }

    public String getRequestCharset() {
        return requestCharset;
    }

    public BaseWebHunterModel setRequestCharset(String requestCharset) {
        this.requestCharset = requestCharset;
        return this;
    }

    public String getResultCharset() {
        return resultCharset;
    }

    public BaseWebHunterModel setResultCharset(String resultCharset) {
        this.resultCharset = resultCharset;
        return this;
    }

    public String getRuleResult() {
        return ruleResult;
    }

    public BaseWebHunterModel setRuleResult(String ruleResult) {
        this.ruleResult = ruleResult;
        return this;
    }

    public String getRuleResultTitle() {
        return ruleResultTitle;
    }

    public BaseWebHunterModel setRuleResultTitle(String ruleResultTitle) {
        this.ruleResultTitle = ruleResultTitle;
        return this;
    }

    public String getRuleResultLinkHeader() {
        return ruleResultLinkHeader;
    }

    public BaseWebHunterModel setRuleResultLinkHeader(String ruleResultLinkHeader) {
        this.ruleResultLinkHeader = ruleResultLinkHeader;
        return this;
    }

    public String getRuleResultLink() {
        return ruleResultLink;
    }

    public BaseWebHunterModel setRuleResultLink(String ruleResultLink) {
        this.ruleResultLink = ruleResultLink;
        return this;
    }

    public String getRuleResultCoverHeader() {
        return ruleResultCoverHeader;
    }

    public BaseWebHunterModel setRuleResultCoverHeader(String ruleResultCoverHeader) {
        this.ruleResultCoverHeader = ruleResultCoverHeader;
        return this;
    }

    public String getRuleResultCover() {
        return ruleResultCover;
    }

    public BaseWebHunterModel setRuleResultCover(String ruleResultCover) {
        this.ruleResultCover = ruleResultCover;
        return this;
    }

    public String getRuleResultDate() {
        return ruleResultDate;
    }

    public BaseWebHunterModel setRuleResultDate(String ruleResultDate) {
        this.ruleResultDate = ruleResultDate;
        return this;
    }

    public String getRuleResultExtra1() {
        return ruleResultExtra1;
    }

    public BaseWebHunterModel setRuleResultExtra1(String ruleResultExtra1) {
        this.ruleResultExtra1 = ruleResultExtra1;
        return this;
    }

    public String getRuleResultExtra2() {
        return ruleResultExtra2;
    }

    public BaseWebHunterModel setRuleResultExtra2(String ruleResultExtra2) {
        this.ruleResultExtra2 = ruleResultExtra2;
        return this;
    }

    public boolean isHasDetailPage() {
        return hasDetailPage;
    }

    public BaseWebHunterModel setHasDetailPage(boolean hasDetailPage) {
        this.hasDetailPage = hasDetailPage;
        return this;
    }

    public List<TabModel> getTabModels() {
        return tabModels;
    }

    public BaseWebHunterModel setTabModels(List<TabModel> tabModels) {
        this.tabModels = tabModels;
        return this;
    }

    public BaseParseUrlModel getBaseParseUrlModel() {
        return baseParseUrlModel;
    }

    public BaseWebHunterModel setBaseParseUrlModel(BaseParseUrlModel baseParseUrlModel) {
        this.baseParseUrlModel = baseParseUrlModel;
        return this;
    }

    public BaseWebHunterModel() {
    }

    public BaseWebHunterModel(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            this.modelName = jsonObject.getString("modelName");
            this.modelUrl = jsonObject.getString("modelUrl");
            this.modelVersion = jsonObject.getString("modelVersion");
            if (jsonObject.has("requestCharset")) {
                this.requestCharset = jsonObject.getString("requestCharset");
            }
            if (jsonObject.has("resultCharset")) {
                this.resultCharset = jsonObject.getString("resultCharset");
            }
            this.ruleResult = jsonObject.getString("ruleResult");

            if (jsonObject.has("ruleResultTitle")) {
                this.ruleResultTitle = jsonObject.getString("ruleResultTitle");
            }
            if (jsonObject.has("ruleResultCoverHeader")) {
                this.ruleResultCoverHeader = jsonObject.getString("ruleResultCoverHeader");
            }
            if (jsonObject.has("ruleResultCover")) {
                this.ruleResultCover = jsonObject.getString("ruleResultCover");
            }
            if (jsonObject.has("ruleResultLinkHeader")) {
                this.ruleResultLinkHeader = jsonObject.getString("ruleResultLinkHeader");
            }
            if (jsonObject.has("ruleResultLink")) {
                this.ruleResultLink = jsonObject.getString("ruleResultLink");
            }
            if (jsonObject.has("ruleResultDate")) {
                this.ruleResultDate = jsonObject.getString("ruleResultDate");
            }
            if (jsonObject.has("ruleResultExtra1")) {
                this.ruleResultExtra1 = jsonObject.getString("ruleResultExtra1");
            }
            if (jsonObject.has("ruleResultExtra2")) {
                this.ruleResultExtra2 = jsonObject.getString("ruleResultExtra2");
            }
            if (jsonObject.has("hasDetailPage")) {
                this.hasDetailPage = jsonObject.getBoolean("hasDetailPage");
            }
            if (jsonObject.has("parseModel")) {
                BaseParseUrlModel parseUrlModel = new BaseParseUrlModel(jsonObject.getString("parseModel"));
                if (parseUrlModel.getBaseUrl() != null) {
                    if (parseUrlModel.getBaseUrl().equals("parent")) {
                        parseUrlModel.setBaseUrl(this.modelUrl);
                    }
                }
                this.baseParseUrlModel = parseUrlModel;
            }
            JSONArray array = jsonObject.getJSONArray("tabModels");
            for (int i = 0; i < array.length(); i++) {
                JSONObject model = array.getJSONObject(i);
                TabModel tabModel = new TabModel();
                tabModel.setTabTitle(model.getString("title"));
                if (model.has("first")) {
                    tabModel.setFirst(model.getString("first"));
                }
                tabModel.setTabUrl(model.getString("url"));
                this.tabModels.add(tabModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String toJson() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("modelName", this.modelName);
            jsonObject.put("modelUrl", this.modelUrl);
            jsonObject.put("modelVersion", this.modelVersion);
            jsonObject.put("requestCharset", this.requestCharset);
            jsonObject.put("resultCharset", this.resultCharset);
            jsonObject.put("ruleResult", this.ruleResult);
            if (!TextUtils.isEmpty(ruleResultCoverHeader)) {
                jsonObject.put("ruleResultCoverHeader", this.ruleResultCoverHeader);
            }
            if (!TextUtils.isEmpty(ruleResultCover)) {
                jsonObject.put("ruleResultCover", this.ruleResultCover);
            }
            if (!TextUtils.isEmpty(ruleResultTitle)) {
                jsonObject.put("ruleResultTitle", this.ruleResultTitle);
            }
            if (!TextUtils.isEmpty(ruleResultLinkHeader)) {
                jsonObject.put("ruleResultLinkHeader", this.ruleResultLinkHeader);
            }
            if (!TextUtils.isEmpty(ruleResultLink)) {
                jsonObject.put("ruleResultLink", this.ruleResultLink);
            }
            if (!TextUtils.isEmpty(ruleResultDate)) {
                jsonObject.put("ruleResultDate", this.ruleResultDate);
            }
            if (!TextUtils.isEmpty(ruleResultExtra1)) {
                jsonObject.put("ruleResultExtra1", this.ruleResultExtra1);
            }
            if (!TextUtils.isEmpty(ruleResultExtra2)) {
                jsonObject.put("ruleResultExtra2", this.ruleResultExtra2);
            }
            jsonObject.put("hasDetailPage", true);
            if (this.hasDetailPage) {
                jsonObject.put("parseModel", this.baseParseUrlModel.toJsonObject().toString());
            }
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < this.tabModels.size(); i++) {
                JSONObject tabModel = new JSONObject();
                tabModel.put("title", tabModels.get(i).getTabTitle());
                tabModel.put("url", tabModels.get(i).getTabUrl());
                if (!TextUtils.isEmpty(tabModels.get(i).getFirst())) {
                    tabModel.put("first", tabModels.get(i).getFirst());
                }
                jsonArray.put(tabModel);
            }
            jsonObject.put("tabModels", jsonArray);
        } catch (
                JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


}
