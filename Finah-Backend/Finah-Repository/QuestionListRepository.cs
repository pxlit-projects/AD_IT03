using Finah_DomainClasses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Finah_Repository
{
    public class QuestionListRepository
    {
        public List<questionlist> GetQuestionLists()
        {
            var context = new db_projectEntities();
            var questionLists = context.questionlist.ToList();
            return questionLists;
        }
    }
}
