package com.interview.task.Wizertask.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "favourites")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favourites {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;
    private String bookName;
    private String author;
    private String categoryName;
    private String yearOfRelease;

}
