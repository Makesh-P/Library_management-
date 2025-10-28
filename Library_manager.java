import java.sql.*;
import java.util.*;

public class LibraryManager {
 public static void main(String[] args) throws Exception {
  Class.forName("com.mysql.cj.jdbc.Driver");
  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "");
  Scanner sc = new Scanner(System.in);
  while (true) {
   System.out.println("1. Add 2. View 3. Update 4. Delete 5. Exit");
   int ch = sc.nextInt();
   if (ch == 1) {
    System.out.print("ID: "); int id = sc.nextInt();
    sc.nextLine();
    System.out.print("Title: "); String title = sc.nextLine();
    System.out.print("Author: "); String author = sc.nextLine();
    System.out.print("Publisher: "); String pub = sc.nextLine();
    System.out.print("Price: "); double price = sc.nextDouble();
    PreparedStatement ps = con.prepareStatement("insert into books values(?,?,?,?,?)");
    ps.setInt(1, id);
    ps.setString(2, title);
    ps.setString(3, author);
    ps.setString(4, pub);
    ps.setDouble(5, price);
    ps.executeUpdate();
    System.out.println("Book Added");
   } else if (ch == 2) {
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("select * from books");
    while (rs.next()) {
     System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getDouble(5));
    }
   } else if (ch == 3) {
    System.out.print("Book ID: "); int id = sc.nextInt();
    System.out.print("New Price: "); double price = sc.nextDouble();
    PreparedStatement ps = con.prepareStatement("update books set price=? where book_id=?");
    ps.setDouble(1, price);
    ps.setInt(2, id);
    ps.executeUpdate();
    System.out.println("Price Updated");
   } else if (ch == 4) {
    System.out.print("Book ID: "); int id = sc.nextInt();
    PreparedStatement ps = con.prepareStatement("delete from books where book_id=?");
    ps.setInt(1, id);
    ps.executeUpdate();
    System.out.println("Book Deleted");
   } else if (ch == 5) {
    break;
   }
  }
  con.close();
  sc.close();
 }
}
