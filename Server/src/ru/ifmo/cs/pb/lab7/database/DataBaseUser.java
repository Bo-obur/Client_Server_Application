package ru.ifmo.cs.pb.lab7.database;

import ru.ifmo.cs.pb.lab7.object.User;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseUser {

      public static final String ROOT = "s289307";

      public static void addNewUser(User user, Connection connection) throws SQLException {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlPattern.ADD_NEW_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, DataBaseUser.hashByAlgorithmSHA(user.getPassword()));
            preparedStatement.executeUpdate();
            preparedStatement.close();
      }

      public static boolean checkExistUser(User user, Connection connection) throws SQLException {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlPattern.CHECK_EXIST_USER);
            preparedStatement.setString(1, user.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                  if (resultSet.getString("password")
                          .equals(hashByAlgorithmSHA(user.getPassword())))
                        return true;
            }
            return false;
      }

      private static String hashByAlgorithmSHA(String password){
            try {
                  MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
                  byte[] passwordInBytes = password.getBytes(StandardCharsets.UTF_8);
                  byte[] hashOfPassword = messageDigest.digest(passwordInBytes);
                  BigInteger bigInteger = new BigInteger(1, hashOfPassword);
                  return bigInteger.toString(16);
            } catch (NoSuchAlgorithmException exception) {
                  return password;
            }
      }
}
