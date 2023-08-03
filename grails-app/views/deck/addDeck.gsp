<head>
    <link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'generalStyle.css')}">
</head>
<body>
    <h1>TrAnki</h1>
    <p>Enter new deck name...</p>
    <g:form controller="deck" action="addDeck" params="[learnerId: learnerId]">
        <input type="text" name="newDeckName"/> 
        <input type="submit"/>
    </g:form>
</body>