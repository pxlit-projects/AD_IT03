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

Rapport window

![Summary Screen](/Screenshots/Finah-Desktop-Java/RapportWindow.PNG)

Send questionlists

![Send questionlists](/Screenshots/Finah-Desktop-Java/SendQuenstionList.PNG)

Users window

![Users Window](/Screenshots/Finah-Desktop-Java/Users.png)

Add user window

![Add user window](/Screenshots/Finah-Desktop-Java/AddUser.png)

Edit user window

![Edituser window](/Screenshots/Finah-Desktop-Java/EditUser.png)

User details window

![User details window](/Screenshots/Finah-Desktop-Java/UserDetails.png)

Theme list

![Theme list window](/Screenshots/Finah-Desktop-Java/themeList.JPG)

Add theme

![Add theme window](/Screenshots/Finah-Desktop-Java/AddThemeEmpty.png)

Add theme

![Add theme window](/Screenshots/Finah-Desktop-Java/AddThemeFull.png)

Add questions

![Add questions window](/Screenshots/Finah-Desktop-Java/addQuestions.png)

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

Finah-WebApi
-------------

De Finah-WebApi staat online op de volgende link: finah-webapi-appdevit03.azurewebsites.net
Door de volgende url's achter de link te plaatsen kan u aan de gegevens waarmee we werken.
                            of met een id erachter voor één enkele record.

/api/user                   /api/user/{id} 
/api/usertype               /api/usertype/{id} 
/api/question               /api/question/{id} 
/api/questionlist           /api/questionlist/{id} 
/api/answer                 /api/answer/{id} 
/api/answerlist             /api/answerlist/{id}        of met een hash     /api/answerList/GetAnswerlistByHash/{hash} 
/api/theme                  /api/theme/{id} 
/api/time                   /api/time/{id} 
/api/hashes                 /api/hashes/{id}            of met een hash     /api/Hashes/GetHashByHash/{hash}

- Screenshots Web Api
- Home Page
 ![Web API home page](/Screenshots/Finah-WebApi-Screens/Web API home page.png)

- Help Page
 ![Web API Help Page](/Screenshots/Finah-WebApi-Screens/Finah Web API Help Page.png)

- Example GET finah-webapi-appdevit03.azurewebsites.net/api/user
 ![Web API home page](/Screenshots/Finah-WebApi-Screens/api_user.png)


Finah-Backend
-------------

De Finah-Backend staat online op de volgende link: finah-backend-appdevit03.azurewebsites.net
Met deze backend kan je alle gegevens van het project bekijken, aanpassen of verwijderen.
Het is natuurlijk ook mogelijk om gegevens toe te voegen via deze manier.

- Screenshots Finah-Backend
- Home page
 ![Backend Home page](/Screenshots/Finah-Backend-Screens/Home Page.png)

- Index Users
 ![Backend Users index](/Screenshots/Finah-Backend-Screens/Index.png)

- Edit User
 ![Backend Edit User](/Screenshots/Finah-Backend-Screens/Edit.png)

- Details of a User with Usertype
 ![Backend Details of a User with Usertype](/Screenshots/Finah-Backend-Screens/DetailsWithUsertype.png)

 - Create User
 ![Backend Create User](/Screenshots/Finah-Backend-Screens/Create.png)

 - Delete User
 ![Backend Delete User](/Screenshots/Finah-Backend-Screens/Delete.png)


Finah-Android
-------------

Een Android-App met de functionaliteit om gegevens van users, usertypes, answers, questions en themes te bekijken.
Enkel een admin user kan op deze app inloggen. U kan het testen door in te loggen met login='sven' pwd='p'.

- Screenshots Finah-Android
- Login Screen
 ![Android Login Screen](/Screenshots/Finah-Android-Screens/LoginScreen.png)

- Admin Screen
 ![Android Admin Screen](/Screenshots/Finah-Android-Screens/AdminScreen.png)

- Users screen
 ![Android Users Screen](/Screenshots/Finah-Android-Screens/Users.png)

- User Details screen
 ![Android UserDetails Screen](/Screenshots/Finah-Android-Screens/UserDetails.png)


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

Versie 3.0
- 
- Screenshot desktop 
- ![desktop](http://www.synbitz.net/share/finah-web-desktop-2.png)
- Screenshot mobile :  
- ![mobile](http://www.synbitz.net/share/finah-web-mobile-2.png)
- Screenshot eindscherm :  
- ![eindraport](http://www.synbitz.net/share/end-page.png)
- Screenshot error :  
- ![desktop](http://www.synbitz.net/share/error-page.png)

live version : http://finah-web.cloudapp.net
