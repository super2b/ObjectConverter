package com.wblei.vo;

import com.wblei.converter_annotation.Converter;
import com.wblei.converter_annotation.PBField;

/**
 * Created by leiweibo on 27/04/2017.
 */
@Converter(source = NBMsgRzrqEnableRevokeInfo.MsgRzrqEnableRevokeInfo.class)
public class RzrqEnableRevokeQueryInfo {
  @PBField(name = "entrustBatchNo")
  public String batchNo; //委托批号
  public String entrustNo;//委托编号
  @PBField(name = "marketId")
  public String exchangeType; //交易类别
  public String stockAccount; //证券账号
  public String stockCode; // 证券代码
  public String entrustBs; // 买卖方向
  public String entrustPrice; //委托价格
  public String entrustAmount; //委托数量
  public String withDrawAmount; //撤单数量
  @PBField(name = "bizAmount")
  public String businessAmount; //成交数量
  public String businessPrice; // 成交价格
  @PBField(name = "bizBalance")
  public String businessBalance; //成交金额
  public String reportNo; //申请编号
  public String reportTime; //申请时间
  public String entrustType; //委托类型
  public String entrustStatus; //委托状态
  public String entrustTime; //委托时间
  public String entrustDate; //委托日期
  public String entrustProp; //委托属性
  public String stockName; //证券名称
  public String tradeName; //订单名称
  public String cancelInfo; //废单原因
  public String positionStr; //定位串
  public String compactId; //合约编号
  public String orderId; //客户订单编号
  public String origOrderId; //原客户订单编号
  @PBField(name = "statusString")
  public String showStatus; //页面显示的状态

  public String getBatchNo() {
    return batchNo;
  }

  public void setBatchNo(String batchNo) {
    this.batchNo = batchNo;
  }

  public String getEntrustNo() {
    return entrustNo;
  }

  public void setEntrustNo(String entrustNo) {
    this.entrustNo = entrustNo;
  }

  public String getExchangeType() {
    return exchangeType;
  }

  public void setExchangeType(String exchangeType) {
    this.exchangeType = exchangeType;
  }

  public String getStockAccount() {
    return stockAccount;
  }

  public void setStockAccount(String stockAccount) {
    this.stockAccount = stockAccount;
  }

  public String getStockCode() {
    return stockCode;
  }

  public void setStockCode(String stockCode) {
    this.stockCode = stockCode;
  }

  public String getEntrustBs() {
    return entrustBs;
  }

  public void setEntrustBs(String entrustBs) {
    this.entrustBs = entrustBs;
  }

  public String getEntrustPrice() {
    return entrustPrice;
  }

  public void setEntrustPrice(String entrustPrice) {
    this.entrustPrice = entrustPrice;
  }

  public String getEntrustAmount() {
    return entrustAmount;
  }

  public void setEntrustAmount(String entrustAmount) {
    this.entrustAmount = entrustAmount;
  }

  public String getWithDrawAmount() {
    return withDrawAmount;
  }

  public void setWithDrawAmount(String withDrawAmount) {
    this.withDrawAmount = withDrawAmount;
  }

  public String getBusinessAmount() {
    return businessAmount;
  }

  public void setBusinessAmount(String businessAmount) {
    this.businessAmount = businessAmount;
  }

  public String getBusinessPrice() {
    return businessPrice;
  }

  public void setBusinessPrice(String businessPrice) {
    this.businessPrice = businessPrice;
  }

  public String getBusinessBalance() {
    return businessBalance;
  }

  public void setBusinessBalance(String businessBalance) {
    this.businessBalance = businessBalance;
  }

  public String getReportNo() {
    return reportNo;
  }

  public void setReportNo(String reportNo) {
    this.reportNo = reportNo;
  }

  public String getReportTime() {
    return reportTime;
  }

  public void setReportTime(String reportTime) {
    this.reportTime = reportTime;
  }

  public String getEntrustType() {
    return entrustType;
  }

  public void setEntrustType(String entrustType) {
    this.entrustType = entrustType;
  }

  public String getEntrustStatus() {
    return entrustStatus;
  }

  public void setEntrustStatus(String entrustStatus) {
    this.entrustStatus = entrustStatus;
  }

  public String getEntrustTime() {
    return entrustTime;
  }

  public void setEntrustTime(String entrustTime) {
    this.entrustTime = entrustTime;
  }

  public String getEntrustDate() {
    return entrustDate;
  }

  public void setEntrustDate(String entrustDate) {
    this.entrustDate = entrustDate;
  }

  public String getEntrustProp() {
    return entrustProp;
  }

  public void setEntrustProp(String entrustProp) {
    this.entrustProp = entrustProp;
  }

  public String getStockName() {
    return stockName;
  }

  public void setStockName(String stockName) {
    this.stockName = stockName;
  }

  public String getTradeName() {
    return tradeName;
  }

  public void setTradeName(String tradeName) {
    this.tradeName = tradeName;
  }

  public String getCancelInfo() {
    return cancelInfo;
  }

  public void setCancelInfo(String cancelInfo) {
    this.cancelInfo = cancelInfo;
  }

  public String getPositionStr() {
    return positionStr;
  }

  public void setPositionStr(String positionStr) {
    this.positionStr = positionStr;
  }

  public String getCompactId() {
    return compactId;
  }

  public void setCompactId(String compactId) {
    this.compactId = compactId;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getOrigOrderId() {
    return origOrderId;
  }

  public void setOrigOrderId(String origOrderId) {
    this.origOrderId = origOrderId;
  }

  public String getShowStatus() {
    return showStatus;
  }

  public void setShowStatus(String showStatus) {
    this.showStatus = showStatus;
  }
}
