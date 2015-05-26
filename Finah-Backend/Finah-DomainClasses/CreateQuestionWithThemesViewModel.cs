using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Finah_DomainClasses
{
    public class CreateQuestionWithThemesViewModel
    {
        public question Question { get; set; }
        public virtual List<theme> Themes { get; set; }
    }
}
