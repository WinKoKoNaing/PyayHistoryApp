package com.techhousestudio.pyayhistory;

import com.techhousestudio.pyayhistory.models.Article;
import com.techhousestudio.pyayhistory.models.Body;
import com.techhousestudio.pyayhistory.models.Education;
import com.techhousestudio.pyayhistory.models.Header;
import com.techhousestudio.pyayhistory.models.Master;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppData {
    public static List<Master> getMasterData() {
        List<Education> educations = Arrays.asList(
                new Education("Pyay Computer University", "http://ucspyay.moe.edu.mm/wp-content/uploads/2016/03/12522960_869974773120470_8635025931647418085_n.jpg"),
                new Education("Pyay University", "https://www.edge.com.mm/media/k2/items/cache/2cf1f8d39e0698fbf0c2be15e9fa8cfb_XL.jpg"),
                new Education("Pyay Technological University", "http://ptu.moe.edu.mm/wp-content/uploads/2016/03/PyayTechnologicalUniversity2.jpg")
        );


        List<Master> masters = new ArrayList<>();
        masters.add(new Master(Master.HEADER, new Header("You know University, School ..?", educations)));
        masters.add(new Master(Master.BODY, new Body("I know what do you need ..?", Article.articles)));
        masters.add(new Master(Master.FOOTER, new Body("Beautiful Places", Article.articles)));


        return masters;

    }
}
