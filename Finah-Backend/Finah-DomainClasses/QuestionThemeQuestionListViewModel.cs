using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Finah_DomainClasses
{
    public class QuestionThemeQuestionListViewModel
    {
        public question Question { get; set; }
        public virtual theme Theme { get; set; }
        public virtual ICollection<questionlist> Questionslists { get; set; }

    }
}
