package models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private int id;
    private int idBook;
    private String firstName;
    private String lastName;
}