using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using DataObjects;

namespace Database
{
    public class HashDataConnect
    {
    
        public static void AddHash(HashObj hash)
        {
            var httpWebRequest = (HttpWebRequest)WebRequest.Create(WebApiConnect.GetUri() + "Hashes");
            httpWebRequest.ContentType = "text/json";
            httpWebRequest.Method = "POST";

            WebApiWriterHash(httpWebRequest, hash);

        }

        public static List<HashObj> GetHashesList()
        {
            String input = WebApiConnect.GetConnectionString("Hashes");
            List<HashObj> hashesList = new List<HashObj>();

            try
            {
                hashesList = Newtonsoft.Json.JsonConvert.DeserializeObject<List<HashObj>>(input);

            }
            catch (Newtonsoft.Json.JsonException e)
            {
                Console.Write(e);
            }

            return hashesList;
        }

        // creates json string and does WebRequest
        private static void WebApiWriterHash(HttpWebRequest httpWebRequest, HashObj hash)
        {
            string json = Newtonsoft.Json.JsonConvert.SerializeObject(hash);

            DataConnect.WebApiWriter(httpWebRequest, json);
        }


    }
}
