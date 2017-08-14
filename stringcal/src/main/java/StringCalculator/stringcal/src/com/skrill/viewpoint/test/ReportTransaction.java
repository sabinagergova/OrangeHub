package com.skrill.viewpoint.test;

import java.math.BigDecimal;

public class ReportTransaction {

    private String settlementStatus;
    private String paymentType;
    private String paymentMethod;
    private String transactionID;
    private String uniqueID;
    private String shortID;
    private String channelName;
    private String customerCountry;
    private String valuedate;
    private String requestTimestamp;
    private String brand;
    private int separatorTrn;
    private BigDecimal creditTrnAmount;
    private BigDecimal debitTrnAmount;
    private String trnCcy;
    private int separatorGrossFunding;
    private BigDecimal grossFundingAmount;
    private int separatorNetFunding;
    private BigDecimal netFundingAmount;
    private String fundingCcy;
    private int separatorFees;
    private BigDecimal totalFees;
    private String feeCcy;
    private String settlementAccount;
    private int separatorAcqFx;
    private BigDecimal acqFx;
    private int separatorSettlFx;
    private BigDecimal settlementFx;
    private int separatorSettlAmt;
    private BigDecimal settlementAmt;
    private String settlementCcy;


    /**
     * @return the settlementStatus
     */
    public String getSettlementStatus() {
        return settlementStatus;
    }
    /**
     * @param settlementStatus the settlementStatus to set
     */
    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
    }
    /**
     * @return the paymentType
     */
    public String getPaymentType() {
        return paymentType;
    }
    /**
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    /**
     * @return the paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }
    /**
     * @param paymentMethod the paymentMethod to set
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    /**
     * @return the transactionID
     */
    public String getTransactionID() {
        return transactionID;
    }
    /**
     * @param transactionID the transactionID to set
     */
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
    /**
     * @return the uniqueID
     */
    public String getUniqueID() {
        return uniqueID;
    }
    /**
     * @param uniqueID the uniqueID to set
     */
    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }
    /**
     * @return the shortID
     */
    public String getShortID() {
        return shortID;
    }
    /**
     * @param shortID the shortID to set
     */
    public void setShortID(String shortID) {
        this.shortID = shortID;
    }
    /**
     * @return the channelName
     */
    public String getChannelName() {
        return channelName;
    }
    /**
     * @param channelName the channelName to set
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    /**
     * @return the customerCountry
     */
    public String getCustomerCountry() {
        return customerCountry;
    }
    /**
     * @param customerCountry the customerCountry to set
     */
    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }
    /**
     * @return the valuedate
     */
    public String getValuedate() {
        return valuedate;
    }
    /**
     * @param valuedate the valuedate to set
     */
    public void setValuedate(String valuedate) {
        this.valuedate = valuedate;
    }
    /**
     * @return the requestTimestamp
     */
    public String getRequestTimestamp() {
        return requestTimestamp;
    }
    /**
     * @param requestTimestamp the requestTimestamp to set
     */
    public void setRequestTimestamp(String requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }
    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }
    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }
    /**
     * @return the separatorTrn
     */
    public int getSeparatorTrn() {
        return separatorTrn;
    }
    /**
     * @param separatorTrn the separatorTrn to set
     */
    public void setSeparatorTrn(int separatorTrn) {
        this.separatorTrn = separatorTrn;
    }
    /**
     * @return the creditTrnAmount
     */
    public BigDecimal getCreditTrnAmount() {
        return creditTrnAmount;
    }
    /**
     * @param creditTrnAmount the creditTrnAmount to set
     */
    public void setCreditTrnAmount(BigDecimal creditTrnAmount) {
        this.creditTrnAmount = creditTrnAmount;
    }

    public void setCreditTrnAmount(String creditTrnAmount) {
        // Credit will be set to 0 (Zero) if value is invalid
        String creditCleaned;
        BigDecimal creditBD = null;

        if (Util.stringEmpty(creditTrnAmount)) {
            this.setCreditTrnAmount(BigDecimal.ZERO);
        } else {
            try {
                creditCleaned = creditTrnAmount.replace(",", ".");
                creditBD = new BigDecimal(creditCleaned);
            } catch (Exception e) {
                creditBD = BigDecimal.ZERO;
            } finally {
                creditBD.setScale(6, BigDecimal.ROUND_HALF_UP);
                this.setCreditTrnAmount(creditBD);
            }
        }
    }
    /**
     * @return the debitTrnAmount
     */
    public BigDecimal getDebitTrnAmount() {
        return debitTrnAmount;
    }
    /**
     * @param debitTrnAmount the debitTrnAmount to set
     */
    public void setDebitTrnAmount(BigDecimal debitTrnAmount) {
        this.debitTrnAmount = debitTrnAmount;
    }

    public void setDebitTrnAmount(String debitTrnAmount) {
        // Debit will be set to 0 (Zero) if value is invalid
        String debitCleaned = null;
        BigDecimal debitBD = null;

        if (Util.stringEmpty(debitTrnAmount)) {
            this.setDebitTrnAmount(BigDecimal.ZERO);
        } else {
            try {
                debitCleaned = debitTrnAmount.replace(",", ".");
                debitBD = new BigDecimal(debitCleaned);
            } catch (Exception e) {
                debitBD = BigDecimal.ZERO;
            } finally {
                debitBD.setScale(6, BigDecimal.ROUND_HALF_UP);
                this.setDebitTrnAmount(debitBD);
            }
        }
    }
    /**
     * @return the trnCcy
     */
    public String getTrnCcy() {
        return trnCcy;
    }
    /**
     * @param trnCcy the trnCcy to set
     */
    public void setTrnCcy(String trnCcy) {
        this.trnCcy = trnCcy;
    }
    /**
     * @return the separatorGrossFunding
     */
    public int getSeparatorGrossFunding() {
        return separatorGrossFunding;
    }
    /**
     * @param separatorGrossFunding the separatorGrossFunding to set
     */
    public void setSeparatorGrossFunding(int separatorGrossFunding) {
        this.separatorGrossFunding = separatorGrossFunding;
    }
    /**
     * @return the grossFundingAmount
     */
    public BigDecimal getGrossFundingAmount() {
        return grossFundingAmount;
    }
    /**
     * @param grossFundingAmount the grossFundingAmount to set
     */
    public void setGrossFundingAmount(BigDecimal grossFundingAmount) {
        this.grossFundingAmount = grossFundingAmount;
    }

    public void setGrossFundingAmount(String grossFundingAmount) {
        //GrossFunding will be set to 0 (Zero) if value is invalid
        String grossFundingCleaned = null;
        BigDecimal grossFundingBD = null;

        if (Util.stringEmpty(grossFundingAmount)) {
            this.setGrossFundingAmount(BigDecimal.ZERO);
        } else {
            try {
                grossFundingCleaned = grossFundingAmount.replace(",", ".");
                grossFundingBD = new BigDecimal(grossFundingCleaned);
            } catch (Exception e) {
                grossFundingBD = BigDecimal.ZERO;
            } finally {
                grossFundingBD.setScale(6, BigDecimal.ROUND_HALF_UP);
                this.setGrossFundingAmount(grossFundingBD);
            }
        }
    }
    /**
     * @return the separatorNetFunding
     */
    public int getSeparatorNetFunding() {
        return separatorNetFunding;
    }
    /**
     * @param separatorNetFunding the separatorNetFunding to set
     */
    public void setSeparatorNetFunding(int separatorNetFunding) {
        this.separatorNetFunding = separatorNetFunding;
    }
    /**
     * @return the netFundingAmount
     */
    public BigDecimal getNetFundingAmount() {
        return netFundingAmount;
    }
    /**
     * @param netFundingAmount the netFundingAmount to set
     */
    public void setNetFundingAmount(BigDecimal netFundingAmount) {
        this.netFundingAmount = netFundingAmount;
    }

    public void setNetFundingAmount(String netFundingAmount) {
        //GrossFunding will be set to 0 (Zero) if value is invalid
        String netFundingCleaned = null;
        BigDecimal netFundingBD = null;

        if (Util.stringEmpty(netFundingAmount)) {
            this.setNetFundingAmount(BigDecimal.ZERO);
        } else {
            try {
                netFundingCleaned = netFundingAmount.replace(",", ".");
                netFundingBD = new BigDecimal(netFundingCleaned);
            } catch (Exception e) {
                netFundingBD = BigDecimal.ZERO;
            } finally {
                netFundingBD.setScale(6, BigDecimal.ROUND_HALF_UP);
                this.setNetFundingAmount(netFundingBD);
            }
        }
    }
    /**
     * @return the fundingCcy
     */
    public String getFundingCcy() {
        return fundingCcy;
    }
    /**
     * @param fundingCcy the fundingCcy to set
     */
    public void setFundingCcy(String fundingCcy) {
        this.fundingCcy = fundingCcy;
    }
    /**
     * @return the separatorFees
     */
    public int getSeparatorFees() {
        return separatorFees;
    }
    /**
     * @param separatorFees the separatorFees to set
     */
    public void setSeparatorFees(int separatorFees) {
        this.separatorFees = separatorFees;
    }
    /**
     * @return the totalFees
     */
    public BigDecimal getTotalFees() {
        return totalFees;
    }
    /**
     * @param totalFees the totalFees to set
     */
    public void setTotalFees(BigDecimal totalFees) {
        this.totalFees = totalFees;
    }

    public void setTotalFees(String totalFees) {
        //GrossFunding will be set to 0 (Zero) if value is invalid
        String feesCleaned = null;
        BigDecimal feesBD = null;

        if (Util.stringEmpty(totalFees)) {
            this.setTotalFees(BigDecimal.ZERO);
        } else {
            try {
                feesCleaned = totalFees.replace(",", ".");
                feesBD = new BigDecimal(feesCleaned);
            } catch (Exception e) {
                feesBD = BigDecimal.ZERO;
            } finally {
                feesBD.setScale(6, BigDecimal.ROUND_HALF_UP);
                this.setTotalFees(feesBD);
            }
        }
    }
    /**
     * @return the feeCcy
     */
    public String getFeeCcy() {
        return feeCcy;
    }
    /**
     * @param feeCcy the feeCcy to set
     */
    public void setFeeCcy(String feeCcy) {
        this.feeCcy = feeCcy;
    }
    /**
     * @return the settlementAccount
     */
    public String getSettlementAccount() {
        return settlementAccount;
    }
    /**
     * @param settlementAccount the settlementAccount to set
     */
    public void setSettlementAccount(String settlementAccount) {
        this.settlementAccount = settlementAccount;
    }
    /**
     * @return the separatorAcqFx
     */
    public int getSeparatorAcqFx() {
        return separatorAcqFx;
    }
    /**
     * @param separatorAcqFx the separatorAcqFx to set
     */
    public void setSeparatorAcqFx(int separatorAcqFx) {
        this.separatorAcqFx = separatorAcqFx;
    }
    /**
     * @return the acqFx
     */
    public BigDecimal getAcqFx() {
        return acqFx;
    }
    /**
     * @param acqFx the acqFx to set
     */
    public void setAcqFx(BigDecimal acqFx) {
        this.acqFx = acqFx;
    }

    public void setAcqFx(String acqFx) {
        //GrossFunding will be set to 0 (Zero) if value is invalid
        String acqFxCleaned = null;
        BigDecimal acqFxBD = null;

        if (Util.stringEmpty(acqFx)) {
            this.setAcqFx(BigDecimal.ZERO);
        } else {
            try {
                acqFxCleaned = acqFx.replace(",", ".");
                acqFxBD = new BigDecimal(acqFxCleaned);
            } catch (Exception e) {
                acqFxBD = BigDecimal.ZERO;
            } finally {
                acqFxBD.setScale(6, BigDecimal.ROUND_HALF_UP);
                this.setAcqFx(acqFxBD);
            }
        }
    }
    /**
     * @return the separatorSettlFx
     */
    public int getSeparatorSettlFx() {
        return separatorSettlFx;
    }
    /**
     * @param separatorSettlFx the separatorSettlFx to set
     */
    public void setSeparatorSettlFx(int separatorSettlFx) {
        this.separatorSettlFx = separatorSettlFx;
    }
    /**
     * @return the settlementFx
     */
    public BigDecimal getSettlementFx() {
        return settlementFx;
    }
    /**
     * @param settlementFx the settlementFx to set
     */
    public void setSettlementFx(BigDecimal settlementFx) {
        this.settlementFx = settlementFx;
    }

    public void setSettlementFx(String settlementFx) {
        //GrossFunding will be set to 0 (Zero) if value is invalid
        String settlementFxCleaned = null;
        BigDecimal settlementFxBD = null;

        if (Util.stringEmpty(settlementFx)) {
            this.setSettlementFx(BigDecimal.ZERO);
        } else {
            try {
                settlementFxCleaned = settlementFx.replace(",", ".");
                settlementFxBD = new BigDecimal(settlementFxCleaned);
            } catch (Exception e) {
                settlementFxBD = BigDecimal.ZERO;
            } finally {
                settlementFxBD.setScale(6, BigDecimal.ROUND_HALF_UP);
                this.setSettlementFx(settlementFxBD);
            }
        }
    }
    /**
     * @return the separatorSettlAmt
     */
    public int getSeparatorSettlAmt() {
        return separatorSettlAmt;
    }
    /**
     * @param separatorSettlAmt the separatorSettlAmt to set
     */
    public void setSeparatorSettlAmt(int separatorSettlAmt) {
        this.separatorSettlAmt = separatorSettlAmt;
    }
    /**
     * @return the settlementAmt
     */
    public BigDecimal getSettlementAmt() {
        return settlementAmt;
    }
    /**
     * @param settlementAmt the settlementAmt to set
     */
    public void setSettlementAmt(BigDecimal settlementAmt) {
        this.settlementAmt = settlementAmt;
    }

    public void setSettlementAmt(String settlementAmt) {
        //GrossFunding will be set to 0 (Zero) if value is invalid
        String settlementAmtCleaned = null;
        BigDecimal settlementAmtBD = null;

        if (Util.stringEmpty(settlementAmt)) {
            this.setSettlementAmt(BigDecimal.ZERO);
        } else {
            try {
                settlementAmtCleaned = settlementAmt.replace(",", ".");
                settlementAmtBD = new BigDecimal(settlementAmtCleaned);
            } catch (Exception e) {
                settlementAmtBD = BigDecimal.ZERO;
            } finally {
                settlementAmtBD.setScale(6, BigDecimal.ROUND_HALF_UP);
                this.setSettlementAmt(settlementAmtBD);
            }
        }
    }
    /**
     * @return the settlementCcy
     */
    public String getSettlementCcy() {
        return settlementCcy;
    }
    /**
     * @param settlementccy the settlementCcy to set
     */
    public void setSettlementCcy(String settlementCcy) {
        this.settlementCcy = settlementCcy;
    }

}
