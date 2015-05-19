using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DataObjects;
using System.Net;
using System.IO;

namespace Database
{
    public static class DataConnect
    {
        
        public static T GetDataId<T>(string type, int id)
        {
            String input = WebApiConnect.GetConnectionStringWithId(type, id);
            T objectType = (T)Activator.CreateInstance(typeof(T));

            try
            {
                objectType = Newtonsoft.Json.JsonConvert.DeserializeObject<T>(input);

            }
            catch (Newtonsoft.Json.JsonException e)
            {
                Console.Write(e);
            }

            return objectType;
        }

        public static void WebApiWriter(HttpWebRequest httpWebRequest, string json)
        {
            try
            {
                using (var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
                {


                    streamWriter.Write(json);
                    streamWriter.Flush();
                    streamWriter.Close();

                    var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
                    using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
                    {
                        var result = streamReader.ReadToEnd();
                        Console.Write(result.ToString());
                    }
                }

            }
            catch (WebException ex)
            {
                throw ex;
            }
            catch (HttpListenerException ex)
            {
                throw ex;
            }
        }

    }


    

    
}
