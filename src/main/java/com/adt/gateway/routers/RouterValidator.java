package com.adt.gateway.routers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouterValidator {

	public List<String> endpoints() {
		List<String> list = new ArrayList<String>();
		list.add("/auth/register");
		list.add("/auth/login");
		return list;
	}

	public Predicate<ServerHttpRequest> isSecured = request -> endpoints().stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));

}
