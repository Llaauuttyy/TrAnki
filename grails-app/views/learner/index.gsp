<head>
    <link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'generalStyle.css')}">
</head>
<h1>TrAnki</h1>
<p>Enter learner name...</p>
<g:form controller="learner" action="start">
    <input type="text" name="learnerName"/>
    <input type="submit"/>
</g:form>