package com.example.taskcreator.services;

import com.example.taskcreator.Exception.TCException;
import com.example.taskcreator.dtos.RefreshTokenPayload;
import com.example.taskcreator.dtos.TokenResponse;
import com.example.taskcreator.dtos.UserPayload;
import com.example.taskcreator.entities.User;
import com.example.taskcreator.helpers.MessageModel;
import com.example.taskcreator.repositories.UserRepository;
import com.example.taskcreator.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public ResponseEntity<MessageModel> register(
            UserPayload payload
    ) throws TCException {
        User userExist = userRepository.findByUsername(payload.getUsername());

        if(userExist != null) throw new TCException("User already exist");
        User result = userRepository.save(
                new User(
                        payload.getUsername(),
                        encoder.encode(payload.getPassword())
                )
        );

        if(result.getUserId() == null) throw new TCException("Register failed!");
        return ResponseEntity.ok().body(new MessageModel(
                "Succesfully register new user",
                true,
                null,
                null
        ));
    }

    public ResponseEntity<MessageModel> login(
            UserPayload payload
    ) throws TCException {
        User userExist = userRepository.findByUsername(payload.getUsername());

        if(userExist == null) throw new TCException("User not found, please check your info!");
        if(!encoder.matches(payload.getPassword(), userExist.getPassword()))
            throw new TCException("Wrong password, please check your info!");

        String accessToken = jwtUtil.generateToken(userExist, false);
        String refreshToken = jwtUtil.generateToken(userExist, true);

        return ResponseEntity.ok().body(new MessageModel(
                "Succesfully generated token",
                true,
                null,
                new TokenResponse(
                        accessToken,
                        refreshToken
                )
        ));
    }

    public ResponseEntity<MessageModel> refreshToken(RefreshTokenPayload refreshToken) throws TCException {
        Boolean isRefreshTokenValid = jwtUtil.validateRefreshToken(refreshToken.getRefreshToken());
        if(!isRefreshTokenValid) throw new TCException("Refresh token not valid");

        User userToken = jwtUtil.getDataToken(refreshToken.getRefreshToken(), true);
        jwtUtil.forceExpireToken(refreshToken.getRefreshToken(), true);

        User user = userRepository.findByUserId(userToken.getUserId());
        if(user == null) throw new TCException("Failed generate new token, please login again!");

        String newAccessToken = jwtUtil.generateToken(user, false);
        String newRefreshToken = jwtUtil.generateToken(user, true);

        return ResponseEntity.ok().body(new MessageModel(
                "Succesfully generated new token",
                true,
                null,
                new TokenResponse(
                        newAccessToken,
                        newRefreshToken
                )
        ));
    }
}
