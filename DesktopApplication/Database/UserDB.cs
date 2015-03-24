using DataObjects;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MySql.Data.MySqlClient;

namespace Database
{
    public static class UserDB
    {
        //Insert statement
        public static void InsertUser(User User)
        {
            MySqlConnection connection = DBConnect.GetConnection();

            //insertStatement
            string insertStatement =
                "INSERT INTO user " +
                  "(login, firstname, lastname, password, email, type) " +
                "VALUES(@Login, @Firstname, @Lastname, @Password, @Email, @Type)";

            //string query = "INSERT INTO user (login, firstname, password, email, type) VALUES('" + Login + "', '" + FirstName + "', '" + password + "', '" + Email + "', '2')";

            //create command and assign the parameters
            MySqlCommand insertCommand = new MySqlCommand(insertStatement, connection);
            insertCommand.Parameters.AddWithValue("@Login", User.Login);
            insertCommand.Parameters.AddWithValue("@Firstname", User.Firstname);
            insertCommand.Parameters.AddWithValue("@Lastname", User.Lastname);
            insertCommand.Parameters.AddWithValue("@Password", User.Password);
            insertCommand.Parameters.AddWithValue("@Email", User.Email);
            insertCommand.Parameters.AddWithValue("@Type", "2");

            //Open connection, execute command and close connection
            try
            {
                connection.Open();
                insertCommand.ExecuteNonQuery();
            }
            catch (MySqlException ex)
            {
                throw ex;
            }
            finally
            {
                connection.Close();
            }
        }

        //Update statement
        public static void UpdateUser(User User)
        {
            MySqlConnection connection = DBConnect.GetConnection();

            //insertStatement
            string updateStatement =
                "UPDATE user " +
                  "SET login=@Login, firstname=@Firstname, lastname=@Lastname, password=@Password, email=@Email, type=@Type " +
                  "WHERE id=@Id ";

            //create command and assign the parameters
            MySqlCommand updateCommand = new MySqlCommand(updateStatement, connection);
            updateCommand.Parameters.AddWithValue("@Id", User.Id);
            updateCommand.Parameters.AddWithValue("@Login", User.Login);
            updateCommand.Parameters.AddWithValue("@Firstname", User.Firstname);
            updateCommand.Parameters.AddWithValue("@Lastname", User.Lastname);
            updateCommand.Parameters.AddWithValue("@Password", User.Password);
            updateCommand.Parameters.AddWithValue("@Email", User.Email);
            updateCommand.Parameters.AddWithValue("@Type", "2");

            //Open connection, execute command and close connection
            try
            {
                connection.Open();
                updateCommand.ExecuteNonQuery();
            }
            catch (MySqlException ex)
            {
                throw ex;
            }
            finally
            {
                connection.Close();
            }
        }

        //Delete statement
        public static void DeleteUser(User User)
        {
            MySqlConnection connection = DBConnect.GetConnection();

            //insertStatement
            string deleteStatement =
                "DELETE FROM user WHERE id=@Id ";

            //string query = "INSERT INTO user (login, firstname, password, email, type) VALUES('" + Login + "', '" + FirstName + "', '" + password + "', '" + Email + "', '2')";

            //create command and assign the parameters
            MySqlCommand deleteCommand = new MySqlCommand(deleteStatement, connection);
            deleteCommand.Parameters.AddWithValue("@Id", User.Id);


            //Open connection, execute command and close connection
            try
            {
                connection.Open();
                deleteCommand.ExecuteNonQuery();
            }
            catch (MySqlException ex)
            {
                throw ex;
            }
            finally
            {
                connection.Close();
            }
        }
    }
}