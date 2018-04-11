package com.wblei.vo;

import com.wblei.converter_annotation.Converter;

/**
 * Created by chenxiaoyan on 16/6/17.
 */
@Converter(source = NBMsgRzrqPositionInfo.MsgRzrqPositionInfo.class)
public class RzrqPostionQueryInfo extends PostionQueryInfo {
  public String assetPrice;
  public String assureRatio;
  public String avBuyPrice;
  public String avIncomeBalance;
  public String entrustSellAmount;
  public String fairPrice;
  public String fairPriceFlag;
  public String fundAccount;
  public String handFlag;
  public String incomeBalanceNofare;
  public String realBuyAmount;
  public String realSellAmount;
  public String uncomeBuyAmount;
  public String uncomeSellAmount;

  public RzrqPostionQueryInfo() {}

  public String getAssetPrice() {
    return assetPrice;
  }

  public void setAssetPrice(String assetPrice) {
    this.assetPrice = assetPrice;
  }

  public String getAssureRatio() {
    return assureRatio;
  }

  public void setAssureRatio(String assureRatio) {
    this.assureRatio = assureRatio;
  }

  public String getAvBuyPrice() {
    return avBuyPrice;
  }

  public void setAvBuyPrice(String avBuyPrice) {
    this.avBuyPrice = avBuyPrice;
  }

  public String getAvIncomeBalance() {
    return avIncomeBalance;
  }

  public void setAvIncomeBalance(String avIncomeBalance) {
    this.avIncomeBalance = avIncomeBalance;
  }

  public String getEntrustSellAmount() {
    return entrustSellAmount;
  }

  public void setEntrustSellAmount(String entrustSellAmount) {
    this.entrustSellAmount = entrustSellAmount;
  }

  public String getFairPrice() {
    return fairPrice;
  }

  public void setFairPrice(String fairPrice) {
    this.fairPrice = fairPrice;
  }

  public String getFairPriceFlag() {
    return fairPriceFlag;
  }

  public void setFairPriceFlag(String fairPriceFlag) {
    this.fairPriceFlag = fairPriceFlag;
  }

  public String getFundAccount() {
    return fundAccount;
  }

  public void setFundAccount(String fundAccount) {
    this.fundAccount = fundAccount;
  }

  public String getHandFlag() {
    return handFlag;
  }

  public void setHandFlag(String handFlag) {
    this.handFlag = handFlag;
  }

  public String getIncomeBalanceNofare() {
    return incomeBalanceNofare;
  }

  public void setIncomeBalanceNofare(String incomeBalanceNofare) {
    this.incomeBalanceNofare = incomeBalanceNofare;
  }

  public String getRealBuyAmount() {
    return realBuyAmount;
  }

  public void setRealBuyAmount(String realBuyAmount) {
    this.realBuyAmount = realBuyAmount;
  }

  public String getRealSellAmount() {
    return realSellAmount;
  }

  public void setRealSellAmount(String realSellAmount) {
    this.realSellAmount = realSellAmount;
  }

  public String getUncomeBuyAmount() {
    return uncomeBuyAmount;
  }

  public void setUncomeBuyAmount(String uncomeBuyAmount) {
    this.uncomeBuyAmount = uncomeBuyAmount;
  }

  public String getUncomeSellAmount() {
    return uncomeSellAmount;
  }

  public void setUncomeSellAmount(String uncomeSellAmount) {
    this.uncomeSellAmount = uncomeSellAmount;
  }
}
