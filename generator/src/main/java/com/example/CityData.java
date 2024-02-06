package main.java.com.example;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityData {

    String name;
    double x;
    double y;

}
