package com.library.actions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.library.beans.Book;
import com.opensymphony.xwork2.ActionSupport;

public class BookAction extends ActionSupport{
    private List<Book> bookList;



    private String searchText;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public static Connection conn() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "sakshi");
    }


   public String BookSearch()
    {
        String searchValue=getSearchText();
        bookList=getBooks(searchValue);
        System.out.println("bookList"+bookList.size());
        return "success";
    }
        public  List<Book>  getBooks( String searchValue ) {

            bookList=new ArrayList<Book>();
           Map<String,Book> bookMap=new HashMap<String,Book>();
            try {
               // PreparedStatement ps = conn().prepareStatement("select b.isbn , b.title, a.name from book b,  book_authors ba, authors  a where b.isbn=ba.isbn and a.author_id=ba.author_id and b.title like ? OR b.isbn like ? or a.name like ?");
                PreparedStatement ps = conn().prepareStatement(" SELECT a.Isbn as ISBN , GROUP_CONCAT(DISTINCT d.Name) AS Authors,a.Title, a.available FROM ( book AS a LEFT JOIN book_authors AS s ON a.isbn = s.isbn  )left join authors as d on s.Author_id=d.Author_id  where upper(d.name) like ? or a.Isbn like ? or upper(Title) like ? group by a.isbn");
                Book oldBook =null;
                ps.setString(1,"%"+searchValue.toUpperCase()+"%");
               ps.setString(2,"%"+searchValue.toUpperCase()+"%");
                ps.setString(3,"%"+searchValue.toUpperCase()+"%");
                ResultSet rs=   ps.executeQuery();
                while (rs.next()) {

                    Book b=new Book();
                    b.setIsbn(rs.getString("ISBN"));
                    b.setTitle(rs.getString("a.Title"));
                    b.setAuthor(rs.getString("Authors"));
                    if(rs.getInt("a.available")==1)
                        b.setAvailable("Available");
                    else
                        b.setAvailable("Not Available");
                    bookList.add(b);
                }

                System.out.println(rs);
             
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
            return bookList;
        }



}
