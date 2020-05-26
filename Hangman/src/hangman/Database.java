package hangman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




public class Database {


 private final String DRIVER = "com.mysql.jdbc.Driver";
 private final String URL = "jdbc:mysql://localhost:3306/hangman";
 private final String USER = "root";
 private final String PASSWORD = "emanuel77";

 private Connection dbCon;
 private PreparedStatement dbStmnt;
 private ResultSet rs;
 private String sql;


 public void connectDatabase() {


  try {
   Class.forName(DRIVER);
   dbCon = DriverManager.getConnection(URL, USER, PASSWORD);
   dbCon.setAutoCommit(false);
  } catch (Exception ex) {
   ex.printStackTrace();
   System.out.println(" *** Problem establishing connection to a database! ***");
  }



 }

 public void insertPlayer(String player) {
  try {

   sql = "INSERT into PLAYER(name)" + " VALUES(?)";

   try {
    dbStmnt = dbCon.prepareStatement(sql);
    dbStmnt.setString(1, player);
   } catch (SQLException ex) {
    ex.printStackTrace();
    System.out.println(" *** Problem with creating a database statement object! ***");
    this.connectDatabase();
   }

   dbStmnt.execute();
   dbCon.commit();
  } catch (SQLException ex) {
   ex.printStackTrace();
   System.out.println("*** Problem with inserting player to a database! ***");
  }
 }

 public ArrayList < String > getCategories() {

  ArrayList < String > categories = new ArrayList < String > ();

  try {

   sql = "SELECT name FROM category ORDER BY name";

   try {
    dbStmnt = dbCon.prepareStatement(sql);

   } catch (SQLException ex) {
    ex.printStackTrace();
    System.out.println(" *** Problem with creating a database statement object! ***");
    this.connectDatabase();
   }

   rs = dbStmnt.executeQuery(sql);
   while (rs.next()) {
    String category = rs.getString("name");;
    categories.add(category);

   }

  } catch (SQLException ex) {
   ex.printStackTrace();
   System.out.println("*** Problem with reading data from a category table! ***");
  }

  return categories;

 }

 public String getSecretWord(String category) {
  String secretWord = null;
  try {

   sql = "SELECT word from guessword WHERE idCategory IN " +
    " ( SELECT idCategory from category WHERE name = '" + category + "')" +
    " ORDER BY rand() LIMIT 1 ";

   try {
    dbStmnt = dbCon.prepareStatement(sql);


   } catch (SQLException ex) {
    ex.printStackTrace();
    System.out.println(" *** Problem with creating a database statement object! ***");
    this.connectDatabase();
   }

   rs = dbStmnt.executeQuery(sql);
   while (rs.next()) {
    secretWord = rs.getString("word");


   }

  } catch (SQLException ex) {
   ex.printStackTrace();
   System.out.println("*** Problem with reading data from a guessword table! ***");
  }

  return secretWord;

 }

 public void updateScore(int score, String name) {

  sql = "UPDATE player SET highscore = ? WHERE player.name = '" + name + "' and idPlayer <> 0";

  try {
   dbStmnt = dbCon.prepareStatement(sql);
   dbStmnt.setInt(1, score);

   try {
    dbStmnt.executeUpdate();
    dbCon.commit();
   } catch (SQLException ex) {
    System.out.println(" *** Problem updating player score in DB! ***");

   }
  } catch (SQLException ex) {
   ex.printStackTrace();
   System.out.println(" *** Problem with creating a database statement object! ***");
   this.connectDatabase();
  }


 }

 public ArrayList < String > get10Highscores() {

  ArrayList < String > highscores = new ArrayList < String > ();

  try {

   sql = "SELECT distinct name,IFNULL(highscore,'0') as score FROM player ORDER BY highscore DESC LIMIT 10";

   try {
    dbStmnt = dbCon.prepareStatement(sql);

   } catch (SQLException ex) {
    ex.printStackTrace();
    System.out.println(" *** Problem with creating a database statement object! ***");
    this.connectDatabase();
   }

   rs = dbStmnt.executeQuery(sql);
   while (rs.next()) {
    int i = rs.getInt("score");
    String s = rs.getString("name");
    highscores.add(s + " => " + String.valueOf(i));

   }

  } catch (SQLException ex) {
   ex.printStackTrace();
   System.out.println("*** Problem with reading data from a category table! ***");
  }

  return highscores;

 }


 public void cleanUp() {
  try {
   rs.close();
   dbStmnt.close();
   dbCon.close();
  } catch (SQLException ex) {
   ex.printStackTrace();
  }

 }


}