# TTC-Tracker
A project that aims to allows users to have easy and intuitive access to Toronto TTC data through an interactive map. Our project aims to do three things: display a map of all non-subway TTC stops, predict arrival times of non-subway TTC vehicles, and suggest the three quickest routes to a user given a starting and ending point.

## Accessing the TTC API
In order to access the TTC API we use in our project, we use two libraries: okhttp3 and opencsv.

We use the okhttp3 library in order to make HTTP requests. You can find the library [here](https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp). We specifically use version 4.10.

The opencsv library is used to read and write data to csv files. You can find the library [here](https://mvnrepository.com/artifact/com.opencsv/opencsv). We use version 5.8.
