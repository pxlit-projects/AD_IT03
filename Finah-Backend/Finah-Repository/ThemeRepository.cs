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

        public theme GetThemeById(int id)
        {
            var context = new db_projectEntities();
            //var customer = context.Customers.First(c => c.CustomerId == id); is hetzelfde als eronder
            var theme = context.theme.Find(id);
            return theme;
        }

        public void UpdateTheme(int id, theme theme)
        {
            using (var context = new db_projectEntities())
            {
                var updatedTheme = context.theme.FirstOrDefault(c => c.id == id);
                //Hier komen de velden die geupdate worden in de stijl als hieronder
                updatedTheme.description = theme.description;
                context.SaveChanges();
            }
        }
    }
}
