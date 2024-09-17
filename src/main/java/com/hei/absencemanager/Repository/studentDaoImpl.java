// package com.hei.absencemanager.Repository;

// import java.beans.Statement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Cache.Connection;

// import com.hei.absencemanager.Entity.Student;

// public class studentDaoImpl implements studentDao {

//     @Override
//     public void readStudentList() {
//         List<Student> todos = new ArrayList<>();
//         DatabaseConnection db = new DatabaseConnection();
//         Connection connection = db.connect();

//         try(Statement stm = connection.createStatement()){
//             String sql = "SELECT * FROM details";
//             ResultSet rs = stm.executeQuery(sql);

//             // while(rs.next()) {
//             //     Student toAdd = new Student(
//             //             // rs.getInt("id"),
//             //             // rs.getString("title"),
//             //             // rs.getString("description"),
//             //             // rs.getDate("creationdate").toLocalDate(),
//             //             // rs.getDate("deadline").toLocalDate(),
//             //             // rs.getDate("executionDate").toLocalDate(),
//             //             // Priorities.valueOf(rs.getString("priority")),
//             //             // Status.valueOf(rs.getString("status"))
//             //     );
//             //     todos.add(toAdd);
//             // }
//         }
//         catch (SQLException e) {
//             System.out.println(e);
//         }

//     }

// }
