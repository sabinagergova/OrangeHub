// marcels transactions class

package com.skrill.viewpoint.jobs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MBTransaction implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Map<String, Object> data;

    public MBTransaction() {
        data = new HashMap<String, Object>();
    }

    public void setId(Integer Id) {
        data.put("ID", Id);
    }

    public Integer getId() {
        return (Integer) data.get("ID");
    }

    public void setSlipId(Integer slipId) {
        data.put("SLIP_ID", slipId);
    }

    public Integer getSlipId() {
        return (Integer) data.get("SLIP_ID");
    }

    public void setCustId(Integer custId) {
        data.put("CUST_ID", custId);
    }

    public Integer getCustId() {
        return (Integer) data.get("CUST_ID");
    }

    public void setQHidden(Boolean qHidden) {
        data.put("Q_HIDDEN", qHidden);
    }

    public Boolean getQHidden() {
        return (Boolean) data.get("Q_HIDDEN");
    }

    public void setStatus(Integer status) {
        data.put("STATUS", status);
    }

    public Integer getStatus() {
        return (Integer) data.get("STATUS");
    }

    public void setType(String type) {
        data.put("TYPE", type);
    }

    public String getType() {
        return (String) data.get("TYPE");
    }

    public void setInsTime(Date insTime) {
        data.put("INS_TIME", insTime);
    }

    public Date getInsTime() {
        return (Date) data.get("INS_TIME");
    }

    public void setCustAmount(BigDecimal custAmount) {
        data.put("CUST_AMOUNT", custAmount);
    }

    public BigDecimal getCustAmount() {
        return (BigDecimal) data.get("CUST_AMOUNT");
    }

    public void setCustCurrency(String custCurrency) {
        data.put("CUST_CUR", custCurrency);
    }

    public String getCustCurrency() {
        return (String) data.get("CUST_CUR");
    }

    public void setCustFxRate(BigDecimal custFxRate) {
        data.put("CUST_FXRATE", custFxRate);
    }

    public BigDecimal getCustFxRate() {
        return (BigDecimal) data.get("CUST_FXRATE");
    }

    public void setMBAmount(BigDecimal mbAmount) {
        data.put("MB_AMOUNT", mbAmount);
    }

    public BigDecimal getMBAmount() {
        return (BigDecimal) data.get("MB_AMOUNT");
    }

    public void setMBCurrency(String mbCurrency) {
        data.put("MB_CUR", mbCurrency);
    }

    public String getMBCurrency() {
        return (String) data.get("MB_CUR");
    }

    public void setMBFxRate(BigDecimal custFxRate) {
        data.put("MB_FXRATE", custFxRate);
    }

    public BigDecimal getMBFxRate() {
        return (BigDecimal) data.get("MB_FXRATE");
    }

    public void setProvider(BigDecimal provider) {
        data.put("PROVIDER", provider);
    }

    public BigDecimal getProvider() {
        return (BigDecimal) data.get("PROVIDER");
    }

    public void setProviderName(String name) {
        data.put("NAME", name);
    }

    public String getProviderName() {
        return (String) data.get("NAME");
    }

    public void setProcessTime(Date processTime) {
        data.put("PROCESS_TIME", processTime);
    }

    public Date getProcessTime() {
        return (Date) data.get("PROCESS_TIME");
    }

    public void setBalance(BigDecimal balance) {
        data.put("BALANCE", balance);
    }

    public BigDecimal getBalance() {
        return (BigDecimal) data.get("BALANCE");
    }

    public void setAdjAmount(BigDecimal adjAmount) {
        data.put("ADJ_AMOUNT", adjAmount);
    }

    public BigDecimal getAdjAMount() {
        return (BigDecimal) data.get("ADJ_AMOUNT");
    }

    public void setParentId(Integer parentId) {
        data.put("PARENT_ID", parentId);
    }

    public Integer getParentId() {
        return (Integer) data.get("PARENT_ID");
    }

    public void setDirection(String direction) {
        data.put("DIRECTION", direction);
    }

    public String getDirection() {
        return (String) data.get("DIRECTION");
    }

    public void setMerchantEMail(String merchantEMail) {
        data.put("MERCHANT_EMAIL", merchantEMail);
    }

    public String getMerchantEMail() {
        return (String) data.get("MERCHANT_EMAIL");
    }

    public static MBTransaction fromString(String line) {
        MBTransaction result = new MBTransaction();

        if (line == null || line.length() == 0) {
            return null;
        }
        String[] fields = line.replace(":00\",", "00\",").replace("\"", "").split(",");

        // 2012-10-23 17:36:26+02:00
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DecimalFormat df = new DecimalFormat("#.#");
        df.setParseBigDecimal(true);
        for (int i = 0; i < fields.length; i++) {
            switch (i) {
            case 0:
                try {
                    result.setId(Integer.parseInt(fields[i]));
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Error while parsing ID. " + fields[i] + " is not valid integer.");
                    nfe.printStackTrace();
                    break;
                }
            case 1:
                try {
                    result.setSlipId(Integer.parseInt(fields[i]));
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Error while parsing slipId. " + fields[i] + " is not valid integer.");
                    nfe.printStackTrace();
                    break;
                }
            case 2:
                result.setDirection(fields[i]);
                break;
            case 3:
                try {
                    if (fields[i] != null && fields[i].length() > 0) {
                        result.setParentId(Integer.parseInt(fields[i]));
                    } else {
                        result.setParentId(null);
                    }
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Error while parsing parentId. " + fields[i] + " is not valid integer.");
                    nfe.printStackTrace();
                    break;
                }
            case 4:
                try {
                    result.setCustId(Integer.parseInt(fields[i]));
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Error while parsing custId. " + fields[i] + " is not valid integer.");
                    nfe.printStackTrace();
                    break;
                }
            case 5:
                try {
                    if ("1".equals(fields[i])) {
                        result.setQHidden(true);
                    } else if ("0".equals(fields[i])) {
                        result.setQHidden(false);
                    } else {
                        System.out.println("Wrong value for q_hidden - " + fields[i] + ". Value of 1 or 0 is expected.");
                    }
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Error while parsing custId. " + fields[i] + " is not valid integer.");
                    nfe.printStackTrace();
                    break;
                }
            case 6:
                try {
                    result.setStatus(Integer.parseInt(fields[i]));
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Error while parsing status. " + fields[i] + " is not valid integer.");
                    nfe.printStackTrace();
                    break;
                }
            case 7:
                result.setType(fields[i]);
                break;
            case 8:
                try {
                    result.setInsTime(sdf.parse(fields[i]));
                    break;
                } catch (ParseException pe) {
                    System.out.println("Error while parsing INS_DATE. " + fields[i] + " is not valid date.");
                    pe.printStackTrace();
                    break;
                }
            case 9:
                try {
                    result.setCustAmount((BigDecimal) df.parse(fields[i], new ParsePosition(0)));
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Error while parsing cust_amount. " + fields[i] + " is not valid number.");
                    nfe.printStackTrace();
                    break;
                }
            case 10:
                result.setCustCurrency(fields[i]);
                break;
            case 11:
                try {
                    result.setCustFxRate((BigDecimal) df.parse(fields[i], new ParsePosition(0)));
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Error while parsing cust_fx_rate. " + fields[i] + " is not valid number.");
                    nfe.printStackTrace();
                    break;
                }
            case 12:
                try {
                    result.setMBAmount((BigDecimal) df.parse(fields[i], new ParsePosition(0)));
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Error while parsing mb_amount. " + fields[i] + " is not valid number.");
                    nfe.printStackTrace();
                    break;
                }
            case 13:
                result.setMBCurrency(fields[i]);
                break;
            case 14:
                try {
                    result.setMBFxRate((BigDecimal) df.parse(fields[i], new ParsePosition(0)));
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Error while parsing mb_fx_rate. " + fields[i] + " is not valid number.");
                    nfe.printStackTrace();
                    break;
                }
            case 15:
                try {
                    result.setProvider((BigDecimal) df.parse(fields[i], new ParsePosition(0)));
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Error while parsing provider. " + fields[i] + " is not valid number.");
                    nfe.printStackTrace();
                    break;
                }
            case 16:
                try {
                    if (fields[i].length() > 5) {
                        result.setProcessTime(sdf.parse(fields[i]));
                    } else {
                        result.setProcessTime(null);
                    }
                    break;
                } catch (ParseException pe) {
                    System.out.println("Error while parsing process_date. " + fields[i] + " is not valid date.");
                    pe.printStackTrace();
                    break;
                }
            case 17:
                try {
                    result.setBalance((BigDecimal) df.parse(fields[i], new ParsePosition(0)));
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Error while parsing balance. " + fields[i] + " is not valid number.");
                    nfe.printStackTrace();
                    break;
                }
            case 18:
                try {
                    result.setAdjAmount((BigDecimal) df.parse(fields[i], new ParsePosition(0)));
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Error while parsing adj_amount. " + fields[i] + " is not valid number.");
                    nfe.printStackTrace();
                    break;
                }
            }
        }

        return result;
    }

}
