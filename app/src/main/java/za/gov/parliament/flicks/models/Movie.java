package za.gov.parliament.flicks.models;

import java.util.Date;
import java.util.List;

/**
 * Created by vmutshinya on 4/25/2017.
 */

public class Movie
{
    public String objectId;
    public Image image;
    public Date createdAt;
    public Date updatedAt;
    public String title;
    public String releaseYear;
    public List<String> genre;
    public double rating;
    public String description;
}
