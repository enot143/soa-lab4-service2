package soa.endpoints;

import soa.dto.CoordinateDto;
import soa.dto.RouteDto;
import soa.entities.Route;
import soa.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import soa.dtos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Endpoint
public class RouteEndpoint {
    private static final String NAMESPACE_URI = "http://soa/dtos";
    private final RouteService routeService;

    @Autowired
    public RouteEndpoint(RouteService routeService) {
        this.routeService = routeService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findRoutesBetweenLocationsRequest")
    @ResponsePayload
    public FindRoutesBetweenLocationsResponse findRoutesBetweenLocations(@RequestPayload FindRoutesBetweenLocationsRequest request) {
        FindRoutesBetweenLocationsResponse response = new FindRoutesBetweenLocationsResponse();
        List<soa.dtos.RouteDto> list = routeService.findRouteByIds(request.getIdFrom(), request.getIdTo(), request.getOrder());
        response.getDtos().addAll(list);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addRouteRequest")
    @ResponsePayload
    public AddRouteResponse addRoute(@RequestPayload AddRouteRequest request) {
        RouteDto routeDto = new RouteDto(request.getName(), new CoordinateDto(request.getCoordinates().getX(), request.getCoordinates().getY()));
        routeService.addRouteBetweenLocations(request.getIdFrom(), request.getIdTo(), request.getDistance(), routeDto);
        return new AddRouteResponse();
    }
}
