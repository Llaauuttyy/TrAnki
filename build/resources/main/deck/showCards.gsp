<h1>TrAnki</h1>
<%-- <g:form controller="learner" action="start"> --%>
<p>Front: ${card.front}</p>
<p>Back: ${card.back}</p>
<%-- Me fijaria en el controller si ya tiene esa difficultad y si la tiene no hago nada, solo
renderizo la misma view, si no la tiene la cambio y renderizo la misma view. --%>
<g:link uri="/deck/changeCardDifficulty/${card.id.toInteger()}/easy">easy</g:link>
<br>
<g:link uri="/deck/changeCardDifficulty/${card.id.toInteger()}/normal">normal</g:link>
<br>
<br>
<g:link uri="/deck/showCards/${deckId.toInteger()}">next card</g:link>