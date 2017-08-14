package com.skrill.interns.shoppingCartCore;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.skrill.interns.shoppingCartApi.IShoppingCart;
import com.skrill.interns.shoppingCartApi.Item;

public class ShoppingCart implements IShoppingCart {

    @Context
    HttpServletRequest request;
    Response response;
    private Item sessionItem;
    private static final String SHOPPING_CART_ATTRIBUTE_NAME = "cart";
    private static final String UPDATE_SUCCESS_MESSAGE = "The shopping cart was updated. ";
    private static final String CREATE_SUCCESS_MESSAGE = "Shopping cart was created. ";
    private static final String REMOVE_SUCCESS_MESSAGE = "Item was removed. ";
    private static final String ADD_SUCCESS_MESSAGE = "Item successfully added. ";
    private static final String NO_ITEM_MESSAGE = "This item does not exist in the shopping cart. ";
    private static final String NO_SHOPPING_CART_MESSAGE = "There is no shopping cart. ";
    HttpSession session;
    ArrayList<Item> sessionShoppingCart = new ArrayList<Item>();
    ResponseBuilder builder;

    @Override
    public Response viewCart() {
        if (checkForExistingShoppingCartInRequest()) {
            return buildResponse(sessionShoppingCart, Status.OK);
        }
        else {
            return buildResponse("Shopping cart is empty.", Status.NO_CONTENT);
        }
    }

    @Override
    public Response addItem(Item item) {
        response = validateItemNameAndQuantity(item);
        if (response != null) {
            return response;
        }
        if (checkForExistingShoppingCartInRequest()) {
            if (searchForItemInShoppingCart(item)) {
                sessionItem.setQuantity(sessionItem.getQuantity() + item.getQuantity());
            } else {
                if (item.getQuantity() == null) {
                    item.setQuantity(1);
                }
                sessionShoppingCart.add(item);
            }
            response = buildResponse(ADD_SUCCESS_MESSAGE, Status.CREATED);
        } else {
            sessionShoppingCart = new ArrayList<Item>();
            sessionShoppingCart.add(item);
            session.setAttribute(SHOPPING_CART_ATTRIBUTE_NAME, sessionShoppingCart);
            response = buildResponse(CREATE_SUCCESS_MESSAGE + ADD_SUCCESS_MESSAGE, Status.CREATED);
        }
        return response;
    }

    @Override
    public Response updateItem(Item item) {
        response = validateItemNameAndQuantity(item);
        if (response != null) {
            return response;
        }

        if (checkForExistingShoppingCartInRequest()) {
            if (searchForItemInShoppingCart(item)) {
                sessionItem.setQuantity(item.getQuantity());
                response = buildResponse(UPDATE_SUCCESS_MESSAGE, Status.OK);
            } else {
                sessionShoppingCart.add(item);
                response = buildResponse(UPDATE_SUCCESS_MESSAGE, Status.CREATED);
            }
        } else {
            sessionShoppingCart = new ArrayList<Item>();
            sessionShoppingCart.add(item);
            session.setAttribute(SHOPPING_CART_ATTRIBUTE_NAME, sessionShoppingCart);
            response = buildResponse((CREATE_SUCCESS_MESSAGE + "\n" + UPDATE_SUCCESS_MESSAGE), Status.OK);
        }
        return response;
    }

    @Override
    public Response removeItem(Item item) {
        response = validateItemNameAndQuantity(item);
        if (response != null) {
            return response;
        }
        if (checkForExistingShoppingCartInRequest()) {
            if (searchForItemInShoppingCart(item)) {
                sessionShoppingCart.remove(sessionItem);
                response = buildResponse(REMOVE_SUCCESS_MESSAGE, Status.OK);
            } else {
                response = buildResponse(NO_ITEM_MESSAGE, Status.NOT_FOUND);
            }
        } else {
            response = buildResponse(NO_SHOPPING_CART_MESSAGE, Status.NOT_FOUND);
        }
        return response;
    }

    @Override
    public Response viewItem(String itemName) {
        sessionShoppingCart = (ArrayList<Item>) request.getSession().getAttribute("cart");
        if (sessionShoppingCart == null) {
            return buildResponse(NO_SHOPPING_CART_MESSAGE, Status.NOT_FOUND);
        }
        sessionItem = null;
        for (int i = 0; i < sessionShoppingCart.size(); i++) {
            if (sessionShoppingCart.get(i).getName().equals(itemName)) {
                sessionItem = sessionShoppingCart.get(i);
                break;
            }
        }
        if (sessionItem != null) {
            response = buildResponse(sessionItem, Status.OK);
        } else {
            response = buildResponse("No such item", Status.NOT_FOUND);
        }
        return response;
    }

    private boolean searchForItemInShoppingCart(Item item) {
        for (int i = 0; i < sessionShoppingCart.size(); i++) {
            if (sessionShoppingCart.get(i).getName().equals(item.getName())) {
                sessionItem = sessionShoppingCart.get(i);
                return true;
            }
        }
        return false;
    }

    private Response buildResponse(Object response, Status status) {
        ResponseBuilder builder = Response.status(status);
        builder.entity(response);
        return builder.build();
    }

    private boolean checkForExistingShoppingCartInRequest() {
        session = request.getSession();
        sessionShoppingCart = (ArrayList<Item>) session.getAttribute(SHOPPING_CART_ATTRIBUTE_NAME);
        if (sessionShoppingCart == null) {
            return false;
        }
        return true;
    }


    private Response validateItemNameAndQuantity(Item item) {
        if (item.getName() == null) {
            return buildResponse("Invalid item name", Status.NOT_ACCEPTABLE);
        }
        if (item.getQuantity() != null) {
            if (item.getQuantity() <= 0) {
                return buildResponse("Invalid quantity", Status.NOT_ACCEPTABLE);
            }
        } else {
            item.setQuantity(1);
        }
        return null;
    }

}
