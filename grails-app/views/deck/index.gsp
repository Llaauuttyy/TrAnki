<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Decks...</title>
</head>
<body>
    <div id="header">
        <h1>My Decks</h1>
    </div>
    
    <div id="content">
        <ul>
            <g:each in="${learnerDecks}" var="learnerDeck">
                <g:link uri="/deck/index/${learnerDeck.id}">${learnerDeck.name}</g:link>
            </g:each>
        </ul>
    </div>
    
    <div id="footer">
        <p>Derechos de autor &copy; 2023 - Mi Sitio Web</p>
    </div>
</body>
</html>
