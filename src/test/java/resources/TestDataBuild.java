package resources;

import GoogleMapsSerializationWithPOJO.addPlacePOJO;
import GoogleMapsSerializationWithPOJO.location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public addPlacePOJO addPlacePayLoad(String name, String language, String address)
    {
        addPlacePOJO p = new addPlacePOJO(); //Creating addPlacePOJO class object

        //Start of SERIALIZATION
        //Setting all the direct JSON body values
        p.setAccuracy(50);
        p.setAddress(address);
        p.setName(name);
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com ");
        p.setLanguage(language);

        //Setting Types values
        List<String> typesList = new ArrayList<String>();
        typesList.add("shoe park");
        typesList.add("shop");
        p.setTypes(typesList);

        //Setting location values
        location l = new location(); //Creating location class object and passing this object to setLocation method
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);

        return p;
    }

}
