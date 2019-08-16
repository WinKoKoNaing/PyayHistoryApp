package com.techhousestudio.pyayhistory.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Article {
    public long id;
    public String title;
    public String content;
    public String category;
    public String imageUri;
    public Date created_at;
    public Date updated_at;

    // additional

    public String city;

    public Article(String title, String content, String category, String imageUri, Date created_at) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.imageUri = imageUri;
        this.created_at = created_at;
    }

    public static List<Article> articles = Arrays.asList(
            new Article("","All the pagodas throughout the country celebrate on Full Moon Day of Kason. This ritual commemorates the date 2500 years ago when the Buddha gained enlightenment while mediation under a Bodhi tree. Pilgrims pour water on this tree in pagoda compounds to keep them fresh in the summer heat of May", "place", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQX7yYzAubN-_klKZFquENe2Sys4x4fhnitzFNnoYUE3vCQi_b", new Date()),
            new Article("Playfair Display", "Playfair Display is a serif font. This font takes influence from the designs of typeface designer John Baskerville. As the name indicates, Playfair Display is well suited for titling and headlines. It has an extra large x-height and short descenders. It can be set for instance in news headlines, or for stylistic effect in titles.", "POGODA", "https://thamtran90.files.wordpress.com/2015/07/white-temple.jpg?w=398&h=269", new Date()),
            new Article("Playfair Display", "The second point is important. Even if you do not use the compat vector functionality yourself, you need to follow this post as if you do, since AppCompat requires it.", "POGODA", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSD27gIgavWxi9dhMvaPbEyo_9kUC3FOydXhIO5TW-zBSxy9NPv", new Date()),
            new Article("Playfair Display", "Pyay is principal town and located on the Irrawaddy River and is 260 km (160 mi) north-west of Yangon. Along the western side of Pyay District are the Arakan Mountains and along the eastern side are the Pegu Range. Pyay District’s main towns are Pyay, Shwedaung, and Paungde. There are Unesco World Heritage Sites and one of the Pyu ancient cities.", "Place", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRe-VA5ZzvNB5zk2Pv-brIUiJ0YnRqDniOcE-NRziPfj90Qaj1WLQ", new Date()),
            new Article("Playfair Display", "After the breakfast, visit ancient buddha images carved on stone walls of Akauk Mountain.\n" +
                    "Akauk Mountain is situated near the bank of Ayeyarwaddy River between the southern most part of Pyay District and northern most part of Hinthata District can be reached by waterway from Pyay.", "POGODA", "https://supertravelme.com/wp-content/uploads/2017/03/Shwe-Yan-Pyay-Monastery-in-Nyaung-Shwe.jpg", new Date()),
            new Article("","All the pagodas throughout the country celebrate on Full Moon Day of Kason. This ritual commemorates the date 2500 years ago when the Buddha gained enlightenment while mediation under a Bodhi tree. Pilgrims pour water on this tree in pagoda compounds to keep them fresh in the summer heat of May", "place", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQX7yYzAubN-_klKZFquENe2Sys4x4fhnitzFNnoYUE3vCQi_b", new Date()),
            new Article("Playfair Display", "Playfair Display is a serif font. This font takes influence from the designs of typeface designer John Baskerville. As the name indicates, Playfair Display is well suited for titling and headlines. It has an extra large x-height and short descenders. It can be set for instance in news headlines, or for stylistic effect in titles.", "POGODA", "https://thamtran90.files.wordpress.com/2015/07/white-temple.jpg?w=398&h=269", new Date()),
            new Article("Playfair Display", "The second point is important. Even if you do not use the compat vector functionality yourself, you need to follow this post as if you do, since AppCompat requires it.", "POGODA", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSD27gIgavWxi9dhMvaPbEyo_9kUC3FOydXhIO5TW-zBSxy9NPv", new Date()),
            new Article("Playfair Display", "Pyay is principal town and located on the Irrawaddy River and is 260 km (160 mi) north-west of Yangon. Along the western side of Pyay District are the Arakan Mountains and along the eastern side are the Pegu Range. Pyay District’s main towns are Pyay, Shwedaung, and Paungde. There are Unesco World Heritage Sites and one of the Pyu ancient cities.", "Place", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRe-VA5ZzvNB5zk2Pv-brIUiJ0YnRqDniOcE-NRziPfj90Qaj1WLQ", new Date()),
            new Article("Playfair Display", "After the breakfast, visit ancient buddha images carved on stone walls of Akauk Mountain.\n" +
                    "Akauk Mountain is situated near the bank of Ayeyarwaddy River between the southern most part of Pyay District and northern most part of Hinthata District can be reached by waterway from Pyay.", "POGODA", "https://supertravelme.com/wp-content/uploads/2017/03/Shwe-Yan-Pyay-Monastery-in-Nyaung-Shwe.jpg", new Date())

    );
}
