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

De kunnen gevonden worden onder:
Finah-Desktop-Java/src/lib

Finah-Desktop
-------------

Hierin werkt users opvragen, toevoegen, bekijken en bewerken.
Ook kan je al een questionlist opstellen maar nog niet opslaan.

Omdit project te runnen moet je MySql Connector net 6.9.6 geïnstalleerd hebben.

Finah-Backend
-------------

Om dit te runnen dien je het volgende te installeren:
http://dev.mysql.com/downloads/connector/odbc/3.51.html

Hierin werkt communicatie naar de voorlopige database.
Om dit te testen kan je de volgende urls typen achter je localhost adres.

/api/user
/api/usertype
/api/question
/api/questionlist
/api/answer
/api/answerlist
/api/theme
/api/time








