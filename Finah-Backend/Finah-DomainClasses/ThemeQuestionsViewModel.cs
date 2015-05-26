using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Finah_DomainClasses
{
    public class ThemeQuestionsViewModel
    {
        public theme Theme { get; set; }
        public virtual ICollection<question> Questions { get; set; }
    }
}
