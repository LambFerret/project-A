package com.lambferret.project_a.document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
public class Bulletin implements Serializable {
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private ObjectId authorId;
    private String date;
}
