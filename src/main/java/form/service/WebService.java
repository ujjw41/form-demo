package form.service;

import form.entity.File;
import form.entity.User;
import form.repository.FileRepo;
import form.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebService {
	@Autowired
	UserRepo userRepo;

	@Autowired
	FileRepo fileRepo;

	public void saveUser(User user) {
		userRepo.save(user);
	}

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public void saveFile(File file) {
		fileRepo.save(file);
	}

}
