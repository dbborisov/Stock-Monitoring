package net.lesno.stock.services.services.imp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class Post implements Serializable {

    private int userId;
    private int id;
    private String title;
    private String body;
}
