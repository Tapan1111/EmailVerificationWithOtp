package com.tapan.EmailVerification.service.IMPL;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.tapan.EmailVerification.models.Users;
import com.tapan.EmailVerification.repository.UserRepository;
import com.tapan.EmailVerification.requests.RegisterRequest;
import com.tapan.EmailVerification.response.RegisterResponse;
import com.tapan.EmailVerification.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final EmailService emailService;

	private final UserRepository userRepository;


	@Override
	public RegisterResponse register(RegisterRequest registerRequest) {
		Users existingUser = userRepository.findByEmail(registerRequest.getEmail());
		if (existingUser != null && existingUser.isVerified()) {
			throw new RuntimeException("User already Registered..");
		}
		Users user = Users.builder().userName(registerRequest.getUserName()).email(registerRequest.getEmail())
				.password(registerRequest.getPassword()).build();

		String otp = generateOTP();
		user.setOtp(otp);
		Users savedUser = userRepository.save(user);
		sendVerificationEmail(savedUser.getEmail(), otp);
		RegisterResponse response = RegisterResponse.builder().userName(user.getUserName()).email(user.getEmail())
				.build();
		return response;
	}

	public String generateOTP() {
		Random random = new Random();
		int otpValue = 100000 + random.nextInt(900000);
		return String.valueOf(otpValue);
	}

	private void sendVerificationEmail(String email, String otp) {
		String subject = "Email verification";
		String body = "your verfication otp is :" + otp;
		emailService.sendEmail(email, subject, body);
	}

}
