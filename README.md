# AD_IT03
AppDev repo voor AD_IT03

Groepsleden:
- Vincent De Meersman
- Sven Vranken
- Pieter Switten
- Daan Van Robays
- Timothy Baert
- Emre Altinyay
- Jordy Collas

Finah-Desktop-Java
------------------

Hierin werkt users toevoegen, bekijken, verwijderen en aanpassen.
Je kan ook alle thema's terug vinden onder het tabblad "Vragenlijsten". Hierin zijn alle vensters gemaakt om vragen en thema's toe te voegen maar het wordt nog niet naar de database weggeschreven. Dit komt doordat we alleen nog maar uit de webAPI kunnen lezen.
Users wegschrijven werkt wel, dit komt doordat we rechtstreeks naar de database wegschrijven en niet via de webAPI

Om dit project te kunnen runnen moet er aan het build path 2 jars worden toegevoegd, nl:
- jdatepicker-1.3.4.jar
- mysql-connector-java-5.1.34-bin.jar
- gson-2.3.1.jar
- gson-2.3.1-javadoc.jar
- gson-2.3.1-sources.jar
- 

Je kan inloggen met gebruiker "p" met wachtwoord "p". Als je als dokter wilt inloggen gaat dit onder gebruikersnaam "d" met wachtwoord "d".
Als je als dokter inlogt, kan je de gebruikers niet zien.

De kunnen gevonden worden onder:
Finah-Desktop-Java/src/lib

Screenshots van het java project vindt u hier: http://imgur.com/gallery/U3KI3/new

Finah-Desktop
-------------

Hierin werkt users opvragen, toevoegen, bekijken en bewerken.
Er kunnen ook vragenlijsten opgesteld worden en thema's aangemaakt worden maar nog niet opgeslagen worden. De connectie met de webAPI is nog niet afgewerkt.

Omdit project te runnen moet je MySql Connector net 6.9.6 geïnstalleerd hebben en het Nuget-package WPF Themes voor de kleuren thema.
Een keer de WebAPI connectie werkt is het ook nodig om het Nuget-package JSON.NET te installeren.

Screenshots van het .NET project vindt u hier: http://imgur.com/a/oXih4

Finah-Backend
-------------

De Finah-Backend staat online op de volgende link: http://finah-backend.cloudapp.net
Door de volgende url's achter de link te plaatsen kan u aan de gegevens waarmee we werken.
                            of met een id erachter voor één enkele record.

/api/user                   /api/user/id
/api/usertype               /api/usertype/id
/api/question               /api/question/id
/api/questionlist           /api/questionlist/id
/api/answer                 /api/answer/id
/api/answerlist             /api/answerlist/id
/api/theme                  /api/theme/id
/api/time                   /api/time/id

Voorlopig kan je via de backend enkel gegevens bekijken van de Users. Users adden en andere objecten worden later toegevoegd.

Screenshots van de Finah-Backend vindt u hier: http://imgur.com/a/69JLF