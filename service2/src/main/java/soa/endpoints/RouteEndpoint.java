package soa.endpoints;

import soa.dto.CoordinateDto;
import soa.dto.RouteDto;
import soa.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import soa.dtos.*;

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
        response.getDtos().addAll(
                routeService.findRouteByIds(request.getIdFrom(), request.getIdTo(), request.getOrder())
        );
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addRouteRequest")
    @ResponsePayload
    public AddRouteResponse addRoute(@RequestPayload AddRouteRequest request) {
        AddRouteResponse response = new AddRouteResponse();
        RouteDto routeDto = new RouteDto(request.getName(),
                new CoordinateDto(request.getCoordinates().getX(), request.getCoordinates().getY()));
        routeService.addRouteBetweenLocations(
                request.getIdFrom(),
                request.getIdTo(),
                request.getDistance(),
                routeDto);
        return response;
    }
}
