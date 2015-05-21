using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using DataObjects;
using Database;


namespace UnitTests
{
    [TestClass]
    public class UnitTest
    {
        [TestMethod]
        public void TestMethod()
        {
        }

        [TestMethod]
        public void HashFucntionTest()
        {
            // arrange
            string expected = "098f6bcd4621d373cade4e832627b4f6";
            string test = "test";


            // act
            string actualHash = UserDataConnect.CalculateMd5Hash(test);

            //assert
            Assert.AreEqual(expected.ToUpper(), actualHash, false, "Hash not calculated correctly");

        }

        /*[TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void TestOutOfRange()
        {
            // arrange

            // act
            double actual = kubus.BerekenVolume();

            //assert

        }*/

    }
}
