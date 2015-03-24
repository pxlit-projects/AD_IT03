using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using DataObjects;
using System.Data.SqlClient;
using MySql.Data.MySqlClient;

namespace Database
{
    public class DBConnect
    {
        //private SqlConnection connection;
        //private string server;
        //private string database;
        //private string uid;
        //private string password;

        public static MySqlConnection GetConnection()
        {
            string connectionString =
                "SERVER=81.4.126.109;DATABASE=db_project;UID=luke;PASSWORD=lukeluke;";
            MySqlConnection connection = new MySqlConnection(connectionString);
            return connection;
        }
    }
}
