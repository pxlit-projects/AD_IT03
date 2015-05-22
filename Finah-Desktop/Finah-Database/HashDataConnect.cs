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

        // creates json string and does WebRequest
        private static void WebApiWriterHash(HttpWebRequest httpWebRequest, HashObj hash)
        {
            string json = Newtonsoft.Json.JsonConvert.SerializeObject(hash);

            DataConnect.WebApiWriter(httpWebRequest, json);
        }


    }
}
