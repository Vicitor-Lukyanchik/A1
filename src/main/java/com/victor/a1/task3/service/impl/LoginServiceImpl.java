package com.victor.a1.task3.service.impl;

import com.victor.a1.task3.entity.Login;
import com.victor.a1.task3.repository.LoginRepository;
import com.victor.a1.task3.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Override
    public List<Login> createAll(List<Login> logins) {
        return loginRepository.saveAll(logins);
    }

    @Override
    public List<Login> findAll() {
        return loginRepository.findAll();
    }
}
