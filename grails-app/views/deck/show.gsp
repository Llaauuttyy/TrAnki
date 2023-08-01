<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'generalStyle.css')}">
    <title>Cards...</title>

</head>
<body>
    <div id="header">
        <h1>${learner.name} decks</h1>
        <h4>Level: ${learner.level}</h4>
    </div>
    <div id="content">
        <ul>
            <g:each in="${cards}" var="card">
                <p>Front: ${card.front}</p>
                <p>Back: ${card.back}</p>
                <p>Difficulty: ${card.difficulty}</p>
                <g:link uri="/deck/removeCard/${card.id}">- remove</g:link>
                <br>
                <br>
            </g:each>
        </ul>
    </div>
    
    <div id="footer">
        <p>Derechos de autor &copy; 2023 - Mi Sitio Web</p>
    </div>
</body>
</html>
