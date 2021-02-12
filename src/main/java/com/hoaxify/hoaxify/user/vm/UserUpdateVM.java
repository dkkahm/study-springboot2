package com.hoaxify.hoaxify.user.vm;

import com.hoaxify.hoaxify.shared.ProfileImage;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserUpdateVM {
    @NotNull
    @Size(min=4, max=255)
    private String displayName;

    @ProfileImage
    private String image;
}
