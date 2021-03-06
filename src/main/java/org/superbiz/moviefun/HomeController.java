package org.superbiz.moviefun;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by pivotal on 5/1/17.
 */
@Controller
public class HomeController {


    private MoviesBean moviesBean;

    HomeController(MoviesBean moviesBean)
    {
        this.moviesBean = moviesBean;
    }

    @RequestMapping("/")
    public String visitHome() {

        return "index";
    }


    @RequestMapping("/setup")
    public String  visitSetup(Model model) {


         moviesBean.addMovie(new Movie("Wedding Crashers", "David Dobkin", "Comedy", 7, 2005));
        moviesBean.addMovie(new Movie("Starsky & Hutch", "Todd Phillips", "Action", 6, 2004));
        moviesBean.addMovie(new Movie("Shanghai Knights", "David Dobkin", "Action", 6, 2003));
        moviesBean.addMovie(new Movie("I-Spy", "Betty Thomas", "Adventure", 5, 2002));
        moviesBean.addMovie(new Movie("The Royal Tenenbaums", "Wes Anderson", "Comedy", 8, 2001));
        moviesBean.addMovie(new Movie("Zoolander", "Ben Stiller", "Comedy", 6, 2001));
        moviesBean.addMovie(new Movie("Shanghai Noon", "Tom Dey", "Comedy", 7, 2000));

        List list = moviesBean.getMovies();

        model.addAttribute("movies",list);

        return "setup";
    }
}
