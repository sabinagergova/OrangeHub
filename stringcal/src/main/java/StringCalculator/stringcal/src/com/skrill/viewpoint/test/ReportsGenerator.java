package com.skrill.viewpoint.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

//import org.jboss.resteasy.client.ClientRequest;

import com.skrill.viewpoint.jobs.entity.VIewpointTransaction;


public class ReportsGenerator extends ViewpointJob{


    private DatabaseUtils dbUtil = null;


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd%20HH:mm");


    private void init() {
        // initialize database connection
        try {
            conn = Util.getConnection();
            dbUtil = new DatabaseUtils(conn);


        } catch (SQLException ex) {
            logger("Cannot obtain db connection!");
        }
    }

    @Override
    public void startJob(String[] args) {
        try {
            init();

            generateMerchantReports();
        } catch (Exception vie) {
            vie.printStackTrace();
            return;
        }

    }

    private void generateMerchantReports() {
//                    List<VIewpointTransaction> txList = dbUtil.getReconcileMerchantFile();
//                    writeMerchantOutput(txList, "C:\\Users\\anetapetkova\\Documents\\ViewPoint\\VPReports\\");
        //            dbUtil.markTransactionsAsProcessed(true);
        //            dbUtil.markTransactionsAsProcessed(false);

        //            Calendar todayCalendar = Calendar.getInstance();            
        //            if(todayCalendar.get(Calendar.DAY_OF_MONTH) == 10)
        //            {
        java.util.Date today = new java.util.Date();
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTimeInMillis(today.getTime());
        startCalendar.add(Calendar.MONTH, -1);
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);
        java.util.Date startDate = new Date(startCalendar.getTimeInMillis());
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTimeInMillis(today.getTime());
        endCalendar.set(Calendar.DAY_OF_MONTH, 1);
        java.util.Date endDate = new Date(endCalendar.getTimeInMillis());
        List<VIewpointTransaction> txListAmex = dbUtil.getAmexMerchantFile(startDate, endDate);
        writeMerchantOutputAMEX(txListAmex, "C:\\Users\\anetapetkova\\Documents\\ViewPoint\\AMEX\\", startDate, endDate);
        //            }
        //                writeMerchantOutputAMEX(txListAmex, "C:\\Users\\anetapetkova\\Documents\\ViewPoint\\AMEX\\", startDate, endDate);
        //}
    }


    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void writeMerchantOutputAMEX(List<VIewpointTransaction> txList, String filepath, Date start, Date end) {
        Map<String, List<VIewpointTransaction>> map = new HashMap<String, List<VIewpointTransaction>>();
        for (int i = 0; i < txList.size(); i++) {
            boolean existing = false;

            VIewpointTransaction trn = (VIewpointTransaction) txList.get(i);
            String company = (String) trn.get("companyName");
            if (map.size() == 0) {
                List<VIewpointTransaction> tempResult = new ArrayList<VIewpointTransaction>();
                tempResult.add(trn);
                map.put(company, tempResult);
            } else {
                if (map.containsKey(company)){
                    List<VIewpointTransaction> tempResult = map.get(company);
                    tempResult.add(trn);
                    existing = true;
                }
                if (!existing) {
                    List<VIewpointTransaction> tempResult = new ArrayList<VIewpointTransaction>();
                    tempResult.add(trn);
                    map.put(company, tempResult);
                }
            }
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Set s = map.entrySet();
        Iterator it = s.iterator();
        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            String key = (String) m.getKey();

            String startDate = dateFormat.format(start.getTime());
            String endDate = dateFormat.format(end.getTime());

            String extension = ".csv";
            StringBuffer filename = new StringBuffer("Merchant_Report_");
            filename.append(key);
            filename.append("_");
            filename.append(startDate);
            filename.append("_AMEX");
            int counter = dbUtil.getFileCounter(filename.toString());
            counter++;
            filename.append("_");
            filename.append(counter);
            filename.append(extension);
            List<VIewpointTransaction> l = (List<VIewpointTransaction>) m.getValue();

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filepath + filename.toString()));
                String lineSeparator = System.getProperty("line.separator");

                // Header
                writer.write("FileTimestamp;Rows");
                writer.write(lineSeparator);
                writer.write(endDate.toString() + ";" + l.size());
                writer.write(lineSeparator);
                writer.write(lineSeparator);

                // Output
                writer.write("SettlementStatus;PaymentType;PaymentMethod;TransactionID;UniqueID;ShortID;ChannelName;CustomerCountry;Valuedate;RequestTimestamp;Brand;Separator;Credit Trn Amount;Debit Trn Amount;Trn Ccy;Separator;Gross Funding Amount;Separator;");
                writer.write("Net Funding Amount;Funding Ccy;Separator;Total Fees;Fee Ccy;Settlement Account;Separator;Acq Fx;Separator;Settlement Fx;Separator;Settlement Amt;Settlement ccy");
                writer.write(lineSeparator);
                for (int x = 0; x < l.size(); x++) {
                    VIewpointTransaction stx = (VIewpointTransaction) l.get(x);

                    writer.write(stx.getResult());
                    writer.write(";");
                    writer.write(stx.getType());
                    writer.write(";");
                    writer.write(stx.getMethod());
                    writer.write(";");
                    writer.write(stx.getTransactionId());
                    writer.write(";");
                    writer.write(stx.getUniqueId());
                    writer.write(";");
                    writer.write(stx.getShortId());
                    writer.write(";");
                    writer.write((String) stx.get("channelName"));
                    writer.write(";");
                    if (stx.getCustomerCountry() != null) {
                        writer.write(stx.getCustomerCountry());
                    }
                    writer.write(";");
                    writer.write(dateFormat.format(stx.getProcessTimestamp()));
                    writer.write(";");
                    writer.write(timestampFormat.format(stx.getProcessTimestamp()));
                    writer.write(";");
                    writer.write(stx.getBrand());
                    writer.write(";");

                    // Comma Separator
                    writer.write("2;");

                    String amount = Long.toString(stx.getAmount().movePointRight(2).setScale(0, RoundingMode.HALF_UP).longValue());
                    if (stx.getType().equals("RF") || stx.getType().equals("CB") || stx.getType().equals("CD")) {
                        writer.write(padNumber(amount, 3, '0'));
                        writer.write(";0");
                        writer.write(";");
                    } else {
                        writer.write("0;");
                        writer.write(padNumber(amount, 3, '0'));
                        writer.write(";");
                    }

                    writer.write(stx.getCurrency());
                    writer.write(";");

                    //gross funding amount
                    writer.write("2;");
                    writer.write("");
                    writer.write(";");

                    // net funding amount
                    writer.write("4;");
                    writer.write("");
                    writer.write(";");

                    writer.write("");
                    writer.write(";");

                    writer.write("4;");

                    // total fees
                    writer.write("");
                    writer.write(";");

                    writer.write("");
                    writer.write(";");
                    writer.write("");
                    writer.write(";");

                    writer.write("6;");
                    writer.write("");
                    writer.write(";6;");
                    writer.write("");
                    writer.write(";2;");
                    writer.write("");
                    writer.write(";");
                    writer.write("");
                    writer.write(lineSeparator);
                }
                writer.flush();
                writer.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void writeMerchantOutput(List<VIewpointTransaction> txList, String filepath) {
        Map<String, List<VIewpointTransaction>> map = new HashMap<String, List<VIewpointTransaction>>();
        for (int i = 0; i < txList.size(); i++) {
            boolean vorhanden = false;

            VIewpointTransaction trn = (VIewpointTransaction) txList.get(i);
            String company = (String) trn.get("companyName");

            if (map.size() == 0) {
                List<VIewpointTransaction> tempResult = new ArrayList<VIewpointTransaction>();
                tempResult.add(trn);
                map.put(company, tempResult);
            } else {
                Set set = map.entrySet();
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    Map.Entry m = (Map.Entry) it.next();
                    String key = (String) m.getKey();
                    if (key.equals(company)) {
                        List<VIewpointTransaction> tempResult = (List<VIewpointTransaction>) m.getValue();
                        tempResult.add(trn);
                        vorhanden = true;
                    }
                }
                if (!vorhanden) {
                    List<VIewpointTransaction> tempResult = new ArrayList<VIewpointTransaction>();
                    tempResult.add(trn);
                    map.put(company, tempResult);
                }
            }
        }

        Set s = map.entrySet();
        Iterator it = s.iterator();
        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            String key = (String) m.getKey();
            java.util.Date today = new java.util.Date();
            SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");

            Calendar yesterdayCalendar = Calendar.getInstance();
            yesterdayCalendar.setTimeInMillis(today.getTime());
            yesterdayCalendar.add(Calendar.DAY_OF_YEAR, -1);

            StringBuffer date = new StringBuffer();
            date.append(simple.format(new Date(yesterdayCalendar.getTimeInMillis())));

            String extension = ".csv";
            StringBuffer filename = new StringBuffer("Merchant_Report_");
            filename.append(key);
            filename.append("_");
            filename.append(date.toString());
            int counter = dbUtil.getFileCounter(filename.toString());
            counter++;
            filename.append("_");
            filename.append(counter);
            filename.append(extension);

            List<VIewpointTransaction> l = (List<VIewpointTransaction>) m.getValue();
            int company_fk = 0;

            SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filepath + filename.toString()));

                String mbDbTimezone = "Europe/Berlin";
                String vpDbTimezone = "Europe/London";

                // Header
                writer.write("FileTimestamp;Rows");
                writer.write(System.getProperty("line.separator"));
                writer.write(date.toString() + ";" + l.size());
                writer.write(System.getProperty("line.separator"));
                writer.write(System.getProperty("line.separator"));

                // Output
                writer.write("SettlementStatus;PaymentType;PaymentMethod;TransactionID;UniqueID;ShortID;ChannelName;CustomerCountry;Valuedate;RequestTimestamp;Brand;Separator;Credit Trn Amount;Debit Trn Amount;Trn Ccy;Separator;Gross Funding Amount;Separator;");
                writer.write("Net Funding Amount;Funding Ccy;Separator;Total Fees;Fee Ccy;Settlement Account;Separator;Acq Fx;Separator;Settlement Fx;Separator;Settlement Amt;Settlement ccy");
                writer.write(System.getProperty("line.separator"));
                for (int x = 0; x < l.size(); x++) {
                    VIewpointTransaction stx = (VIewpointTransaction) l.get(x);

                    int timeOffset = TimeZone.getTimeZone(mbDbTimezone).getOffset(stx.getProcessTimestamp().getTime()) - TimeZone.getTimeZone(vpDbTimezone).getOffset(stx.getProcessTimestamp().getTime());

                    String mb = "";
                    if (stx.getMBPaymentMethod() != null)
                        mb = stx.getMBPaymentMethod();

                    writer.write(stx.getResult());
                    writer.write(";");
                    writer.write(stx.getType());
                    writer.write(";");
                    writer.write(stx.getMethod());
                    writer.write(";");
                    writer.write(stx.getTransactionId());
                    writer.write(";");
                    writer.write(stx.getUniqueId());
                    writer.write(";");
                    writer.write(stx.getShortId());
                    writer.write(";");
                    writer.write((String) stx.get("channelName"));
                    writer.write(";");
                    if (stx.getCustomerCountry() != null) {
                        writer.write(stx.getCustomerCountry());
                    }
                    writer.write(";");
                    if ("VA".equals(stx.getMethod())) {
                        writer.write(dateFormat.format(new Date(stx.getProcessTimestamp().getTime() + timeOffset)));
                    } else {
                        writer.write(dateFormat.format(stx.getProcessTimestamp()));
                    }
                    writer.write(";");
                    if ("VA".equals(stx.getMethod())) {
                        writer.write(timestampFormat.format(new Date(stx.getProcessTimestamp().getTime() + timeOffset)));
                    } else {
                        writer.write(timestampFormat.format(stx.getProcessTimestamp()));
                    }
                    writer.write(";");
                    writer.write(stx.getBrand());
                    writer.write(";");

                    company_fk = stx.getFkCompany();

                    // Comma Separator
                    writer.write("2;");

                    String amount = Long.toString(stx.getAmount().movePointRight(2).setScale(0, RoundingMode.HALF_UP).longValue());
                    if (stx.getType().equals("RF") || stx.getType().equals("CB") || stx.getType().equals("CD")) {
                        writer.write(padNumber(amount, 3, '0'));
                        writer.write(";0");
                        writer.write(";");
                    } else {
                        writer.write("0;");
                        writer.write(padNumber(amount, 3, '0'));
                        writer.write(";");
                    }

                    writer.write(stx.getCurrency());
                    writer.write(";");

                    BigDecimal fee = stx.getRmf().add(stx.getPtf());

                    writer.write("2;");
                    String acqGrossAmount = Long.toString(stx.getAcqGrossAmount().movePointRight(2).longValue());
                    writer.write(padNumber(acqGrossAmount, 3, '0'));
                    writer.write(";");

                    // net funding amount
                    writer.write("4;");
                    String netFundingAmount = Long.toString(stx.getAcqGrossAmount().subtract(fee).movePointRight(4).longValue());
                    writer.write(padNumber(netFundingAmount, 5, '0'));
                    writer.write(";");

                    writer.write(stx.getAcqCurrency());
                    writer.write(";");

                    writer.write("4;");

                    // total fees
                    String totalFees = Long.toString(fee.movePointRight(4).longValue());
                    writer.write(padNumber(totalFees, 5, '0'));
                    writer.write(";");

                    writer.write(stx.getAcqCurrency());
                    writer.write(";");
                    writer.write(stx.getMBAccount());
                    writer.write(";");

                    writer.write("6;");
                    try {
                        String acqFxRate = Long.toString(stx.getAcqFxRate().movePointRight(6).longValue());
                        writer.write(padNumber(acqFxRate, 7, '0'));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    writer.write(";6;");
                    if (stx.getSettlementFxRate() != null) {
                        String settlementFxRate = Long.toString(stx.getSettlementFxRate().movePointRight(6).longValue());
                        writer.write(padNumber(settlementFxRate, 7, '0'));
                    } else {
                        writer.write("");
                    }
                    writer.write(";2;");
                    if (stx.getSettlementAmount() != null) {
                        String settlementAmount = Long.toString(stx.getSettlementAmount().movePointRight(2).longValue());
                        writer.write(padNumber(settlementAmount, 3, '0'));
                    } else {
                        writer.write("");
                    }
                    writer.write(";");
                    if (stx.getSettlementCurrency() != null) {
                        writer.write(stx.getSettlementCurrency());
                    } else {
                        writer.write("");
                    }

                    writer.write(System.getProperty("line.separator"));
                }
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String padNumber(String number, int minLength, char padWith) {
        if (number != null) {
            boolean isNegative = false;
            if (number.startsWith("-")) {
                isNegative = true;
                number = number.substring(1);
            }

            if (number.length() >= minLength) {
                if (isNegative) {
                    return "-" + number;
                }
                return number;
            } else {
                StringBuffer returnBuf = new StringBuffer(number);
                while (returnBuf.length() < minLength) {
                    returnBuf.insert(0, padWith);
                }

                if (isNegative) {
                    returnBuf.insert(0, "-");
                }

                return returnBuf.toString();
            }
        }

        return null;
    }

}
