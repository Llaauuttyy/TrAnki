<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'generalStyle.css')}">
    <title>Decks...</title>
</head>
<body>
    <div id="header">
        <h1>${learner.name} decks</h1>
        <h4>Level: ${learner.level}</h4>
    </div>
    <div>
        <p>Max points: ${stats.maxPoints}</p>
        <p>Current points: ${stats.currentPoints}</p>
        <p>Deck amount: ${stats.deckAmount}</p>
        <p>Card amount: ${stats.cardAmount}</p>
    </div>
    <div id="content">
        <ul>
            <g:each in="${learner.decks}" var="learnerDeck"> --%>
                <%-- <g:link controller="deck" action="prueba" params="[deckId: learnerDeck.id.toInteger()]">${learnerDeck.name}</g:link> --%>
                <g:link uri="/deck/showCards/${learnerDeck.id}">${learnerDeck.name}</g:link>
                <g:link uri="/deck/newCard/${learnerDeck.id}">+ add card</g:link>
                <g:link uri="/deck/show/${learnerDeck.id}"> see cards</g:link>
                <g:link uri="/deck/removeDeck/${learnerDeck.id}">- remove</g:link>
                <br>
            </g:each>
        </ul>
    </div>
    <div id="header">
        <g:link uri="/deck/newDeck/${learner.id}">+ add deck</g:link>
    </div>
    
    <div id="footer">
        <p>Derechos de autor &copy; 2023 - Mi Sitio Web</p>
    </div>
</body>
</html>
