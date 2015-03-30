using Finah_DomainClasses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Finah_Repository
{
    public class AnswerRepository
    {
        public List<answer> GetAnswers()
        {
            var context = new db_projectEntities();
            var answers = context.answer.ToList();
            return answers;
        }
    }
}
