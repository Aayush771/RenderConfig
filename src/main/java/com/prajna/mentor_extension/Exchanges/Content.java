package com.prajna.mentor_extension.Exchanges;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    private List<Part> parts;
    private String role;
}
