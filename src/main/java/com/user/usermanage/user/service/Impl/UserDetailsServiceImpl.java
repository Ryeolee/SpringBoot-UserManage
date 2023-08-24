package com.user.usermanage.user.service.Impl;

import com.user.usermanage.user.repository.UserRepository;
import com.user.usermanage.user.service.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public String loadUserByUserusername(String userIdentifier) {
        LOGGER.info("loadUserByUsernam 수행");


        LOGGER.info(userRepository.getByIdentifier(userIdentifier));
        return userRepository.getByIdentifier(userIdentifier);
    }


}
