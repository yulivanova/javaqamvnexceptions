package ru.netology.repository;

import ru.netology.domain.Product;
import ru.netology.exception.NotFoundException;
import ru.netology.exception.AlreadyExistsException;

public class ProductRepository {
    private Product[] products = new Product[0];

    public void save(Product product) {
        Product[] tmp = new Product[products.length + 1];
        System.arraycopy(products, 0, tmp, 0, products.length);
        tmp[tmp.length - 1] = product;
        products = tmp;
    }

    public Product[] findAll() {

        return products;
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id " + id + " not found");
        }
        Product[] tmp = new Product[products.length - 1];
        int index = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[index] = product;
                index++;
            }
        }
        products = tmp;
    }


    public void add(Product product) {

        if (findById(product.getId()) != null) {
            throw new AlreadyExistsException("Element with id " + product.getId() + " already exists");
        }
        Product[] tmp = new Product[products.length + 1];
        System.arraycopy(products, 0, tmp, 0, products.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = product;
        products = tmp;
    }
}