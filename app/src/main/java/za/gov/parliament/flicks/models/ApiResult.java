package za.gov.parliament.flicks.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResult
{
    @SerializedName("results")
    public List<Movie> movies;
}