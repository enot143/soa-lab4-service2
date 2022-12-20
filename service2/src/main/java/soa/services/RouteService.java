package soa.services;

import soa.dto.LocationDto;
import soa.dto.RouteDto;
import soa.dto.RoutePostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RouteService {
    public static final String BASE_URL = "http://localhost:4567/soa-3-1.0-SNAPSHOT";

    RestTemplate restTemplate = new RestTemplate();

    public List findRouteByIds(Integer idFrom, Integer idTo, String order) {
        String URI = BASE_URL + "/routes?from_id=between:"
                + idFrom + ":" + idFrom + "&to_id=between:" + idTo + ":" + idTo +
                "&sort=" + order;
        return restTemplate.getForObject(URI, List.class);
    }

    public ResponseEntity addRouteBetweenLocations(Integer idFrom, Integer idTo, double distance, RouteDto routeDto) {
        System.out.println(routeDto.toString());
        RoutePostDto routePost = new RoutePostDto();
        routePost.setDistance(distance);
        routePost.setName(routeDto.getName());
        routePost.setCoordinates(routeDto.getCoordinates());
        routePost.setFrom(new LocationDto(idFrom));
        routePost.setTo(new LocationDto(idTo));
        System.out.println(routePost);
        String URI = BASE_URL + "/routes";
        return restTemplate.postForEntity(URI, routePost, ResponseEntity.class);
    }
}


