package com.tienlm.studentmanagement.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString

@Document(collection="Student")
public class Student {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String address;
}
