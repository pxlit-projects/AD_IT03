using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DataObjects;

namespace Database
{
    public class AnswerDataConnect
    {

        public static List<AnswerList> GetAnswerList()
        {
            String input = WebApiConnect.GetConnectionString("AnswerList");
            List<AnswerList> answerList = new List<AnswerList>();

            try
            {
                answerList = Newtonsoft.Json.JsonConvert.DeserializeObject<List<AnswerList>>(input);

            }
            catch (Newtonsoft.Json.JsonException e)
            {
                Console.Write(e);
            }

            return answerList;
        }

        public static List<Answer> GetAnswers()
        {
            String input = WebApiConnect.GetConnectionString("Answer");
            List<Answer> answers = new List<Answer>();

            try
            {
                answers = Newtonsoft.Json.JsonConvert.DeserializeObject<List<Answer>>(input);

            }
            catch (Newtonsoft.Json.JsonException e)
            {
                Console.Write(e);
            }

            return answers;
        }


    }
}
