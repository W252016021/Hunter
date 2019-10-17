package com.walixiwa.webhunter.model;

import java.io.Serializable;

public class BaseWebResultModel  implements Serializable {
    private String resultTitle;
    private String resultLink;
    private String resultCover;
    private String resultDate;
    private String resultExtra1;
    private String resultExtra2;
    private BaseWebHunterModel webHunterModel;
    public String getResultTitle() {
        return resultTitle;
    }

    public void setResultTitle(String resultTitle) {
        this.resultTitle = resultTitle;
    }

    public String getResultLink() {
        return resultLink;
    }

    public void setResultLink(String resultLink) {
        this.resultLink = resultLink;
    }

    public String getResultCover() {
        return resultCover;
    }

    public void setResultCover(String resultCover) {
        this.resultCover = resultCover;
    }

    public String getResultDate() {
        return resultDate;
    }

    public void setResultDate(String resultDate) {
        this.resultDate = resultDate;
    }

    public String getResultExtra1() {
        return resultExtra1;
    }

    public void setResultExtra1(String resultExtra1) {
        this.resultExtra1 = resultExtra1;
    }

    public String getResultExtra2() {
        return resultExtra2;
    }

    public BaseWebResultModel setResultExtra2(String resultExtra2) {
        this.resultExtra2 = resultExtra2;
        return this;
    }

    public BaseWebHunterModel getWebHunterModel() {
        return webHunterModel;
    }

    public BaseWebResultModel setWebHunterModel(BaseWebHunterModel webHunterModel) {
        this.webHunterModel = webHunterModel;
        return this;
    }

    @Override
    public String toString() {
        return "BaseWebResultModel{" +
                "resultTitle='" + resultTitle + '\'' +
                ", resultLink='" + resultLink + '\'' +
                ", resultCover='" + resultCover + '\'' +
                ", resultDate='" + resultDate + '\'' +
                ", resultExtra1='" + resultExtra1 + '\'' +
                ", resultExtra2='" + resultExtra2 + '\'' +
                '}';
    }
}
