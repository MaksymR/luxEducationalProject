package com.riaboshapka.servlets.iDForChecking;

import java.util.List;

public interface IDForChecking {


    /**
     * get order's ID for checking
     *
     * @return order's ID for checking
     */
    List<Long> getOrderIDForChecking();

    /**
     * get client's ID for checking
     *
     * @return client's ID for checking
     */
    List<Long> getClientIDForChecking();

    /**
     * get product's ID for checking
     *
     * @return product's ID for checking
     */
    List<Long> getProductIDForChecking();

}
