package com.wblei.vo;

import com.wblei.converter_annotation.PBField;

/**
 * Created by chenxiaoyan on 16/6/17.
 */

public class PostionQueryInfo {

  public String profitRatio;
  public String stockCode;
  public String stockName;
  public String incomeBalance;
  public String costBalance;
  public String costPrice;
  public String lastPrice;
  public String currentAmount;
  public String enableAmount;
  public String holdAmount;
  public String marketValue;
  public String keepCostPrice;
  public String stockAccount;
  @PBField(name = "marketId")
  public String exchangeType;
  public String debtFlag;

  public PostionQueryInfo() {}

  public String getProfitRatio() {
    return profitRatio;
  }

  public void setProfitRatio(String profitRatio) {
    this.profitRatio = profitRatio;
  }

  public String getStockCode() {
    return stockCode;
  }

  public void setStockCode(String stockCode) {
    this.stockCode = stockCode;
  }

  public String getStockName() {
    return stockName;
  }

  public void setStockName(String stockName) {
    this.stockName = stockName;
  }

  public String getIncomeBalance() {
    return incomeBalance;
  }

  public void setIncomeBalance(String incomeBalance) {
    this.incomeBalance = incomeBalance;
  }

  public String getCostBalance() {
    return costBalance;
  }

  public void setCostBalance(String costBalance) {
    this.costBalance = costBalance;
  }

  public String getCostPrice() {
    return costPrice;
  }

  public void setCostPrice(String costPrice) {
    this.costPrice = costPrice;
  }

  public String getLastPrice() {
    return lastPrice;
  }

  public void setLastPrice(String lastPrice) {
    this.lastPrice = lastPrice;
  }

  public String getCurrentAmount() {
    return currentAmount;
  }

  public void setCurrentAmount(String currentAmount) {
    this.currentAmount = currentAmount;
  }

  public String getEnableAmount() {
    return enableAmount;
  }

  public void setEnableAmount(String enableAmount) {
    this.enableAmount = enableAmount;
  }

  public String getHoldAmount() {
    return holdAmount;
  }

  public void setHoldAmount(String holdAmount) {
    this.holdAmount = holdAmount;
  }

  public String getMarketValue() {
    return marketValue;
  }

  public void setMarketValue(String marketValue) {
    this.marketValue = marketValue;
  }

  public String getKeepCostPrice() {
    return keepCostPrice;
  }

  public void setKeepCostPrice(String keepCostPrice) {
    this.keepCostPrice = keepCostPrice;
  }

  public String getStockAccount() {
    return stockAccount;
  }

  public void setStockAccount(String stockAccount) {
    this.stockAccount = stockAccount;
  }

  public String getExchangeType() {
    return exchangeType;
  }

  public void setExchangeType(String exchangeType) {
    this.exchangeType = exchangeType;
  }

  public String getDebtFlag() {
    return debtFlag;
  }

  public void setDebtFlag(String debtFlag) {
    this.debtFlag = debtFlag;
  }

}
