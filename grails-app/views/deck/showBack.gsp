<head>
    <link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'generalStyle.css')}">
</head>
<body>
    <h1>TrAnki</h1>
    <p>Front: ${card.back}</p>
    <br><br>
    <g:link uri="/deck/changeCardDifficulty/${card.id}/${difficulty.EASY}">easy</g:link>
    <br>
    <g:link uri="/deck/changeCardDifficulty/${card.id}/${difficulty.NORMAL}">normal</g:link>
    <br>
    <g:link uri="/deck/changeCardDifficulty/${card.id}/${difficulty.HARD}">hard</g:link>
    <br>
    <br>
    <g:link uri="/deck/showCards/${deckId}">next card</g:link>
</body>
