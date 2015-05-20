using DataObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace Database
{
    public static class ThemeDataConnect
    {

        public static Theme GetTheme(int id)
        {
            String input = WebApiConnect.GetConnectionStringWithId("Theme", id);
            Theme theme = new Theme();

            try
            {
                theme = Newtonsoft.Json.JsonConvert.DeserializeObject<Theme>(input);

            }
            catch (Newtonsoft.Json.JsonException e)
            {
                Console.Write(e);
            }

            return theme;
        }

        public static List<Theme> GetThemes()
        {
            String input = WebApiConnect.GetConnectionString("Theme");
            List<Theme> themes = new List<Theme>();

            try
            {
                themes = Newtonsoft.Json.JsonConvert.DeserializeObject<List<Theme>>(input);

            }
            catch (Newtonsoft.Json.JsonException e)
            {
                Console.Write(e);
            }

            return themes;
        }

        public static void AddTheme(Theme theme)
        {
            var httpWebRequest = (HttpWebRequest)WebRequest.Create(WebApiConnect.GetUri() + "Theme");
            httpWebRequest.ContentType = "text/json";
            httpWebRequest.Method = "POST";

            WebApiWriterTheme(httpWebRequest, theme);
        }

        public static void UpdateTheme(Theme theme)
        {
            var httpWebRequest = (HttpWebRequest)WebRequest.Create(WebApiConnect.GetUri() + "Theme/" + theme.Id);
            httpWebRequest.ContentType = "text/json";
            httpWebRequest.Method = "PUT";

            WebApiWriterTheme(httpWebRequest, theme);
        }

        public static void DeleteTheme(Theme theme)
        {
            var httpWebRequest = (HttpWebRequest)WebRequest.Create(WebApiConnect.GetUri() + "Theme/" + theme.Id);
            httpWebRequest.ContentType = "text/json";
            httpWebRequest.Method = "DELETE";

            WebApiWriterTheme(httpWebRequest, theme);
        }

        // creates json string and does WebRequest
        private static void WebApiWriterTheme(HttpWebRequest httpWebRequest, Theme user)
        {
            string json = Newtonsoft.Json.JsonConvert.SerializeObject(user);

            DataConnect.WebApiWriter(httpWebRequest, json);
        }
    }
}
