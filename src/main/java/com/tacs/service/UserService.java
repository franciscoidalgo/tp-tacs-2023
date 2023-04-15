package com.tacs.service;

import com.google.inject.Inject;
import com.tacs.dto.UserDto;
import com.tacs.model.User;
import com.tacs.repository.UserRepository;
import com.tacs.util.HashingUtils;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.DecoderException;

@Slf4j
public class UserService {
  private static final String FAKE_PASSWORD =
      "65536:2d4c682e8fb5139321edebeb053fa64a:9b5e955efe376b0d3f9173233109966e354be9c4d59b403a2f04cba23def465d";

  private final UserRepository userRepository;

  @Inject
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void createUser(UserDto userDto) throws NoSuchAlgorithmException, InvalidKeySpecException {
    var hashedPassword = HashingUtils.hashPassword(userDto.getPassword());
    var user = User.builder()
        .username(userDto.getUsername())
        .password(hashedPassword)
        .build();

    userRepository.save(user);
    userDto.setId(user.getId());
  }

  public boolean login(UserDto userDto)
      throws NoSuchAlgorithmException, InvalidKeySpecException, DecoderException {
    var user = userRepository.findByUsername(userDto.getUsername());
    if (user == null) {
      HashingUtils.verifyHash(userDto.getPassword(), FAKE_PASSWORD);
      return false;
    }
    return HashingUtils.verifyHash(userDto.getPassword(), user.getPassword());
  }
}
