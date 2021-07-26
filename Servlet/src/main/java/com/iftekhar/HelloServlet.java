package com.iftekhar;

import com.iftekhar.model.PaymentMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HelloServlet extends HttpServlet {
//    private List<String> id = new ArrayList<>();
//    private List<String> account = new ArrayList<>();
//    private List<String> mobile = new ArrayList<>();
//    private List<String> issueDate = new ArrayList<>();
//    private List<String> expireDate = new ArrayList<>();

    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            showData();


            req.setAttribute("msg","Success");
            req.setAttribute("paymentMethodList",paymentMethods);

            RequestDispatcher view = req.getRequestDispatcher("views/Hello.jsp");
            view.forward(req, resp);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void showData() throws ClassNotFoundException {

        String dbManager = "postgresql";
        String host = "localhost";
        String port = "5432";
        String db = "Registration_Login";
        String username = "postgres";
        String password = "1234";
        Class.forName("org.postgresql.Driver");

        try(
                Connection pgCon = DriverManager.getConnection(
                        "jdbc:" + dbManager + "://" + host + ":" + port + "/" + db, username, password)) {

            var query1 = "select id, account, mobile, issue_date, expire_date from public.payment_method; ";
            var pstmt = pgCon.prepareStatement(query1);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()){
                PaymentMethod payment = new PaymentMethod();

                payment.setId(resultSet.getString("id")==null?"": resultSet.getString("id"));
                payment.setAccount(resultSet.getString("account")==null?"": resultSet.getString("account"));
                payment.setMobile(resultSet.getString("mobile")==null?"": resultSet.getString("mobile"));
                payment.setIssueDate(resultSet.getString("issue_date")==null?"": resultSet.getString("issue_date"));
                payment.setExpireDate(resultSet.getString("expire_date")==null?"": resultSet.getString("expire_date"));

                paymentMethods.add(payment);




//                this.id.add(resultSet.getString("id"));
//                this.account.add(resultSet.getString("account")) ;
//                this.mobile.add( resultSet.getString("mobile"));
//                this.issueDate.add(resultSet.getString("issue_date"));
//                this.expireDate.add(resultSet.getString("expire_date"));
            }
            System.out.println("========");

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}



