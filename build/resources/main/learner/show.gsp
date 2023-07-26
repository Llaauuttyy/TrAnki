<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Decks...</title>
</head>
<body>
    <div id="header">
        <h1>${learner.name} decks</h1>
        <h4>Level: ${learner.level}</h4>
    </div>
    
    <div id="content">
        <%-- <p>Deck list</p> --%>
        <ul>
            <g:each in="${learner.decks}" var="learnerDeck">
                <%-- <g:form controller="deck" action="prueba" a="learnerDeck.name">
                    <input type="hidden" name="deckId" value="${learnerDeck.id}" />
                    <input value="${learnerDeck.name}" type="submit"/>
                </g:form> --%>
                <%-- <g:link controller="deck" action="prueba" params="[deckId: learnerDeck.id.toInteger()]">${learnerDeck.name}</g:link> --%>
                <g:link uri="/deck/showCards/${learner.id.toInteger()}/${learnerDeck.id.toInteger()}">${learnerDeck.name}</g:link>
                <g:link uri="/deck/newCard/${learner.id.toInteger()}/${learnerDeck.id.toInteger()}">+ add card</g:link>
                <g:link uri="/deck/removeDeck/${learner.id.toInteger()}/${learnerDeck.id.toInteger()}">- remove</g:link>
                <br>
            </g:each>
        </ul>
    </div>
    <div id="header">
        <g:link uri="/deck/newDeck/${learner.id.toInteger()}">+ add deck</g:link>
    </div>
    
    <div id="footer">
        <p>Derechos de autor &copy; 2023 - Mi Sitio Web</p>
    </div>
</body>
</html>
