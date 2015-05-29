<p><strong>Assumptions</strong></p>

<ul>
	<li>SUPER user cannot be created by using the API endpoints.</li>
	<li>Users have only 1 role.</li>
	<li>User&#39;s role can be changed when the user is updated.</li>
	<li>While updating a User, all the new data should be sent to the API by Http PUT method.</li>
	<li>Creation of Subscribers does not require token. All other endpoints require token.</li>
	<li>Users should Login to get a token.</li>
	<li>Clients should send token along with every request to the API.</li>
</ul>

<p><strong>Comments</strong></p>

<p><em>Given &#39;the use of 3rd party libraries should be minimised&#39;,</em></p>

<ul>
	<li>Roles are handled by hand. -&nbsp;I would have used an existing framework such as Spring Security.</li>
	<li>Tokens are handled by hand. -&nbsp;For a production application would be used an existing implementation such as OAuth.</li>
	<li>Persistence just consists in memory storage for Users and Tokens. -&nbsp;I would have used existing ORMs such as Hibernate or JPA.</li>
</ul>

<p><em>Since this API is a POC, there are tests pending and lack of validations like:&nbsp;</em></p>

<ul>
	<li>email should be unique</li>
	<li>token should be unique</li>
	<li>password encrypt, etc</li>
</ul>

<p><em>Ready to Login</em></p>

<p>Some Users are ready to be used once the API is up and running.<br />
<em>Credentials for each one of them are:</em></p>

<p>SUPER user</p>

<blockquote>
<p>{<br />
&nbsp; &nbsp;&quot;email&quot;: &quot;super@user.com&quot;,<br />
&nbsp; &nbsp;&quot;password&quot;: &quot;pass&quot;<br />
}</p>
</blockquote>

<p>ADMIN user</p>

<blockquote>
<p>{<br />
&nbsp; &nbsp;&quot;email&quot;: &quot;admin@user.com&quot;,<br />
&nbsp; &nbsp;&quot;password&quot;: &quot;pass&quot;<br />
}</p>
</blockquote>

<p>SUBSCRIBER user</p>

<blockquote>
<p>{<br />
&nbsp; &nbsp;&quot;email&quot;: &quot;subscriber@user.com&quot;,<br />
&nbsp; &nbsp;&quot;password&quot;: &quot;pass&quot;<br />
}</p>
</blockquote>

<p><em>Clone, Build and Run</em></p>

<p>API built with Java 8, Spring Boot and Maven 3.<br />
Spring was used for dependency injection and MVC support.<br />
jUnit and Mocktio used for unit testing.</p>

<p>Should you clone the repo, build the project and run the API.<br />
An embedded Tomcat will serve the app under localhost:8080.</p>

<blockquote>
<p><span style="font-size:14px;">git clone https://github.com/pangio/users-rest.git<br />
mvn clean package<br />
java -jar target/users-api-0.0.1.jar</span></p>
</blockquote>

<p>​</p>
</br>
<p><span style="color:#0000FF;"><strong>API DOCUMENTATION</strong></span></p>

<p><strong>GENERAL OVERVIEW</strong></p>

<table border="0" cellpadding="1" cellspacing="1">
	<tbody>
		<tr>
			<td><strong>Method</strong></td>
			<td><strong>Route</strong></td>
			<td><strong>Description</strong></td>
		</tr>
		<tr>
			<td>POST</td>
			<td>/session/login</td>
			<td>User login</td>
		</tr>
		<tr>
			<td>POST</td>
			<td>/session/logout</td>
			<td>User logout</td>
		</tr>
		<tr>
			<td>POST</td>
			<td>/user/admin</td>
			<td>Creates a new user&nbsp;<span style="line-height: 20.7999992370605px;">admin</span></td>
		</tr>
		<tr>
			<td>PUT</td>
			<td>/user/<span style="line-height: 20.7999992370605px;">admin</span>​/<span style="line-height: 20.7999992370605px;">{id}</span></td>
			<td>Updates a <span style="line-height: 20.7999992370605px;">user&nbsp;admin</span></td>
		</tr>
		<tr>
			<td>GET</td>
			<td><span style="line-height: 20.7999992370605px;">/user/admin​/{id}</span></td>
			<td>Retrieves a&nbsp;<span style="line-height: 20.7999992370605px;">user&nbsp;admin</span></td>
		</tr>
		<tr>
			<td>GET</td>
			<td>/user/<span style="line-height: 20.7999992370605px;">admin</span></td>
			<td>Retrieves all <span style="line-height: 20.7999992370605px;">user&nbsp;admins</span></td>
		</tr>
		<tr>
			<td>DELETE</td>
			<td><span style="line-height: 20.7999992370605px;">/user/admin​/{id}</span></td>
			<td>Deletes a&nbsp;<span style="line-height: 20.7999992370605px;">user&nbsp;admin</span></td>
		</tr>
		<tr>
			<td>POST</td>
			<td>/user/subscriber</td>
			<td>Creates a new subscriber</td>
		</tr>
		<tr>
			<td>PUT</td>
			<td>/user/subscriber​/<span style="line-height: 20.7999992370605px;">{id}</span></td>
			<td>Updates a <span style="line-height: 20.7999992370605px;">subscriber</span></td>
		</tr>
		<tr>
			<td>GET</td>
			<td><span style="line-height: 20.7999992370605px;">/user/subscriber​/</span><span style="line-height: 20.7999992370605px;">{id}</span></td>
			<td>Retrieves a&nbsp;<span style="line-height: 20.7999992370605px;">subscriber</span></td>
		</tr>
		<tr>
			<td>GET</td>
			<td>/user/subscriber</td>
			<td>Retrieves all <span style="line-height: 20.7999992370605px;">subscribers</span></td>
		</tr>
		<tr>
			<td>DELETE</td>
			<td><span style="line-height: 20.7999992370605px;">/user/subscriber​/</span><span style="line-height: 20.7999992370605px;">{id}</span></td>
			<td>Deletes a&nbsp;<span style="line-height: 20.7999992370605px;">subscriber</span></td>
		</tr>
	</tbody>
</table>

<strong>Response Errors</strong>

<p>Not Authorized user error : HTTP status code 403: FORBIDDEN</p>
<p>Not Found user error : HTTP status code 404: NOT_FOUND</p>

</br>
<p><strong>SESSION OVERVIEW&nbsp;</strong></p>

<table border="0" cellpadding="1" cellspacing="1">
	<tbody>
		<tr>
			<td><strong>Method</strong></td>
			<td><strong>Route</strong></td>
			<td><strong>Description</strong></td>
		</tr>
		<tr>
			<td>POST</td>
			<td>/session/login</td>
			<td>User login</td>
		</tr>
		<tr>
			<td>POST</td>
			<td>/session/logout</td>
			<td>User logout</td>
		</tr>
	</tbody>
</table>

<p><strong>User Login&nbsp;</strong></p>

<table border="0" cellpadding="1" cellspacing="1">
	<tbody>
		<tr>
			<td>POST</td>
			<td>/session/login</td>
			<td>User login</td>
		</tr>
	</tbody>
</table>

<p><strong><span style="line-height: 1.6em;">Request body:</span></strong></p>

<p>{<br />
&nbsp; &nbsp;&quot;email&quot;: &quot;super@user.com&quot;,<br />
&nbsp; &nbsp;&quot;password&quot;: &quot;pass&quot;<br />
}</p>
<p><strong>Response:&nbsp;</strong><span style="line-height: 1.6em;">HTTP status code 200: OK</span></p>
<p><strong>Response body:</strong></p>

<p>{<br />
&nbsp; &nbsp;&quot;token&quot;: &quot;3412341234124&quot;<br />
}</p>

<p><strong><span style="line-height: 1.6em;">User logout</span></strong></p>

<table border="0" cellpadding="1" cellspacing="1">
	<tbody>
		<tr>
			<td>POST</td>
			<td>/session/logout</td>
			<td>User logout</td>
		</tr>
	</tbody>
</table>

<p style="line-height: 20.7999992370605px;"><strong style="line-height: 1.6em;">Request header:</strong></p>

<p style="line-height: 20.7999992370605px;"><span style="line-height: 1.6em;">{</span></p>

<p style="line-height: 20.7999992370605px;"><span style="line-height: 1.6em;">&nbsp; &nbsp;&quot;token&quot;: &quot;3412341234124&quot;</span></p>

<p>}</p>

<p><strong>Response:&nbsp;</strong><span style="line-height: 1.6em;">HTTP status code 200: OK</span></p>

</br>
<p><strong>ADMIN&nbsp;OVERVIEW</strong></p>

<table cellpadding="1" cellspacing="1">
	<tbody>
		<tr>
			<td><strong>Method</strong></td>
			<td><strong>Route</strong></td>
			<td><strong>Description</strong></td>
		</tr>
		<tr>
			<td>POST</td>
			<td>/user/admin</td>
			<td>Creates a new user&nbsp;<span style="line-height: 20.7999992370605px;">admin</span></td>
		</tr>
		<tr>
			<td>PUT</td>
			<td>/user/<span style="line-height: 20.7999992370605px;">admin</span>​/<span style="line-height: 20.7999992370605px;">{id}</span></td>
			<td>Updates a <span style="line-height: 20.7999992370605px;">user&nbsp;admin</span></td>
		</tr>
		<tr>
			<td>GET</td>
			<td><span style="line-height: 20.7999992370605px;">/user/admin​/{id}</span></td>
			<td>Retrieves a&nbsp;<span style="line-height: 20.7999992370605px;">user&nbsp;admin</span></td>
		</tr>
		<tr>
			<td>GET</td>
			<td>/user/<span style="line-height: 20.7999992370605px;">admin</span></td>
			<td>Retrieves all <span style="line-height: 20.7999992370605px;">user&nbsp;admins</span></td>
		</tr>
		<tr>
			<td>DELETE</td>
			<td><span style="line-height: 20.7999992370605px;">/user/admin​/{id}</span></td>
			<td>Deletes a&nbsp;<span style="line-height: 20.7999992370605px;">user&nbsp;admin</span></td>
		</tr>
	</tbody>
</table>

<p><span style="line-height: 1.6em;">Creates new </span><span style="line-height: 20.7999992370605px;">user&nbsp;</span><span style="line-height: 20.7999992370605px;">admin</span></p>

<table cellpadding="1" cellspacing="1">
	<tbody>
		<tr>
			<td>POST</td>
			<td>/user/admin</td>
			<td>Creates a new user&nbsp;<span style="line-height: 20.7999992370605px;">admin</span></td>
		</tr>
	</tbody>
</table>

<p><br />
<strong style="line-height: 1.6em;"><span style="line-height: 1.6em;">Request body:</span></strong></p>

<p><span style="line-height: 20.7999992370605px;">{</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;title&quot;: &quot;Mr&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;firstName&quot;: &quot;Pablo&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;lastName&quot;: &quot;Angio&quot;,&nbsp;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp; &nbsp; &nbsp; &nbsp; &quot;credentials&quot;:{</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp; &nbsp; &quot;email&quot;: &quot;pablo@gmail.com&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp; &nbsp; &quot;password&quot;: &quot;123123123&quot;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp; &nbsp; &nbsp; &nbsp;},</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;dateOfBirth&quot;: &quot;11-11-2000&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;homeAddress&quot;: &quot;my home address&quot; ,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;billingAddress&quot;: &quot;my billing address&quot;&nbsp;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">}</span></p>

<p><strong>Response:&nbsp;</strong><span style="line-height: 1.6em;">HTTP status code 201: CREATED</span></p>

<p><strong style="line-height: 1.6em;">Response body:</strong></p>

<p>{<br />
&nbsp;&nbsp; &nbsp;&quot;id&quot;: &quot;1&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;title&quot;: &quot;Mr&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;firstName&quot;: &quot;Pablo&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;lastName&quot;: &quot;Angio&quot;,&nbsp;<br />
&nbsp; &nbsp; &nbsp; &nbsp; &quot;credentials&quot;:{<br />
&nbsp;&nbsp; &nbsp; &nbsp; &quot;email&quot;: &quot;pablo@gmail.com&quot;,<br />
&nbsp;&nbsp; &nbsp; &nbsp; &quot;password&quot;: &quot;123123123&quot;<br />
&nbsp; &nbsp; &nbsp; &nbsp;},<br />
&nbsp;&nbsp; &nbsp;&quot;role&quot;: &quot;ADMIN&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;dateOfBirth&quot;: &quot;11-11-2000&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;homeAddress&quot;: &quot;my home address&quot; ,<br />
&nbsp;&nbsp; &nbsp;&quot;billingAddress&quot;: &quot;my billing address&quot;&nbsp;<br />
}</p>

<p><strong><span style="line-height: 20.7999992370605px;">Updates a&nbsp;</span><span style="line-height: 20.7999992370605px;">user&nbsp;admin</span></strong></p>

<table cellpadding="1" cellspacing="1">
	<tbody>
		<tr>
			<td>PUT</td>
			<td>/user/<span style="line-height: 20.7999992370605px;">admin</span>​/<span style="line-height: 20.7999992370605px;">{id}</span></td>
			<td>Updates a&nbsp;<span style="line-height: 20.7999992370605px;">user&nbsp;admin</span></td>
		</tr>
	</tbody>
</table>

<p style="line-height: 20.7999992370605px;"><strong style="line-height: 1.6em;">Request </strong><strong>Headers:&nbsp;</strong><br />
{<br />
&nbsp; &nbsp;&quot;token&quot;: &quot;3412341234124&quot;<br />
}</p>

<p style="line-height: 20.7999992370605px;"><strong>Request Body:&nbsp;</strong></p>

<p style="line-height: 20.7999992370605px;">{<br />
&nbsp;&nbsp; &nbsp;&quot;title&quot;: &quot;Mr&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;firstName&quot;: &quot;Pablo&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;lastName&quot;: &quot;Angio&quot;,&nbsp;<br />
&nbsp; &nbsp; &nbsp; &nbsp; &quot;credentials&quot;:{<br />
&nbsp;&nbsp; &nbsp; &nbsp; &quot;email&quot;: &quot;pablo@gmail.com&quot;,<br />
&nbsp;&nbsp; &nbsp; &nbsp; &quot;password&quot;: &quot;123123123&quot;<br />
&nbsp; &nbsp; &nbsp; &nbsp;},<br />
&nbsp;&nbsp; &nbsp;&quot;role&quot;: &quot;ADMIN&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;dateOfBirth&quot;: &quot;11-11-2000&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;homeAddress&quot;: &quot;my home address&quot; ,<br />
&nbsp;&nbsp; &nbsp;&quot;billingAddress&quot;: &quot;my billing address&quot;&nbsp;<br />
}</p>

<p style="line-height: 20.7999992370605px;"><strong>Response:&nbsp;</strong>HTTP status code 200: OK</p>

<p style="line-height: 20.7999992370605px;">&nbsp;</p>

<p style="line-height: 20.7999992370605px;"><strong style="line-height: 1.6em;">Response body:</strong></p>

<div>{<br />
&nbsp;&nbsp; &nbsp;&quot;id&quot;: &quot;1&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;title&quot;: &quot;Mr&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;firstName&quot;: &quot;Pablo&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;lastName&quot;: &quot;Angio&quot;,&nbsp;<br />
&nbsp; &nbsp; &nbsp; &nbsp; &quot;credentials&quot;:{<br />
&nbsp;&nbsp; &nbsp; &nbsp; &quot;email&quot;: &quot;pablo@gmail.com&quot;,<br />
&nbsp;&nbsp; &nbsp; &nbsp; &quot;password&quot;: &quot;123123123&quot;<br />
&nbsp; &nbsp; &nbsp; &nbsp;},<br />
&nbsp;&nbsp; &nbsp;&quot;role&quot;: &quot;ADMIN&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;dateOfBirth&quot;: &quot;11-11-2000&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;homeAddress&quot;: &quot;my home address&quot; ,<br />
&nbsp;&nbsp; &nbsp;&quot;billingAddress&quot;: &quot;my billing address&quot;&nbsp;<br />
}</div>

<p><strong><span style="line-height: 20.7999992370605px;">Retrieves a&nbsp;</span><span style="line-height: 20.7999992370605px;">user&nbsp;admin</span></strong></p>

<table cellpadding="1" cellspacing="1">
	<tbody>
		<tr>
			<td>GET</td>
			<td><span style="line-height: 20.7999992370605px;">/user/admin​/{id}</span></td>
			<td>Retrieves a&nbsp;<span style="line-height: 20.7999992370605px;">user&nbsp;admin</span></td>
		</tr>
	</tbody>
</table>

<p style="line-height: 20.7999992370605px;"><strong style="line-height: 1.6em;">Request&nbsp;</strong><strong style="line-height: 20.7999992370605px;">Headers:&nbsp;</strong><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">{</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp; &nbsp;&quot;token&quot;: &quot;3412341234124&quot;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">}</span></p>

<p style="line-height: 20.7999992370605px;"><strong>Response:&nbsp;</strong>HTTP status code 200: OK</p>

<p style="line-height: 20.7999992370605px;"><strong style="line-height: 1.6em;">Response body:</strong></p>

<p style="line-height: 20.7999992370605px;">{<br />
&nbsp;&nbsp; &nbsp;&quot;id&quot;: &quot;1&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;title&quot;: &quot;Mr&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;firstName&quot;: &quot;Pablo&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;lastName&quot;: &quot;Angio&quot;,&nbsp;<br />
&nbsp; &nbsp; &nbsp; &nbsp; &quot;credentials&quot;:{<br />
&nbsp;&nbsp; &nbsp; &nbsp; &quot;email&quot;: &quot;pablo@gmail.com&quot;,<br />
&nbsp;&nbsp; &nbsp; &nbsp; &quot;password&quot;: &quot;123123123&quot;<br />
&nbsp; &nbsp; &nbsp; &nbsp;},<br />
&nbsp;&nbsp; &nbsp;&quot;role&quot;: &quot;ADMIN&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;dateOfBirth&quot;: &quot;11-11-2000&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;homeAddress&quot;: &quot;my home address&quot; ,<br />
&nbsp;&nbsp; &nbsp;&quot;billingAddress&quot;: &quot;my billing address&quot;&nbsp;<br />
}</p>

<div><strong><span style="line-height: 20.7999992370605px;">Retrieves all&nbsp;</span><span style="line-height: 20.7999992370605px;">user&nbsp;admins</span></strong></div>

<div>&nbsp;</div>

<table cellpadding="1" cellspacing="1">
	<tbody>
		<tr>
			<td>GET</td>
			<td>/user/<span style="line-height: 20.7999992370605px;">admin</span></td>
			<td>Retrieves all&nbsp;<span style="line-height: 20.7999992370605px;">user&nbsp;admins</span></td>
		</tr>
	</tbody>
</table>

<p style="line-height: 20.7999992370605px;"><strong style="line-height: 1.6em;">Request&nbsp;</strong><strong style="line-height: 20.7999992370605px;">Headers:&nbsp;</strong><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">{</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp; &nbsp;&quot;token&quot;: &quot;3412341234124&quot;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">}</span></p>

<p style="line-height: 20.7999992370605px;"><strong>Response:&nbsp;</strong>HTTP status code 200: OK</p>

<p style="line-height: 20.7999992370605px;"><strong style="line-height: 1.6em;">Response body:</strong><br />
{<br />
&nbsp;&nbsp; &nbsp;[<br />
&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;{<br />
&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&quot;id&quot;: &quot;1&quot;,<br />
&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&quot;title&quot;: &quot;Mr&quot;,<br />
&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&quot;firstName&quot;: &quot;Pablo&quot;,<br />
&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&quot;lastName&quot;: &quot;Angio&quot;,&nbsp;<br />
&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&quot;credentials&quot;:{<br />
&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &quot;email&quot;: &quot;pablo@gmail.com&quot;,<br />
&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &quot;password&quot;: &quot;123123123&quot;<br />
&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; },<br />
&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&quot;role&quot;: &quot;ADMIN&quot;,<br />
&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&quot;dateOfBirth&quot;: &quot;11-11-2000&quot;,<br />
&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&quot;homeAddress&quot;: &quot;my home address&quot; ,<br />
&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&quot;billingAddress&quot;: &quot;my billing address&quot;&nbsp;<br />
&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;}<br />
&nbsp;&nbsp; &nbsp;]<br />
}</p>

<div><strong><span style="line-height: 20.7999992370605px;">Deletes a&nbsp;</span><span style="line-height: 20.7999992370605px;">user&nbsp;admin</span></strong></div>

<div>&nbsp;</div>

<table cellpadding="1" cellspacing="1">
	<tbody>
		<tr>
			<td>DELETE</td>
			<td><span style="line-height: 20.7999992370605px;">/user/admin​/{id}</span></td>
			<td>Deletes a&nbsp;<span style="line-height: 20.7999992370605px;">user&nbsp;admin</span></td>
		</tr>
	</tbody>
</table>

<p>​</p>

<p style="line-height: 20.7999992370605px;"><strong style="line-height: 1.6em;">Request&nbsp;</strong><strong style="line-height: 20.7999992370605px;">Headers:&nbsp;</strong><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">{</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp; &nbsp;&quot;token&quot;: &quot;3412341234124&quot;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">}</span></p>

<p style="line-height: 20.7999992370605px;"><strong>Response:&nbsp;</strong>HTTP status code 204: NO_CONTENT</p>

<p style="line-height: 20.7999992370605px;">&nbsp;</p>

<div>&nbsp;</div>

</br>
<p><strong><span style="line-height: 1.6em;">SUBSCRIBER OVERVIEW</span></strong></p>

<table border="0" cellpadding="1" cellspacing="1">
	<tbody>
		<tr>
			<td><strong>Method</strong></td>
			<td><strong>Route</strong></td>
			<td><strong>Description</strong></td>
		</tr>
		<tr>
			<td>POST</td>
			<td>/user/subscriber</td>
			<td>Creates a new subscriber</td>
		</tr>
		<tr>
			<td>PUT</td>
			<td>/user/subscriber​/<span style="line-height: 20.7999992370605px;">{id}</span></td>
			<td>Updates a <span style="line-height: 20.7999992370605px;">subscriber</span></td>
		</tr>
		<tr>
			<td>GET</td>
			<td><span style="line-height: 20.7999992370605px;">/user/subscriber​/</span><span style="line-height: 20.7999992370605px;">{id}</span></td>
			<td>Retrieves a&nbsp;<span style="line-height: 20.7999992370605px;">subscriber</span></td>
		</tr>
		<tr>
			<td>GET</td>
			<td>/user/subscriber</td>
			<td>Retrieves all <span style="line-height: 20.7999992370605px;">subscribers</span></td>
		</tr>
		<tr>
			<td>DELETE</td>
			<td><span style="line-height: 20.7999992370605px;">/user/subscriber​/</span><span style="line-height: 20.7999992370605px;">{id}</span></td>
			<td>Deletes a&nbsp;<span style="line-height: 20.7999992370605px;">subscriber</span></td>
		</tr>
	</tbody>
</table>

<p><strong><span style="line-height: 1.6em;">Creates new subscriber</span></strong></p>

<table border="0" cellpadding="1" cellspacing="1">
	<tbody>
		<tr>
			<td>POST</td>
			<td>/user/subscriber</td>
			<td>Creates a new subscriber</td>
		</tr>
	</tbody>
</table>

<p><strong style="line-height: 1.6em;"><span style="line-height: 1.6em;">Request body:</span></strong></p>

<p><span style="line-height: 20.7999992370605px;">{</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;title&quot;: &quot;Mr&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;firstName&quot;: &quot;Pablo&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;lastName&quot;: &quot;Angio&quot;,&nbsp;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp; &nbsp; &nbsp; &nbsp; &quot;credentials&quot;:{</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp; &nbsp; &quot;email&quot;: &quot;pablo@gmail.com&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp; &nbsp; &quot;password&quot;: &quot;123123123&quot;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp; &nbsp; &nbsp; &nbsp;},</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;dateOfBirth&quot;: &quot;11-11-2000&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;homeAddress&quot;: &quot;my home address&quot; ,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;billingAddress&quot;: &quot;my billing address&quot;&nbsp;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">}</span></p>

<p><strong>Response:&nbsp;</strong><span style="line-height: 20.7999992370605px;">HTTP status code 201: CREATED</span></p>

<p><span style="line-height: 20.7999992370605px;">​</span><strong style="line-height: 1.6em;">Response body:</strong></p>

<p><span style="line-height: 20.7999992370605px;">{</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;id&quot;: &quot;1&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;title&quot;: &quot;Mr&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;firstName&quot;: &quot;Pablo&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;lastName&quot;: &quot;Angio&quot;,&nbsp;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp; &nbsp; &nbsp; &nbsp; &quot;credentials&quot;:{</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp; &nbsp; &quot;email&quot;: &quot;pablo@gmail.com&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp; &nbsp; &quot;password&quot;: &quot;123123123&quot;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp; &nbsp; &nbsp; &nbsp;},</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;role&quot;: &quot;SUBSCRIBER&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;dateOfBirth&quot;: &quot;11-11-2000&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;homeAddress&quot;: &quot;my home address&quot; ,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;billingAddress&quot;: &quot;my billing address&quot;&nbsp;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">}</span></p>

<p><span style="line-height: 20.7999992370605px;"><strong>Updates a&nbsp;</strong></span><strong><span style="line-height: 20.7999992370605px;">subscriber</span></strong></p>

<table border="0" cellpadding="1" cellspacing="1">
	<tbody>
		<tr>
			<td>PUT</td>
			<td>/user/subscriber​/<span style="line-height: 20.7999992370605px;">{id}</span></td>
			<td>Updates a&nbsp;<span style="line-height: 20.7999992370605px;">subscriber</span></td>
		</tr>
	</tbody>
</table>

<p style="line-height: 20.7999992370605px;"><strong>Reques Header:</strong><br />
{<br />
&nbsp; &nbsp;&quot;token&quot;: &quot;3412341234124&quot;<br />
}</p>

<p style="line-height: 20.7999992370605px;"><strong style="line-height: 1.6em;">Request body:</strong></p>

<p style="line-height: 20.7999992370605px;">{<br />
&nbsp;&nbsp; &nbsp;&quot;title&quot;: &quot;Mr&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;firstName&quot;: &quot;Pablo&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;lastName&quot;: &quot;Angio&quot;,&nbsp;<br />
&nbsp; &nbsp; &nbsp; &nbsp; &quot;credentials&quot;:{<br />
&nbsp;&nbsp; &nbsp; &nbsp; &quot;email&quot;: &quot;pablo@gmail.com&quot;,<br />
&nbsp;&nbsp; &nbsp; &nbsp; &quot;password&quot;: &quot;123123123&quot;<br />
&nbsp; &nbsp; &nbsp; &nbsp;},<br />
&nbsp;&nbsp; &nbsp;&quot;role&quot;: &quot;SUBSCRIBER&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;dateOfBirth&quot;: &quot;11-11-2000&quot;,<br />
&nbsp;&nbsp; &nbsp;&quot;homeAddress&quot;: &quot;my home address&quot; ,<br />
&nbsp;&nbsp; &nbsp;&quot;billingAddress&quot;: &quot;my billing address&quot;&nbsp;<br />
}</p>

<p style="line-height: 20.7999992370605px;"><strong>Response:&nbsp;</strong><span style="line-height: 20.7999992370605px;">HTTP status code 200: OK</span></p>

<p style="line-height: 20.7999992370605px;"><strong style="line-height: 1.6em;">Response body:</strong></p>

<div><span style="line-height: 20.7999992370605px;">{</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;id&quot;: &quot;1&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;title&quot;: &quot;Mr&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;firstName&quot;: &quot;Pablo&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;lastName&quot;: &quot;Angio&quot;,&nbsp;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp; &nbsp; &nbsp; &nbsp; &quot;credentials&quot;:{</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp; &nbsp; &quot;email&quot;: &quot;pablo@gmail.com&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp; &nbsp; &quot;password&quot;: &quot;123123123&quot;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp; &nbsp; &nbsp; &nbsp;},</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;role&quot;: &quot;SUBSCRIBER&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;dateOfBirth&quot;: &quot;11-11-2000&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;homeAddress&quot;: &quot;my home address&quot; ,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;billingAddress&quot;: &quot;my billing address&quot;&nbsp;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">}</span></div>

<p><strong><span style="line-height: 20.7999992370605px;">Retrieves a&nbsp;</span><span style="line-height: 20.7999992370605px;">subscriber</span></strong></p>

<table border="0" cellpadding="1" cellspacing="1">
	<tbody>
		<tr>
			<td>GET</td>
			<td><span style="line-height: 20.7999992370605px;">/user/subscriber​/</span><span style="line-height: 20.7999992370605px;">{id}</span></td>
			<td>Retrieves a&nbsp;<span style="line-height: 20.7999992370605px;">subscriber</span></td>
		</tr>
	</tbody>
</table>

<p style="line-height: 20.7999992370605px;"><strong>Request header:</strong></p>

<p style="line-height: 20.7999992370605px;">{<br />
&nbsp; &nbsp;&quot;token&quot;: &quot;3412341234124&quot;<br />
}</p>

<p style="line-height: 20.7999992370605px;"><strong>Response:&nbsp;</strong><span style="line-height: 20.7999992370605px;">HTTP status code 200: OK</span></p>

<p style="line-height: 20.7999992370605px;"><strong style="line-height: 1.6em;">Response body:</strong></p>

<div><span style="line-height: 20.7999992370605px;">{</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;id&quot;: &quot;1&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;title&quot;: &quot;Mr&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;firstName&quot;: &quot;Pablo&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;lastName&quot;: &quot;Angio&quot;,&nbsp;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp; &nbsp; &nbsp; &nbsp; &quot;credentials&quot;:{</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp; &nbsp; &quot;email&quot;: &quot;pablo@gmail.com&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp; &nbsp; &quot;password&quot;: &quot;123123123&quot;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp; &nbsp; &nbsp; &nbsp;},</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;role&quot;: &quot;SUBSCRIBER&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;dateOfBirth&quot;: &quot;11-11-2000&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;homeAddress&quot;: &quot;my home address&quot; ,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&quot;billingAddress&quot;: &quot;my billing address&quot;&nbsp;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">}</span></div>

<div>&nbsp;</div>

<div><strong><span style="line-height: 20.7999992370605px;">Retrieves all&nbsp;</span><span style="line-height: 20.7999992370605px;">subscribers</span></strong></div>

<div>&nbsp;</div>

<table border="0" cellpadding="1" cellspacing="1">
	<tbody>
		<tr>
			<td>GET</td>
			<td>/user/subscriber</td>
			<td>Retrieves all&nbsp;<span style="line-height: 20.7999992370605px;">subscribers</span></td>
		</tr>
	</tbody>
</table>

<p style="line-height: 20.7999992370605px;"><strong style="line-height: 1.6em;">Request header:</strong></p>

<p style="line-height: 20.7999992370605px;">{<br />
&nbsp; &nbsp;&quot;token&quot;: &quot;3412341234124&quot;<br />
}</p>

<p style="line-height: 20.7999992370605px;"><strong>Response:&nbsp;</strong><span style="line-height: 20.7999992370605px;">HTTP status code 200: OK</span></p>

<p style="line-height: 20.7999992370605px;"><strong style="line-height: 1.6em;">Response body:</strong></p>

<div><span style="line-height: 20.7999992370605px;">{</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;[</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;{</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&quot;id&quot;: &quot;1&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&quot;title&quot;: &quot;Mr&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&quot;firstName&quot;: &quot;Pablo&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&quot;lastName&quot;: &quot;Angio&quot;,&nbsp;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&quot;credentials&quot;:{</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &quot;email&quot;: &quot;pablo@gmail.com&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &quot;password&quot;: &quot;123123123&quot;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; },</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&quot;role&quot;: &quot;SUBSCRIBER&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&quot;dateOfBirth&quot;: &quot;11-11-2000&quot;,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&quot;homeAddress&quot;: &quot;my home address&quot; ,</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&quot;billingAddress&quot;: &quot;my billing address&quot;&nbsp;</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;}</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">&nbsp;&nbsp; &nbsp;]</span><br style="line-height: 20.7999992370605px;" />
<span style="line-height: 20.7999992370605px;">}</span></div>

<div>&nbsp;</div>

<div><strong><span style="line-height: 20.7999992370605px;">Deletes a&nbsp;</span><span style="line-height: 20.7999992370605px;">subscriber</span></strong></div>

<div><strong><span style="line-height: 20.7999992370605px;">​</span></strong></div>

<table border="0" cellpadding="1" cellspacing="1">
	<tbody>
		<tr>
			<td>DELETE</td>
			<td><span style="line-height: 20.7999992370605px;">/user/subscriber​/</span><span style="line-height: 20.7999992370605px;">{id}</span></td>
			<td>Deletes a&nbsp;<span style="line-height: 20.7999992370605px;">subscriber</span></td>
		</tr>
	</tbody>
</table>

<p style="line-height: 20.7999992370605px;"><strong style="line-height: 1.6em;">Request header:</strong></p>

<p style="line-height: 20.7999992370605px;">{<br />
&nbsp; &nbsp;&quot;token&quot;: &quot;3412341234124&quot;<br />
}</p>

<p style="line-height: 20.7999992370605px;"><strong>Response:&nbsp;</strong>HTTP status code 204: NO_CONTENT</p>

