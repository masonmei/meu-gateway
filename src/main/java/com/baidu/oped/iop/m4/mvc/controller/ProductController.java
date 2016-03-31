package com.baidu.oped.iop.m4.mvc.controller;

import static com.baidu.oped.sia.boot.utils.Constrains.Profile.DEV;

import com.baidu.oped.iop.m4.custom.security.Product;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Product controller for testing purpose.
 *
 * @author mason
 */
@RestController
@RequestMapping("products")
@Profile(DEV)
public class ProductController {

    public static final HashSet<String> ROLES = new HashSet<>();

    static {
        ROLES.add("TestRole");
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> myProducts() {
        List<Product> products = new ArrayList<>();
        Product e = new Product();
        e.setName("TestProduct");
        e.setRoles(ROLES);
        products.add(e);
        return products;
    }
}
