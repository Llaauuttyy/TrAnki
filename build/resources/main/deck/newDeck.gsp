<h1>TrAnki</h1>
<%-- <g:form controller="learner" action="start"> --%>
<p>Enter new deck name...</p>
<g:form controller="deck" action="newDeck">
    <input type="text" name="newDeckName" required/>
    <input type="submit"/>
</g:form>