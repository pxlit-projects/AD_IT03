using Finah_DomainClasses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Finah_Repository
{
    public class ThemeRepository
    {
        public List<theme> GetThemes()
        {
            var context = new db_projectEntities();
            var themes = context.theme.ToList();
            return themes;
        }
    }
}
