using DataObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Database
{
    public static class QuestionDataConnect
    {

        public static List<QuestionList> GetQuestionnaires()
        {
            String input = WebApiConnect.GetConnectionString("QuestionList");
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
            String input = WebApiConnect.GetConnectionString("Question");
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
            String input = WebApiConnect.GetConnectionString("Theme");
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
            String input = WebApiConnect.GetConnectionStringWithId("Theme", id);
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
            String input = WebApiConnect.GetConnectionString("Question");
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
