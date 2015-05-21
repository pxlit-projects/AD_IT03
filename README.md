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

De .jar files kunnen gevonden worden onder:
Finah-Desktop-Java/src/lib


- Screenshots Finah-Desktop-Java
-
Login screen

![Login Screen](/Screenshots/Finah-Desktop-Java/Login.png)

SummaryPanel

![Summary Screen](/Screenshots/Finah-Desktop-Java/SummaryPanel.PNG)

Send questionlists

![Send questionlists](/Screenshots/Finah-Desktop-Java/SendQuenstionList.png)

Users window

![Users Window](/Screenshots/Finah-Desktop-Java/Users.png)

Add user window

![Add user window](/Screenshots/Finah-Desktop-Java/AddUser.png)

Edit user window

![Edituser window](/Screenshots/Finah-Desktop-Java/EditUser.png)

User details window

![User details window](/Screenshots/Finah-Desktop-Java/UserDetails.png)

Theme list

![Theme list window](/Screenshots/Finah-Desktop-Java/ThemeList.png)

Add theme

![Add theme window](/Screenshots/Finah-Desktop-Java/AddThemeEmpty.png)

Add theme

![Add theme window](/Screenshots/Finah-Desktop-Java/AddThemeFull.png)

Add questions

![Add questions window](/Screenshots/Finah-Desktop-Java/AddQuestions.png)

![Add questions window](/Screenshots/Finah-Desktop-Java/addquestionwindow.png)

Question list

![Question list window](/Screenshots/Finah-Desktop-Java/Questionlist.png)



Finah-Desktop
-------------

Hierin werkt users opvragen, toevoegen, bekijken en bewerken.
Er kunnen ook vragenlijsten opgesteld worden en thema's aangemaakt worden maar nog niet opgeslagen worden. De connectie met de webAPI is nog niet afgewerkt.

Om dit project te runnen moet je MySql Connector net 6.9.6 geïnstalleerd hebben en het Nuget-package WPF Themes voor de kleuren thema.
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


Finah-Site
-------------
Finah-site met php en jquery.
Versie 1.0 - screenshot : http://www.synbitz.net/share/finah-website.png
- heb de opmerking gekregen dat deze te "prikkelbaar/afleidend" was door de header. 
- ook de text was te klein en de text in de thema velden had te weinig contrast met de achtergrond.
- knoppen om aan te klikken waren ook te klein
Verie 2.0 
- Screenshot desktop : http://www.synbitz.net/share/finah-web-desktop-2.png
- Screenshot mobile :  http://www.synbitz.net/share/finah-web-mobile-2.png
- met versie 2.0 is de layout geoptimalizeerd met grotere text en knoppen.
- De header is verdwenen omdat deze mogelijk de aandacht afleid.
