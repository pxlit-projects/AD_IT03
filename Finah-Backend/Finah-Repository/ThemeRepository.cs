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
            var themeWithId = context.theme.First(t => t.id == id);
            return themeWithId;
        }

        public theme AddTheme(theme newTheme)
        {
            using (var context = new db_projectEntities())
            {
                if (newTheme == null)
                {
                    throw new ArgumentNullException("newTheme");
                }
                context.theme.Add(newTheme);
                context.SaveChanges();
                return newTheme;
            }
        }

        public Boolean UpdateTheme(int id, theme theme)
        {
            if (theme == null || id == null)
            {
                return false;
            }
            else
            {
                using (var context = new db_projectEntities())
                {
                    var updatedTheme = context.theme.First(t => t.id == id);
                    updatedTheme.title = theme.title;
                    updatedTheme.description = theme.description;
                    context.SaveChanges();
                    return true;
                }
            }

        }
        //This also deletes all questions within the theme
        public void DeleteTheme(int id)
        {
            using (var context = new db_projectEntities())
            {
                var themes = context.theme.First(t => t.id == id);
                var questions = context.question.ToList();
                for (int i = 0; i < questions.Count; i++)
                {
                    if (questions[i].theme == themes.id)
                    {
                        context.question.Remove(questions[i]);
                    }
                }
                context.theme.Remove(themes);
                context.SaveChanges();
            }
        }
    }
}
