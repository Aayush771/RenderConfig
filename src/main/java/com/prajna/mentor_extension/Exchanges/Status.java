package com.prajna.mentor_extension.Exchanges;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    Integer noOfQueries;// up till now per user (in demo, only one user)
    Integer inlineCompletions;// up till now counter for all requests in User entity
    Integer overallQuality;// inPercentage
    // public Integer getOverAllQuality() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getOverAllQuality'");
    // }
}