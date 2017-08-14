package com.skrill.viewpoint.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * Common job functionalities
 * 
 * @author desi
 * 
 */
public class Util {
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_FORMATTER_FILENAME = new SimpleDateFormat("yyyy.MM.dd");
    public static final SimpleDateFormat TIMESTAMP_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Properties props;
    static {
        props = new Properties();
//        try {
//            InputStream is = ClassLoader.getSystemResourceAsStream("build.properties");
//            if (is == null)
//                throw new IOException("Properties file not found");
//            props.load(is);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.exit(-1);
//        }
    }

    public static final String INSERT_TRN_QUERY =
            "insert into " +
                Util.getProperty("transactions_table") +
                " (uuid,amount,transactionid,uniqueid,shortid,usage,fkiso4217,result,status,reason,return,method,type,customer_given,customer_family,customer_street,customer_zip,customer_city,customer_email,clearing_institute,account_fkbrand,channelid,valuedate,processtimestamp,account_holder,customer_fkcountry,fkcompany,fkgroup,risk_operations,payon,reconcile," +
                "referenceid,mbpaymentmethod,mb_account_id,acqfkiso4217,risk_cost,payon_cost,acqcost,acqgrossamt,ptf,rmf,base_ptf,base_rmf,fx_acqgrossamt,fx_acqcost,fx_ptf,fx_rmf,dca,dc1,descriptor,connector_tx_id, settled, payon_failed_cost, reconcile_account_number)" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public static final String ACQ_RECONCILE_ACCOUNTS_QUERY = "SELECT acquirer,currency_code,account_number FROM acquirer_rec_acc_num";
    public static final String DEFAULT_FEES_QUERY = "SELECT fk_method as methodId, fk_type as typeId, default_fee as defaultFee FROM fees_acquirer";
    public static final String COMPANY_GROUP_INFO_QUERY = "SELECT c.companyid as compId,ch.fkgroup as chanGroupId, ch.channelId as chanId from company c inner join groups g on g.fkcompany=c.companyid inner join channel ch on ch.fkgroup=g.pk";
    public static final String GET_TRANSACTION_PTF_QUERY = "SELECT ptf from fees_mid where fk_brand=? and fk_mid=? and fk_type=? and funding_ccy=?";
    public static final String GET_MID_BY_CHANNELID_QUERY = "SELECT fk_mid from rs_channel_mid where fk_channelid=?";
    public static final String GET_MERCHANT_WALLET_ACCOUNT_QUERY = "SELECT mb_account_id from fees_mid where fk_brand=? and fk_mid=? and fk_type=? and funding_ccy=?";
    public static final String SQL_GET_FX_RATE_FOR_CURRENCY_AND_DATE = "select 1/fxrate as fxrate from exchange_rate_based_eur where currency = ? and time_stamp <= ? order by time_stamp  desc limit 1;";

//    private static final String DB_URL = props.getProperty("db_url");
//    private static final String DB_USERNAME = props.getProperty("db_username");
//    private static final String DB_PASS = props.getProperty("db_password");
    
    /**
     * @return Returns DB connection if it is already established else creates a new one and returns it.
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: org.postgresql.Driver NOT found!");
            System.exit(0);
        }

        return DriverManager.getConnection("jdbc:postgresql://ci1.neterra.moneybookers.net:5432/vp_stage", "vp_stage", "Aps7Ofs1");
    }

    /**
     * Closes DB the connection if it exists
     */
    public static void closeConnection(Connection conn) {
        if (conn == null) {
            return;
        }
        try {
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes database statement
     * 
     * @param stmt
     *            - the statement
     */
    public static void closeStatement(Statement stmt) {
        if (stmt != null)
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    /**
     * Closes result set
     * 
     * @param rs
     *            - the result set
     */
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads property
     * 
     * @param prop
     *            - the property key
     * @return - the property value
     */
    public static String getProperty(String prop) {
        if (prop == null) {
            return null;
        }
        return props.getProperty(prop);
    }

    public static void setProperty(String key, String value) {
        props.put(key, value);
    }

    public static boolean stringEmpty(String s) {
        return s == null || s.isEmpty();
    }
}
