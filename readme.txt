Cybersecurity project 1. You’ll need a browser and OWASP ZAP to do this. 

You'll find my files from here https://github.com/Mikkopoika/cybersecuritybase-project

Issues: 
1.	Sensitive Data Exposure
2.	Cross-site scripting, XSS-attack
3.	Unvalidated redirect
4.	Cross-Site Request Forgery
5.	Missing Function Level Access Control



Issue 1 Sensitive Data Exposure steps. 
1.	Open the page
2.	Enter name and address two times
3.	On the second done-page, change the address from ”http://localhost:8080/done/2” to ”http://localhost:8080/done/1”. You can now see the done-page of the first signup.
Issue 1 fix
1.	Authenticate users or use unpredictable IDs.

Issue 2 XSS-attack steps
1.	Open the page
2.	Copy <script>alert("Hello there")</script> to the address-space
3.	As you submit the form, your script is executed.
Issue 2 fix
1.	The done.html uses ”utext” which is unescaped text. You can fix this by changing ”th:utext” to ”th:text”, which is escaped text. After that the scripts won’t work. 
Issue 3 Unvalidated redirect steps
1.	Open the page 
2.	Enter some data and click submit.
3.	On done-page open developer tools from your browser
4.	Look for row with <a href="http://localhost:8080">Back to the signup-page</a> and change address to ”http://localhost:8080/secret"
5.	Click the hyperlink
Issue 3 fix
1.	Don’t put any addresses to the html, handle them on the server side.

Issue 4 Cross-Site Request Forgery
1.	Open OWASP ZAP 
2.	Run 'generate anti CSRF test FORM' to POST:form(address,name)
3.	Browser opens up 
4.	Submit form 

Issue 4 fix
1.	Comment or delete following line on the SecurityConfiguration: http.csrf().disable();
	
Issue 5 Missing Function Level Access Control
1.	Write http://localhost:8080/secret to the address
2.	Gain access to a secret site that you shouldn’t see

Issue 5 Fix
1.	Use login and authenticate users
2.	Show page only to authenticated users
