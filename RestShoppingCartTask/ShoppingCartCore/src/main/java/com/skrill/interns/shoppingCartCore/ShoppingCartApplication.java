package com.skrill.interns.shoppingCartCore;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath(ShoppingCartApplication.BASE_PATH)
public class ShoppingCartApplication extends Application {

    public static final String BASE_PATH = "/";

    public ShoppingCartApplication() {

    };
}
