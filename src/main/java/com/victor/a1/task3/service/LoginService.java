package com.victor.a1.task3.service;

import com.victor.a1.task3.entity.Login;
import com.victor.a1.task3.entity.Product;

import java.util.List;

public interface LoginService {

    List<Login> createAll(List<Login> logins);

    List<Login> findAll();
}
