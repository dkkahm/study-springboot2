package com.hoaxify.hoaxify.user.vm;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateVM {
    private String displayName;
    private String image;
}
