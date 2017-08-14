package com.skrill.viewpoint.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.skrill.viewpoint.jobs.entity.Setting;
import com.skrill.viewpoint.jobs.entity.VIewpointTransaction;

public class DatabaseUtils {

    // ps = connection.prepareStatement("update transactions set result=?, ptf=?, rmf=?, fxrate=?, fx_ptf=?, fx_rmf=?, acqfkiso4217=?, settlement_fx_rate=?, acqgrossamt=?, " +
    // "settlement_gross_amnt=?, settlement_currency=?, acquirer_fx_rate=?, mb_account_id=?, acqcost=0, fx_acqcost=0, reconcile=1, settled=5 where pk = ?;");
    static final String SQL_AMEX_MECHANT_FILE = "select t.acquirer_fx_rate, t.settlement_currency, t.settlement_gross_amnt, t.settlement_fx_rate, "
            + "t.result,t.type,t.method,t.transactionid,t.uniqueid,t.shortid,ch.channel_name,customer_fkcountry,t.valuedate,account_holder,t.account_fkbrand,t.amount,t.fkiso4217, "
            + "t.acqgrossamt,t.ptf,t.rmf,t.acqfkiso4217,c.company_name as company_name,t.processtimestamp, t.fkcompany, t.mb_account_id,t.mbpaymentmethod,t.reconcile_account_number "
            + "from transactions t "
            + "inner join company c on c.companyid=t.fkcompany "
            + "left outer join channel ch on ch.channelid=t.channelid "
            + "where result='ACK' and account_fkbrand = 'AMEX' and fkiso4217 <> 'XXX' "
            + "and valuedate >= ?  and valuedate < ? order by t.processtimestamp asc; ";

    static final String SQL_OT_MECHANT_FILE = "select t.acquirer_fx_rate, t.settlement_currency, t.settlement_gross_amnt, t.settlement_fx_rate, "
            + "t.result,t.type,t.method,t.transactionid,t.uniqueid,t.shortid,ch.channel_name,customer_fkcountry,t.valuedate,account_holder,t.account_fkbrand,t.amount,t.fkiso4217, "
            + "t.acqgrossamt,t.ptf,t.rmf,t.acqfkiso4217,c.company_name as company_name,t.processtimestamp, t.fkcompany, t.mb_account_id,t.mbpaymentmethod,t.reconcile_account_number "
            + "from transactions t "
            + "inner join company c on c.companyid=t.fkcompany "
            + "left outer join channel ch on ch.channelid=t.channelid "
            + "where result='ACK' and method = 'OT' and fkiso4217 <> 'XXX' "
            + "and valuedate >= ?  and valuedate < ? order by t.processtimestamp asc; ";

    static final String SQL_RECONCILE_MECHANT_FILE = "select t.acquirer_fx_rate, t.settlement_currency, t.settlement_gross_amnt, t.settlement_fx_rate, "
            + "t.result,t.type,t.method,t.transactionid,t.uniqueid,t.shortid,ch.channel_name,customer_fkcountry,t.valuedate,account_holder,t.account_fkbrand,t.amount,t.fkiso4217, "
            + "t.acqgrossamt,t.ptf,t.rmf,t.acqfkiso4217,c.company_name as company_name,t.processtimestamp, t.fkcompany, t.mb_account_id,t.mbpaymentmethod,t.reconcile_account_number "
            + "from transactions t "
            + "inner join company c on c.companyid=t.fkcompany "
            + "left outer join channel ch on ch.channelid=t.channelid "
            + "where t.fkcompany = 49 and result='ACK' and account_fkbrand = 'MONEYBOOKERS' and fkiso4217 <> 'XXX' "
            + "and valuedate = to_date('2013-03-08', 'yyyy-mm-dd') order by t.processtimestamp asc; ";

    static final String SQL_ON_DEMAND_FILE = "select t.acquirer_fx_rate, t.settlement_currency, t.settlement_gross_amnt, t.settlement_fx_rate, "
            + "t.result,t.type,t.method,t.transactionid,t.uniqueid,t.shortid,ch.channel_name,customer_fkcountry,t.valuedate,account_holder,t.account_fkbrand,t.amount,t.fkiso4217, "
            + "t.acqgrossamt,t.ptf,t.rmf,t.acqfkiso4217,c.company_name as company_name,t.processtimestamp, t.fkcompany, t.mb_account_id,t.mbpaymentmethod,t.reconcile_account_number "
            + "from transactions t "
            + "inner join company c on c.companyid=t.fkcompany "
            + "left outer join channel ch on ch.channelid=t.channelid "
            + "where t.fkcompany = 161 and result='ACK' and fkiso4217 <> 'XXX' and mb_account_id is not null and mb_account_id <> ''"
            + "and valuedate >= to_date('2012-12-01', 'yyyy-mm-dd') and valuedate <= to_date('2013-03-31', 'yyyy-mm-dd') "
            + "order by t.processtimestamp asc; ";

    static final String SQL_GET_COMPANY_NAME_BY_ID = "SELECT company_name from company where companyid = ?;";

    private Connection connection;

    public DatabaseUtils() {
        try {
            connection = Util.getConnection();
        } catch (SQLException sqle) {
            System.out.println("Cannot obtain db connection!");
            sqle.printStackTrace();
        }
    }

    public DatabaseUtils(Connection conn) {
        this.connection = conn;
    }

    private void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("Error closing statement!");
                ex.printStackTrace();
                ex.printStackTrace();
            }
        }
    }

    private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Error closing result set!");
                ex.printStackTrace();
                ex.printStackTrace();
            }
        }
    }

    public String getMerchantName(int companyId){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String name = null;
        try {
            ps = connection.prepareStatement(SQL_GET_COMPANY_NAME_BY_ID);
            ps.setInt(1, companyId);
            rs = ps.executeQuery();
            if (rs.next())
                name = rs.getString(1);
        } catch (Exception se) {
            System.out.println(se.getMessage());
        } finally {
            closeStatement(ps);
            closeResultSet(rs);
        }
        return name;
    }

    public List<VIewpointTransaction> getReconcileMerchantFile() {
        List<VIewpointTransaction> list = new ArrayList<VIewpointTransaction>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SQL_ON_DEMAND_FILE);
            rs = ps.executeQuery();
            while (rs.next()) {
                VIewpointTransaction transaction = new VIewpointTransaction();
                transaction.setResult(rs.getString("result"));
                transaction.setType(rs.getString("type"));
                transaction.setMethod(rs.getString("method"));
                transaction.setTransactionId(rs.getString("transactionid"));
                transaction.setUniqueId(rs.getString("uniqueid"));
                transaction.setShortId(rs.getString("shortid"));
                transaction.set("channelName", rs.getString("channel_name"));
                transaction.setCustomerCountry(rs.getString("customer_fkcountry"));
                transaction.setValuedate(rs.getDate("valuedate"));
                transaction.setAccountHolder(rs.getString("account_holder"));
                transaction.setBrand(rs.getString("account_fkbrand"));
                transaction.setAmount(rs.getBigDecimal("amount"));
                transaction.setCurrency(rs.getString("fkiso4217"));
                transaction.setAcqGrossAmount(rs.getBigDecimal("acqgrossamt"));
                transaction.setAcqCurrency(rs.getString("acqfkiso4217"));
                transaction.set("companyName", rs.getString("company_name"));
                transaction.setProcessTimestamp(rs.getTimestamp("processtimestamp"));
                transaction.setPtf(rs.getBigDecimal("ptf"));
                transaction.setRmf(rs.getBigDecimal("rmf"));
                transaction.setFkCompany(rs.getInt("fkcompany"));
                transaction.setMBAccount(rs.getString("mb_account_id"));
                transaction.setMBPaymentMethod(rs.getString("mbpaymentmethod"));
                transaction.setReconcileAccountNumber(rs.getString("reconcile_account_number"));
                transaction.setAcqFxRate(rs.getBigDecimal("acquirer_fx_rate"));
                transaction.setSettlementFxRate(rs.getBigDecimal("settlement_fx_rate"));
                transaction.setAcqGrossAmount(rs.getBigDecimal("acqgrossamt"));
                transaction.setSettlementAmount(rs.getBigDecimal("settlement_gross_amnt"));
                transaction.setSettlementCurrency(rs.getString("settlement_currency"));
                // t.acquirer_fx_rate, t.settlement_currency, t.settlement_gross_amnt,

                list.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStatement(ps);
            closeResultSet(rs);
        }
        return list;
    }

    public List<VIewpointTransaction> getAmexMerchantFile(Date from, Date to) {
        List<VIewpointTransaction> list = new ArrayList<VIewpointTransaction>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SQL_AMEX_MECHANT_FILE);
            ps.setDate(1, new java.sql.Date(from.getTime()));
            ps.setDate(2, new java.sql.Date(to.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                VIewpointTransaction transaction = new VIewpointTransaction();
                transaction.setResult(rs.getString("result"));
                transaction.setType(rs.getString("type"));
                transaction.setMethod(rs.getString("method"));
                transaction.setTransactionId(rs.getString("transactionid"));
                transaction.setUniqueId(rs.getString("uniqueid"));
                transaction.setShortId(rs.getString("shortid"));
                transaction.set("channelName", rs.getString("channel_name"));
                transaction.setCustomerCountry(rs.getString("customer_fkcountry"));
                transaction.setValuedate(rs.getDate("valuedate"));
                transaction.setAccountHolder(rs.getString("account_holder"));
                transaction.setBrand(rs.getString("account_fkbrand"));
                transaction.setAmount(rs.getBigDecimal("amount"));
                transaction.setCurrency(rs.getString("fkiso4217"));
                transaction.setAcqGrossAmount(rs.getBigDecimal("acqgrossamt"));
                transaction.setAcqCurrency(rs.getString("acqfkiso4217"));
                transaction.set("companyName", rs.getString("company_name"));
                transaction.setProcessTimestamp(rs.getTimestamp("processtimestamp"));
                transaction.setPtf(rs.getBigDecimal("ptf"));
                transaction.setRmf(rs.getBigDecimal("rmf"));
                transaction.setFkCompany(rs.getInt("fkcompany"));
                transaction.setMBAccount(rs.getString("mb_account_id"));
                transaction.setMBPaymentMethod(rs.getString("mbpaymentmethod"));
                transaction.setReconcileAccountNumber(rs.getString("reconcile_account_number"));
                transaction.setAcqFxRate(rs.getBigDecimal("acquirer_fx_rate"));
                transaction.setSettlementFxRate(rs.getBigDecimal("settlement_fx_rate"));
                transaction.setAcqGrossAmount(rs.getBigDecimal("acqgrossamt"));
                transaction.setSettlementAmount(rs.getBigDecimal("settlement_gross_amnt"));
                transaction.setSettlementCurrency(rs.getString("settlement_currency"));
                // t.acquirer_fx_rate, t.settlement_currency, t.settlement_gross_amnt,

                list.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStatement(ps);
            closeResultSet(rs);
        }
        return list;
    }

    public Setting getSettingByKey(String settingKey) {
        Setting result = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement("select * from settings where setting_key = ?");
            ps.setString(1, settingKey);
            rs = ps.executeQuery();

            if (rs.next()) {
                result = new Setting();
                result.setSettingKey(rs.getString("setting_key"));
                result.setDescription(rs.getString("description"));
                result.setStringValue(rs.getString("string_value"));
                result.setDateValue(rs.getTimestamp("date_value"));
                result.setDoubleValue(rs.getDouble("double_value"));
                result.setIntValue(rs.getInt("int_value"));
                result.setBoolValue(rs.getBoolean("bool_value"));
            }
        } catch (Exception e) {
            System.out.println("Error while getting settings: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeStatement(ps);
            closeResultSet(rs);
        }

        return result;
    }
    
    public int getFileCounter(String filename) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int counter = 0;
        try {
            ps = connection.prepareStatement("SELECT count(pk) from reconcile_files where filename like ?;");
            ps.setString(1, filename + "%");
            rs = ps.executeQuery();
            if (rs.next())
                counter = rs.getInt(1);
        } catch (Exception se) {
            System.out.println(se.getMessage());
        } finally {
            closeStatement(ps);
            closeResultSet(rs);
        }
        return counter;
    }
}
