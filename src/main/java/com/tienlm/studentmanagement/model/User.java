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

@Document(collection="User")
public class User {
    @Id
    private ObjectId _id;
    private String userName;
    private String password;
}
