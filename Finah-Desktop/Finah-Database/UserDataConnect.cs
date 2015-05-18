using DataObjects;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace Database
{
    public static class UserDataConnect
    {

        public static List<User> getUsers()
        {
            String input = WebAPIConnect.GetConnectionString("User");
            List<User> userList = new List<User>();

            try
            {
                userList = Newtonsoft.Json.JsonConvert.DeserializeObject<List<User>>(input);

            }
            catch (Newtonsoft.Json.JsonException e)
            {
                Console.Write(e);
            }

            return userList;
        }

        public static User getUser(int id)
        {
            return DataConnect.getDataId<User>("User", id);
        }

        public static UserType getUserType(int id)
        {

            String input = WebAPIConnect.GetConnectionStringWithId("UserType", id);
            UserType userType = new UserType();

            Console.WriteLine(input);

            try
            {
                userType = Newtonsoft.Json.JsonConvert.DeserializeObject<UserType>(input);

            }
            catch (Newtonsoft.Json.JsonException e)
            {
                Console.Write("fout >>>>>>>>>>>>>>>>>>:" + e);
            }

            return userType;

        }

        public static List<UserType> getUserTypes()
        {

            String input = WebAPIConnect.GetConnectionString("UserType");
            List<UserType> userTypeList = new List<UserType>();

            try
            {
                userTypeList = Newtonsoft.Json.JsonConvert.DeserializeObject<List<UserType>>(input);

            }
            catch (Newtonsoft.Json.JsonException e)
            {
                Console.Write(e);
            }

            return userTypeList;

        }

        // check login and password, retuns false if not correct, true if correct
        public static bool checkLogin(String login, String pass)
        {
            bool isAllowed = false;

            List<User> users = getUsers();
            User userLogin = null;

            try
            {
                foreach (User user in users)
                {
                    if (user.Login.Equals(login) == true)
                    {
                        userLogin = user;
                    }
                }
            }
            catch (NullReferenceException e)
            {
                throw (e);
            }


            if (userLogin != null)
            {

                string str = CalculateMD5Hash(pass);

                if (str.Equals(userLogin.Password, StringComparison.InvariantCultureIgnoreCase))
                {
                    isAllowed = true;
                }
            }

            return isAllowed;
        }

        public static void addUser(User user)
        {
            var httpWebRequest = (HttpWebRequest)WebRequest.Create("http://finah-backend.cloudapp.net/api/User/" + user.Id);
            httpWebRequest.ContentType = "text/json";
            httpWebRequest.Method = "POST";

            /*httpWebRequest.ContentType = "application/json; charset=utf-8";
            httpWebRequest.Method = "POST";
            httpWebRequest.Accept = "application/json; charset=utf-8";*/

            try
            {
                using (var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
                {
                    string json = Newtonsoft.Json.JsonConvert.SerializeObject(user);

                    streamWriter.Write(json);
                    streamWriter.Flush();
                    streamWriter.Close();

                    var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
                    using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
                    {
                        var result = streamReader.ReadToEnd();
                        Console.Write(result.ToString());
                    }
                }

            }
            catch (WebException ex)
            {
                throw ex;
            }
            catch (HttpListenerException ex)
            {
                throw ex;
            }

        }


        // Calculate MD5 Hash
        public static string CalculateMD5Hash(string input)
        {
            // step 1, calculate MD5 hash from input
            MD5 md5 = System.Security.Cryptography.MD5.Create();
            byte[] inputBytes = System.Text.Encoding.ASCII.GetBytes(input);
            byte[] hash = md5.ComputeHash(inputBytes);

            // step 2, convert byte array to hex string
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hash.Length; i++)
            {
                sb.Append(hash[i].ToString("X2"));
            }
            return sb.ToString();
        }
    }
}
