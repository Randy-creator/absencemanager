 package com.hei.absencemanager.Repository;

 import java.sql.Statement;
 import java.sql.Connection;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.List;



 import com.hei.absencemanager.Entity.Student;
 import org.springframework.stereotype.Repository;

 @Repository
 public class StudentDaoImpl implements StudentDao {
    private DatabaseConnection db;
    

     public StudentDaoImpl(DatabaseConnection db) {
        this.db = db;
    }


    @Override
     public List<Student> readStudentList() throws SQLException {
         List<Student> student = new ArrayList<>();
         DatabaseConnection db = new DatabaseConnection();
         Connection connection = db.connect();

         try(Statement stm = connection.createStatement()){
             String sql = "SELECT * FROM Student";
             ResultSet rs = stm.executeQuery(sql);

              while(rs.next()) {
                  Student toAdd = new Student(
                           rs.getString("std"),
                           rs.getString("email"),
                           rs.getString("firstName"),
                           rs.getString("lastName"),
                           rs.getString("group"),
                          rs.getBoolean("corstatus")
                  );
                  student.add(toAdd);
              }
         }
         catch (SQLException e) {
             System.out.println(e);
         }
         return student;
     }

 }
