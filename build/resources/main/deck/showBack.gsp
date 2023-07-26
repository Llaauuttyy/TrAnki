<h1>TrAnki</h1>
<%-- <g:form controller="learner" action="start"> --%>
<p>Front: ${card.back}</p>
<%-- <p>Back: ${card.back}</p> --%>
<%-- Me fijaria en el controller si ya tiene esa difficultad y si la tiene no hago nada, solo
renderizo la misma view, si no la tiene la cambio y renderizo la misma view. --%>
<br><br>
<g:link uri="/deck/changeCardDifficulty/${card.id.toInteger()}/${difficulty.EASY}">easy</g:link>
<br>
<g:link uri="/deck/changeCardDifficulty/${card.id.toInteger()}/${difficulty.NORMAL}">normal</g:link>
<br>
<br>
<g:link uri="/deck/showCards/${learnerId.toInteger()}/${deckId.toInteger()}">next card</g:link>