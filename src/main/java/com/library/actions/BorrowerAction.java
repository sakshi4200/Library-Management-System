package com.library.actions;

import java.sql.*;
import java.util.*;
import java.util.Calendar;

import com.opensymphony.xwork2.ActionSupport;
import com.library.service.MyConnection;
import com.library.beans.bookLoans;

public class BorrowerAction extends ActionSupport {

    private String name;
    private String ssn;
    private String address;
    private String phone;
    private String cardId;
    private String libId;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLibId() {
        return libId;
    }

    public void setLibId(String libId) {
        this.libId = libId;
    }

    public String payFine()
    {
        if(getLibId()!=null && getLibId().toUpperCase().equals("sakshi4200".toUpperCase())) {
            List<bookLoans> bookLoans = new ArrayList<bookLoans>();
            java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            Float fine = 0.0f;
            MyConnection myConn = new MyConnection();
            Connection conn = myConn.getConnection();
            int generatedkey = 0;
            try {

                PreparedStatement ps = conn.prepareStatement("Select * from borrower where card_id=?;");
                ps.setInt(1, Integer.parseInt(getCardId()));
                ResultSet rsCheck = ps.executeQuery();
                if (rsCheck.next()) {


                    ps = conn.prepareStatement("Select loan_id, DATEDIFF( ?, due_date )*.25  as fine from book_loans where card_id=? and date_in IS NULL and  ?>due_date ; ");
                    ps.setDate(1, currentDate);
                    ps.setString(2, getCardId());
                    ps.setDate(3, currentDate);
                    ResultSet rss = ps.executeQuery();
                    int loanId=0;
                    while (rss.next()) {

                        loanId=rss.getInt("loan_id");
                        fine = fine + rss.getFloat("fine");
                        ps = conn.prepareStatement("Select *  from fines where loan_id=? ");
                        ps.setInt(1, loanId);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            if(rs.getInt("paid")==0) {

                                ps = conn.prepareStatement("UPDATE fines set fine_amt =" + fine + " , paid =1  where Loan_id=" + loanId);
                                ps.executeUpdate();
                            }
                            else
                            {
                                fine = fine - rss.getFloat("fine");
                            }
                        } else {
                            ps = conn.prepareStatement("INSERT INTO Fines values (?,?,?) ");
                            ps.setFloat(2, fine);
                            ps.setInt(1, loanId);
                            ps.setInt(3, 1);
                            ps.executeUpdate();
                        }


                    }
                    if(fine==0.0f)
                    {
                        addActionMessage("User has no fine to pay");
                    }
                    else
                    addActionMessage("Amount to be taken: " + fine + "$");
                } else {
                    addActionMessage("User with the given card id does not exist in System");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            addActionMessage("Enter a valid librarian Id");
        }

            return "success";

    }
    public String checkFine()
    {
        List<bookLoans> bookLoans=new ArrayList<bookLoans>();
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
         Float fine=0.0f;
        {
            MyConnection myConn=new MyConnection();
            Connection conn=myConn.getConnection();
            int generatedkey=0;
            try {

                PreparedStatement  ps = conn.prepareStatement("Select * from borrower where card_id=?;" );
                ps.setInt(1, Integer.parseInt(getCardId()));
                ResultSet  rsCheck=ps.executeQuery();
                if(rsCheck.next()) {


                    ps = conn.prepareStatement("Select loan_id, DATEDIFF( ?, due_date )*.25  as fine from book_loans where card_id=? and date_in IS NULL and  ?>due_date ; ");
                    ps.setDate(1,currentDate);
                    ps.setString(2, getCardId());
                    ps.setDate(3,currentDate);
                    ResultSet rss = ps.executeQuery();
                    while (rss.next()) {
                        fine=fine+rss.getFloat("fine");
                        ps = conn.prepareStatement("Select *  from fines where loan_id=? ");
                        ps.setInt(1,rss.getInt("loan_id"));
                        ResultSet rs = ps.executeQuery();
                        if(rs.next())
                        {
                            ps = conn.prepareStatement("UPDATE Fines set fine_amt =? where Loan_id=? ");
                            ps.setFloat(1,rss.getFloat("fine"));
                            ps.setInt(2,rss.getInt("loan_id"));
                            ps.executeUpdate();
                        }
                        else
                        {
                            ps = conn.prepareStatement("INSERT INTO Fines values (?,?,?) ");
                            ps.setFloat(2,rss.getFloat("fine"));
                            ps.setInt(1,rss.getInt("loan_id"));
                            ps.setInt(3,0);
                            ps.executeUpdate();
                        }

                    }
                    addActionMessage("Fine amount of this user is : "+fine+"$");
                }
                else
                {
                    addActionMessage("User with the given card id does not exist in System");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return "success";
        }
    }

    public String createBorrower()
    {
        MyConnection myConn=new MyConnection();
        Connection conn=myConn.getConnection();
        int generatedkey=0;
        try {

            PreparedStatement  ps = conn.prepareStatement("Select card_id from borrower where ssn=?; " );
            ps.setString(1, getSsn());
            ResultSet  rsCheck=ps.executeQuery();
            if(!rsCheck.next()) {
                ps = conn.prepareStatement("Insert into borrower(SSN,Bname,Address,Phone) values(?,?,?,?) ");
                ps.setString(1, getSsn());
                ps.setString(2, getName());
                ps.setString(3, getAddress());
                ps.setString(4, getPhone());

                Boolean rs = ps.execute();



                ps = conn.prepareStatement("Select card_id from borrower where ssn=?; ");
                ps.setString(1, getSsn());
                ResultSet rss = ps.executeQuery();
                if (rss.next()) {
                    generatedkey = rss.getInt(1);
                    System.out.println("card_id" + generatedkey);
                    addActionMessage("Congratulations your card Id is "+generatedkey);
                }

            }
            else
            {
                addActionError("User with the given ssn exist in System");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "success";
    }
}
