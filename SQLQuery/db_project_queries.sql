/* example queries tested and working.*/

SELECT 
questionlist.list,
question.choice,
question.description,
question.theme,
question.title,
theme.description,
theme.title,
user.type,
user.firstname,
usertype.screenname
FROM `questionlist`
JOIN `question` on questionlist.question = question.id
JOIN `theme` on question.theme = theme.id
JOIN `user` on questionlist.user = user.id 
JOIN `usertype` on user.type = usertype.id
WHERE theme.title = 'Leren en toepassen van kennis' AND questionlist.list = 1;
/* 
retrieves the questionlist questions that are part of the first questionlist - and where the theme title is 'Leren en toepassen van kennis'
it also retrieves the theme , the user who created the questionlist and the type of said user.
you can easily add and modify this base query to retrieve anything from the database regarding a questionlist.
*/


/* query that retrieves answers */ 

SELECT
answerlist.workpoint AS 'Werkpunt',
answer.title AS 'Antwoord',
answer.number AS 'Score',
answer.choice AS 'Multiple choice',
question.title AS 'Vraag',
theme.title AS 'Thema',
user.login AS 'Gebruiker',
usertype.screenname AS 'Functie',
time.datetime AS 'datum',
time.time AS 'invultijd'

FROM `answerlist`
LEFT JOIN `answer` on answerlist.answer = answer.id
LEFT JOIN `question` on answerlist.question = question.id
LEFT JOIN `theme` on question.theme = theme.id
LEFT JOIN `user` on answerlist.user = user.id
LEFT JOIN `usertype` on user.type = usertype.id
LEFT JOIN `time` on answerlist.user = time.user AND answerlist.list = time.list

