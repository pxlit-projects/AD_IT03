using System;

namespace Finah_UnitTests
{
    [TestClass]
    public class UnitTests
    {
        
        [TestMethod]
        public void TestMethod1()
        {
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void Kubus_equalSides()
        {
            // arrange
            double breedte = 5;
            double lengte = 6;
            double  hoogte = 7;
            Kubus kubus = new Kubus(breedte, lengte, hoogte);

            // act
            double actual = kubus.BerekenVolume();

            //assert

        }

        [TestMethod]
        public void Kubus_checkOppervlakte()
        {
            // arrange
            double breedte = 5;
            double lengte = 5;
            double hoogte = 5;
            double expected = 150;
            Kubus kubus = new Kubus(breedte, lengte, hoogte);

            // act
            double actual = kubus.BerekenOppervlakte();

            //assert
            Assert.AreEqual(expected, actual, 0, "Oppervlakte niet juist berekend");

        }

        [TestMethod]
        public void Kubus_checkVolume()
        {
            // arrange
            double breedte = 5;
            double lengte = 5;
            double hoogte = 5;
            double expected = 125;
            Kubus kubus = new Kubus(breedte, lengte, hoogte);

            // act
            double actual = kubus.BerekenVolume();

            //assert
            Assert.AreEqual(expected, actual, 0, "Oppervlakte niet juist berekend");

        }

    }
}