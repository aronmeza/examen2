<!doctype html>
<html>
	<head>
		<title>Grails Runtime Exception</title>
		<meta name="layout" content="main">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}" type="text/css">
                  <r:require module="jquery-ui"/>
	</head>
	<body>
		<g:renderException exception="${exception}" />
	</body>
</html>