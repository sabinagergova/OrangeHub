// transaction list class
// this class contains all transactins which shown in the transcation search view
// its only a small subset from all fields to reduce traffic an improve performance
// to get more details of a transaction use the singleTransaction class
// because of a missunderstsandig with marcel he used the transactions.java instead
// of the SingleTransactionResult. 

package com.skrill.viewpoint.jobs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class VIewpointTransaction implements Serializable {

    private static final long serialVersionUID = -3230848975104421903L;

    private Map<String, Object> data;

    public VIewpointTransaction() {
        data = new HashMap<String, Object>();
    }

    public void set(String key, Object value) {
        data.put(key, value);
    }

    public Object get(String key) {
        return data.get(key);
    }

    public String getRegion() {
        if (data.containsKey("region")) {
            return (String) data.get("region");
        } else {
            return null;
        }
    }

    public void setRegion(String region) {
        data.put("region", region);
    }

    public String getClearingInstitute() {
        if (data.containsKey("clearing_institute")) {
            return (String) data.get("clearing_institute");
        } else {
            return null;
        }
    }

    public void setClearingInstitute(String clearing_institute) {
        data.put("clearing_institute", clearing_institute);
    }

    public void setDescriptor(String descriptor) {
        data.put("descriptor", descriptor);
    }

    public String getDescriptor() {
        if (data.containsKey("descriptor")) {
            return (String) data.get("descriptor");
        } else {
            return null;
        }
    }

    public void setUniqueId(String uniqueId) {
        data.put("uniqueid", uniqueId);
    }

    public String getUniqueId() {
        if (data.containsKey("uniqueid")) {
            return (String) data.get("uniqueid");
        } else {
            return null;
        }
    }

    public String getAcqCurrency() {
        if (data.containsKey("acqfkiso4217")) {
            return (String) data.get("acqfkiso4217");
        } else {
            return null;
        }
    }

    public void setAcqCurrency(String acqfkiso4217) {
        data.put("acqfkiso4217", acqfkiso4217);
    }

    public BigDecimal getAcqGrossAmount() {
        if (data.containsKey("acqGrossAmount")) {
            return (BigDecimal) data.get("acqGrossAmount");
        } else {
            return null;
        }
    }

    public void setAcqGrossAmount(BigDecimal acqGrossAmount) {
        data.put("acqGrossAmount", acqGrossAmount);
    }

    public Double getAcqcost() {
        if (data.containsKey("acqcost")) {
            return (Double) data.get("acqcost");
        } else {
            return null;
        }
    }

    public void setAcqcost(double acqcost) {
        data.put("acqcost", acqcost);
    }

    public BigDecimal getPtf() {
        if (data.containsKey("ptf")) {
            return (BigDecimal) data.get("ptf");
        } else {
            return null;
        }
    }

    public void setPtf(BigDecimal ptf) {
        data.put("ptf", ptf);
    }

    public BigDecimal getRmf() {
        if (data.containsKey("rmf")) {
            return (BigDecimal) data.get("rmf");
        } else {
            return null;
        }
    }

    public void setRmf(BigDecimal rmf) {
        data.put("rmf", rmf);
    }

    public Integer getPk() {
        if (data.containsKey("pk")) {
            return ((Integer) (data.get("pk")));
        } else {
            return null;
        }
    }

    public void setPk(int pk) {
        data.put("pk", pk);
    }

    public String getShortId() {
        if (data.containsKey("shortid")) {
            return (String) data.get("shortid");
        } else {
            return null;
        }
    }

    public void setShortId(String shortid) {
        data.put("shortid", shortid);
    }

    public Date getValuedate() {
        if (data.containsKey("valuedate")) {
            return (Date) data.get("valuedate");
        } else {
            return null;
        }
    }

    public void setValuedate(Date valuedate) {
        data.put("valuedate", valuedate);
    }

    public BigDecimal getAmount() {
        if (data.containsKey("amount")) {
            return (BigDecimal) data.get("amount");
        } else {
            return null;
        }
    }

    public void setAmount(BigDecimal amount) {
        data.put("amount", amount);
    }

    public Double getAcqnetamount() {
        if (data.containsKey("acqnetamount")) {
            return (Double) data.get("acqnetamount");
        } else {
            return null;
        }
    }

    public void setAcqnetamount(double acqnetamount) {
        data.put("acqnetamount", acqnetamount);
    }

    public String getCurrency() {
        if (data.containsKey("fkiso4217")) {
            return (String) data.get("fkiso4217");
        } else {
            return null;
        }
    }

    public void setCurrency(String fkiso4217) {
        data.put("fkiso4217", fkiso4217);
    }

    public String getResult() {
        if (data.containsKey("result")) {
            return (String) data.get("result");
        } else {
            return null;
        }
    }

    public void setResult(String result) {
        data.put("result", result);
    }

    public String getMethod() {
        if (data.containsKey("method")) {
            return (String) data.get("method");
        } else {
            return null;
        }
    }

    public void setMethod(String method) {
        data.put("method", method);
    }

    public String getType() {
        if (data.containsKey("type")) {
            return (String) data.get("type");
        } else {
            return null;
        }
    }

    public void setType(String type) {
        data.put("type", type);
    }

    public String getAccount_holder() {
        if (data.containsKey("account_holder")) {
            return (String) data.get("account_holder");
        } else {
            return null;
        }
    }

    public void setAccount_holder(String account_holder) {
        data.put("account_holder", account_holder);
    }

    public String getSettled() {
        if (data.containsKey("settled")) {
            return (String) data.get("settled");
        } else {
            return null;
        }
    }

    public void setSettled(String settled) {
        data.put("settled", settled);
    }

    public String getReconcile() {
        if (data.containsKey("reconcile")) {
            return (String) data.get("reconcile");
        } else {
            return null;
        }
    }

    public void setReconcile(String reconcile) {
        data.put("reconcile", reconcile);
    }

    public String getShopperId() {
        if (data.containsKey("shopperid")) {
            return (String) data.get("shopperid");
        } else {
            return null;
        }
    }

    public void setShopperId(String shopperId) {
        data.put("shopperid", shopperId);
    }

    public String getReferenceId() {
        if (data.containsKey("referenceid")) {
            return (String) data.get("referenceid");
        } else {
            return null;
        }
    }

    public void setReferenceId(String referenceId) {
        data.put("referenceid", referenceId);
    }

    public String getTransactionId() {
        if (data.containsKey("transactionid")) {
            return (String) data.get("transactionid");
        } else {
            return null;
        }
    }

    public void setTransactionId(String transactionId) {
        data.put("transactionid", transactionId);
    }

    public Timestamp getProcessTimestamp() {
        if (data.containsKey("processTimestamp")) {
            return (Timestamp) data.get("processTimestamp");
        } else {
            return null;
        }
    }

    public void setProcessTimestamp(Timestamp processTimestamp) {
        data.put("processTimestamp", processTimestamp);
    }

    public String getClaimId() {
        if (data.containsKey("claimid")) {
            return (String) data.get("claimid");
        } else {
            return null;
        }
    }

    public void setClaimId(String value) {
        data.put("claimid", value);
    }

    public BigDecimal getCbAcqCost() {
        if (data.containsKey("cbacqcost")) {
            return (BigDecimal) data.get("cbacqcost");
        } else {
            return null;
        }
    }

    public void setCbAcqCost(BigDecimal value) {
        data.put("cbacqcost", value);
    }

    public Timestamp getExportedDate() {
        if (data.containsKey("exported_date")) {
            return (Timestamp) data.get("exported_date");
        } else {
            return null;
        }
    }

    public void setExportedDate(Timestamp value) {
        data.put("exported_date", value);
    }

    public Integer getFkCompany() {
        if (data.containsKey("fkcompany")) {
            return (Integer) data.get("fkcompany");
        } else {
            return null;
        }
    }

    public void setFkCompany(Integer value) {
        data.put("fkcompany", value);
    }

    public String getAcqskiso4217() {
        if (data.containsKey("acqskiso4217")) {
            return (String) data.get("acqskiso4217");
        } else {
            return null;
        }
    }

    public void setAcqskiso4217(String value) {
        data.put("acqskiso4217", value);
    }

    public String getChannelId() {
        if (data.containsKey("channelid")) {
            return (String) data.get("channelid");
        } else {
            return null;
        }
    }

    public void setChannelId(String value) {
        data.put("channelid", value);
    }

    public String getReconcileAccountNumber() {
        if (data.containsKey("reconcile_account_number")) {
            return (String) data.get("reconcile_account_number");
        } else {
            return null;
        }
    }

    public void setReconcileAccountNumber(String value) {
        data.put("reconcile_account_number", value);
    }

    public Integer getConnectorTxId() {
        if (data.containsKey("connectorTxId")) {
            return (Integer) data.get("connectorTxId");
        } else {
            return null;
        }
    }

    public void setConnectorTxId(Integer value) {
        data.put("connectorTxId", value);
    }

    public String getCustomerCountry() {
        if (data.containsKey("customerCountry")) {
            return (String) data.get("customerCountry");
        } else {
            return null;
        }
    }

    public void setCustomerCountry(String customerCountry) {
        data.put("customerCountry", customerCountry);
    }

    public String getAccountHolder() {
        if (data.containsKey("accountHolder")) {
            return (String) data.get("accountHolder");
        } else {
            return null;
        }
    }

    public void setAccountHolder(String accountHolder) {
        data.put("accountHolder", accountHolder);
    }

    public String getBrand() {
        if (data.containsKey("brand")) {
            return (String) data.get("brand");
        } else {
            return null;
        }
    }

    public void setBrand(String brand) {
        data.put("brand", brand);
    }

    public String getMBAccount() {
        if (data.containsKey("mbAccount")) {
            return (String) data.get("mbAccount");
        } else {
            return null;
        }
    }

    public void setMBAccount(String mbAccount) {
        data.put("mbAccount", mbAccount);
    }

    public String getMBPaymentMethod() {
        if (data.containsKey("mbPaymentMethod")) {
            return (String) data.get("mbPaymentMethod");
        } else {
            return null;
        }
    }

    public void setMBPaymentMethod(String mbPaymentMethod) {
        data.put("mbPaymentMethod", mbPaymentMethod);
    }

    public BigDecimal getSettlementFxRate() {
        if (data.containsKey("settlementFxRate")) {
            return (BigDecimal) data.get("settlementFxRate");
        } else {
            return null;
        }
    }

    public void setSettlementFxRate(BigDecimal settlementFxRate) {
        data.put("settlementFxRate", settlementFxRate);
    }

    public BigDecimal getSettlementAmount() {
        if (data.containsKey("settlementAmount")) {
            return (BigDecimal) data.get("settlementAmount");
        } else {
            return null;
        }
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        data.put("settlementAmount", settlementAmount);
    }

    public String getSettlementCurrency() {
        if (data.containsKey("settlementCurrency")) {
            return (String) data.get("settlementCurrency");
        } else {
            return null;
        }
    }

    public void setSettlementCurrency(String settlementCurrency) {
        data.put("settlementCurrency", settlementCurrency);
    }

    public BigDecimal getAcqFxRate() {
        if (data.containsKey("acqFxRate")) {
            return (BigDecimal) data.get("acqFxRate");
        } else {
            return null;
        }
    }

    public void setAcqFxRate(BigDecimal acqFxRate) {
        data.put("acqFxRate", acqFxRate);
    }

    public BigDecimal getFxRate() {
        if (data.containsKey("fxRate")) {
            return (BigDecimal) data.get("fxRate");
        } else {
            return null;
        }
    }

    public void setFxRate(BigDecimal fxRate) {
        data.put("fxRate", fxRate);
    }

}
