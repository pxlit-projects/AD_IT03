﻿using DataObjects;
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

        public static List<User> GetUsers()
        {
            String input = WebApiConnect.GetConnectionString("User");
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

        public static User GetUser(int id)
        {
            return DataConnect.GetDataId<User>("User", id);
        }

        public static UserType GetUserType(int id)
        {

            String input = WebApiConnect.GetConnectionStringWithId("UserType", id);
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

        public static List<UserType> GetUserTypes()
        {

            String input = WebApiConnect.GetConnectionString("UserType");
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

        // check login and password, retuns 0 if not correct, returns FunctionId (> 0) if user exists 
        public static int CheckLogin(String login, String pass)
        {
            int functionId = 0;

            List<User> users = GetUsers();
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

                string str = CalculateMd5Hash(pass);

                if (str.Equals(userLogin.Password, StringComparison.InvariantCultureIgnoreCase))
                {
                    functionId = userLogin.Type;
                }
            }

            return functionId;
        }

        public static void AddUser(User user)
        {
            var httpWebRequest = (HttpWebRequest)WebRequest.Create(WebApiConnect.GetUri() + "User");
            httpWebRequest.ContentType = "text/json";
            httpWebRequest.Method = "POST";

            user.Password = CalculateMd5Hash(user.Password);

            WebApiWriterUser(httpWebRequest, user);

        }

        public static void UpdateUser(User user)
        {
            var httpWebRequest = (HttpWebRequest) WebRequest.Create(WebApiConnect.GetUri() + "User/" + user.Id);
            httpWebRequest.ContentType = "text/json";
            httpWebRequest.Method = "PUT";

            WebApiWriterUser(httpWebRequest, user);
        }

        public static void DeleteUser(User user)
        {
            var httpWebRequest = (HttpWebRequest)WebRequest.Create(WebApiConnect.GetUri() + "User/" + user.Id );
            httpWebRequest.ContentType = "text/json";
            httpWebRequest.Method = "DELETE";

            // Hash password
            user.Password = CalculateMd5Hash(user.Password);

            WebApiWriterUser(httpWebRequest, user);
        }

        // creates json string and does WebRequest
        private static void WebApiWriterUser(HttpWebRequest httpWebRequest, User user)
        {
            string json = Newtonsoft.Json.JsonConvert.SerializeObject(user);

            DataConnect.WebApiWriter(httpWebRequest, json);
        }

        // Calculate MD5 Hash
        public static string CalculateMd5Hash(string input)
        {
            // step 1, calculate MD5 hash from input
            MD5 md5 = System.Security.Cryptography.MD5.Create();
            byte[] inputBytes = System.Text.Encoding.ASCII.GetBytes(input);
            byte[] hash = md5.ComputeHash(inputBytes);

            // step 2, convert byte array to hex string
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hash.Length; i++)
            {
                sb.Append(hash[i].ToString("x2"));
            }
            return sb.ToString();
        }
    }
}
