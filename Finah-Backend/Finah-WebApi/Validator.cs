using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebAPI
{
    public class Validator
    {
        public static bool IsPositive(int number)
        {
            return number > 0;
        }

        public static bool IsNegative(int number)
        {
            return number < 0;
        }

        public static bool IsZero(int number)
        {
            return number == 0;
        }
    }
}