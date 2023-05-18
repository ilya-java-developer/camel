package org.app;

//import com.fasterxml.jackson.databind.ObjectMapper

import com.google.gson.Gson;
import org.app.routing.Route;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Utils {
    public static Route getRoutes(String routeName) throws Exception {
        String js = convertFileIntoString("C:/Users/work/Downloads/apache-karaf-4.3.9/deploy/routes/routes.json");

        Gson gson = new Gson();
        Route findedRoute = null;
        Route[] rr = gson.fromJson(js, Route[].class);
        for (Route route: rr
             ) {
            if(route.getName().equalsIgnoreCase(routeName))  findedRoute = route;
        }
        System.out.println("-= routes finded =-");
        return findedRoute;
    }

    public static String convertFileIntoString(String file) throws Exception
    {
        // declare a variable in which we store the JSON data as a string and return it to the main() method
        String result;
        // initialize result variable
        // we use the get() method of Paths to get the file data
        // we use readAllBytes() method of Files to read byted data from the files
        result = new String(Files.readAllBytes(Paths.get(file)));
        // return result to the main() method
        return result;
    }

    public static String removeLastChar(String s) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - 1));
    }

    public static InputStream getFileInputStream(String fileSource) throws FileNotFoundException {
        final File initialFile = new File(fileSource);
        final InputStream targetStream =
                new DataInputStream(new FileInputStream(initialFile));
        return targetStream;
    }

}
