package com.skrill.interns.shoppingCartApi;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(IShoppingCart.PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface IShoppingCart {

    static final String PATH = "/shopping-cart";
    static final String ITEM_MANIPULATION_PATH = "/item";

    @GET
    @Path("/view/{itemName}")
    public Response viewItem(@PathParam("itemName") String itemName);

    @GET
    @Path("/view")
    public Response viewCart();

    @POST
    @Path(ITEM_MANIPULATION_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(Item item);

    @PUT
    @Path(ITEM_MANIPULATION_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateItem(Item item);

    @DELETE
    @Path(ITEM_MANIPULATION_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeItem(Item item);

}
