using Finah_DomainClasses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Finah_Repository
{
    public class QuestionRepository
    {
        public List<question> GetQuestions()
        {
            var context = new db_projectEntities();
            var questions = context.question.ToList();
            return questions;
        }
    }
}
