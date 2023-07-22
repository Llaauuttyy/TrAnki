<h1>TrAnki</h1>
<%-- <g:form controller="learner" action="start"> --%>
<p>Enter new card front side (learning language) and back side (native language)...</p>
<g:form controller="deck" action="addCard" params="[learnerId: learnerId, deckId: deckId]">
    <input type="text" name="newCardFront" required/>
    <input type="text" name="newCardBack" required/>
    <input type="submit"/>
</g:form>