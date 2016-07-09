
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

import com.SampleProject.DButil.DBConnection;
import com.SampleProject.model.Identity;
/**
 * Servlet implementation class Signupservlet
 */
@WebServlet("/Signupservlet")
public class Signupservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private Connection conn;
    public Signupservlet() {
        super();
        conn = DBConnection.getConnection();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		Identity identity =new Identity();
		try {
			JSONObject jsonObject  = new JSONObject(request.getParameter("data"));
			String fname= jsonObject.getString("fname");
			String lname= jsonObject.getString("lname");
			String email= jsonObject.getString("email");
			String phonenumber= jsonObject.getString("phonenumber");
			String password= jsonObject.getString("password");
			String pincode= jsonObject.getString("pincode");
			String address= jsonObject.getString("address");
			identity.setFirstName(fname);
			identity.setLastName(lname);
			identity.setEmailAddress(email);
			identity.setPassword(password);
			identity.setPhoneNumber(phonenumber);
			identity.setPincode(pincode);
			identity.setAddress(address);
			System.out.println("prepare qury");
			String query = "insert into identity (firstname, lastname, emailaddress,password,phonenumber,pincode,address) values (?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement( query );
			preparedStatement.setString( 1, identity.getFirstName() );
			preparedStatement.setString( 2, identity.getLastName() );
			preparedStatement.setString( 3, identity.getEmailAddress() );
			preparedStatement.setString( 4, identity.getPassword() );
			preparedStatement.setString(5,identity.getPhoneNumber());
			preparedStatement.setString(6, identity.getPincode());
			preparedStatement.setString(7, identity.getAddress());
			preparedStatement.executeUpdate();	
			preparedStatement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			System.out.println("Records created successfully");
		}
	}

}
