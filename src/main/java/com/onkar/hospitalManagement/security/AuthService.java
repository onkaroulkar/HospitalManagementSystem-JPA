package com.onkar.hospitalManagement.security;

import com.onkar.hospitalManagement.dto.LoginRequestDto;
import com.onkar.hospitalManagement.dto.LoginResponseDto;
import com.onkar.hospitalManagement.dto.SignUpRequestDto;
import com.onkar.hospitalManagement.dto.SignUpResponseDto;
import com.onkar.hospitalManagement.entity.User;
import com.onkar.hospitalManagement.repository.UserRepository;
import com.onkar.hospitalManagement.type.AuthProviderType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        User user = (User) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);
        return ResponseEntity.ok(new LoginResponseDto(token, user.getId()));
    }


    public User signupInternal(SignUpRequestDto signUpRequestDto,AuthProviderType authProviderType,String providerId) {
        if (userRepository.findByUsername(signUpRequestDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User already exists!");
        }

        User user = new User(signUpRequestDto.getUsername());
        if(authProviderType == AuthProviderType.EMAIL){
            user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        }
        return userRepository.save(user);
    }


    // login controller
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        User user = signupInternal(signUpRequestDto,AuthProviderType.EMAIL,null);
        userRepository.save(user);
        return new SignUpResponseDto(user.getId(), user.getUsername());
    }


    @Transactional
    public ResponseEntity<LoginResponseDto> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {
        // Fetch providerId and ProviderType
        AuthProviderType providerType = authUtil.getProviderTypeFromRegistrationId(registrationId);
        String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User, registrationId);

        // save the providerType and provider id info with user
        User user = userRepository.findByProviderIdAndAuthProviderType(providerId, providerType).orElse(null);
        String email = oAuth2User.getAttribute("email");
        User emailUser = userRepository.findByUsername(email).orElse(null);

        // otherwise first signup then login the user.
        // If user has the account directly save the user
        if (user == null && emailUser == null) {
            // Signup flow
            String username = authUtil.determineUsernameFromOAuth2User(oAuth2User, registrationId, providerId);
            user = signupInternal(new SignUpRequestDto(username, null),providerType,providerId);
        } else if (user != null) {
            if (email != null && !email.isBlank() && !email.equals(user.getUsername())) {
                user.setUsername(email);
                userRepository.save(user);
            }else {
                userRepository.save(user);
            }
        } else {
            throw new BadCredentialsException("This email is already registered with provider " + emailUser.getAuthProviderType());
        }
        LoginResponseDto loginResponseDto = new LoginResponseDto(authUtil.generateAccessToken(user), user.getId());
        return ResponseEntity.ok(loginResponseDto);
    }
}
