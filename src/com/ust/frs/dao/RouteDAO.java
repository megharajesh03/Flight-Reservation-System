package com.ust.frs.dao;

import java.util.*;
import com.ust.frs.bean.*;

public class RouteDAO {

    public String createRoute(RouteBean route) {
        for (RouteBean r : DataStore.routes) {
            if (r.getRouteID().equalsIgnoreCase(route.getRouteID())) {
                return "FAIL";
            }
        }
        DataStore.routes.add(route);
        return "SUCCESS";
    }

    public boolean deleteRoute(String routeID) {
        return DataStore.routes.removeIf(r -> r.getRouteID().equalsIgnoreCase(routeID));
    }

    public RouteBean findById(String routeID) {
        for (RouteBean r : DataStore.routes) {
            if (r.getRouteID().equalsIgnoreCase(routeID)) return r;
        }
        return null;
    }

    public List<RouteBean> findAll() {
        return new ArrayList<>(DataStore.routes);
    }
}
