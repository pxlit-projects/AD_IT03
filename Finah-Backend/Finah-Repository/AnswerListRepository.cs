using Finah_DomainClasses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Finah_Repository
{
    public class AnswerListRepository
    {
        public List<answerlist> GetAnswerLists()
        {
            var context = new db_projectEntities();
            var answerLists = context.answerlist.ToList();
            return answerLists;
        }
    }
}
