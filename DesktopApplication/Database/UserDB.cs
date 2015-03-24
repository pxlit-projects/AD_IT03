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
            //String Login = User.Login;
            //String FirstName = User.Firstname;
            //String password = User.Password;
            //String Email = User.Email;
            //int Type = User.Type;

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
    }
}
