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

    }


    

    
}
