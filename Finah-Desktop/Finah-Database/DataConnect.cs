using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DataObjects;
using System.Net;
using System.IO;
using System.Security.Cryptography;

namespace Database
{
    public static class DataConnect
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

            String input = WebAPIConnect.GetConnectionStringWithId("User", id);
            User user = new User();

            Console.WriteLine(input);

            try
            {
                user = Newtonsoft.Json.JsonConvert.DeserializeObject<User>(input);

            }
            catch (Newtonsoft.Json.JsonException e)
            {
                Console.Write(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> fout >>>>>>>>>>>>>>>>>>:" + e);
            }



            return user;

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

        public static bool checkLogin(String login, String pass)
        {
            bool isAllowed = false;

            List<User> users = getUsers();
            User userLogin = null;

            foreach (User user in users)
            {
                if (user.Login.Equals(login) == true)
                {
                    userLogin = user;
                }
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

        public static List<QuestionList> GetQuestionnaires()
        {
            String input = WebAPIConnect.GetConnectionString("QuestionList");
            List<QuestionList> questionList = new List<QuestionList>();

            try
            {
                questionList = Newtonsoft.Json.JsonConvert.DeserializeObject<List<QuestionList>>(input);

            }
            catch (Newtonsoft.Json.JsonException e)
            {
                Console.Write(e);
            }


            return questionList;

        }

        public static List<Question> GetQuestions()
        {
            String input = WebAPIConnect.GetConnectionString("Question");
            List<Question> questions = new List<Question>();

            try
            {
                questions = Newtonsoft.Json.JsonConvert.DeserializeObject<List<Question>>(input);

            }
            catch (Newtonsoft.Json.JsonException e)
            {
                Console.Write(e);
            }

            return questions;
        }

        public static List<Theme> GetThemes()
        {
            String input = WebAPIConnect.GetConnectionString("Theme");
            List<Theme> themes = new List<Theme>();

            try
            {
                themes = Newtonsoft.Json.JsonConvert.DeserializeObject<List<Theme>>(input);

            }
            catch (Newtonsoft.Json.JsonException e)
            {
                Console.Write(e);
            }

            return themes;
        }

        public static Theme GetTheme(int id)
        {
            String input = WebAPIConnect.GetConnectionStringWithId("Theme", id);
            Theme theme = new Theme();

            try
            {
                theme = Newtonsoft.Json.JsonConvert.DeserializeObject<Theme>(input);

            }
            catch (Newtonsoft.Json.JsonException e)
            {
                Console.Write(e);
            }

            return theme;
        }

        public static List<Question> GetThemesQuestions(int themeId)
        {
            String input = WebAPIConnect.GetConnectionString("Question");
            List<Question> themeQuestions = new List<Question>();

            try
            {
                List<Question> questions = Newtonsoft.Json.JsonConvert.DeserializeObject<List<Question>>(input);

                foreach (Question question in questions)
                {
                    if (question.Theme == themeId)
                    {
                        themeQuestions.Add(question);
                    }
                }

            }
            catch (Newtonsoft.Json.JsonException e)
            {
                Console.Write(e);
            }

            return themeQuestions;
        }
    }
    
}
