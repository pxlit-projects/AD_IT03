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
            try
            {
                var context = new db_projectEntities();
                var themes = context.theme.ToList();
                return themes;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public theme GetThemeById(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var themeWithId = context.theme.First(t => t.id == id);
                return themeWithId;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public theme GetThemeWithQuestions(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var themeWithQuestions = context.theme.Include("Questions").First(t => t.id == id);
                return themeWithQuestions;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public theme AddTheme(theme newTheme)
        {
            try
            {
                var context = new db_projectEntities();
                if (newTheme == null)
                {
                    throw new ArgumentNullException("newTheme");
                }
                context.theme.Add(newTheme);
                context.SaveChanges();
                return newTheme;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public Boolean UpdateTheme(int id, theme theme)
        {
            try
            {
                if (theme == null || id == null)
                {
                    return false;
                }
                else
                {
                    var context = new db_projectEntities();

                    var updatedTheme = context.theme.First(t => t.id == id);
                    updatedTheme.title = theme.title;
                    updatedTheme.description = theme.description;
                    context.SaveChanges();
                    return true;
                }
            }
            catch (Exception)
            {
                return false;
            }
            

        }
        //This also deletes all questions within the theme
        public Boolean DeleteTheme(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var theme = context.theme.First(t => t.id == id);
                var questions = context.question.ToList();
                for (int i = 0; i < questions.Count; i++)
                {
                    if (questions[i].theme == theme.id)
                    {
                        context.question.Remove(questions[i]);
                    }
                }
                context.theme.Remove(theme);
                context.SaveChanges();
                return true;
            }
            catch (Exception)
            {
                return false;
            }
            
        }
    }
}
