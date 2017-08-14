package com.skrill.viewpoint.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.skrill.viewpoint.jobs.entity.Setting;

public class MerchantReportsTest {

    String reportsOutputDir = "C:\\Users\\anetapetkova\\Documents\\ViewPoint\\VPReports\\ReportsChecks\\special";

    private String getReportDate(){

        java.util.Date today = new java.util.Date();
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");

        Calendar yesterdayCalendar = Calendar.getInstance();
        yesterdayCalendar.setTimeInMillis(today.getTime());
        yesterdayCalendar.add(Calendar.DAY_OF_YEAR, -1);

        String date = simple.format(new Date(yesterdayCalendar.getTimeInMillis()));

        return date;
    }

    //cheks if the amount is correct according the separator and then applies it
    private BigDecimal applySeparator(BigDecimal numericValue, String stringValue, int separator) throws SeparatorMismatchException{
        if(!numericValue.equals(BigDecimal.ZERO)){
            if(stringValue.length() < separator + 1){
                throw new SeparatorMismatchException();
            }
            else{
                BigDecimal finalValue = numericValue.divide(new BigDecimal(Math.pow(10, separator)).setScale(0, BigDecimal.ROUND_HALF_UP));
                return finalValue;
            }
        }
        return BigDecimal.ZERO;
    }

    private boolean checkMerchantReports(){

        DatabaseUtils dbUtil = new DatabaseUtils();
        Map<String, Boolean> keyMerchants = new HashMap<String, Boolean>();
        Setting keyMerchantIds = dbUtil.getSettingByKey("KEY_MERCHANTS");
        String keyMerchantIdsArray[] =  keyMerchantIds.getStringValue().split(",");
        for(String id : keyMerchantIdsArray){
            try{
                int merchantId = Integer.parseInt(id);
                keyMerchants.put(dbUtil.getMerchantName(merchantId), false);
            }
            catch(NumberFormatException nfe){
                nfe.printStackTrace();
            }
        }

        String date = getReportDate();
        File reportsDir = new File(reportsOutputDir); 
        File[] listOfFiles = reportsDir.listFiles();

        String sPattern = "^Merchant_Report_{1}(.+)(" + date + "{1})(.*)\\.csv{1}$";
        Pattern pattern = Pattern.compile(sPattern);
        Matcher matcher;

        int reportCounter = 0;

        for(File file : listOfFiles){
            matcher = pattern.matcher(file.getName());
            if (matcher.find()){
                reportCounter++;
                if (file.length() == 0){
                    System.out.println(file.getName() + " is empty!");
                }
                else {
                    for(String merchant : keyMerchants.keySet()){
                        if (file.getName().contains(merchant)){
                            keyMerchants.put(merchant, true);
                            try {
                                ArrayList<ReportTransaction> trns = parseMerchantReport(file, date);
                                checkParsedTransactions(trns, merchant);
                            } catch (SeparatorMismatchException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        if (reportCounter == 0){
            System.out.println("No files present!");
            return false;
        }
        else{
            if(keyMerchants.containsValue(false)){
                for (String merchant : keyMerchants.keySet()){
                    if(!keyMerchants.get(merchant)){
                        System.out.println(String.format("No file found for merchant %s!", merchant));
                    }
                }
            }
            System.out.println(reportCounter + " files were generated.");
            return true;
        }
    }

    private ReportTransaction parseReportLine(String line) throws SeparatorMismatchException{

        ReportTransaction trn = new ReportTransaction();
        String[] trnFields = line.split(";");
        trn.setSettlementStatus(trnFields[0]);
        trn.setPaymentType(trnFields[1]);
        trn.setPaymentMethod(trnFields[2]);
        trn.setTransactionID(trnFields[3]);
        trn.setUniqueID(trnFields[4]);
        trn.setShortID(trnFields[5]);
        trn.setChannelName(trnFields[6]);
        trn.setCustomerCountry(trnFields[7]);
        trn.setValuedate(trnFields[8]);
        trn.setRequestTimestamp(trnFields[9]);
        trn.setBrand(trnFields[10]);
        trn.setSeparatorTrn(Integer.parseInt(trnFields[11]));

        trn.setCreditTrnAmount(new BigDecimal(trnFields[12]));
        trn.setCreditTrnAmount(applySeparator(trn.getCreditTrnAmount(), trnFields[12], trn.getSeparatorTrn()));

        trn.setDebitTrnAmount(new BigDecimal(trnFields[13]));
        trn.setDebitTrnAmount(applySeparator(trn.getDebitTrnAmount(), trnFields[13], trn.getSeparatorTrn()));

        trn.setTrnCcy(trnFields[14]);
        trn.setSeparatorGrossFunding(Integer.parseInt(trnFields[15]));

        trn.setGrossFundingAmount(new BigDecimal(trnFields[16]));
        trn.setGrossFundingAmount(applySeparator(trn.getGrossFundingAmount(), trnFields[16], trn.getSeparatorGrossFunding()));

        trn.setSeparatorNetFunding(Integer.parseInt(trnFields[17]));

        trn.setNetFundingAmount(new BigDecimal(trnFields[18]));
        trn.setNetFundingAmount(applySeparator(trn.getNetFundingAmount(), trnFields[18], trn.getSeparatorNetFunding()));

        trn.setFundingCcy(trnFields[19]);
        trn.setSeparatorFees(Integer.parseInt(trnFields[20]));

        trn.setTotalFees(new BigDecimal(trnFields[21]));
        trn.setTotalFees(applySeparator(trn.getTotalFees(), trnFields[21], trn.getSeparatorFees()));

        trn.setFeeCcy(trnFields[22]);
        trn.setSettlementAccount(trnFields[23]);
        trn.setSeparatorAcqFx(Integer.parseInt(trnFields[24]));

        trn.setAcqFx(new BigDecimal(trnFields[25]));
        trn.setAcqFx(applySeparator(trn.getAcqFx(), trnFields[25], trn.getSeparatorAcqFx()));

        trn.setSeparatorSettlFx(Integer.parseInt(trnFields[26]));

        trn.setSettlementFx(new BigDecimal(trnFields[27]));
        trn.setSettlementFx(applySeparator(trn.getSettlementFx(), trnFields[27], trn.getSeparatorSettlFx()));

        trn.setSeparatorSettlAmt(Integer.parseInt(trnFields[28]));

        try{
            trn.setSettlementAmt(new BigDecimal(trnFields[29]));
            trn.setSettlementAmt(applySeparator(trn.getSettlementAmt(), trnFields[29], trn.getSeparatorSettlAmt()));

            trn.setSettlementCcy(trnFields[30]);
        }
        catch(ArrayIndexOutOfBoundsException e){
            if (!trn.getPaymentType().equals("RG") && !trn.getPaymentType().equals("PA")){
                System.out.println("Missing Settlement Data for transaction with shortid " + trn.getShortID());
            }
        }

        return trn;
    }

    private ArrayList<ReportTransaction> parseMerchantReport(File file, String fileDate) throws SeparatorMismatchException{

        ArrayList<ReportTransaction> trns = new ArrayList<ReportTransaction>();

        int linesNumber = 0;
        String line = null;
        String firstLine = null;
        String secondLine = null;

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
            //process first four lines of the report
            firstLine = br.readLine();
            secondLine = br.readLine();
            br.readLine();
            br.readLine();

            // Deal with all other lines
            while ((line = br.readLine()) != null) {
                try{
                    trns.add(parseReportLine(line));
                    linesNumber++;
                }
                catch(SeparatorMismatchException ex){
                    System.out.println(String.format("Separator issue in file %s, line %d", file.getName(), linesNumber));
                    throw ex;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + file.getName() + " not found. Skipping...");
            return trns;
        } catch (IOException e) {
            System.out.println("Error: Cant read from file " + file.getName());
            e.printStackTrace();
            return trns;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        if (!firstLine.equals("FileTimestamp;Rows")){
            System.out.println("Error in line 1 of file " + file.getName());
        }
        if (!secondLine.equals(fileDate + ";" + linesNumber)){
            System.out.println("Error in line 2 of file " + file.getName());
        }

        return trns;
    }

    private void checkParsedTransactions(ArrayList<ReportTransaction> trns, String merchantName){

        for(ReportTransaction trn : trns){

            //transaction amount * Acquiring FX rate
            BigDecimal fxTrnAmt;
            BigDecimal scaledDownFxTrnAmt;
            BigDecimal scaledUpFxTrnAmt;

            if(!trn.getDebitTrnAmount().equals(BigDecimal.ZERO)){
                //For DKK and JPY MONEYBOOKERS only, Acquiring Gross amount = transaction amount / Acquiring FX rate 
                if(trn.getTrnCcy().equals("DKK") || ("JPY".equals(trn.getTrnCcy()) && "MONEYBOOKERS".equals(trn.getBrand()))){
                    fxTrnAmt = trn.getDebitTrnAmount().divide(trn.getAcqFx(), trn.getSeparatorGrossFunding() + 1, BigDecimal.ROUND_DOWN);
                }
                else{
                    fxTrnAmt = trn.getDebitTrnAmount().multiply(trn.getAcqFx()).setScale(trn.getSeparatorGrossFunding() + 1, BigDecimal.ROUND_DOWN);
                }
                scaledDownFxTrnAmt = fxTrnAmt.setScale(trn.getSeparatorGrossFunding(), BigDecimal.ROUND_HALF_DOWN);
                scaledUpFxTrnAmt = fxTrnAmt.setScale(trn.getSeparatorGrossFunding(), BigDecimal.ROUND_HALF_UP);
            }
            else{
                if(trn.getTrnCcy().equals("DKK")){
                    fxTrnAmt = trn.getCreditTrnAmount().divide(trn.getAcqFx(), trn.getSeparatorGrossFunding() + 1, BigDecimal.ROUND_DOWN).negate();
                }
                else{
                    fxTrnAmt = trn.getCreditTrnAmount().multiply(trn.getAcqFx()).negate().setScale(trn.getSeparatorGrossFunding() + 1, BigDecimal.ROUND_DOWN);
                }
                scaledDownFxTrnAmt = fxTrnAmt.setScale(trn.getSeparatorGrossFunding(), BigDecimal.ROUND_HALF_DOWN);
                scaledUpFxTrnAmt = fxTrnAmt.setScale(trn.getSeparatorGrossFunding(), BigDecimal.ROUND_HALF_UP);
            }

            //Acquiring Gross Amount * Settlement FX rate
            BigDecimal fxGrossAmt = trn.getGrossFundingAmount().multiply(trn.getSettlementFx()).setScale(trn.getSeparatorSettlAmt(), BigDecimal.ROUND_HALF_UP);

            if(trn.getTrnCcy().equals(trn.getFundingCcy())){
                int amountsEqual = trn.getGrossFundingAmount().compareTo(fxTrnAmt);
                if(amountsEqual != 0){
                    //For JPY only the part before the decimal separator matters
                    if( ! "JPY".equals(trn.getFundingCcy()) || trn.getGrossFundingAmount().intValue() != fxTrnAmt.intValue()){
                        System.out.println("Amount missmatch in file for merchant " + merchantName + ", shortid = " + trn.getShortID());
                        System.out.println("getGrossFundingAmount" + trn.getGrossFundingAmount());
                        System.out.println("fxTrnAmt " + fxTrnAmt);
                        System.out.println("trn.getSettlementAmt() " + trn.getSettlementAmt());
                        System.out.println("fxGrossAmt " + fxGrossAmt);
                        return;
                    }
                }
            }else {
                if((trn.getGrossFundingAmount().compareTo(scaledDownFxTrnAmt) != 0) && (trn.getGrossFundingAmount().compareTo(scaledUpFxTrnAmt) != 0) && (!trn.getPaymentType().equals("CB"))){
                    System.out.println(trn.getGrossFundingAmount() + "; scaledDown:" + scaledDownFxTrnAmt + ", scaledUp:" + scaledUpFxTrnAmt);
                    System.out.println("GrossFunding Amount missmatch in file for merchant " + merchantName + ", shortid = " + trn.getShortID());
                }
            }
            try{
                if (trn.getSettlementAmt().compareTo(fxGrossAmt) != 0){
                    System.out.println("Settlement Amount missmatch in file for merchant " + merchantName + ", shortid = " + trn.getShortID());
                }
            }catch(NullPointerException e){
                if (!trn.getPaymentType().equals("RG") && !trn.getPaymentType().equals("PA")){
                    System.out.println("Missing Settlement Data in file for merchant " + merchantName + ", shortid " + trn.getShortID());
                }
            }
            if ((trn.getGrossFundingAmount().subtract(trn.getTotalFees())).compareTo(trn.getNetFundingAmount()) != 0){
                System.out.println("Gross/Net Funding Amount missmatch in file for merchant " + merchantName + ", shortid = " + trn.getShortID());
                return;
            }
          //Check VA MONEYBOOKERS transactions valuedate
            if("MONEYBOOKERS".equals(trn.getBrand())){
                SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
                Calendar reportCalendar = Calendar.getInstance();
                reportCalendar.add(Calendar.DAY_OF_YEAR, -2);
                String reportDate = simple.format(new Date(reportCalendar.getTimeInMillis()));
                if( ! reportDate.equals(trn.getValuedate())){
                    System.out.println("Wrong valuedate in file for merchant " + merchantName + ", shortid = " + trn.getShortID());
                }
            }
        }
    }



    public static void main(String[] args) {

                ReportsGenerator rg = new ReportsGenerator();
                rg.startJob(args);
        //        Calendar todayCalendar = Calendar.getInstance();            
        //        if(todayCalendar.get(Calendar.DAY_OF_MONTH) == 20)
        //        {
        //            java.util.Date today = new java.util.Date();
        //            Calendar startCalendar = Calendar.getInstance();
        //            startCalendar.setTimeInMillis(today.getTime());
        //            startCalendar.add(Calendar.MONTH, -1);
        //            startCalendar.set(Calendar.DAY_OF_MONTH, 1);
        //            java.util.Date startDate = new Date(startCalendar.getTimeInMillis());
        //            
        //            System.out.println(startDate.toString());
        //            
        //            Calendar endCalendar = Calendar.getInstance();
        //            endCalendar.setTimeInMillis(today.getTime());
        //            endCalendar.set(Calendar.DAY_OF_MONTH, 1);
        //            java.util.Date endDate = new Date(endCalendar.getTimeInMillis());
        //            System.out.println(endDate.toString());
        //            
        //       }
//        MerchantReportsTest test = new MerchantReportsTest();
//        test.checkMerchantReports();

    }
}
