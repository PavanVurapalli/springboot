package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class Main 
{
	@Autowired
	MoviesRepo moviesRepo;
	
	@PostMapping("postData")
	public void postData(@RequestBody Movies movies)
	{
		moviesRepo.save(movies);
	}
	
	@GetMapping("getData")
	public List<Movies> getData()
	{
		return moviesRepo.findAll();
	}
	
	@PutMapping("putData/{sno}")
    public void putData(@PathVariable("sno") Integer sno, @RequestBody Movies movies) 
	{
		System.out.println(sno);
        Optional<Movies> optionalExistingMovie=moviesRepo.findById(sno);
        if (optionalExistingMovie.isPresent()) 
        {
            Movies existingMovie = optionalExistingMovie.get();
            existingMovie.setMovieName(movies.getMovieName());
            existingMovie.setDirector(movies.getDirector());
            existingMovie.setHero(movies.getHero());
            existingMovie.setHeroine(movies.getHeroine());
            existingMovie.setReleaseYear(movies.getReleaseYear());
            System.out.println(existingMovie.getMovieName());
            moviesRepo.save(existingMovie);
        }
    }
	
	@DeleteMapping("deleteData/{sno}")
    public void deleteData(@PathVariable("sno") Integer sno) 
	{
        moviesRepo.deleteById(sno);
    }
}