package form.controller;

import form.entity.User;
import form.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
	@Autowired
	WebService webService;

	@GetMapping("/")
	public String register(Model model) {
		model.addAttribute("errorMessage", null);
		model.addAttribute("empty", new ArrayList());
		model.addAttribute("applicationName", "Form");
		return "index";
	}

	@PostMapping("/submit")
	public String submit(@Valid User user, BindingResult binding, Model model, @RequestParam("myfile") MultipartFile file) throws IOException {
		if (binding.hasErrors()) {
			model.addAttribute("errorMessage", binding);
			return "index";
		}
		String mylocation = System.getProperty("user.dir") + "/src/main/resources/static/";
		String filename = file.getOriginalFilename();

		File mySavedFile = new File(mylocation + filename);

		InputStream inputStream = file.getInputStream();

		OutputStream outputStream = new FileOutputStream(mySavedFile);

		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
		user.setImg_link("http://localhost:8080/" + filename);
		webService.saveUser(user);
		return "redirect:/";

	}

	@GetMapping("/users")
	public String showUsers(Model model) {

		List<User> users = webService.getAllUsers();

		model.addAttribute("errorMessage", null);

		model.addAttribute("users", users);

		return "users";
	}

	@GetMapping("/upload")
	public String upload(Model model) {
		model.addAttribute("errorMessage", null);
		model.addAttribute("empty", new ArrayList());
		model.addAttribute("applicationName", "Files");
		return "file";
	}

	@PostMapping("/save")
	public String save(@Valid form.entity.File files, BindingResult binding, Model model,
					   @RequestParam("myfile") MultipartFile file, @RequestParam("myfile2") MultipartFile file2) throws IOException {
		if (binding.hasErrors()) {
			model.addAttribute("errorMessage", binding);
			return "file";
		}
		String mylocation = System.getProperty("user.dir") + "/src/main/resources/static/";
		String filename = file.getOriginalFilename();
		File mySavedFile = new File(mylocation + filename);

		String filename2 = file2.getOriginalFilename();
		File mySavedFile2 = new File(mylocation + filename2);

		InputStream inputStream = file.getInputStream();
		InputStream inputStream2 = file2.getInputStream();

		OutputStream outputStream = new FileOutputStream(mySavedFile);
		OutputStream outputStream2 = new FileOutputStream(mySavedFile2);

		int read = 0;
		byte[] bytes = new byte[102400000];

		while ((read = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
		while ((read = inputStream2.read(bytes)) != -1) {
			outputStream2.write(bytes, 0, read);
		}
//		files.setLocation("http://localhost:8080/" + filename);
//		files.setLocation2("http://localhost:8080/" + filename2);
//		webService.saveFile(files);
		return "redirect:/success";
	}

	@GetMapping("/success")
	public String success() {
		return "success";
	}


}
