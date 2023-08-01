<head>
    <link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'generalStyle.css')}">
</head>

<h1>TrAnki</h1>
<p>Enter new deck name...</p>
<g:form controller="deck" action="newDeck">
    <input type="text" name="newDeckName" required/>
    <input type="submit"/>
</g:form>