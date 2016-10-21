<h1>EMAIL BROADCAST SERVICE&nbsp;</h1>

<p><small>Version: 1&nbsp;Beta.</small></p>

<p>Written in Java Spring Framework. Service&nbsp;can&nbsp;send&nbsp;mails from your smtp account to others automatically&nbsp;(manage cronjob&nbsp;in app).&nbsp;Upload your mail base to database by group. Create your mail templates&nbsp;(for example: &quot;Happy New Year !&quot; ).&nbsp; After all you can send broadcast to people&nbsp;with&nbsp;some clicks.<br />
Service&nbsp;has&nbsp;own blacklist . Mails on there&nbsp;will not&nbsp;receive any mails from you.&nbsp;<br />
Mail sending process occurs in one more threads. I used&nbsp;ThreadPoolTaskExecutor for it.</p>

<p><br />
<strong>Following technologies used</strong>:</p>

<ul>
	<li>Java 8</li>
	<li>Spring Framework 4.3.3</li>
	<li>Spring MVC 4.3.3</li>
	<li>Spring Security 4.3.1</li>
	<li>Hibernate Core 5.2.2</li>
	<li>Hibernate Entity Manager 5.2.2</li>
	<li>Apache POI 3.15</li>
	<li>Flyway Migrations 4.0.3</li>
	<li>Thymeleaf Template Engine&nbsp;3.0.2</li>
	<li>Jackson 2.8.3</li>
	<li>SLF4J 1.7.21</li>
	<li>Log4j 2.6.2</li>
	<li>Tomcat 9</li>
	<li>JDK 1.8</li>
	<li>MySQL 5.7</li>
	<li>Maven 3</li>
</ul>

<p><br />
<strong>Installation</strong>:</p>

<ol>
	<li>Rename this file&nbsp;/src/main/resources/properties/persistence.properties.example to persistence.properties. Then write own database configurations .</li>
	<li>Create folder named &quot;emailbroadcastservice&quot; in /var/log and be sure that is writable.</li>
	<li>Run this command: mvn&nbsp;initialize flyway:migrate</li>
	<li>Build and Run application</li>
	<li>After opening GUI in browser, open Config from menu and change all to&nbsp;your smtp congurations then save it.</li>
	<li>Manage cronjob from&nbsp;this file /src/main/resources/properties/persistence.properties</li>
	<li>Enjoy it.</li>
</ol>
