How to run a complete test:
- first of all should be launched the server running "ServerLauncher.java" 
- then for each client that wants to connect has to be runned a "ClientMain.java"
We didn't create the part of an user registration, so the allowed users are statically 
inside an online database and are: 
Alb (pass: Alb123), Pav (pass: Pav123), Nic (pass: Nic123), Bard (pass: Bard123)
(to accede to the database is necessary a free connection (not like UNIPV-WIFI))
To do a complete test the first user logging with the client is "Alb", then Alb has to 
create a new team using the interface with the name "Prova".
After this every other client can login and select the team Prova.
In this situation every client can:
- Chat in the team conversation 
- Start a shared tomato with the other members of the chat selecting a particular macroevent
- Start a private conversation with another member and inside it start a private tomato
- Schedule a meeting on the timeline