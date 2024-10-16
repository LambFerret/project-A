package com.lambferret.project_a.document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class User {
    @Id
    private ObjectId id;
    private String companyName;
    @CreatedDate
    private LocalDateTime createdDate;
    private LocalDateTime expirationDate;

    private boolean doesTestCICD;
    private boolean doesTestMalfunction;

}
