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
        <%-- <p>Deck list</p> --%>
        <ul>
            <g:each in="${learnerDecks}" var="learnerDeck">
                <%-- <g:form controller="deck" action="prueba" a="learnerDeck.name">
                    <input type="hidden" name="deckId" value="${learnerDeck.id}" />
                    <input value="${learnerDeck.name}" type="submit"/>
                </g:form> --%>
                <%-- <g:link controller="deck" action="prueba" params="[deckId: learnerDeck.id.toInteger()]">${learnerDeck.name}</g:link> --%>
                <g:link uri="/deck/index/${learnerDeck.id.toInteger()}">${learnerDeck.name}</g:link>
            </g:each>
        </ul>
    </div>
    
    <div id="footer">
        <p>Derechos de autor &copy; 2023 - Mi Sitio Web</p>
    </div>
</body>
</html>
