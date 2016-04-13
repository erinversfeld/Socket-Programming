About:
------
This code was written as part of the Networks Assignment for CSC3002F in 2016. The original framework was written by Stephan Jamieson. The rest of the code was implemented by Erin Versfeld, student number VRSERI001. As no makefile was requested as part of the assignment brief, none has been included.

Supported architectures:
------------------------
This code has been compiled and tested across a variety of operating systems using a number of different browsers. If it does not compile or run on your particular set-up, please log an issue at www.github.com/erinversfeld/Socket-Programming .

How to query the server:
------------------------
Open your browser and type in the following URL:

http://localhost:PORT_NUMBER/FILE_NAME

PORT_NUMBER is the number which you specified the Server to listen on when compiling the code. If you did not specify a number the server will default to port 0.
FILE_NAME is the name of the file you wish to search for. For example: adorableKitten.png

Understanding the server responses:
-----------------------------------
If your browser displays the contents of the requested file (for example, an image of an adorable kitten), then your request was successful.
If your browser displays the text "FILE NOT FOUND" then your requested file was not available on the server. Please add the file to ./src/ServerFiles/ if you want to make it available.
If your browser displays the text "RESPONSE CORRUPTED. PLEASE TRY AGAIN OR LOG AN ISSUE AT www.github.com/erinversfeld/Socket-Programming", make sure that you have entered your query correctly before logging an issue at the indicated URL.