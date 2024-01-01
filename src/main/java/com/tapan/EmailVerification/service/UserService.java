package com.tapan.EmailVerification.service;

import com.tapan.EmailVerification.requests.RegisterRequest;
import com.tapan.EmailVerification.response.RegisterResponse;

public interface UserService {
	RegisterResponse register(RegisterRequest registerRequest);

}
