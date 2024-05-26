package kilic.mehmet.stock_exchange.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kilic.mehmet.stock_exchange.entity.AppUser;
import kilic.mehmet.stock_exchange.entity.request.UserCreateRequest;
import kilic.mehmet.stock_exchange.service.AppUserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AppUserController {
	
	private final AppUserService userService;
	
	
	@GetMapping
	public ResponseEntity<List<AppUser>> getAll() {
		return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<AppUser> create(@RequestBody UserCreateRequest userRequest) {
		return new ResponseEntity<>(userService.create(userRequest), HttpStatus.OK);
	}
	
}
