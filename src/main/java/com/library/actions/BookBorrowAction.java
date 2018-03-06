package com.library.actions;

import com.library.beans.Book;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookBorrowAction extends ActionSupport {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private String isbn;

    private String cardId;
   private List<Book>  bookList;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    private String Msg;

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public String getBorrowerId()
    {
        System.out.println("getIsbn"+getIsbn());
        return "success";


    }
    public static Connection conn() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "sakshi");
    }



    public String issueBook()
    {
        int alreadyIssued=0;
        try {
            PreparedStatement ps = conn().prepareStatement(" SELECT * from borrower where card_id=?");
            ps.setInt(1, Integer.parseInt(getCardId()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int available=1;
                 ps = conn().prepareStatement(" SELECT available from book where isbn=?");
                ps.setString(1, getIsbn());
                 rs = ps.executeQuery();
                if(rs.next())
                {
                    available= rs.getInt(1);
                    System.out.println("available"+available);
                }
                if(available==1) {
                    ps = conn().prepareStatement(" SELECT count(*) as books from book_loans where card_id=?");
                    ps.setInt(1, Integer.parseInt(getCardId()));
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        alreadyIssued = rs.getInt("books");
                    }
                    if (alreadyIssued >= 3)
                        addActionMessage("You can't take more than 3 books");
                    else {

                        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                        // convert date to calendar
                        Calendar c = Calendar.getInstance();
                        c.add(Calendar.DATE, 14);

                        // convert calendar to date
                        Date currentDatePlus14 = c.getTime();
                        ps = conn().prepareStatement(" INSERT into book_loans (isbn,card_id,date_out,due_date) VALUE ( ?,?,?,?)");
                        ps.setString(1, getIsbn());
                        System.out.println("getCardId()" + getCardId() + getIsbn());
                        ps.setInt(2, Integer.parseInt(getCardId()));
                        ps.setDate(3, currentDate);
                        ps.setDate(4, new java.sql.Date(c.getTime().getTime()));
                        ps.execute();
                        ps = conn().prepareStatement(" update book set available=0 where isbn=?");
                        ps.setString(1, getIsbn());
                        ps.execute();
                        addActionMessage("Book has been Issued");

                    }
                }
                else {
                    addActionMessage("  Book is not available currently");
                }
            }
            else
            {
                addActionMessage("Enter a valid Card Id");
            }
        }
        catch(Exception e)
            {
    e.printStackTrace();
            }
        return "success";


    }


    public  String returnThisBook() {


        try {
            PreparedStatement    ps = conn().prepareStatement(" SELECT * from book_loans, fines where  book_loans.loan_id=fines.loan_id and isbn=? and paid=0 ");
            ps.setString(1, getIsbn());
            ResultSet rs= ps.executeQuery();
            if(!rs.next()) {
                java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                ps = conn().prepareStatement(" Update book_loans set date_in=? where isbn=? and date_in IS NULL");
                ps.setDate(1, currentDate);
                ps.setString(2, getIsbn());
                ps.executeUpdate();
                ps = conn().prepareStatement(" Update book set available=1 where isbn=?");
                ps.setString(1, getIsbn());
                ps.executeUpdate();
                addActionMessage("Book has been Returned");
            }
            else
            {
                addActionMessage("Please pay your fine amount first");
            }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
            return "success";

    }

    public  String returnBook() {

       bookList=new ArrayList<Book>();
        try {
            PreparedStatement ps = conn().prepareStatement(" SELECT * from borrower where card_id=?");
            ps.setInt(1, Integer.parseInt(getCardId()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {


                // PreparedStatement ps = conn().prepareStatement("select b.isbn , b.title, a.name from book b,  book_authors ba, authors  a where b.isbn=ba.isbn and a.author_id=ba.author_id and b.title like ? OR b.isbn like ? or a.name like ?");
                ps = conn().prepareStatement(" SELECT  DISTINCT a.Isbn  ,a.Title FROM  book a, book_loans b where a.isbn=b.isbn and b.card_id =? and date_in IS NULL ");
                Book oldBook = null;
                ps.setInt(1, Integer.parseInt(cardId));
                rs = ps.executeQuery();
                while (rs.next()) {

                    Book b = new Book();
                    b.setIsbn(rs.getString("ISBN"));
                    b.setTitle(rs.getString("a.Title"));
                    bookList.add(b);
                }
            }
            else
            {
                addActionMessage("Enter a valid Card Id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                conn().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "success";
    }


}

