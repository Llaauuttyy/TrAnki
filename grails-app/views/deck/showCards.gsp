<h1>TrAnki</h1>
<%-- <g:form controller="learner" action="start"> --%>
<p>Front: ${card.front}</p>
<br>
<p>Difficulty: ${card.difficulty}</p>
<br>
<%-- <p>Back: ${card.back}</p> --%>
<%-- Me fijaria en el controller si ya tiene esa difficultad y si la tiene no hago nada, solo
renderizo la misma view, si no la tiene la cambio y renderizo la misma view. --%>
<g:link uri="/deck/showBack/${learnerId.toInteger()}/${deckId.toInteger()}/${card.id.toInteger()}">show back</g:link>
<br>
<br>
<g:link uri="/deck/changeCardDifficulty/${learnerId.toInteger()}/${deckId.toInteger()}/${card.id.toInteger()}/${difficulty.EASY}">easy</g:link>
<br>
<g:link uri="/deck/changeCardDifficulty/${learnerId.toInteger()}/${deckId.toInteger()}/${card.id.toInteger()}/${difficulty.NORMAL}">normal</g:link>
<br>
<br>
<g:link uri="/deck/showCards/${learnerId.toInteger()}/${deckId.toInteger()}">next card</g:link>