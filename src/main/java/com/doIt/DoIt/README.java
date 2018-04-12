package com.doIt.DoIt;

public class README {
    /*
    this class is just here for some notes:

    1. To RUN the application you first need to add your IP address to Azure DB
       This is because by default Azure DB has a firewall that will prevent you from connecting to it

       So here's what to do:
       -Go to Azure portal (portal.azure.com), log in w/ our group email: Group3SW@Gmail.com, pass:  DogCat10
       -Go to SQL DB, click on our DB (DoIt)
       -You'll see options such as "Copy", "Restore", "Export" etc...
       -Click on "Set server firewall"
       -Make sure Allow access to Azure services is on
       -Then just click "Add client IP" and "Save"
       -And that's it your IP should appear under mine and you should be able to connect to DB now

       From now you can just build and run the app.

    2.  There are 2 files in resources/templates:
        -index.html (this one i used for all-tasks http:/localhost:8080/all-tasks)
        -home.html (this one i used for "home page" http://localhost:8080)
        -updatetask.html for http:/localhost:8080/update-task

     */
}
