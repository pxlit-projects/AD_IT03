using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;

namespace Database
{
    public class WebAPIConnect
    {
        private static String URILINK = Properties.Resource.WebApiURL;

        public static String GetConnectionString(string specifier)
        {
            var json = "";

            try
            {
                json = new WebClient().DownloadString(URILINK + specifier);
            }
            catch (WebException e)
            {
                Console.WriteLine("GetConnectionString Error: " + e);
            }
            

            return json;
        }

        public static String GetConnectionStringWithId(string specifier, int id)
        {
            var json = "";

            try
            {
                json = new WebClient().DownloadString(URILINK + specifier + "/" + id);
            }
            catch (WebException e)
            {
                Console.WriteLine("GetConnectionStringWithId Error: " + e);
            }
            return json;
        }

        public static String getURI()
        {
            return URILINK;
        }
        
    }
}
