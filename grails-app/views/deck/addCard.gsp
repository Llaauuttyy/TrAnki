<head>
    <link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'generalStyle.css')}">
</head>
<body>
    <h1>TrAnki</h1>
    <p>Enter new card front side (learning language) and back side (native language)...</p>
    <g:form controller="deck" action="addCard" params="[deckId: deckId]">
        <input type="text" name="newCardFront"/>
        <input type="text" name="newCardBack"/>
        <input type="submit"/>
    </g:form>
</body>